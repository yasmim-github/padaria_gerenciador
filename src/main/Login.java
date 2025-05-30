package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

import db.ConectaBanco;

public class Login extends JFrame {

    JTextField cpf = new JTextField(15);
    JTextField nome = new JTextField(20);
    JComboBox<String> cargosbox = new JComboBox<>();
    JButton entrar = new JButton("Entrar");

    JLabel labelTitulo = new JLabel("Login");
    JLabel labelCpf = new JLabel("CPF:");
    JLabel labelNome = new JLabel("Nome:");
    JLabel labelCargo = new JLabel("Cargo:");

    JPanel painelEsquerdo = new JPanel();
    JPanel painelDireito = new JPanel();

    public Login() {
        setTitle("Login");
        setSize(600, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        painelEsquerdo.setBackground(new Color(248, 236, 218));
        painelEsquerdo.setBounds(0, 0, 250, 350);
        painelEsquerdo.setLayout(null);

        painelDireito.setBackground(Color.WHITE);
        painelDireito.setBounds(250, 0, 350, 350);
        painelDireito.setLayout(null);

        labelTitulo.setBounds(120, 10, 150, 30);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelTitulo.setForeground(new Color(130, 92, 60));

        labelCpf.setBounds(30, 60, 100, 20);
        labelCpf.setFont(new Font("SansSerif", Font.PLAIN, 14));

        cpf.setForeground(Color.BLACK);
        cpf.setBackground(Color.WHITE);
        cpf.setBounds(30, 80, 280, 25);

        labelNome.setBounds(30, 115, 100, 20);
        labelNome.setFont(new Font("SansSerif", Font.PLAIN, 14));

        nome.setBounds(30, 135, 280, 25);

        labelCargo.setBounds(30, 170, 100, 20);
        labelCargo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        cargosbox.setBounds(30, 190, 280, 25);

        entrar.setBounds(100, 240, 120, 35);
        entrar.setBackground(new Color(130, 92, 60));
        entrar.setForeground(Color.WHITE);
        entrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        entrar.setFocusPainted(false);

        painelDireito.add(labelTitulo);
        painelDireito.add(labelCpf);
        painelDireito.add(cpf);
        painelDireito.add(labelNome);
        painelDireito.add(nome);
        painelDireito.add(labelCargo);
        painelDireito.add(cargosbox);
        painelDireito.add(entrar);

        ImageIcon iconOriginal = new ImageIcon(getClass().getResource("/imgs/icon_1.jpg"));
        Image imagemRedimensionada = iconOriginal.getImage().getScaledInstance(166, 220, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);

        JLabel logoLabel = new JLabel(iconRedimensionado);
        logoLabel.setBounds(41, 57, 149, 171);
        painelEsquerdo.add(logoLabel);

        getContentPane().add(painelEsquerdo);
        getContentPane().add(painelDireito);

        carregarCargos();

        entrar.addActionListener((ActionEvent e) -> {
            fazerLogin();
        });

        setVisible(true);
    }

    
    public void limparCampos() {
        cpf.setText("");
        nome.setText("");
        cargosbox.setSelectedIndex(0);
    }

    
    public void fazerLogin() {
        String cpftxt = cpf.getText().trim();
        String nometxt = nome.getText().trim();
        String cargotxt = cargosbox.getSelectedItem().toString().trim();

        try {
            ConectaBanco conbd = new ConectaBanco();
            Connection conn = conbd.obtemConexao();

            String sql = "SELECT * FROM usuario WHERE LOWER(cpf) = LOWER(?) AND LOWER(nome) = LOWER(?) AND LOWER(cargo) = LOWER(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpftxt.toLowerCase());
            stmt.setString(2, nometxt.toLowerCase());
            stmt.setString(3, cargotxt.toLowerCase());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo(a) " + nometxt);
                HomeModulos home = new HomeModulos(cargotxt); // Passa o cargo para a tela de módulos
                home.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado. Verifique suas credenciais.");
                limparCampos();
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao fazer login: " + ex.getMessage());
        }
    }

    
    public void carregarCargos() {
        try {
            ConectaBanco conbd = new ConectaBanco();
            Connection conn = conbd.obtemConexao();

            String sql = "SELECT DISTINCT cargo FROM usuario";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cargosbox.addItem(rs.getString("cargo"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar cargos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

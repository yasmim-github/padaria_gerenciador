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
    JPasswordField senha = new JPasswordField(20);
    JComboBox<String> cargosbox = new JComboBox<>();
    JButton entrar = new JButton("Entrar");

    JLabel labelTitulo = new JLabel("Login");
    JLabel labelCpf = new JLabel("CPF:");
    JLabel labelSenha = new JLabel("Senha:");
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

        cpf.setBounds(30, 80, 280, 25);

        labelSenha.setBounds(30, 115, 100, 20);
        labelSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));

        senha.setBounds(30, 135, 280, 25);

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
        painelDireito.add(labelSenha);
        painelDireito.add(senha);
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
        senha.setText("");
        cargosbox.setSelectedIndex(0);
    }

    public void fazerLogin() {
        String cpftxt = cpf.getText().trim();
        String senhatxt = new String(senha.getPassword()).trim();
        String cargotxt = cargosbox.getSelectedItem().toString().trim();

        try {
            ConectaBanco conbd = new ConectaBanco();
            Connection conn = conbd.obtemConexao();

            String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha_hash = ? AND cargo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpftxt);
            stmt.setString(2, senhatxt);
            stmt.setString(3, cargotxt);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("id");
                JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo(a)!");

                HomeModulos home = new HomeModulos(cargotxt, usuarioId);
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
        cargosbox.addItem("operador");
        cargosbox.addItem("gerente");
        cargosbox.addItem("dono");
    }

    public static void main(String[] args) {
        new Login();
    }
}

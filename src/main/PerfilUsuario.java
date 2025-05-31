package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

import db.ConectaBanco;

public class PerfilUsuario extends JFrame {

    JLabel lblNome = new JLabel();
    JLabel lblCpf = new JLabel();
    JLabel lblCargo = new JLabel();
    JLabel lblCidade = new JLabel();
    JLabel lblBairro = new JLabel();
    JLabel lblNumeroCasa = new JLabel();
    JLabel lblTelefone = new JLabel();
    JLabel lblEmail = new JLabel();

    JLabel lblVendas = new JLabel();
    JLabel lblTotalVendido = new JLabel();
    JLabel lblEstoque = new JLabel();

    JButton btnFechar = new JButton("Fechar");

    int usuarioId;

    public PerfilUsuario(int usuarioId) {
        this.usuarioId = usuarioId;

        setTitle("Perfil do Usuário");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel painelDados = new JPanel();
        painelDados.setBackground(new Color(248, 236, 218));
        painelDados.setLayout(new GridLayout(8, 1, 5, 5));
        painelDados.setBorder(BorderFactory.createTitledBorder("Dados Pessoais"));

        painelDados.add(lblNome);
        painelDados.add(lblCpf);
        painelDados.add(lblCargo);
        painelDados.add(lblCidade);
        painelDados.add(lblBairro);
        painelDados.add(lblNumeroCasa);
        painelDados.add(lblTelefone);
        painelDados.add(lblEmail);

        JPanel painelAcoes = new JPanel();
        painelAcoes.setBackground(new Color(248, 236, 218));
        painelAcoes.setLayout(new GridLayout(3, 1, 5, 5));
        painelAcoes.setBorder(BorderFactory.createTitledBorder("Resumo de Ações"));

        painelAcoes.add(lblVendas);
        painelAcoes.add(lblTotalVendido);
        painelAcoes.add(lblEstoque);
        btnFechar.setForeground(new Color(255, 255, 255));
        btnFechar.setBackground(new Color(159, 119, 84));

        btnFechar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        JPanel painelInferior = new JPanel();
        painelInferior.setBackground(new Color(248, 236, 218));
        painelInferior.add(btnFechar);

        getContentPane().add(painelDados, BorderLayout.NORTH);
        getContentPane().add(painelAcoes, BorderLayout.CENTER);
        getContentPane().add(painelInferior, BorderLayout.SOUTH);

        carregarDadosUsuario();
        carregarResumoAcoes();

        setVisible(true);
    }

    private void carregarDadosUsuario() {
        try {
            ConectaBanco conbd = new ConectaBanco();
            Connection conn = conbd.obtemConexao();

            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lblNome.setText("Nome: " + rs.getString("nome"));
                lblCpf.setText("CPF: " + rs.getString("cpf"));
                lblCargo.setText("Cargo: " + rs.getString("cargo"));
                lblCidade.setText("Cidade: " + rs.getString("cidade"));
                lblBairro.setText("Bairro: " + rs.getString("bairro"));
                lblNumeroCasa.setText("Número da Casa: " + rs.getString("numero_casa"));
                lblTelefone.setText("Telefone: " + rs.getString("numero_telefone"));
                lblEmail.setText("Email: " + rs.getString("email"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

    private void carregarResumoAcoes() {
        try {
            ConectaBanco conbd = new ConectaBanco();
            Connection conn = conbd.obtemConexao();

            String sqlVendas = "SELECT COUNT(*) AS total_vendas, SUM(total) AS total_vendido FROM vendas WHERE usuario_id = ?";
            PreparedStatement stmtVendas = conn.prepareStatement(sqlVendas);
            stmtVendas.setInt(1, usuarioId);
            ResultSet rsVendas = stmtVendas.executeQuery();

            if (rsVendas.next()) {
                int totalVendas = rsVendas.getInt("total_vendas");
                double totalVendido = rsVendas.getDouble("total_vendido");
                lblVendas.setText("Total de Vendas: " + totalVendas);
                lblTotalVendido.setText("Total Vendido: R$ " + String.format("%.2f", totalVendido));
            }

            rsVendas.close();
            stmtVendas.close();

            String sqlEstoque = "SELECT COUNT(*) AS total_acoes FROM estoque_log WHERE usuario_id = ?";
            PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque);
            stmtEstoque.setInt(1, usuarioId);
            ResultSet rsEstoque = stmtEstoque.executeQuery();

            if (rsEstoque.next()) {
                int totalAcoes = rsEstoque.getInt("total_acoes");
                lblEstoque.setText("Total de Ações no Estoque: " + totalAcoes);
            }

            rsEstoque.close();
            stmtEstoque.close();

            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar resumo de ações: " + ex.getMessage());
        }
    }
}

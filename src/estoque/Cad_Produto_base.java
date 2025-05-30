package estoque;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

import db.ConectaBanco;

public class Cad_Produto_base extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome;
    private JComboBox<String> comboTipo;

    public Cad_Produto_base() {
        setTitle("Cadastro de Produto Base");
        setBounds(100, 100, 450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(248, 236, 218));

        JLabel lblNome = new JLabel("Nome do Produto:");
        lblNome.setBounds(30, 30, 120, 20);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(160, 30, 200, 20);
        getContentPane().add(txtNome);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 70, 120, 20);
        getContentPane().add(lblTipo);

        comboTipo = new JComboBox<>();
        comboTipo.setModel(new DefaultComboBoxModel<>(new String[]{"produzido", "nao_produzido"}));
        comboTipo.setBounds(160, 70, 200, 22);
        getContentPane().add(comboTipo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(160, 120, 100, 30);
        getContentPane().add(btnSalvar);

        btnSalvar.addActionListener(e -> salvarProduto());
    }

    private void salvarProduto() {
        String nome = txtNome.getText();
        String tipo = (String) comboTipo.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do produto é obrigatório.");
            return;
        }

        try (Connection conn = new ConectaBanco().obtemConexao()) {
            String sql = "INSERT INTO produtos_base (nome, tipo) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, nome);
            stmt.setString(2, tipo);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                JOptionPane.showMessageDialog(this, "Produto base cadastrado com ID: " + idGerado);

                if (tipo.equals("produzido")) {
                    produto_produzido tela = new produto_produzido(idGerado);
                    tela.setVisible(true);
                } else {
                    CadastroDeProdutosExternos tela = new CadastroDeProdutosExternos(idGerado);
                    tela.setVisible(true);
                }
            }

            txtNome.setText("");
            comboTipo.setSelectedIndex(0);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco: " + e.getMessage());
        }
    }
}

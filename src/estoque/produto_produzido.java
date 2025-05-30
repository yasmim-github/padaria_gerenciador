package estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class produto_produzido extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtPrecoQuilo;
    private JTextField txtPrecoTotal;

    private int idBase;

   
    private final String URL = "jdbc:mysql://localhost:3306/padaria_do_pandoca";
    private final String USER = "root";
    private final String PASSWORD = "root";

   
    public produto_produzido(int idBase) {
    	getContentPane().setBackground(new Color(248, 236, 218));
        this.idBase = idBase;
        initialize();
        setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Cadastro Produto Produzido");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código Identificação:");
        lblCodigo.setBounds(30, 30, 150, 14);
        getContentPane().add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 27, 150, 20);
        getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblPrecoQuilo = new JLabel("Preço por Quilo:");
        lblPrecoQuilo.setBounds(30, 70, 150, 14);
        getContentPane().add(lblPrecoQuilo);

        txtPrecoQuilo = new JTextField();
        txtPrecoQuilo.setBounds(180, 67, 150, 20);
        getContentPane().add(txtPrecoQuilo);
        txtPrecoQuilo.setColumns(10);

        JLabel lblPrecoTotal = new JLabel("Preço Total:");
        lblPrecoTotal.setBounds(30, 110, 150, 14);
        getContentPane().add(lblPrecoTotal);

        txtPrecoTotal = new JTextField();
        txtPrecoTotal.setBounds(180, 107, 150, 20);
        getContentPane().add(txtPrecoTotal);
        txtPrecoTotal.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        btnSalvar.setBounds(140, 180, 100, 30);
        getContentPane().add(btnSalvar);
    }

    
    private void salvar() {
        String codigo = txtCodigo.getText();
        String precoQuiloStr = txtPrecoQuilo.getText();
        String precoTotalStr = txtPrecoTotal.getText();

        if (codigo.isEmpty() || precoQuiloStr.isEmpty() || precoTotalStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            return;
        }

        double precoQuilo;
        double precoTotal;

        try {
            precoQuilo = Double.parseDouble(precoQuiloStr);
            precoTotal = Double.parseDouble(precoTotalStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preços devem ser números válidos.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO produtos_produzidos (id, codigo_identificacao, preco_por_quilo, preco_total) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBase);
            stmt.setString(2, codigo);
            stmt.setDouble(3, precoQuilo);
            stmt.setDouble(4, precoTotal);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produto produzido cadastrado com sucesso!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco: " + e.getMessage());
        }
    }
}

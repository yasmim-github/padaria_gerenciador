package estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.BorderLayout;

public class produto_produzido extends JFrame {

    private JTextField txtPrecoQuilo;
    private JLabel lblImagemSelecionada;
    private JLabel lblPreviewImagem;

    private File imagemSelecionada;

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

    private void initialize() {
        setTitle("Cadastro Produto Produzido");
        setBounds(100, 100, 450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblPrecoQuilo = new JLabel("Preço por Quilo:");
        lblPrecoQuilo.setBounds(30, 30, 150, 14);
        getContentPane().add(lblPrecoQuilo);

        txtPrecoQuilo = new JTextField();
        txtPrecoQuilo.setBounds(180, 27, 200, 20);
        getContentPane().add(txtPrecoQuilo);
        txtPrecoQuilo.setColumns(10);

        JButton btnSelecionarImagem = new JButton("Selecionar Imagem");
        btnSelecionarImagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionarImagem();
            }
        });
        btnSelecionarImagem.setBounds(30, 70, 150, 25);
        getContentPane().add(btnSelecionarImagem);

        lblImagemSelecionada = new JLabel("Nenhuma imagem selecionada");
        lblImagemSelecionada.setBounds(200, 75, 220, 14);
        getContentPane().add(lblImagemSelecionada);

        lblPreviewImagem = new JLabel();
        lblPreviewImagem.setBounds(150, 110, 150, 150);
        lblPreviewImagem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        getContentPane().add(lblPreviewImagem);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        btnSalvar.setBounds(170, 280, 100, 30);
        getContentPane().add(btnSalvar);
    }

    private void selecionarImagem() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            String nomeArquivo = arquivo.getName().toLowerCase();

            if (!(nomeArquivo.endsWith(".png") || nomeArquivo.endsWith(".jpg") || nomeArquivo.endsWith(".jpeg"))) {
                JOptionPane.showMessageDialog(this, "Selecione um arquivo de imagem (.png, .jpg ou .jpeg)");
                return;
            }

            imagemSelecionada = arquivo;
            lblImagemSelecionada.setText(imagemSelecionada.getName());

            // Exibir preview
            try {
                Image img = ImageIO.read(imagemSelecionada);
                Image imgRedimensionada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblPreviewImagem.setIcon(new ImageIcon(imgRedimensionada));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar preview da imagem.");
            }
        }
    }

    private void salvar() {
        String precoQuiloStr = txtPrecoQuilo.getText();

        if (precoQuiloStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Preço por Quilo é obrigatório.");
            return;
        }

        double precoQuilo;

        try {
            precoQuilo = Double.parseDouble(precoQuiloStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço deve ser um número válido.");
            return;
        }

        if (imagemSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma imagem.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             FileInputStream fis = new FileInputStream(imagemSelecionada)) {

            String sql = "INSERT INTO produtos_produzidos (id, preco_por_quilo, imagem) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBase);
            stmt.setDouble(2, precoQuilo);
            stmt.setBlob(3, fis);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produto produzido cadastrado com sucesso!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao processar a imagem: " + e.getMessage());
        }
    }
}

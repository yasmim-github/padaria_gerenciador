package estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CadastroDeProdutosExternos extends JFrame {

    private FileInputStream fis;
    private int tamanhoImagem;

    private JTextField txtCodigo, txtVenda, txtCompra, txtQuantidade, txtMarca, txtPeso, txtAlergenos, txtValidade, txtFornecedor, txtLote;
    private JLabel lblImagemPreview;
    private JButton btnEscolherImagem;

    public CadastroDeProdutosExternos(int idProdutoBase) {
        
        Color corFundoPrincipal = new Color(248, 236, 218); 
        Color corFundoSecundaria = new Color(220, 204, 182); 
        Color corTextoTitulo = new Color(159, 119, 84); 
        Color corTextoLabels = new Color(57, 48, 48); 
        Color corBotoes = new Color(159, 119, 84); 
        Color corTextoBotoes = Color.WHITE;
        Color corBordaCampos = new Color(180, 160, 140); 

        setTitle("Cadastro de Produto Externo");
        setBounds(100, 100, 500, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setBackground(corFundoPrincipal);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("CADASTRO DE PRODUTO EXTERNO");
        lblTitulo.setForeground(corTextoTitulo);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 20, 484, 25);
        contentPane.add(lblTitulo);

        
        int yOffset = 60; 
        int ySpacing = 30; 

        JLabel lblCodigo = new JLabel("Código Serial:");
        lblCodigo.setForeground(corTextoLabels);
        lblCodigo.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBackground(corFundoSecundaria);
        txtCodigo.setForeground(corTextoLabels);
        txtCodigo.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtCodigo.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtCodigo);

        yOffset += ySpacing;
        JLabel lblVenda = new JLabel("Preço Venda:");
        lblVenda.setForeground(corTextoLabels);
        lblVenda.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblVenda);

        txtVenda = new JTextField();
        txtVenda.setBackground(corFundoSecundaria);
        txtVenda.setForeground(corTextoLabels);
        txtVenda.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtVenda.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtVenda);

        yOffset += ySpacing;
        JLabel lblCompra = new JLabel("Preço Compra:");
        lblCompra.setForeground(corTextoLabels);
        lblCompra.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblCompra);

        txtCompra = new JTextField();
        txtCompra.setBackground(corFundoSecundaria);
        txtCompra.setForeground(corTextoLabels);
        txtCompra.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtCompra.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtCompra);

        yOffset += ySpacing;
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setForeground(corTextoLabels);
        lblQuantidade.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBackground(corFundoSecundaria);
        txtQuantidade.setForeground(corTextoLabels);
        txtQuantidade.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtQuantidade.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtQuantidade);

        yOffset += ySpacing;
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setForeground(corTextoLabels);
        lblMarca.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBackground(corFundoSecundaria);
        txtMarca.setForeground(corTextoLabels);
        txtMarca.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtMarca.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtMarca);

        yOffset += ySpacing;
        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setForeground(corTextoLabels);
        lblPeso.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBackground(corFundoSecundaria);
        txtPeso.setForeground(corTextoLabels);
        txtPeso.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtPeso.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtPeso);

        yOffset += ySpacing;
        JLabel lblAlergenos = new JLabel("Alérgenos:");
        lblAlergenos.setForeground(corTextoLabels);
        lblAlergenos.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblAlergenos);

        txtAlergenos = new JTextField();
        txtAlergenos.setBackground(corFundoSecundaria);
        txtAlergenos.setForeground(corTextoLabels);
        txtAlergenos.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtAlergenos.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtAlergenos);

        yOffset += ySpacing;
        JLabel lblValidade = new JLabel("Validade:");
        lblValidade.setForeground(corTextoLabels);
        lblValidade.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblValidade);

        txtValidade = new JTextField();
        txtValidade.setBackground(corFundoSecundaria);
        txtValidade.setForeground(corTextoLabels);
        txtValidade.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtValidade.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtValidade);

        yOffset += ySpacing;
        JLabel lblFornecedor = new JLabel("Fornecedor:");
        lblFornecedor.setForeground(corTextoLabels);
        lblFornecedor.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblFornecedor);

        txtFornecedor = new JTextField();
        txtFornecedor.setBackground(corFundoSecundaria);
        txtFornecedor.setForeground(corTextoLabels);
        txtFornecedor.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtFornecedor.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtFornecedor);

        yOffset += ySpacing;
        JLabel lblLote = new JLabel("Lote:");
        lblLote.setForeground(corTextoLabels);
        lblLote.setBounds(30, yOffset, 120, 20);
        contentPane.add(lblLote);

        txtLote = new JTextField();
        txtLote.setBackground(corFundoSecundaria);
        txtLote.setForeground(corTextoLabels);
        txtLote.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        txtLote.setBounds(160, yOffset, 280, 25);
        contentPane.add(txtLote);

        yOffset += ySpacing + 10; 

       
        lblImagemPreview = new JLabel();
        lblImagemPreview.setBounds(160, yOffset, 150, 150);
        lblImagemPreview.setBorder(BorderFactory.createLineBorder(corBordaCampos));
        lblImagemPreview.setBackground(corFundoSecundaria);
        lblImagemPreview.setOpaque(true); 
        lblImagemPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagemPreview.setVerticalAlignment(SwingConstants.CENTER);
        contentPane.add(lblImagemPreview);

        btnEscolherImagem = new JButton("Escolher Imagem");
        btnEscolherImagem.setBounds(30, yOffset + 60, 120, 30); 
        btnEscolherImagem.setBackground(corBotoes);
        btnEscolherImagem.setForeground(corTextoBotoes);
        btnEscolherImagem.setFont(new Font("Tahoma", Font.BOLD, 10));
        contentPane.add(btnEscolherImagem);

        btnEscolherImagem.addActionListener(e -> escolherImagem());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(160, yOffset + 180, 100, 30);
        btnSalvar.setBackground(corBotoes);
        btnSalvar.setForeground(corTextoBotoes);
        btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, yOffset + 180, 100, 30);
        btnCancelar.setBackground(corBotoes);
        btnCancelar.setForeground(corTextoBotoes);
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvar(idProdutoBase));
        btnCancelar.addActionListener(e -> dispose()); 

        setVisible(true);
    }

    private void escolherImagem() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
        int retorno = chooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                fis = new FileInputStream(file);
                tamanhoImagem = (int) file.length();

                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(lblImagemPreview.getWidth(), lblImagemPreview.getHeight(), Image.SCALE_SMOOTH);
                lblImagemPreview.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar imagem: " + ex.getMessage(), "Erro de Imagem", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvar(int idProdutoBase) {
        String sql = "INSERT INTO produtos_nao_produzidos (codigo_serial, preco_venda, preco_compra, quantidade, marca, peso, alergenos, validade, fornecedor, lote, imagem, id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/padaria_do_pandoca", "root", "root")) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtCodigo.getText());
            ps.setString(2, txtVenda.getText());
            ps.setString(3, txtCompra.getText());
            ps.setString(4, txtQuantidade.getText());
            ps.setString(5, txtMarca.getText());
            ps.setString(6, txtPeso.getText());
            ps.setString(7, txtAlergenos.getText());
            ps.setString(8, txtValidade.getText());
            ps.setString(9, txtFornecedor.getText());
            ps.setString(10, txtLote.getText());

            if (fis != null) {
                ps.setBinaryStream(11, fis, tamanhoImagem);
            } else {
                ps.setNull(11, java.sql.Types.BLOB);
            }

            ps.setInt(12, idProdutoBase);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
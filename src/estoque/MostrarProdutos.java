package estoque;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarProdutos extends JFrame {

    private JPanel contentPane;
    private JTable table;

   
    private static final String URL = "jdbc:mysql://localhost:3306/padaria_do_pandoca";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarProdutos frame = new MostrarProdutos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MostrarProdutos() {
        setTitle("Produtos em Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 20, 950, 450);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setRowHeight(100);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Nome", "Tipo", "Código", "Preço Venda", "Quantidade",
                "Marca", "Peso", "Validade", "Fornecedor", "Preço/kg", "Preço Total", "Imagem"
            }
        ));
        scrollPane.setViewportView(table);

        JButton btnCarregar = new JButton("Listar Produtos");
        btnCarregar.setForeground(Color.WHITE);
        btnCarregar.setBackground(new Color(130, 92, 60));
        btnCarregar.setBounds(400, 500, 180, 30);
        btnCarregar.addActionListener(e -> carregarProdutos());
        contentPane.add(btnCarregar);
        
        JButton btnNewButton = new JButton(loadIcon("/imgs/voltar.png", 50, 50));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        
        btnNewButton.setBounds(49, 496, 50, 50);
        contentPane.add(btnNewButton);

       
        table.getColumn("Imagem").setCellRenderer(new ImageRenderer());
    }

    /**
     * Carrega produtos do banco e exibe na tabela
     */
    private void carregarProdutos() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT id, nome, tipo FROM produtos_base";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String tipo = rs.getString("tipo");

                    if (tipo.equals("nao_produzido")) {
                        adicionarProdutoNaoProduzido(conn, modelo, id, nome, tipo);
                    } else if (tipo.equals("produzido")) {
                        adicionarProdutoProduzido(conn, modelo, id, nome, tipo);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco ou carregar produtos: " + e.getMessage());
        }
    }

    /**
     * Adiciona produto não produzido na tabela
     */
    private void adicionarProdutoNaoProduzido(Connection conn, DefaultTableModel modelo, int id, String nome, String tipo) throws SQLException {
        String sql = "SELECT * FROM produtos_nao_produzidos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ImageIcon imagem = getImagem(rs.getBlob("imagem"));

                    modelo.addRow(new Object[]{
                        id,
                        nome,
                        tipo,
                        rs.getString("codigo_serial"),
                        rs.getBigDecimal("preco_venda"),
                        rs.getInt("quantidade"),
                        rs.getString("marca"),
                        rs.getBigDecimal("peso"),
                        rs.getDate("validade"),
                        rs.getString("fornecedor"),
                        "", 
                        "", 
                        imagem
                    });
                }
            }
        }
    }

    /**
     * Adiciona produto produzido na tabela
     */
    private void adicionarProdutoProduzido(Connection conn, DefaultTableModel modelo, int id, String nome, String tipo) throws SQLException {
        String sql = "SELECT * FROM produtos_produzidos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ImageIcon imagem = getImagem(rs.getBlob("imagem"));

                    modelo.addRow(new Object[]{
                        id,
                        nome,
                        tipo,
                        rs.getString("codigo_identificacao"),
                        "", "", "", "", "", "", "",
                        rs.getBigDecimal("preco_por_quilo"),
                        rs.getBigDecimal("preco_total"),
                        imagem
                    });
                }
            }
        }
    }

    /**
     * Converte o Blob do banco em ImageIcon ou exibe imagem padrão se não houver
     */
    private ImageIcon getImagem(Blob blob) {
        try {
            if (blob != null) {
                InputStream is = blob.getBinaryStream();
                BufferedImage img = ImageIO.read(is);
                if (img != null) {
                    Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    return new ImageIcon(dimg);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem do banco: " + e.getMessage());
        }

        
        try {
            BufferedImage imgPadrao = ImageIO.read(getClass().getResource("/imgs/icon_1.png"));
            if (imgPadrao != null) {
                Image dimg = imgPadrao.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                return new ImageIcon(dimg);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem padrão: " + e.getMessage());
        }

        return null;
    }

    /**
     * Renderer para exibir imagem na JTable
     */
    private class ImageRenderer extends DefaultTableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                JLabel label = new JLabel();
                label.setIcon((ImageIcon) value);
                label.setHorizontalAlignment(CENTER);
                return label;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    private ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}


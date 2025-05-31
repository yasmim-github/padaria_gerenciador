package estoque;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;

public class MostrarProdutos extends JFrame {

    private JPanel contentPane;
    private JPanel cardsPanel;
    private JComboBox<String> filtroTipo;

    private static final String URL = "jdbc:mysql://localhost:3306/padaria_do_pandoca";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private JButton btnNewButton;

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
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(248, 236, 218));

        filtroTipo = new JComboBox<>(new String[]{"Todos", "produzido", "nao_produzido"});
        filtroTipo.setForeground(new Color(255, 255, 255));
        filtroTipo.setBackground(new Color(159, 119, 84));
        
        btnNewButton = new JButton(" Sair");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(159, 119, 84));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        topPanel.add(btnNewButton);
        topPanel.add(new JLabel("Filtrar por tipo:"));
        topPanel.add(filtroTipo);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setForeground(new Color(255, 255, 255));
        btnFiltrar.setBackground(new Color(159, 119, 84));
        btnFiltrar.addActionListener(this::carregarProdutos);
        topPanel.add(btnFiltrar);

        contentPane.add(topPanel, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(248, 236, 218));
        cardsPanel.setLayout(new GridLayout(0, 4, 10, 10));  // 4 cards por linha
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarProdutos(ActionEvent e) {
        cardsPanel.removeAll();

        String filtro = (String) filtroTipo.getSelectedItem();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT id, nome, tipo FROM produtos_base";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String tipo = rs.getString("tipo");

                    if (!filtro.equals("Todos") && !filtro.equals(tipo)) {
                        continue;
                    }

                    if ("produzido".equals(tipo)) {
                        adicionarProdutoProduzido(conn, id, nome, tipo);
                    } else if ("nao_produzido".equals(tipo)) {
                        adicionarProdutoNaoProduzido(conn, id, nome, tipo);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + ex.getMessage());
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void adicionarProdutoProduzido(Connection conn, int id, String nome, String tipo) throws SQLException {
        String sql = "SELECT * FROM produtos_produzidos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ImageIcon imagem = getImagem(rs.getBlob("imagem"));
                    String info = "ID: " + id + "\n" +
                                  "Nome: " + nome + "\n" +
                                  "Tipo: " + tipo + "\n" +
                                  "Preço/kg: " + rs.getBigDecimal("preco_por_quilo");
                    JPanel card = criarCard(imagem, info, false);
                    cardsPanel.add(card);
                }
            }
        }
    }

    private void adicionarProdutoNaoProduzido(Connection conn, int id, String nome, String tipo) throws SQLException {
        String sql = "SELECT * FROM produtos_nao_produzidos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ImageIcon imagem = getImagem(rs.getBlob("imagem"));
                    Date validade = rs.getDate("validade");
                    boolean vencimentoProximo = isVencimentoProximo(validade);

                    String info = "ID: " + id + "\n" +
                                  "Nome: " + nome + "\n" +
                                  "Tipo: " + tipo + "\n" +
                                  "Serial: " + rs.getString("codigo_serial") + "\n" +
                                  "Preço: " + rs.getBigDecimal("preco_venda") + "\n" +
                                  "Qtd: " + rs.getInt("quantidade") + "\n" +
                                  "Marca: " + rs.getString("marca") + "\n" +
                                  "Peso: " + rs.getBigDecimal("peso") + "\n" +
                                  "Alergenos: " + rs.getString("alergenos") + "\n" +
                                  "Validade: " + validade + "\n" +
                                  "Fornecedor: " + rs.getString("fornecedor") + "\n" +
                                  "Lote: " + rs.getString("lote");

                    JPanel card = criarCard(imagem, info, vencimentoProximo);
                    cardsPanel.add(card);
                }
            }
        }
    }

    private boolean isVencimentoProximo(Date validade) {
        if (validade == null) return false;
        LocalDate dataValidade = validade.toLocalDate();
        LocalDate hoje = LocalDate.now();
        return !dataValidade.isBefore(hoje) && dataValidade.minusDays(7).isBefore(hoje);
    }

    private JPanel criarCard(ImageIcon imagem, String info, boolean destacar) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(160, 220));  

        Color corCard = new Color(159, 119, 84);
        card.setBackground(corCard);

        card.setBorder(new CompoundBorder(
                new LineBorder(destacar ? Color.RED : Color.LIGHT_GRAY, 2, true),
                new EmptyBorder(0, 0, 0, 0)
        ));

        JLabel imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setVerticalAlignment(SwingConstants.TOP);

        Image img = imagem.getImage().getScaledInstance(160, 120, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(img));
        imgLabel.setPreferredSize(new Dimension(160, 120));

        card.add(imgLabel, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea(info);
        infoArea.setFont(new Font("Arial", Font.BOLD, 10));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setOpaque(false);
        infoArea.setForeground(Color.WHITE);

        JScrollPane scrollInfo = new JScrollPane(infoArea);
        scrollInfo.setBorder(null);
        scrollInfo.setPreferredSize(new Dimension(160, 90));
        scrollInfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInfo.getViewport().setOpaque(false);
        scrollInfo.setOpaque(false);

        card.add(scrollInfo, BorderLayout.CENTER);

        return card;
    }

    private ImageIcon getImagem(Blob blob) {
        try {
            if (blob != null) {
                InputStream is = blob.getBinaryStream();
                BufferedImage img = ImageIO.read(is);
                if (img != null) {
                    Image dimg = img.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
                    return new ImageIcon(dimg);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem do banco: " + e.getMessage());
        }

        try {
            BufferedImage imgPadrao = ImageIO.read(getClass().getResource("/imgs/icon_1.jpg"));
            if (imgPadrao != null) {
                Image dimg = imgPadrao.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
                return new ImageIcon(dimg);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem padrão: " + e.getMessage());
        }

        return new ImageIcon();
    }
}

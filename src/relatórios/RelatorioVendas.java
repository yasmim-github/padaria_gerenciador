package relatórios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import db.ConectaBanco;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileOutputStream;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class RelatorioVendas extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> comboBoxFiltro;
    private DefaultTableModel modelo;
    private JLabel lblTotalVendas;
    private JButton btnExportarPDF;

    ConectaBanco conecta = new ConectaBanco();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RelatorioVendas frame = new RelatorioVendas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RelatorioVendas() {
        setTitle("Relatório de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        // Painel superior
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(248, 236, 218));
        contentPane.add(panelTop, BorderLayout.NORTH);

        JLabel lblFiltro = new JLabel("Selecione o Filtro:");
        panelTop.add(lblFiltro);

        comboBoxFiltro = new JComboBox<>();
        comboBoxFiltro.setModel(new DefaultComboBoxModel<>(new String[]{"Diário", "Semanal", "Mensal"}));
        comboBoxFiltro.setBackground(new Color(159, 119, 84));
        comboBoxFiltro.setForeground(Color.WHITE);
        panelTop.add(comboBoxFiltro);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(159, 119, 84));
        btnBuscar.setForeground(Color.WHITE);
        panelTop.add(btnBuscar);

        btnExportarPDF = new JButton("Exportar PDF");
        btnExportarPDF.setBackground(new Color(159, 119, 84));
        btnExportarPDF.setForeground(Color.WHITE);
        panelTop.add(btnExportarPDF);

        // Scroll da tabela
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID", "Usuário", "Data/Hora", "Total", "Valor Recebido", "Troco"
                }
        );
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        // Painel inferior (Total)
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.setBackground(new Color(248, 236, 218));
        lblTotalVendas = new JLabel("Total das Vendas: R$ 0.00");
        lblTotalVendas.setFont(new Font("Arial", Font.BOLD, 14));
        panelBottom.add(lblTotalVendas);
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        // Ações dos botões
        btnBuscar.addActionListener(e -> buscarVendas());
        btnExportarPDF.addActionListener(e -> exportarParaPDF());
    }

    private void buscarVendas() {
        modelo.setRowCount(0); // Limpa a tabela

        String filtro = comboBoxFiltro.getSelectedItem().toString();
        String sql = gerarSQL(filtro);

        double totalVendas = 0.0;

        try (Connection conn = conecta.obtemConexao();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                double total = rs.getDouble("total");
                totalVendas += total;

                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome_usuario"),
                        rs.getString("data_hora"),
                        total,
                        rs.getDouble("valor_recebido"),
                        rs.getDouble("troco")
                });
            }

            lblTotalVendas.setText(String.format("Total das Vendas: R$ %.2f", totalVendas));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar vendas: " + e.getMessage());
        }
    }

    private String gerarSQL(String filtro) {
        String sqlBase = """
                SELECT v.*, u.nome AS nome_usuario
                FROM vendas v
                JOIN usuario u ON v.usuario_id = u.id
                WHERE 
                """;

        String dataColuna = "DATE(v.data_hora)";

        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return switch (filtro) {
            case "Diário" -> sqlBase + dataColuna + " = '" + hoje.format(formatter) + "'";
            case "Semanal" -> {
                LocalDate inicioSemana = hoje.minusDays(hoje.getDayOfWeek().getValue() - 1);
                LocalDate fimSemana = inicioSemana.plusDays(6);
                yield sqlBase + dataColuna + " BETWEEN '" + inicioSemana.format(formatter) +
                        "' AND '" + fimSemana.format(formatter) + "'";
            }
            case "Mensal" -> {
                String anoMes = hoje.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                yield sqlBase + "DATE_FORMAT(v.data_hora, '%Y-%m') = '" + anoMes + "'";
            }
            default -> sqlBase + "1 = 1";
        };
    }

    private void exportarParaPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar PDF");
        fileChooser.setSelectedFile(new File("RelatorioVendas.pdf"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

                document.open();

                // Fonte para título
                com.lowagie.text.Font fontTitulo = new com.lowagie.text.Font(
                        com.lowagie.text.Font.HELVETICA, 18, com.lowagie.text.Font.BOLD);

                Paragraph titulo = new Paragraph("Relatório de Vendas", fontTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                document.add(new Paragraph(" ")); 

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);

                
                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
                    cell.setBackgroundColor(Color.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

               
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        Object value = table.getValueAt(rows, cols);
                        pdfTable.addCell(value != null ? value.toString() : "");
                    }
                }

                
                document.add(pdfTable);
                document.add(new Paragraph("\n" + lblTotalVendas.getText()));

                document.close();

                JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar PDF: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

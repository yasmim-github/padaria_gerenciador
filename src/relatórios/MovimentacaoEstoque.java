package relatórios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import db.ConectaBanco;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

public class MovimentacaoEstoque extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel modelo;
    private JButton btnAtualizar;
    private JButton btnExportarPDF;

    ConectaBanco conecta = new ConectaBanco();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MovimentacaoEstoque frame = new MovimentacaoEstoque();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MovimentacaoEstoque() {
        setTitle("Movimentações de Estoque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(248, 236, 218));
        contentPane.add(panelTop, BorderLayout.NORTH);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setForeground(new Color(255, 255, 255));
        btnAtualizar.setBackground(new Color(159, 119, 84));
        panelTop.add(btnAtualizar);

        btnExportarPDF = new JButton("Exportar PDF");
        btnExportarPDF.setForeground(new Color(255, 255, 255));
        btnExportarPDF.setBackground(new Color(159, 119, 84));
        panelTop.add(btnExportarPDF);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Produto", "Usuário", "Ação", "Data", "Quantidade"
            }
        );
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarMovimentacoes();
            }
        });

        btnExportarPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportarParaPDF();
            }
        });

       
        carregarMovimentacoes();
    }

    private void carregarMovimentacoes() {
        modelo.setRowCount(0); 

        String sql = """
                SELECT 
                    p.nome AS produto,
                    u.nome AS usuario,
                    e.acao,
                    e.data_hora,
                    e.quantidade
                FROM 
                    estoque_log e
                JOIN 
                    produtos_base p ON e.produto_id = p.id
                JOIN 
                    usuario u ON e.usuario_id = u.id
                ORDER BY 
                    e.data_hora DESC
                """;

        try (Connection conn = conecta.obtemConexao();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[] {
                    rs.getString("produto"),
                    rs.getString("usuario"),
                    rs.getString("acao"),
                    rs.getString("data_hora"),
                    rs.getInt("quantidade")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar movimentações: " + e.getMessage());
        }
    }

    private void exportarParaPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar PDF");
        fileChooser.setSelectedFile(new File("MovimentacoesEstoque.pdf"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

                document.open();

                // Título
                Font fontTitulo = new Font(Font.HELVETICA, 18, Font.BOLD);
                Paragraph titulo = new Paragraph("Relatório de Movimentações de Estoque", fontTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                document.add(new Paragraph(" ")); // Espaço

                // Tabela
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);

                // Cabeçalho
                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
                    cell.setBackgroundColor(Color.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                // Dados
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        Object value = table.getValueAt(rows, cols);
                        pdfTable.addCell(value != null ? value.toString() : "");
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar PDF: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

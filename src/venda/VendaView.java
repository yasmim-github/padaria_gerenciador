package venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VendaView extends JFrame {
    private JTextField txtValorRecebido;
    private JTextField txtTroco;
    private JTextField txtTotal;
    private JTable table;
    private VendaController controller;

    public VendaView() {
    	getContentPane().setBackground(new Color(248, 236, 218));
        controller = new VendaController(this);

        setTitle("Realizar Venda");
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(20, 380, 60, 14);
        getContentPane().add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(80, 377, 86, 20);
        txtTotal.setEditable(false);
        getContentPane().add(txtTotal);
        txtTotal.setColumns(10);

        JLabel lblValorRecebido = new JLabel("Valor Recebido:");
        lblValorRecebido.setBounds(180, 380, 100, 14);
        getContentPane().add(lblValorRecebido);

        txtValorRecebido = new JTextField();
        txtValorRecebido.setBounds(290, 377, 86, 20);
        getContentPane().add(txtValorRecebido);
        txtValorRecebido.setColumns(10);

        JLabel lblTroco = new JLabel("Troco:");
        lblTroco.setBounds(390, 380, 60, 14);
        getContentPane().add(lblTroco);

        txtTroco = new JTextField();
        txtTroco.setBounds(440, 377, 86, 20);
        txtTroco.setEditable(false);
        getContentPane().add(txtTroco);
        txtTroco.setColumns(10);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.setForeground(new Color(255, 255, 255));
        btnAdicionarProduto.setBackground(new Color(130, 92, 60));
        btnAdicionarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.adicionarProduto();
            }
        });
        btnAdicionarProduto.setBounds(236, 27, 180, 23);
        getContentPane().add(btnAdicionarProduto);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 640, 300);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Produto", "Qtd", "Valor Unit.", "Valor Total" }
        ));
        scrollPane.setViewportView(table);

        JButton btnFinalizar = new JButton("Finalizar Venda");
        btnFinalizar.setBackground(new Color(130, 92, 60));
        btnFinalizar.setForeground(new Color(255, 255, 255));
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.finalizarVenda();
            }
        });
        btnFinalizar.setBounds(550, 376, 130, 23);
        getContentPane().add(btnFinalizar);
        
        JButton btnNewButton = new JButton("Sair");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(130, 92, 60));
        btnNewButton.setBounds(35, 28, 85, 21);
        getContentPane().add(btnNewButton);
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getTxtValorRecebido() {
        return txtValorRecebido;
    }

    public JTextField getTxtTroco() {
        return txtTroco;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }
}

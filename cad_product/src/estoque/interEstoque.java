package estoque;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interEstoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                interEstoque frame = new interEstoque();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public interEstoque() {
        setTitle("Controle de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnMostrarProdutos = new JButton("Mostrar Produtos");
        btnMostrarProdutos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MostrarProdutos mostrar = new MostrarProdutos();
        		mostrar.setVisible(true);
        	}
        });
        btnMostrarProdutos.setForeground(new Color(255, 255, 255));
        btnMostrarProdutos.setBackground(new Color(130, 92, 60));
        btnMostrarProdutos.setBounds(87, 121, 180, 30);
        contentPane.add(btnMostrarProdutos);

        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        btnCadastrarProduto.setBackground(new Color(130, 92, 60));
        btnCadastrarProduto.setForeground(new Color(255, 255, 255));
        btnCadastrarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cad_Produto_base produtoBase = new Cad_Produto_base();
                produtoBase.setVisible(true);
            }
        });
        btnCadastrarProduto.setBounds(297, 121, 180, 30);
        contentPane.add(btnCadastrarProduto);
    }
}

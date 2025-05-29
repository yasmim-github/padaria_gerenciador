package estoque;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interEstoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String cargoUsuario; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
               
                interEstoque frame = new interEstoque("gerente");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

   
    public interEstoque(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;

        setTitle("Controle de Estoque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        btnMostrarProdutos.setForeground(Color.WHITE);
        btnMostrarProdutos.setBackground(new Color(130, 92, 60));
        btnMostrarProdutos.setBounds(87, 121, 180, 30);
        contentPane.add(btnMostrarProdutos);

        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        btnCadastrarProduto.setBackground(new Color(130, 92, 60));
        btnCadastrarProduto.setForeground(Color.WHITE);
        btnCadastrarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cargoUsuario.equalsIgnoreCase("dono") || cargoUsuario.equalsIgnoreCase("gerente")) {
                    Cad_Produto_base produtoBase = new Cad_Produto_base();
                    produtoBase.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Acesso negado! Somente Dono ou Gerente podem cadastrar produtos.", 
                        "Permissão Negada", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnCadastrarProduto.setBounds(297, 121, 180, 30);
        contentPane.add(btnCadastrarProduto);
        
        JButton btnNewButton = new JButton(loadIcon("/imgs/voltar.png", 50, 50 ));
        btnNewButton.setBackground(new Color(248, 236, 218));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        
        btnNewButton.setBounds(21, 301, 50, 50);
        contentPane.add(btnNewButton);
    }

private ImageIcon loadIcon(String path, int width, int height) {
    ImageIcon icon = new ImageIcon(getClass().getResource(path));
    Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
}
}

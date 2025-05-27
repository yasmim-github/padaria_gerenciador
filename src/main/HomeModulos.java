package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import colaboradores.CadastroDeFuncionario;
import estoque.CadastroDeProdutos;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Button;

public class HomeModulos extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeModulos frame = new HomeModulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeModulos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 48, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulopadaria = new JLabel("PADARIA DO PADOCA");
		titulopadaria.setForeground(new Color(159, 119, 84));
		titulopadaria.setHorizontalAlignment(SwingConstants.CENTER);
		titulopadaria.setFont(new Font("Arial Black", Font.BOLD, 20));
		titulopadaria.setBounds(176, 90, 248, 29);
		contentPane.add(titulopadaria);
		
		//botao para menu do estoque 
		
		Button btnestoque = new Button("ESTOQUE");
		btnestoque.setBackground(new Color(159, 119, 84));
		btnestoque.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnestoque.setForeground(new Color(248, 236, 218));
		btnestoque.setBounds(10, 71, 160, 48);
		contentPane.add(btnestoque);
		
		btnestoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDeProdutos estoque = new CadastroDeProdutos();
				estoque.setVisible(true);
			}
		});
		
		//botao para menu de funcionarios 
		
		Button btnfunc = new Button("FUNCIONÁRIOS");
		btnfunc.setForeground(new Color(248, 236, 218));
		btnfunc.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnfunc.setBackground(new Color(159, 119, 84));
		btnfunc.setBounds(10, 125, 160, 48);
		contentPane.add(btnfunc);
		
		btnfunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDeFuncionario funcionarios = new CadastroDeFuncionario();
				funcionarios.setVisible(true);
			}
		});
		
		// botao para menu do caixa
		
		Button btncaixa = new Button("MENU CAIXA");
		btncaixa.setForeground(new Color(248, 236, 218));
		btncaixa.setFont(new Font("Arial Black", Font.BOLD, 15));
		btncaixa.setBackground(new Color(159, 119, 84));
		btncaixa.setBounds(10, 179, 160, 48);
		contentPane.add(btncaixa);
		
		
	}
}

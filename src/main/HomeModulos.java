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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public HomeModulos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 48, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesTitle = new JLabel("PADARIA");
		lblNewJgoodiesTitle.setForeground(new Color(159, 119, 84));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewJgoodiesTitle.setBounds(176, 90, 248, 29);
		contentPane.add(lblNewJgoodiesTitle);
		
		JLabel lblNewJgoodiesTitle_1 = new JLabel("DO");
		lblNewJgoodiesTitle_1.setForeground(new Color(159, 119, 84));
		lblNewJgoodiesTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewJgoodiesTitle_1.setBounds(176, 119, 248, 32);
		contentPane.add(lblNewJgoodiesTitle_1);
		
		JLabel lblNewJgoodiesTitle_2 = new JLabel("PADOCA");
		lblNewJgoodiesTitle_2.setForeground(new Color(159, 119, 84));
		lblNewJgoodiesTitle_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewJgoodiesTitle_2.setBounds(176, 155, 248, 29);
		contentPane.add(lblNewJgoodiesTitle_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\marce\\Downloads\\white-home.png"));
		btnNewButton_3.setForeground(new Color(57, 48, 48));
		btnNewButton_3.setBackground(new Color(57, 48, 48));
		btnNewButton_3.setBounds(10, 11, 35, 27);
		contentPane.add(btnNewButton_3);
		
		Button button = new Button("ESTOQUE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDeProdutos estoque = new CadastroDeProdutos();
				estoque.setVisible(true);
			}
		});
		button.setBackground(new Color(159, 119, 84));
		button.setFont(new Font("Arial Black", Font.BOLD, 15));
		button.setForeground(new Color(248, 236, 218));
		button.setBounds(10, 71, 160, 48);
		contentPane.add(button);
		
		Button button_1 = new Button("FUNCIONÁRIOS");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDeFuncionario funcionarios = new CadastroDeFuncionario();
				funcionarios.setVisible(true);
			}
		});
		button_1.setForeground(new Color(248, 236, 218));
		button_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		button_1.setBackground(new Color(159, 119, 84));
		button_1.setBounds(10, 125, 160, 48);
		contentPane.add(button_1);
		
		Button button_1_1 = new Button("MENU CAIXA");
		button_1_1.setForeground(new Color(248, 236, 218));
		button_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		button_1_1.setBackground(new Color(159, 119, 84));
		button_1_1.setBounds(10, 179, 160, 48);
		contentPane.add(button_1_1);
	}
}

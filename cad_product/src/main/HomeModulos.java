package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import colaboradores.CadastroDeFuncionario;
import estoque.interEstoque;

public class HomeModulos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				HomeModulos frame = new HomeModulos();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public HomeModulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeModulos.class.getResource("/imgs/icon_1.png")));
		setTitle("Padaria - Módulos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);
		setResizable(false);

		contentPane = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon(getClass().getResource("/imgs/fundo_padaria.png"));
				g.drawImage(img.getImage(), getWidth() / 2, 0, getWidth() / 2, getHeight(), this);
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.BLACK);
		sidePanel.setBounds(0, 0, 257, 520);
		sidePanel.setLayout(null);
		contentPane.add(sidePanel);

		
		JButton btnUser = new JButton(new ImageIcon(getClass().getResource("/imgs/user.png")));
		btnUser.setBounds(25, 30, 50, 50);
		btnUser.setBorderPainted(false);
		btnUser.setFocusPainted(false);
		btnUser.setContentAreaFilled(false);
		sidePanel.add(btnUser);

		
		JButton btnBack = new JButton(new ImageIcon(getClass().getResource("/imgs/back.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login log = new Login();
				log.setVisible(true);
			}
		});
		btnBack.setBounds(25, 450, 50, 50);
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		sidePanel.add(btnBack);

		
		JButton btnEstoque = createButton("Estoque", "/imgs/bread.png");
		btnEstoque.setBounds(180, 100, 150, 120);
		btnEstoque.addActionListener(e -> {
			interEstoque estoque = new interEstoque();
			estoque.setVisible(true);
		});
		contentPane.add(btnEstoque);

		
		JButton btnCaixa = createButton("Caixa", "/imgs/caixa.png");
		btnCaixa.setBounds(370, 100, 150, 120);
		contentPane.add(btnCaixa);

		
		JButton btnColaboradores = createButton("Colaboradores", "/imgs/list.png");
		btnColaboradores.setBounds(180, 250, 150, 120);
		btnColaboradores.addActionListener(e -> {
			CadastroDeFuncionario cad = new CadastroDeFuncionario();
			cad.setVisible(true);
		});
		contentPane.add(btnColaboradores);

		
		JButton btnRegistros = createButton("Registros", "/imgs/book.png");
		btnRegistros.setBounds(370, 250, 150, 120);
		contentPane.add(btnRegistros);

	}

	
	private JButton createButton(String text, String iconPath) {
		JButton button = new JButton("<html><center>" + text + "</center></html>");
		button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setBackground(new Color(130, 92, 60));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(new Color(130, 92, 60), 10, true));
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		return button;
	}
}

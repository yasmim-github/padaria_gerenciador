package colaboradores;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.ConectaBanco;
import main.HomeModulos;

public class CadastroDeFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeFuncionario frame = new CadastroDeFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroDeFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 48, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(159, 119, 84));
		panel.setBounds(10, 11, 492, 689);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("CADASTRO DE FUNCIONÁRIO");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 31, 472, 29);
		panel.add(lblTitulo);
		
		//botao de voltar a home

		JButton btnvoltar = new JButton("");
		btnvoltar.setBounds(10, 11, 32, 32); 
		btnvoltar.setBackground(new Color(0x393030));
		btnvoltar.setBounds(10, 11, 35, 27);
		panel.add(btnvoltar);

		btnvoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeModulos home = new HomeModulos();
				home.setVisible(true);
				dispose();
			}
		});
		
		ImageIcon icon = new ImageIcon(new ImageIcon(
			    getClass().getResource("/resources/img/houseicon.png")
			).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		btnvoltar.setIcon(icon);

		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompleto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNomeCompleto.setBounds(10, 81, 472, 29);
		panel.add(lblNomeCompleto);

		textField = new JTextField();
		textField.setBounds(10, 107, 472, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCpf.setBounds(10, 130, 472, 29);
		panel.add(lblCpf);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 156, 472, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCargo.setBounds(10, 179, 472, 29);
		panel.add(lblCargo);

		textField_2 = new JTextField();
		textField_2.setBounds(10, 205, 472, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereco.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndereco.setBounds(10, 229, 472, 29);
		panel.add(lblEndereco);

		textField_3 = new JTextField();
		textField_3.setBounds(10, 255, 472, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTelefone.setBounds(10, 281, 472, 29);
		panel.add(lblTelefone);

		textField_5 = new JTextField();
		textField_5.setBounds(10, 307, 472, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblImagem.setBounds(10, 338, 472, 29);
		panel.add(lblImagem);

		textField_4 = new JTextField();
		textField_4.setBounds(10, 366, 472, 107);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton BtnExcluir = new JButton("EXCLUIR");
		BtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// código incompleto de delete
			}
		});
		BtnExcluir.setBounds(318, 510, 120, 29);
		panel.add(BtnExcluir);

		JButton BtnEditar = new JButton("EDITAR");
		BtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// código incompleto de update
			}
		});
		BtnEditar.setBounds(59, 510, 120, 29);
		panel.add(BtnEditar);

		JButton BtnSalvar = new JButton("SALVAR");
		BtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// código incompleto de select
			}
		});
		BtnSalvar.setBounds(188, 510, 120, 29);
		panel.add(BtnSalvar);

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon("C:\\Users\\marce\\Downloads\\search (1).png"));
		btnBuscar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnBuscar.setBackground(new Color(57, 48, 48));
		btnBuscar.setBounds(767, 24, 44, 27);
		contentPane.add(btnBuscar);

		textField_6 = new JTextField();
		textField_6.setBounds(512, 24, 245, 27);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
	}
}
package estoque;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JSplitPane;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

import db.ConectaBanco;

import javax.swing.JTextArea;

public class CadastroDeProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeProdutos frame = new CadastroDeProdutos();
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
	public CadastroDeProdutos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 750);
		setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(120, 106, 63));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(57, 48, 48));
		panel.setBackground(new Color(57, 48, 48));
		panel.setBounds(0, 0, 1367, 744);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 351, 711);
		panel_2.setBackground(new Color(57, 48, 48));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTitleEstoque = new JLabel("Estoque");
		lblTitleEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleEstoque.setForeground(new Color(255, 255, 255));
		lblTitleEstoque.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblTitleEstoque.setBounds(10, 36, 331, 36);
		panel_2.add(lblTitleEstoque);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 114, 331, 586);
		panel_2.add(textArea);
		
		textField_17 = new JTextField();
		textField_17.setBounds(10, 76, 277, 27);
		panel_2.add(textField_17);
		textField_17.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon("C:\\Users\\marce\\Downloads\\search (1).png"));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT * FROM ";
				ConectaBanco factory = new ConectaBanco();
				try(Connection c = factory.obtemConexao()){
					PreparedStatement ps = c.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						String nome = rs.getString("");
						
						String aux = String.format("Nome produto: "+nome+ "\n");
						JOptionPane.showMessageDialog(null,aux);
					}}catch(Exception w) {
						w.printStackTrace();
					}
				}
			
		});
		btnBuscar.setBackground(new Color(57, 48, 48));
		btnBuscar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnBuscar.setBounds(297, 76, 44, 27);
		panel_2.add(btnBuscar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(57, 48, 48));
		btnNewButton.setBackground(new Color(57, 48, 48));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\marce\\Downloads\\white-home.png"));
		btnNewButton.setBounds(10, 11, 35, 27);
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(159, 119, 84));
		panel_3.setBounds(361, 11, 401, 688);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel LblNomeProd = new JLabel("Nome produto");
		LblNomeProd.setBounds(10, 107, 153, 29);
		panel_3.add(LblNomeProd);
		LblNomeProd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		
		textField = new JTextField();
		textField.setBounds(10, 133, 153, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblCdigoSerial = new JLabel("Código Serial");
		lblCdigoSerial.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCdigoSerial.setBounds(10, 156, 153, 29);
		panel_3.add(lblCdigoSerial);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 182, 153, 20);
		panel_3.add(textField_1);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMarca.setBounds(10, 205, 153, 29);
		panel_3.add(lblMarca);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 231, 153, 20);
		panel_3.add(textField_2);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblFornecedor.setBounds(10, 255, 153, 29);
		panel_3.add(lblFornecedor);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 281, 153, 20);
		panel_3.add(textField_3);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblQuantidade.setBounds(10, 305, 153, 29);
		panel_3.add(lblQuantidade);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 331, 153, 20);
		panel_3.add(textField_4);
		
		JLabel lblValorDeCompra = new JLabel("Valor  de Compra");
		lblValorDeCompra.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorDeCompra.setBounds(10, 357, 153, 29);
		panel_3.add(lblValorDeCompra);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 383, 153, 20);
		panel_3.add(textField_5);
		
		JButton BtnExcluir = new JButton("EXCLUIR");
		BtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "delete from  WHERE";
				ConectaBanco factory = new ConectaBanco();
				try(Connection c = factory.obtemConexao()){
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1,LblNomeProd.getText());
					
					ps.execute();
					JOptionPane.showMessageDialog(null,"Produto "+LblNomeProd.getText()+" foi excluido com sucesso");
				}catch(Exception w) {
					w.printStackTrace();				}
			}
		});
		BtnExcluir.setBounds(269, 630, 120, 29);
		panel_3.add(BtnExcluir);
		
		JButton BtnEditar = new JButton("EDITAR");
		BtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE SET ";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()){
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1,LblNomeProd.getText());
					
					ps.execute();
					JOptionPane.showMessageDialog(null,"Alterado com sucesso");
					}catch(Exception w) {
						w.printStackTrace();
				}
				LblNomeProd.setText("");
				}
		});
		BtnEditar.setBounds(10, 630, 120, 29);
		panel_3.add(BtnEditar);
		
		JButton BtnSalvar = new JButton("SALVAR");
		BtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO ";
				ConectaBanco factory  = new ConectaBanco();
				try(Connection c = factory.obtemConexao()){
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1,LblNomeProd.getText());
					
					ps.execute();
					JOptionPane.showMessageDialog(null,"-----!!!Produto Cadastrado com Sucesso!!!-----");
				}catch(Exception w ) {
					w.printStackTrace();
				}
				LblNomeProd.setText("");
			}
			
		});
		BtnSalvar.setBounds(139, 630, 120, 29);
		panel_3.add(BtnSalvar);
		
		JLabel lblValorDeVenda = new JLabel("Valor  de Venda");
		lblValorDeVenda.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorDeVenda.setBounds(10, 407, 153, 29);
		panel_3.add(lblValorDeVenda);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(10, 433, 153, 20);
		panel_3.add(textField_6);
		
		JLabel lblValidade = new JLabel("Validade");
		lblValidade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValidade.setBounds(10, 456, 153, 29);
		panel_3.add(lblValidade);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(10, 482, 153, 20);
		panel_3.add(textField_7);
		
		JLabel lblAlrgenos = new JLabel("Alérgenos ");
		lblAlrgenos.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAlrgenos.setBounds(10, 506, 153, 29);
		panel_3.add(lblAlrgenos);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(10, 532, 153, 20);
		panel_3.add(textField_8);
		
		JLabel lblValorDeCompra_4 = new JLabel("Valor  de Compra");
		lblValorDeCompra_4.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorDeCompra_4.setBounds(10, 558, 153, 29);
		panel_3.add(lblValorDeCompra_4);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(10, 584, 153, 20);
		panel_3.add(textField_9);
		
		JLabel lblNcs = new JLabel("NCM");
		lblNcs.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNcs.setBounds(223, 107, 153, 29);
		panel_3.add(lblNcs);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(223, 133, 153, 20);
		panel_3.add(textField_10);
		
		JLabel lblIcms = new JLabel("ICMS");
		lblIcms.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblIcms.setBounds(223, 156, 153, 29);
		panel_3.add(lblIcms);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(223, 182, 153, 20);
		panel_3.add(textField_11);
		
		JLabel lblPis = new JLabel("PIS");
		lblPis.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPis.setBounds(223, 205, 153, 29);
		panel_3.add(lblPis);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(223, 231, 153, 20);
		panel_3.add(textField_12);
		
		JLabel lblCofins = new JLabel("COFINS");
		lblCofins.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCofins.setBounds(223, 255, 153, 29);
		panel_3.add(lblCofins);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(223, 281, 153, 20);
		panel_3.add(textField_13);
		
		JLabel lblIpi = new JLabel("IPI");
		lblIpi.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblIpi.setBounds(223, 305, 153, 29);
		panel_3.add(lblIpi);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(223, 331, 153, 20);
		panel_3.add(textField_14);
		
		JLabel lblLote = new JLabel("LOTE");
		lblLote.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLote.setBounds(223, 357, 153, 29);
		panel_3.add(lblLote);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(223, 383, 153, 20);
		panel_3.add(textField_15);
		
		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblImagem.setBounds(223, 407, 153, 29);
		panel_3.add(lblImagem);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(223, 433, 153, 171);
		panel_3.add(textField_16);
		
		JLabel lblNewJgoodiesTitle = new JLabel("CADASTRO DE PRODUTO");
		lblNewJgoodiesTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setBounds(10, 40, 379, 40);
		panel_3.add(lblNewJgoodiesTitle);
		
	}
}

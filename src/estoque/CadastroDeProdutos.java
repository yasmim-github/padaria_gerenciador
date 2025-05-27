package estoque;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import db.ConectaBanco;
import main.HomeModulos;
import javax.swing.JTextArea;

public class CadastroDeProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
	private int tamanho;

	private JTextField txtnomeprod;
	private JTextField txtcodserial;
	private JTextField txtmarca;
	private JTextField txtfornecedor;
	private JTextField txtquantidade;
	private JTextField txtvalorvenda;
	private JTextField txtvalidade;
	private JTextField txtalergenos;
	private JTextField txtpeso;
	private JTextField txtvalorcompra;
	private JTextField txtncm;
	private JTextField txticms;
	private JTextField txtpis;
	private JTextField txtcofins;
	private JTextField txtipi;
	private JTextField txtlote;
	private JTextField txtpesquisa;
	private JLabel lblImagem;
	private JLabel iconcamera;

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

		txtpesquisa = new JTextField();
		txtpesquisa.setBounds(10, 76, 277, 27);
		panel_2.add(txtpesquisa);
		txtpesquisa.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT * FROM produtos_externos";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					StringBuilder sb = new StringBuilder();
					while (rs.next()) {
						String nome = rs.getString("marca");
						sb.append("Marca: ").append(nome).append("\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString());
				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		});

		btnBuscar.setBackground(new Color(57, 48, 48));
		btnBuscar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnBuscar.setBounds(297, 76, 44, 27);
		panel_2.add(btnBuscar);

		JButton btnvoltar = new JButton("");
		ImageIcon icon = new ImageIcon(new ImageIcon(
				getClass().getResource("/resources/img/houseicon.png")).getImage().getScaledInstance(32, 32,
						Image.SCALE_SMOOTH));
		btnvoltar.setIcon(icon);
		btnvoltar.setBounds(10, 11, 32, 32);

		btnvoltar.setBackground(new Color(0x9f7754));
		panel_2.add(btnvoltar);

		btnvoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeModulos home = new HomeModulos();
				home.setVisible(true);
				dispose();
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(159, 119, 84));
		panel_3.setBounds(361, 11, 401, 688);
		panel.add(panel_3);
		panel_3.setLayout(null);

		// Initialize the class-level lblImagem and add to panel_3
		lblImagem = new JLabel("Imagem");
		lblImagem.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblImagem.setBackground(new Color(248, 236, 218));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setBounds(223, 407, 153, 29);
		panel_3.add(lblImagem);

		JLabel LblNomeProd = new JLabel("Nome produto");
		LblNomeProd.setBounds(10, 107, 153, 29);
		panel_3.add(LblNomeProd);
		LblNomeProd.setFont(new Font("Arial Black", Font.PLAIN, 16));

		txtnomeprod = new JTextField();
		txtnomeprod.setBounds(10, 133, 153, 20);
		panel_3.add(txtnomeprod);
		txtnomeprod.setColumns(10);

		JLabel lblCodigoSerial = new JLabel("Código Serial");
		lblCodigoSerial.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCodigoSerial.setBounds(10, 156, 153, 29);
		panel_3.add(lblCodigoSerial);

		txtcodserial = new JTextField();
		txtcodserial.setColumns(10);
		txtcodserial.setBounds(10, 182, 153, 20);
		panel_3.add(txtcodserial);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMarca.setBounds(10, 205, 153, 29);
		panel_3.add(lblMarca);

		txtmarca = new JTextField();
		txtmarca.setColumns(10);
		txtmarca.setBounds(10, 231, 153, 20);
		panel_3.add(txtmarca);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblFornecedor.setBounds(10, 255, 153, 29);
		panel_3.add(lblFornecedor);

		txtfornecedor = new JTextField();
		txtfornecedor.setColumns(10);
		txtfornecedor.setBounds(10, 281, 153, 20);
		panel_3.add(txtfornecedor);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblQuantidade.setBounds(10, 305, 153, 29);
		panel_3.add(lblQuantidade);

		txtquantidade = new JTextField();
		txtquantidade.setColumns(10);
		txtquantidade.setBounds(10, 331, 153, 20);
		panel_3.add(txtquantidade);

		JLabel lblValorDeCompra = new JLabel("Valor  de Compra");
		lblValorDeCompra.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorDeCompra.setBounds(10, 357, 153, 29);
		panel_3.add(lblValorDeCompra);

		txtvalorcompra = new JTextField();
		txtvalorcompra.setColumns(10);
		txtvalorcompra.setBounds(10, 383, 153, 20);
		panel_3.add(txtvalorcompra);

		JButton BtnExcluir = new JButton("EXCLUIR");
		BtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM produtos_externos WHERE codigo_serial = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, txtcodserial.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null,
							"Produto " + txtcodserial.getText() + " foi excluido com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		});
		BtnExcluir.setBounds(269, 630, 120, 29);
		panel_3.add(BtnExcluir);

		JButton BtnEditar = new JButton("EDITAR");
		BtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE produtos_externos SET marca = ?, preco_venda = ?, preco_compra = ?, quantidade = ?, fornecedor = ? WHERE codigo_serial = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, txtmarca.getText());
					ps.setDouble(2, Double.parseDouble(txtvalorvenda.getText()));
					ps.setDouble(3, Double.parseDouble(txtvalorcompra.getText()));
					ps.setInt(4, Integer.parseInt(txtquantidade.getText()));
					ps.setString(5, txtfornecedor.getText());
					ps.setString(6, txtcodserial.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Alterado com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		});

		BtnEditar.setBounds(10, 630, 120, 29);
		panel_3.add(BtnEditar);

		JButton BtnSalvar = new JButton("SALVAR");
		BtnSalvar.setBounds(139, 630, 120, 29);
		panel_3.add(BtnSalvar);

		BtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO produtos_externos (codigo_serial, preco_venda, preco_compra, quantidade, marca, peso, alergenos, validade, fornecedor, ncm, icms, pis, cofins, ipi, lote, imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, txtcodserial.getText());
					ps.setDouble(2, Double.parseDouble(txtvalorvenda.getText()));
					ps.setDouble(3, Double.parseDouble(txtvalorcompra.getText()));
					ps.setInt(4, Integer.parseInt(txtquantidade.getText()));
					ps.setString(5, txtmarca.getText());
					ps.setDouble(6, Double.parseDouble(txtpeso.getText()));
					ps.setString(7, txtalergenos.getText());
					ps.setString(8, txtvalidade.getText());
					ps.setString(9, txtfornecedor.getText());
					ps.setString(10, txtncm.getText());
					ps.setDouble(11, Double.parseDouble(txticms.getText()));
					ps.setDouble(12, Double.parseDouble(txtpis.getText()));
					ps.setDouble(13, Double.parseDouble(txtcofins.getText()));
					ps.setDouble(14, Double.parseDouble(txtipi.getText()));
					ps.setString(15, txtlote.getText());
					ps.setBytes(16, getImageBytes());

					ps.execute();
					JOptionPane.showMessageDialog(null, "-----!!!Produto Cadastrado com Sucesso!!!-----");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
				} catch (Exception w) {
					w.printStackTrace();
				}
				limparCampos();
			}
		});

		JLabel lblValorDeVenda = new JLabel("Valor  de Venda");
		lblValorDeVenda.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValorDeVenda.setBounds(10, 407, 153, 29);
		panel_3.add(lblValorDeVenda);

		txtvalorvenda = new JTextField("");
		txtvalorvenda.setColumns(10);
		txtvalorvenda.setBounds(10, 433, 153, 20);
		panel_3.add(txtvalorvenda);

		JLabel lblValidade = new JLabel("Validade");
		lblValidade.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblValidade.setBounds(10, 456, 153, 29);
		panel_3.add(lblValidade);

		txtvalidade = new JTextField();
		txtvalidade.setColumns(10);
		txtvalidade.setBounds(10, 482, 153, 20);
		panel_3.add(txtvalidade);

		JLabel lblAlrgenos = new JLabel("Alérgenos ");
		lblAlrgenos.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAlrgenos.setBounds(10, 506, 153, 29);
		panel_3.add(lblAlrgenos);

		txtalergenos = new JTextField("");
		txtalergenos.setColumns(10);
		txtalergenos.setBounds(10, 532, 153, 20);
		panel_3.add(txtalergenos);

		JLabel lblpeso = new JLabel("Peso");
		lblpeso.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblpeso.setBounds(10, 558, 153, 29);
		panel_3.add(lblpeso);

		txtpeso = new JTextField("");
		txtpeso.setColumns(10);
		txtpeso.setBounds(10, 584, 153, 20);
		panel_3.add(txtpeso);

		JLabel lblNcs = new JLabel("NCM");
		lblNcs.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNcs.setBounds(223, 107, 153, 29);
		panel_3.add(lblNcs);

		txtncm = new JTextField();
		txtncm.setColumns(10);
		txtncm.setBounds(223, 133, 153, 20);
		panel_3.add(txtncm);

		JLabel lblIcms = new JLabel("ICMS");
		lblIcms.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblIcms.setBounds(223, 156, 153, 29);
		panel_3.add(lblIcms);

		txticms = new JTextField();
		txticms.setColumns(10);
		txticms.setBounds(223, 182, 153, 20);
		panel_3.add(txticms);

		JLabel lblPis = new JLabel("PIS");
		lblPis.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPis.setBounds(223, 205, 153, 29);
		panel_3.add(lblPis);

		txtpis = new JTextField();
		txtpis.setColumns(10);
		txtpis.setBounds(223, 231, 153, 20);
		panel_3.add(txtpis);

		JLabel lblCofins = new JLabel("COFINS");
		lblCofins.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCofins.setBounds(223, 255, 153, 29);
		panel_3.add(lblCofins);

		txtcofins = new JTextField();
		txtcofins.setColumns(10);
		txtcofins.setBounds(223, 281, 153, 20);
		panel_3.add(txtcofins);

		JLabel lblIpi = new JLabel("IPI");
		lblIpi.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblIpi.setBounds(223, 305, 153, 29);
		panel_3.add(lblIpi);

		txtipi = new JTextField();
		txtipi.setColumns(10);
		txtipi.setBounds(223, 331, 153, 20);
		panel_3.add(txtipi);

		JLabel lblLote = new JLabel("LOTE");
		lblLote.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLote.setBounds(223, 357, 153, 29);
		panel_3.add(lblLote);

		txtlote = new JTextField();
		txtlote.setColumns(10);
		txtlote.setBounds(223, 383, 153, 20);
		panel_3.add(txtlote);

		JLabel lblNewJgoodiesTitle = new JLabel("CADASTRO DE PRODUTO");
		lblNewJgoodiesTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setBounds(10, 40, 379, 40);
		panel_3.add(lblNewJgoodiesTitle);

		JButton btnCarregar = new JButton("CARREGAR IMAGEM");
		btnCarregar.setBackground(new Color(57, 48, 48));
		btnCarregar.setBounds(223, 456, 153, 20);
		panel_3.add(btnCarregar);

		iconcamera = new JLabel("");
		iconcamera.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon icon2 = new ImageIcon(new ImageIcon(
				getClass().getResource("/resources/img/cameraicon.png")).getImage().getScaledInstance(32, 32,
						Image.SCALE_SMOOTH));
		iconcamera.setIcon(icon2);
		iconcamera.setBackground(new Color(248, 236, 218));
		iconcamera.setBounds(223, 510, 156, 110);
		panel_3.add(iconcamera);

		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarImagem();
			}
		});
	}

	private void carregarImagem() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar Arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPEG,*.JPG)", "png", "jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				// Use lblImagem width/height for scaled image
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblImagem.getWidth(),
						lblImagem.getHeight(), Image.SCALE_SMOOTH);
				lblImagem.setIcon(new ImageIcon(foto));
				lblImagem.updateUI();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Convert the image to a byte array for DB storage
	private byte[] getImageBytes() {
		if (fis != null) {
			try {
				byte[] imageBytes = new byte[tamanho];
				fis.read(imageBytes);
				fis.close();
				return imageBytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void limparCampos() {
		txtcodserial.setText("");
		txtvalorvenda.setText("");
		txtvalorcompra.setText("");
		txtpeso.setText("");
		txtquantidade.setText("");
		txtmarca.setText("");
		txtalergenos.setText("");
		txtvalidade.setText("");
		txtfornecedor.setText("");
		txtncm.setText("");
		txticms.setText("");
		txtpis.setText("");
		txtcofins.setText("");
		txtipi.setText("");
		txtlote.setText("");
		lblImagem.setIcon(null);
	}

}



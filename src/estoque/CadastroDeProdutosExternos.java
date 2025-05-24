package Estoque;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CadastroDeProdutosExternos extends JFrame {
	
	 private FileInputStream fis;
	 private int tamanho;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCodigo;
	private JTextField txtVenda;
	private JTextField txtCompra;
	private JTextField txtQuantidade;
	private JTextField txtMarca;
	private JTextField txtPeso;
	private JTextField txtAlergenos;
	private JTextField txtValidade;
	private JTextField txtFornecedor;
	private JTextField txtLote;
	private JLabel lblImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeProdutosExternos frame = new CadastroDeProdutosExternos();
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
	public CadastroDeProdutosExternos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 48, 48));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(57, 48, 48));
		btnNewButton.setIcon(new ImageIcon(CadastroDeProdutosExternos.class.getResource("/Img/white-home.png")));
		btnNewButton.setBounds(10, 11, 32, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE PRODUTO");
		lblNewLabel.setForeground(new Color(159, 119, 84));
		lblNewLabel.setBackground(new Color(159, 119, 84));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 57, 388, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" NOME COMPLETO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(26, 113, 156, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBackground(new Color(248, 236, 218));
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(26, 134, 156, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel(" CÓDIGO SERIAL");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(26, 165, 156, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(new Color(248, 236, 218));
		txtCodigo.setBounds(26, 186, 156, 20);
		contentPane.add(txtCodigo);
		
		JLabel lblNewLabel_1_2 = new JLabel(" PREÇO DE VENDA(und)");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(26, 217, 156, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtVenda = new JTextField();
		txtVenda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtVenda.setColumns(10);
		txtVenda.setBackground(new Color(248, 236, 218));
		txtVenda.setBounds(26, 238, 156, 20);
		contentPane.add(txtVenda);
		
		JLabel lblNewLabel_1_3 = new JLabel(" PREÇO DE COMPRA(und)");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(26, 269, 167, 14);
		contentPane.add(lblNewLabel_1_3);
		
		txtCompra = new JTextField();
		txtCompra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCompra.setColumns(10);
		txtCompra.setBackground(new Color(248, 236, 218));
		txtCompra.setBounds(26, 290, 156, 20);
		contentPane.add(txtCompra);
		
		JLabel lblNewLabel_1_4 = new JLabel(" QUANTIDADE");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4.setBounds(26, 321, 156, 14);
		contentPane.add(lblNewLabel_1_4);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBackground(new Color(248, 236, 218));
		txtQuantidade.setBounds(26, 342, 156, 20);
		contentPane.add(txtQuantidade);
		
		JLabel lblNewLabel_1_5 = new JLabel(" MARCA");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_5.setBounds(26, 373, 156, 14);
		contentPane.add(lblNewLabel_1_5);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMarca.setColumns(10);
		txtMarca.setBackground(new Color(248, 236, 218));
		txtMarca.setBounds(26, 394, 156, 20);
		contentPane.add(txtMarca);
		
		JLabel lblNewLabel_1_6 = new JLabel(" PESO");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_6.setBounds(26, 425, 156, 14);
		contentPane.add(lblNewLabel_1_6);
		
		txtPeso = new JTextField();
		txtPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPeso.setColumns(10);
		txtPeso.setBackground(new Color(248, 236, 218));
		txtPeso.setBounds(26, 446, 156, 20);
		contentPane.add(txtPeso);
		
		JLabel lblNewLabel_1_7 = new JLabel(" ALERGENOS");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_7.setBounds(203, 113, 156, 14);
		contentPane.add(lblNewLabel_1_7);
		
		txtAlergenos = new JTextField();
		txtAlergenos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAlergenos.setColumns(10);
		txtAlergenos.setBackground(new Color(248, 236, 218));
		txtAlergenos.setBounds(203, 134, 156, 20);
		contentPane.add(txtAlergenos);
		
		JLabel lblNewLabel_1_8 = new JLabel(" VALIDADE");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_8.setBounds(203, 165, 156, 14);
		contentPane.add(lblNewLabel_1_8);
		
		txtValidade = new JTextField();
		txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtValidade.setColumns(10);
		txtValidade.setBackground(new Color(248, 236, 218));
		txtValidade.setBounds(203, 186, 156, 20);
		contentPane.add(txtValidade);
		
		JLabel lblNewLabel_1_9 = new JLabel(" FORNECEDOR");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_9.setBounds(203, 217, 156, 14);
		contentPane.add(lblNewLabel_1_9);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFornecedor.setColumns(10);
		txtFornecedor.setBackground(new Color(248, 236, 218));
		txtFornecedor.setBounds(203, 238, 156, 20);
		contentPane.add(txtFornecedor);
		
		JLabel lblNewLabel_1_10 = new JLabel(" LOTE");
		lblNewLabel_1_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_10.setBounds(203, 269, 156, 14);
		contentPane.add(lblNewLabel_1_10);
		
		txtLote = new JTextField();
		txtLote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtLote.setColumns(10);
		txtLote.setBackground(new Color(248, 236, 218));
		txtLote.setBounds(203, 290, 156, 20);
		contentPane.add(txtLote);
		
		JButton btnNewButton_1 = new JButton("CANCELAR");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBackground(new Color(57, 48, 48));
		btnNewButton_1.setBounds(52, 529, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setForeground(new Color(0, 0, 128));
		btnSalvar.setBackground(new Color(57, 48, 48));
		btnSalvar.setBounds(226, 529, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCarregar = new JButton("CARREGAR IMAGEM");
		btnCarregar.setBackground(new Color(57, 48, 48));
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarImagem();
			}
		});
		btnCarregar.setBounds(203, 318, 156, 23);
		contentPane.add(btnCarregar);
		
		lblImagem = new JLabel("");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setIcon(new ImageIcon(CadastroDeProdutosExternos.class.getResource("/Img/camera.png")));
		lblImagem.setBackground(new Color(248, 236, 218));
		lblImagem.setBounds(203, 356, 156, 110);
		contentPane.add(lblImagem);
	}
	private void carregarImagem() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar Arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPEG,*.JPG)","png","jpg","jpeg"));
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int)jfc.getSelectedFile().length();	
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
				lblImagem.setIcon(new ImageIcon(foto));
				lblImagem.updateUI();
				}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}

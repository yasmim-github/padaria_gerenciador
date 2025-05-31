package relatórios;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class InterRelatorios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterRelatorios frame = new InterRelatorios();
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
	public InterRelatorios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 236, 218));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Relatório de vendas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioVendas rel = new RelatorioVendas();
				rel.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(159, 119, 84));
		btnNewButton_1.setBounds(5, 99, 200, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Relatório de estoque");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovimentacaoEstoque mov = new MovimentacaoEstoque();
				mov.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(159, 119, 84));
		btnNewButton.setBounds(230, 99, 200, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton(loadIcon("/imgs/voltar.png", 50, 50));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	
		btnNewButton_2.setBounds(10, 210, 50, 50);
		contentPane.add(btnNewButton_2);
	}
	 private ImageIcon loadIcon(String path, int width, int height) {
	        ImageIcon icon = new ImageIcon(getClass().getResource(path));
	        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        return new ImageIcon(image);
	    }
}

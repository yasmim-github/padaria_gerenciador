package colaboradores;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class interColaboradores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interColaboradores frame = new interColaboradores();
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
	public interColaboradores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 236, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 426, 84);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("gerenciar funcionários");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerenFuncionario geren = new gerenFuncionario();
				geren.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(159, 119, 84));
		btnNewButton.setBounds(15, 101, 179, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cadastrar funcionários");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroDeFuncionario cad = new CadastroDeFuncionario();
				cad.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(159, 119, 84));
		btnNewButton_1.setBounds(224, 101, 179, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(loadIcon("/imgs/voltar.png", 50, 50));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(20, 210, 50, 50);
		contentPane.add(btnNewButton_2);
	}
	 private ImageIcon loadIcon(String path, int width, int height) {
	        ImageIcon icon = new ImageIcon(getClass().getResource(path));
	        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        return new ImageIcon(image);
	    }
	}

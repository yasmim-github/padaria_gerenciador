package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.ConectaBanco;

public class Login extends JFrame {

	JTextField cpf = new JTextField(15);
	JTextField nome = new JTextField(20);
	
	String[] cargos = {"gerente","dono","caixa"};
	
	JComboBox<String> cargosbox = new JComboBox<>(cargos);
	JButton entrar = new JButton();
	
	JLabel labelCpf = new JLabel("CPF:");
    JLabel labelNome = new JLabel("Nome:");
    JLabel labelCargo = new JLabel("Cargo:");
	
	public Login() {
		setTitle("Login");
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		
		labelCpf.setBounds(50, 10, 100, 20);
        labelNome.setBounds(50, 50, 100, 20);
        labelCargo.setBounds(50, 90, 100, 20);

        cpf.setBounds(50, 30, 300, 25);
        nome.setBounds(50, 70, 300, 25);
        cargosbox.setBounds(50, 110, 300, 25);
        entrar.setBounds(150, 160, 100, 30);

        add(labelCpf);
        add(cpf);
        add(labelNome);
        add(nome);
        add(labelCargo);
        add(cargosbox);
        add(entrar);
        
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirNoBanco();
            }
        });
        
        setVisible(true);
        
	}
	
	public void limparCampos() {
	    cpf.setText("");
	    nome.setText("");
	    cargosbox.setSelectedIndex(0);
	}
	
	public void inserirNoBanco() {
		String cpftxt = cpf.getText().trim();
		String nometxt = nome.getText().trim();
		String cargotxt = cargosbox.getSelectedItem().toString();
		
		try {
			ConectaBanco conbd = new ConectaBanco();
			Connection conn = conbd.obtemConexao();
			String sql = "SELECT * FROM usuario WHERE cpf = ? AND nome = ? AND cargo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cpftxt);
			stmt.setString(2, nometxt);
			stmt.setString(3, cargotxt);
	        ResultSet rs = stmt.executeQuery();
			
	        if (rs.next()) {
	            JOptionPane.showMessageDialog(this, "Login bem-sucedido! Usuário encontrado.");
	            HomeModulos home = new HomeModulos();
	            home.setVisible(true);
	            this.dispose();
	            
	        } else {
	            JOptionPane.showMessageDialog(this, "Usuário não encontrado. Verifique suas credenciais.");
	            limparCampos();
	        
	        }

	        rs.close();
	        stmt.close();
	        conn.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Falha ao logar no sistema." + ex.getMessage());

		}
		
	}
	
	
	   public static void main(String[] args) {
	        new Login();
	    }
	}		
	
	

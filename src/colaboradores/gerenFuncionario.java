package colaboradores;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class gerenFuncionario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPesquisar;
    private JTable table;
    private DefaultTableModel model;

    
    private final String URL = "jdbc:mysql://localhost:3306/padaria_do_pandoca";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                gerenFuncionario frame = new gerenFuncionario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public gerenFuncionario() {
        setTitle("Gerenciar Funcionários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 520);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 236, 218));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblPesquisar = new JLabel("Pesquisar por nome:");
        lblPesquisar.setBounds(30, 20, 150, 25);
        contentPane.add(lblPesquisar);

        txtPesquisar = new JTextField();
        txtPesquisar.setBounds(180, 20, 400, 25);
        contentPane.add(txtPesquisar);
        txtPesquisar.setColumns(10);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setForeground(Color.WHITE);
        btnPesquisar.setBackground(new Color(159, 119, 84));
        btnPesquisar.setBounds(600, 20, 120, 25);
        contentPane.add(btnPesquisar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 70, 820, 300);
        contentPane.add(scrollPane);

        model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane.setViewportView(table);

        model.setColumnIdentifiers(new String[]{
                "ID", "Nome", "Cargo", "Cidade", "Bairro", "Número", "Telefone", "Email", "CPF"
        });

     
        JButton btnSair = new JButton(loadIcon("/imgs/voltar.png", 50, 50));
        btnSair.setForeground(Color.WHITE);
        btnSair.setBackground(new Color(159, 119, 84));
        btnSair.setBounds(30, 400, 50, 50); 
        contentPane.add(btnSair);

        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBackground(new Color(159, 119, 84));
        btnCadastrar.setBounds(260, 400, 120, 30);
        contentPane.add(btnCadastrar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setForeground(Color.WHITE);
        btnAlterar.setBackground(new Color(159, 119, 84));
        btnAlterar.setBounds(390, 400, 120, 30);
        contentPane.add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setBackground(new Color(159, 119, 84));
        btnExcluir.setBounds(520, 400, 120, 30);
        contentPane.add(btnExcluir);


       
        btnPesquisar.addActionListener(e -> pesquisarFuncionarios());
        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
        btnAlterar.addActionListener(e -> alterarFuncionario());
        btnExcluir.addActionListener(e -> excluirFuncionario());
        btnSair.addActionListener(e -> dispose());
    }

   
    private void pesquisarFuncionarios() {
        model.setRowCount(0);
        String nome = txtPesquisar.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT id, nome, cargo, cidade, bairro, numero_casa, numero_telefone, email, cpf "
                       + "FROM usuario WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cargo"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("numero_casa"),
                        rs.getString("numero_telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar funcionários: " + ex.getMessage());
        }
    }

    
    private void cadastrarFuncionario() {
        JTextField txtNome = new JTextField();
        JTextField txtCargo = new JTextField();
        JTextField txtCidade = new JTextField();
        JTextField txtBairro = new JTextField();
        JTextField txtNumeroCasa = new JTextField();
        JTextField txtTelefone = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtCpf = new JTextField();
        JPasswordField txtSenha = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Cargo (operador, gerente, dono):"));
        panel.add(txtCargo);
        panel.add(new JLabel("Cidade:"));
        panel.add(txtCidade);
        panel.add(new JLabel("Bairro:"));
        panel.add(txtBairro);
        panel.add(new JLabel("Número da casa:"));
        panel.add(txtNumeroCasa);
        panel.add(new JLabel("Telefone:"));
        panel.add(txtTelefone);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);

        int result = JOptionPane.showConfirmDialog(this, panel, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO usuario (nome, senha_hash, cargo, cidade, bairro, numero_casa, numero_telefone, email, cpf) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNome.getText());
                stmt.setString(2, hashSenha(new String(txtSenha.getPassword())));
                stmt.setString(3, txtCargo.getText());
                stmt.setString(4, txtCidade.getText());
                stmt.setString(5, txtBairro.getText());
                stmt.setString(6, txtNumeroCasa.getText());
                stmt.setString(7, txtTelefone.getText());
                stmt.setString(8, txtEmail.getText());
                stmt.setString(9, txtCpf.getText());

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                pesquisarFuncionarios();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        }
    }

   
    private void alterarFuncionario() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para alterar.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        String nome = (String) model.getValueAt(row, 1);
        String cargo = (String) model.getValueAt(row, 2);
        String cidade = (String) model.getValueAt(row, 3);
        String bairro = (String) model.getValueAt(row, 4);
        String numero = (String) model.getValueAt(row, 5);
        String telefone = (String) model.getValueAt(row, 6);
        String email = (String) model.getValueAt(row, 7);
        String cpf = (String) model.getValueAt(row, 8);

       
        nome = JOptionPane.showInputDialog(this, "Nome:", nome);
        cargo = JOptionPane.showInputDialog(this, "Cargo (operador, gerente, dono):", cargo);
        cidade = JOptionPane.showInputDialog(this, "Cidade:", cidade);
        bairro = JOptionPane.showInputDialog(this, "Bairro:", bairro);
        numero = JOptionPane.showInputDialog(this, "Número da casa:", numero);
        telefone = JOptionPane.showInputDialog(this, "Telefone:", telefone);
        email = JOptionPane.showInputDialog(this, "Email:", email);
        cpf = JOptionPane.showInputDialog(this, "CPF:", cpf);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE usuario SET nome=?, cargo=?, cidade=?, bairro=?, numero_casa=?, "
                       + "numero_telefone=?, email=?, cpf=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cargo);
            stmt.setString(3, cidade);
            stmt.setString(4, bairro);
            stmt.setString(5, numero);
            stmt.setString(6, telefone);
            stmt.setString(7, email);
            stmt.setString(8, cpf);
            stmt.setInt(9, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso!");
            pesquisarFuncionarios();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar: " + ex.getMessage());
        }
    }

   
    private void excluirFuncionario() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para excluir.");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este funcionário?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "DELETE FROM usuario WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
                pesquisarFuncionarios();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        }
    }

    
    private String hashSenha(String senha) {
        return Integer.toHexString(senha.hashCode());
    }
    private ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}


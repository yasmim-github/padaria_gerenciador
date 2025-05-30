package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import colaboradores.gerenFuncionario;
import estoque.interEstoque;

import venda.VendaView;

public class HomeModulos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private String cargoUsuario;

   


    public HomeModulos(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;

        setIconImage(Toolkit.getDefaultToolkit().getImage(HomeModulos.class.getResource("/imgs/icon_1.jpg")));
        setTitle("Padaria - Módulos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 520);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setBounds(0, 0, 196, 520);
        sidePanel.setLayout(null);
        contentPane.add(sidePanel);

        JButton btnUser = new JButton(loadIcon("/imgs/user.png", 50, 50));
        btnUser.setBounds(25, 30, 50, 50);
        btnUser.setBorderPainted(false);
        btnUser.setFocusPainted(false);
        btnUser.setContentAreaFilled(false);
        sidePanel.add(btnUser);

        JButton btnBack = new JButton(loadIcon("/imgs/voltar.png", 50, 50));
        btnBack.addActionListener((ActionEvent e) -> {
            dispose();
            Login log = new Login();
            log.setVisible(true);
        });
        btnBack.setBounds(25, 411, 50, 50);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        sidePanel.add(btnBack);

        JButton btnEstoque = createButton("Estoque", "/imgs/pão.png");
        btnEstoque.setBounds(300, 100, 150, 120);
        btnEstoque.addActionListener(e -> {

            interEstoque estoque = new interEstoque(cargoUsuario);
 

            estoque.setVisible(true);
        });
        contentPane.add(btnEstoque);

        JButton btnCaixa = createButton("Vender", "/imgs/vender(1).png");

        btnCaixa.addActionListener(e -> {
            VendaView venda = new VendaView();
            venda.setVisible(true);
        });


        btnCaixa.setBounds(500, 100, 150, 120);
        contentPane.add(btnCaixa);

        JButton btnColaboradores = createButton("Colaboradores", "/imgs/colaboradores (2).png");
        btnColaboradores.setBounds(300, 250, 150, 120);
        btnColaboradores.addActionListener(e -> {
            if (cargoUsuario.equalsIgnoreCase("gerente") || cargoUsuario.equalsIgnoreCase("dono")) {
                gerenFuncionario geren = new gerenFuncionario();
                geren.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(this,
                        "Acesso negado! Somente Dono ou Gerente podem acessar Colaboradores.",
                        "Permissão Negada",
                        JOptionPane.WARNING_MESSAGE);

            }
        });
        contentPane.add(btnColaboradores);

        JButton btnRegistros = createButton("Registros", "/imgs/registro.png");
        btnRegistros.setBounds(500, 250, 150, 120);
        btnRegistros.addActionListener(e -> {
            if (cargoUsuario.equalsIgnoreCase("gerente") || cargoUsuario.equalsIgnoreCase("dono")) {

                JOptionPane.showMessageDialog(this, "Abrindo tela de Registros (placeholder).");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Acesso negado! Somente Dono ou Gerente podem acessar Registros.",
                        "Permissão Negada",
                        JOptionPane.WARNING_MESSAGE);

                
                JOptionPane.showMessageDialog(this, "Abrindo tela de Registros (placeholder).");
            } 
        });
        contentPane.add(btnRegistros);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/imgs/fundo_padaria(2).png.png"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(900, 520, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(91, 0, 1104, 503);
        contentPane.add(background);
    }


    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton("<html><center>" + text + "</center></html>");
        button.setIcon(loadIcon(iconPath, 80, 80));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBackground(new Color(130, 92, 60));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(130, 92, 60), 10, true));
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        return button;
    }


    private ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HomeModulos frame = new HomeModulos("gerente"); // ou "dono", "atendente", etc.
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}

package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.EmprestimoController;
import dao.EmprestimoDAO;
import dao.SistemaDAO;
import java.awt.Font;

public class ViewEmprestimo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private EmprestimoController controller;

    private JTextField textidOuCpfCliente;
    private JTextField textIdTitulo;

    private JComboBox<String> idCpfCliente;
    private JComboBox<String> livroMidia;
    private JComboBox<String> idOuTitulo;
    private JButton btnEmprestar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewEmprestimo frame = new ViewEmprestimo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewEmprestimo() {
    	SistemaDAO dao = new SistemaDAO();	
		dao.criarTabela();
		if (dao.carregar() != null) {
			dao.carregar();
		}
    	EmprestimoDAO emprestimodao = new EmprestimoDAO();
    	emprestimodao.criarTabela();
        setTitle("Tela de Empréstimo");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel idCpfLabel = new JLabel("Forneça o ID/CPF do cliente");
        idCpfLabel.setBounds(20, 32, 200, 29);
        contentPane.add(idCpfLabel);

        idCpfCliente = new JComboBox<>();
        idCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        idCpfCliente.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "CPF" }));
        idCpfCliente.setBounds(181, 28, 50, 30);
        contentPane.add(idCpfCliente);

        textidOuCpfCliente = new JTextField();
        textidOuCpfCliente.setBounds(254, 34, 138, 25);
        contentPane.add(textidOuCpfCliente);
        textidOuCpfCliente.setColumns(10);

        JLabel tipoItem = new JLabel("Item a emprestar");
        tipoItem.setBounds(30, 72, 138, 30);
        contentPane.add(tipoItem);

        livroMidia = new JComboBox<>();
        livroMidia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        livroMidia.setModel(new DefaultComboBoxModel(new String[] {"Livro", "Mídia"}));
        livroMidia.setBounds(181, 72, 100, 30);
        contentPane.add(livroMidia);

        JLabel idTitulo = new JLabel("Informe o ID/Título");
        idTitulo.setBounds(20, 113, 132, 29);
        contentPane.add(idTitulo);

        idOuTitulo = new JComboBox<>();
        idOuTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        idOuTitulo.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Título" }));
        idOuTitulo.setBounds(181, 116, 100, 30);
        contentPane.add(idOuTitulo);

        textIdTitulo = new JTextField();
        textIdTitulo.setColumns(10);
        textIdTitulo.setBounds(294, 114, 138, 27);
        contentPane.add(textIdTitulo);

        btnEmprestar = new JButton("Emprestar");
        btnEmprestar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEmprestar.setBounds(181, 189, 114, 36);
        contentPane.add(btnEmprestar);

        
        controller = new EmprestimoController(this);
        btnEmprestar.addActionListener(e -> controller.realizarEmprestimo());
    }

    // Getters
    public JButton getBtnEmprestar() {
        return btnEmprestar;
    }

    public JComboBox<String> getIdCpfCliente() {
        return idCpfCliente;
    }

    public JTextField getTextidOuCpfCliente() {
        return textidOuCpfCliente;
    }

    public JComboBox<String> getLivroMidia() {
        return livroMidia;
    }

    public JComboBox<String> getIdOuTitulo() {
        return idOuTitulo;
    }

    public JTextField getTextIdTitulo() {
        return textIdTitulo;
    }
    
    public void limparCampos() {
        textidOuCpfCliente.setText("");
        textIdTitulo.setText("");
        idCpfCliente.setSelectedIndex(0); // volta para "ID"
        livroMidia.setSelectedIndex(0);   // volta para "Livro"
        idOuTitulo.setSelectedIndex(0);   // volta para "ID"
    }
}

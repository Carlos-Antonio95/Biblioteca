package view;

import javax.swing.*;
import controller.DevolucaoEmprestimoController;
import java.awt.*;

public class ViewDevolucaoEmprestimo extends JFrame {

    private JPanel contentPane;
    private JTextField textIdEmprestimo;
    private JTextField textDia;
    private JTextField textMes;
    private JTextField textAno;
    private JButton btnDevolver;

    public ViewDevolucaoEmprestimo() {
        setTitle("Devolver Empréstimo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblIdEmprestimo = new JLabel("ID do Empréstimo:");
        lblIdEmprestimo.setBounds(30, 30, 150, 25);
        contentPane.add(lblIdEmprestimo);

        textIdEmprestimo = new JTextField();
        textIdEmprestimo.setBounds(180, 30, 150, 25);
        contentPane.add(textIdEmprestimo);

        JLabel lblDataDevolucao = new JLabel("Data de Devolução:");
        lblDataDevolucao.setBounds(30, 70, 150, 25);
        contentPane.add(lblDataDevolucao);

        textDia = new JTextField();
        textDia.setBounds(180, 70, 40, 25);
        contentPane.add(textDia);

        textMes = new JTextField();
        textMes.setBounds(230, 70, 40, 25);
        contentPane.add(textMes);

        textAno = new JTextField();
        textAno.setBounds(280, 70, 60, 25);
        contentPane.add(textAno);

        btnDevolver = new JButton("Devolver");
        btnDevolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDevolver.setBounds(140, 150, 120, 35);
        contentPane.add(btnDevolver);

        // Controller
        DevolucaoEmprestimoController controller = new DevolucaoEmprestimoController(this);
        btnDevolver.addActionListener(e -> controller.realizarDevolucao());
    }

    // Getters
    public JTextField getTextIdEmprestimo() {
        return textIdEmprestimo;
    }

    public JTextField getTextDia() {
        return textDia;
    }

    public JTextField getTextMes() {
        return textMes;
    }

    public JTextField getTextAno() {
        return textAno;
    }
    
    public void limparCampos() {
        textIdEmprestimo.setText("");
        textDia.setText("");
        textMes.setText("");
        textAno.setText("");
    }

}

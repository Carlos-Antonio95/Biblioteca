package view;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AtualizaController;
import java.awt.Font;

public class ViewAtualizarEmprestimo extends JFrame {

    private JTextField idField;
    private JTextField diaField;
    private JTextField mesField;
    private JTextField anoField;

    public ViewAtualizarEmprestimo() {
        setTitle("Atualizar Empréstimo");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);

        JLabel label = new JLabel("ID do Empréstimo:");
        label.setBounds(20, 3, 239, 46);
        panel.add(label);
        idField = new JTextField();
        idField.setBounds(244, 3, 239, 46);
        panel.add(idField);

        JLabel label_1 = new JLabel("Dia da Devolução:");
        label_1.setBounds(20, 54, 239, 46);
        panel.add(label_1);
        diaField = new JTextField();
        diaField.setBounds(244, 54, 239, 46);
        panel.add(diaField);

        JLabel label_2 = new JLabel("Mês da Devolução:");
        label_2.setBounds(20, 105, 239, 46);
        panel.add(label_2);
        mesField = new JTextField();
        mesField.setBounds(244, 105, 239, 46);
        panel.add(mesField);

        JLabel label_3 = new JLabel("Ano da Devolução:");
        label_3.setBounds(20, 156, 239, 46);
        panel.add(label_3);
        anoField = new JTextField();
        anoField.setBounds(244, 156, 239, 46);
        panel.add(anoField);

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        atualizarButton.setBounds(319, 230, 154, 61);
        atualizarButton.addActionListener(e -> {
            AtualizaController controller = new AtualizaController();
            controller.atualizarEmprestimo(this);
        });
        panel.add(atualizarButton);

        getContentPane().add(panel);
    }

    public String getId() {
        return idField.getText();
    }

    public String getDia() {
        return diaField.getText();
    }

    public String getMes() {
        return mesField.getText();
    }

    public String getAno() {
        return anoField.getText();
    }
}

package view;

import controller.RemocaoController;
import exception.ExceptionSQL;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ViewRemocao extends JFrame {

    private JComboBox<String> comboTipo;
    private JTextField textIdOuCpf;
    private JButton btnRemover;

    public ViewRemocao() {
        setTitle("Remoção de Registros");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTipo = new JLabel("Tipo de Remoção:");
        lblTipo.setBounds(30, 30, 120, 25);
        getContentPane().add(lblTipo);

        comboTipo = new JComboBox<>(new String[]{
            "Livro", "Mídia", "Cliente", "Empréstimo"
        });
        comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comboTipo.setBounds(160, 30, 180, 25);
        getContentPane().add(comboTipo);

        JLabel lblIdCpf = new JLabel("ID ou CPF:");
        lblIdCpf.setBounds(30, 70, 120, 25);
        getContentPane().add(lblIdCpf);

        textIdOuCpf = new JTextField();
        textIdOuCpf.setBounds(160, 70, 180, 25);
        getContentPane().add(textIdOuCpf);

        btnRemover = new JButton("Remover");
        btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemover.setBounds(120, 120, 140, 35);
        getContentPane().add(btnRemover);

        // Controller
        RemocaoController controller = new RemocaoController(this);
        btnRemover.addActionListener(e -> {
            try {
                controller.removerRegistro();
            } catch (ExceptionSQL e1) {
                JOptionPane.showMessageDialog(this, "Erro no banco de dados: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public JComboBox<String> getComboTipo() {
        return comboTipo;
    }

    public JTextField getTextIdOuCpf() {
        return textIdOuCpf;
    }

    public void limparCampos() {
        comboTipo.setSelectedIndex(0);
        textIdOuCpf.setText("");
    }
}

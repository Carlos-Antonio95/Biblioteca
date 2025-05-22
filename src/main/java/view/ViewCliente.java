package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ClienteController;
import java.awt.Font;

public class ViewCliente extends JFrame {

    private JTextField txtNome, txtCpf, txtEndereco, txtTelefone, txtEmail;
    private ClienteController controller;

    public ViewCliente() {
        setTitle("Cadastro de Cliente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        controller = new ClienteController(this); // <- passa a view para o controller

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        getContentPane().add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 60, 80, 25);
        getContentPane().add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(100, 60, 200, 25);
        getContentPane().add(txtCpf);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 100, 80, 25);
        getContentPane().add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(100, 100, 200, 25);
        getContentPane().add(txtEndereco);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 140, 80, 25);
        getContentPane().add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(100, 140, 200, 25);
        getContentPane().add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 180, 80, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 180, 200, 25);
        getContentPane().add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSalvar.setBounds(102, 229, 150, 40);
        getContentPane().add(btnSalvar);

        // Ação do botão chama o método no controller
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.salvarCliente();
            }
        });
    }

    // Getters para os campos
    public String getNome() {
        return txtNome.getText().trim();
    }

    public String getCpf() {
        return txtCpf.getText().trim();
    }

    public String getEndereco() {
        return txtEndereco.getText().trim();
    }

    public String getTelefone() {
        return txtTelefone.getText().trim();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }
    
    public void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
    }
}

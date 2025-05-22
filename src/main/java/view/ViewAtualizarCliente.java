
package view;

import javax.swing.*;
import java.awt.*;
import controller.AtualizaController;
import exception.ExceptionsPadrao;
import model.Cliente;

public class ViewAtualizarCliente extends JFrame {

    private JTextField txtId, txtNome, txtEndereco, txtTelefone, txtEmail;
    private AtualizaController controller;
    private JTextField txtCpf;

    public ViewAtualizarCliente(AtualizaController controller) {
        this.controller = controller;
        setTitle("Atualizar Cliente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("ID:");
        label.setBounds(20, 10, 80, 30);
        getContentPane().add(label);
        txtId = new JTextField();
        txtId.setBounds(160, 10, 298, 30);
        getContentPane().add(txtId);

        JLabel label_1 = new JLabel("Nome:");
        label_1.setBounds(20, 50, 80, 30);
        getContentPane().add(label_1);
        txtNome = new JTextField();
        txtNome.setBounds(160, 50, 298, 30);
        getContentPane().add(txtNome);

        JLabel label_2 = new JLabel("CPF:");
        label_2.setBounds(20, 90, 80, 30);
        getContentPane().add(label_2);
        txtCpf = new JTextField();
        txtCpf.setBounds(160, 90, 298, 30);
        getContentPane().add(txtCpf);
        
        JLabel label_3 = new JLabel("Endereço:");
        label_3.setBounds(20, 130, 80, 30);
        getContentPane().add(label_3);
        txtEndereco = new JTextField();
        txtEndereco.setBounds(160, 130, 298, 30);
        getContentPane().add(txtEndereco);

        JLabel label_4 = new JLabel("Telefone:");
        label_4.setBounds(20, 170, 80, 30);
        getContentPane().add(label_4);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(160, 170, 298, 30);
        getContentPane().add(txtTelefone);

        JLabel label_5 = new JLabel("Email:");
        label_5.setBounds(20, 210, 80, 30);
        getContentPane().add(label_5);
        txtEmail = new JTextField();
        txtEmail.setBounds(160, 210, 298, 30);
        getContentPane().add(txtEmail);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAtualizar.setBounds(160, 259, 160, 50);
        btnAtualizar.addActionListener(e -> atualizarCliente());
        getContentPane().add(btnAtualizar);
    }

    private void atualizarCliente() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Cliente cliente = new Cliente(id, txtNome.getText(), txtCpf.getText(), txtEndereco.getText(), txtTelefone.getText(), txtEmail.getText());
            controller.atualizar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar cliente: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
        }
    }
  
    }

// ViewAtualizarLivro.java
package view;

import javax.swing.*;
import java.awt.*;
import controller.AtualizaController;

public class ViewAtualizarLivro extends JFrame {

    private JTextField txtId, txtTitulo, txtDia, txtMes, txtAno, txtExemplares, txtCategoria, txtAutor;
    private AtualizaController controller;

    public ViewAtualizarLivro(AtualizaController controller) {
        this.controller = controller;
        setTitle("Atualizar Livro");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("ID:");
        label.setBounds(20, 3, 239, 46);
        getContentPane().add(label);
        txtId = new JTextField();
        txtId.setBounds(244, 3, 239, 46);
        getContentPane().add(txtId);

        JLabel label_1 = new JLabel("Título:");
        label_1.setBounds(20, 54, 239, 46);
        getContentPane().add(label_1);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(244, 54, 239, 46);
        getContentPane().add(txtTitulo);

        JLabel label_2 = new JLabel("Dia Publicação:");
        label_2.setBounds(20, 105, 239, 46);
        getContentPane().add(label_2);
        txtDia = new JTextField();
        txtDia.setBounds(244, 105, 239, 46);
        getContentPane().add(txtDia);

        JLabel label_3 = new JLabel("Mês Publicação:");
        label_3.setBounds(20, 156, 239, 46);
        getContentPane().add(label_3);
        txtMes = new JTextField();
        txtMes.setBounds(244, 156, 239, 46);
        getContentPane().add(txtMes);

        JLabel label_4 = new JLabel("Ano Publicação:");
        label_4.setBounds(20, 207, 239, 46);
        getContentPane().add(label_4);
        txtAno = new JTextField();
        txtAno.setBounds(244, 207, 239, 46);
        getContentPane().add(txtAno);

        JLabel label_5 = new JLabel("Exemplares Disponíveis:");
        label_5.setBounds(20, 258, 239, 46);
        getContentPane().add(label_5);
        txtExemplares = new JTextField();
        txtExemplares.setBounds(244, 258, 239, 46);
        getContentPane().add(txtExemplares);

        JLabel label_6 = new JLabel("Categoria:");
        label_6.setBounds(20, 309, 239, 46);
        getContentPane().add(label_6);
        txtCategoria = new JTextField();
        txtCategoria.setBounds(244, 309, 239, 46);
        getContentPane().add(txtCategoria);

        JLabel label_7 = new JLabel("Autor:");
        label_7.setBounds(20, 360, 239, 46);
        getContentPane().add(label_7);
        txtAutor = new JTextField();
        txtAutor.setBounds(244, 360, 239, 46);
        getContentPane().add(txtAutor);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAtualizar.setBounds(244, 410, 239, 46);
        btnAtualizar.addActionListener(e -> controller.atualizarLivro(this));
        getContentPane().add(btnAtualizar);
    }

    // Getters
    public String getId() { return txtId.getText(); }
    public String getTitulo() { return txtTitulo.getText(); }
    public String getDia() { return txtDia.getText(); }
    public String getMes() { return txtMes.getText(); }
    public String getAno() { return txtAno.getText(); }
    public String getExemplares() { return txtExemplares.getText(); }
    public String getCategoria() { return txtCategoria.getText(); }
    public String getAutor() { return txtAutor.getText(); }
}

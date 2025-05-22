package view;

import javax.swing.*;
import java.awt.*;

public class ViewListagem extends JFrame {
    private JButton btnClientes, btnLivros, btnMidias, btnEmprestimos, btnVoltar;

    public ViewListagem() {
        setTitle("Listagens");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(6, 1, 10, 10));

        btnClientes = new JButton("Listar Clientes");
        btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLivros = new JButton("Listar Livros");
        btnLivros.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnMidias = new JButton("Listar Mídias");
        btnMidias.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEmprestimos = new JButton("Listar Empréstimos");
        btnEmprestimos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));

        getContentPane().add(btnClientes);
        getContentPane().add(btnLivros);
        getContentPane().add(btnMidias);
        getContentPane().add(btnEmprestimos);
        getContentPane().add(btnVoltar);
    }

    public JButton getBtnClientes() { return btnClientes; }
    public JButton getBtnLivros() { return btnLivros; }
    public JButton getBtnMidias() { return btnMidias; }
    public JButton getBtnEmprestimos() { return btnEmprestimos; }
    public JButton getBtnVoltar() { return btnVoltar; }
}

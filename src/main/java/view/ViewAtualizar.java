// Complemento da classe ViewAtualizar com abertura de telas específicas e controller associado

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.AtualizaController;

public class ViewAtualizar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private AtualizaController controller;

    public ViewAtualizar() {
        setTitle("Atualizar Dados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAtualizarCliente = new JButton("Atualizar Clientes");
        btnAtualizarCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JButton btnAtualizarLivro = new JButton("Atualizar Livro");
        btnAtualizarLivro.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JButton btnAtualizarMidia = new JButton("Atualizar Mídia");
        btnAtualizarMidia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JButton btnAtualizarEmprestimo = new JButton("Atualizar Empréstimos");
        btnAtualizarEmprestimo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JButton btnVoltaMenu = new JButton("Voltar ao Menu Anterior");
        btnVoltaMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));

        getContentPane().add(btnAtualizarCliente);
        getContentPane().add(btnAtualizarLivro);
        getContentPane().add(btnAtualizarMidia);
        getContentPane().add(btnAtualizarEmprestimo);
        getContentPane().add(btnVoltaMenu);

        controller = new AtualizaController();

        btnAtualizarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAtualizarCliente(controller).setVisible(true);
            }
        });

        btnAtualizarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAtualizarLivro(controller).setVisible(true);
            }
        });

        btnAtualizarMidia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAtualizarMidia().setVisible(true);
            }
        });

        btnAtualizarEmprestimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewAtualizarEmprestimo().setVisible(true);
            }
        });

        btnVoltaMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
            }
        });
    }
}

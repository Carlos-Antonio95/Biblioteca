package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RelatorioController;
import exception.ExceptionsPadrao;

public class ViewRelatorio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Labels para exibir os dados
    private JLabel lblTotalLivros;
    private JLabel lblTotalMidias;
    private JLabel lblTotalClientes;
    private JLabel lblTotalEmprestimos;
    private JLabel lblTotalMultas;
    private JLabel lblValorTotalMultas;

    private RelatorioController controller;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewRelatorio frame = new ViewRelatorio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewRelatorio() throws ExceptionsPadrao {
        setTitle("Relatórios do Sistema");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblTotalLivros = new JLabel("Livros cadastrados: ");
        lblTotalLivros.setBounds(20, 30, 400, 20);
        contentPane.add(lblTotalLivros);

        lblTotalMidias = new JLabel("Mídias cadastradas: ");
        lblTotalMidias.setBounds(20, 60, 400, 20);
        contentPane.add(lblTotalMidias);

        lblTotalClientes = new JLabel("Clientes cadastrados: ");
        lblTotalClientes.setBounds(20, 90, 400, 20);
        contentPane.add(lblTotalClientes);

        lblTotalEmprestimos = new JLabel("Empréstimos realizados: ");
        lblTotalEmprestimos.setBounds(20, 120, 400, 20);
        contentPane.add(lblTotalEmprestimos);

        lblTotalMultas = new JLabel("Total de multas: ");
        lblTotalMultas.setBounds(20, 150, 400, 20);
        contentPane.add(lblTotalMultas);

        lblValorTotalMultas = new JLabel("Valor total das multas: ");
        lblValorTotalMultas.setBounds(20, 180, 400, 20);
        contentPane.add(lblValorTotalMultas);

        controller = new RelatorioController(this);
        controller.carregarDados(); // carrega os dados ao abrir a janela
    }

    // Getters para que o controller possa atualizar os valores
    public JLabel getLblTotalLivros() {
        return lblTotalLivros;
    }

    public JLabel getLblTotalMidias() {
        return lblTotalMidias;
    }

    public JLabel getLblTotalClientes() {
        return lblTotalClientes;
    }

    public JLabel getLblTotalEmprestimos() {
        return lblTotalEmprestimos;
    }

    public JLabel getLblTotalMultas() {
        return lblTotalMultas;
    }

    public JLabel getLblValorTotalMultas() {
        return lblValorTotalMultas;
    }
}

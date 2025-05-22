package controller;

import dao.LivroDAO;
import dao.MidiaDAO;
import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.MultaDAO;
import exception.ExceptionsPadrao;
import view.ViewCliente;
import view.ViewRelatorio;

public class RelatorioController {

    private ViewRelatorio view;

    public RelatorioController(ViewRelatorio view) {
        this.view = view;
    }

    public void carregarDados() throws ExceptionsPadrao {
    	try {
        LivroDAO livroDAO = new LivroDAO();
        MidiaDAO midiaDAO = new MidiaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        MultaDAO multaDAO = new MultaDAO();

        int totalLivros = livroDAO.contarTodos();
        int totalMidias = midiaDAO.contarTodos();
        int totalClientes = clienteDAO.contarTodos();
        int totalEmprestimos = emprestimoDAO.contarTodos();
        int totalMultas = multaDAO.contarMultas();
        double totalValorMultas = multaDAO.contarMultasValor();

        // Atualiza os labels da view
        view.getLblTotalLivros().setText("Livros cadastrados: " + totalLivros);
        view.getLblTotalMidias().setText("Mídias cadastradas: " + totalMidias);
        view.getLblTotalClientes().setText("Clientes cadastrados: " + totalClientes);
        view.getLblTotalEmprestimos().setText("Empréstimos realizados: " + totalEmprestimos);
        view.getLblTotalMultas().setText("Total de multas: " + totalMultas);
        view.getLblValorTotalMultas().setText("Valor total das multas: R$ " + String.format("%.2f", totalValorMultas));
    	 } catch (Exception e) {
    	        javax.swing.JOptionPane.showMessageDialog(null,
    	            "Erro ao carregar dados do relatório:\n" + e.getMessage(),
    	            "Erro",
    	            javax.swing.JOptionPane.ERROR_MESSAGE);
    	    }
    	}
  
    
    
    public void abrirTelaRelatorio() {
        try {
            ViewRelatorio viewRelatorio = new ViewRelatorio();
            viewRelatorio.setVisible(true);
        } catch (ExceptionsPadrao | RuntimeException e) {
            // Mostra a mensagem de erro na tela para o usuário
            javax.swing.JOptionPane.showMessageDialog(null,
                "Erro ao abrir relatório:\n" + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}

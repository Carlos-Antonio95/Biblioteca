package controller;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import model.Cliente;
import model.Emprestimo;
import model.Livro;
import model.Midia;
import view.ViewAtualizar;
import view.ViewAtualizarEmprestimo;
import view.ViewAtualizarLivro;
import view.ViewAtualizarMidia;

public class AtualizaController {

    private ClienteDAO clienteDAO;
    private MidiaDAO midiaDAO;

    private LivroDAO livroDAO;
	private EmprestimoDAO emprestimoDAO;

    public AtualizaController() {
        this.clienteDAO = new ClienteDAO();
        this.midiaDAO = new MidiaDAO();
        this.livroDAO = new LivroDAO(); // instanciar aqui
        this.emprestimoDAO = new EmprestimoDAO();
    }


    // Atualizar cliente
    public void atualizar(Cliente cliente) {
        try {
            clienteDAO.atualizar(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }
    
    public void atualizarEmprestimo(ViewAtualizarEmprestimo view) {
        try {
            int id = Integer.parseInt(view.getId());

            LocalDate dataDevolucao = null;

            String diaStr = view.getDia();
            String mesStr = view.getMes();
            String anoStr = view.getAno();

            if (!diaStr.isBlank() && !mesStr.isBlank() && !anoStr.isBlank()) {
                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);
                int ano = Integer.parseInt(anoStr);
                dataDevolucao = LocalDate.of(ano, mes, dia);
            }

            emprestimoDAO.atualizarDataDevolucao(id, dataDevolucao);

            JOptionPane.showMessageDialog(null, "Empréstimo atualizado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: ID e data devem conter apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(null, "Data inválida. Verifique os valores de dia, mês e ano.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar empréstimo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }




public void atualizarLivro(ViewAtualizarLivro view) {
    try {
        int id = Integer.parseInt(view.getId());
        String titulo = view.getTitulo();

        int dia = Integer.parseInt(view.getDia());
        int mes = Integer.parseInt(view.getMes());
        int ano = Integer.parseInt(view.getAno());
        LocalDate data = LocalDate.of(ano, mes, dia);

        int exemplares = Integer.parseInt(view.getExemplares());
        String categoria = view.getCategoria();
        String autor = view.getAutor();

        Livro livro = new Livro(id, titulo, data, exemplares, categoria, autor);
        // Adicione o DAO correspondente, ex:
        livroDAO.atualizarLivroPorId(livro);

        JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erro: informe apenas números nos campos de data e exemplares.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
    } catch (DateTimeException e) {
        JOptionPane.showMessageDialog(null, "Data inválida. Verifique o dia, mês e ano informados.", "Erro de data", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    public void atualizarMidia(ViewAtualizarMidia view) {
        try {
            int id = Integer.parseInt(view.getId());
            String titulo = view.getTitulo();

            int dia = Integer.parseInt(view.getDia());
            int mes = Integer.parseInt(view.getMes());
            int ano = Integer.parseInt(view.getAno());
            LocalDate data = LocalDate.of(ano, mes, dia);

            int exemplares = Integer.parseInt(view.getExemplares());
            String categoria = view.getCategoria();
            String diretor = view.getDiretor();

            Midia midia = new Midia(id, titulo, data, exemplares, categoria, diretor);
            midiaDAO.atualizarMidia(midia);

            JOptionPane.showMessageDialog(null, "Mídia atualizada com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: informe apenas números nos campos de data e exemplares.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(null, "Data inválida. Verifique o dia, mês e ano informados.", "Erro de data", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar mídia: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirTelaAtualizar() {
        ViewAtualizar tela = new ViewAtualizar();
        tela.setVisible(true);
    }
	
}

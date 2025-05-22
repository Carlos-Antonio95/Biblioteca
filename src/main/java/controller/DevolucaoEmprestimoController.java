package controller;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import model.*;

import view.ViewDevolucaoEmprestimo;
import view.ViewEmprestimo;

import javax.swing.*;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.sql.SQLException;

public class DevolucaoEmprestimoController {

    private ViewDevolucaoEmprestimo view;

    public DevolucaoEmprestimoController(ViewDevolucaoEmprestimo view) {
        this.view = view;
    }
    
    public static void abrirTelaDevolucao() {
        ViewDevolucaoEmprestimo viewDevolucaoEmprestimo = new ViewDevolucaoEmprestimo();
        viewDevolucaoEmprestimo.setVisible(true);
    }

    public void realizarDevolucao() {
        try {
            int idEmprestimo = Integer.parseInt(view.getTextIdEmprestimo().getText().trim());
            int dia = Integer.parseInt(view.getTextDia().getText().trim());
            int mes = Integer.parseInt(view.getTextMes().getText().trim());
            int ano = Integer.parseInt(view.getTextAno().getText().trim());
 

            LocalDate dataDev;
            try {
                dataDev = LocalDate.of(ano, mes, dia);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(view, "Data inválida.");
                return;
            }

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            Emprestimo emprestimo = emprestimoDAO.buscarPorId(idEmprestimo);

            if (emprestimo == null) {
                JOptionPane.showMessageDialog(view, "Empréstimo não encontrado.");
                return;
            }
            Cliente cliente = emprestimo.getCliente();
            boolean gambiarraDevolver = false;

            if (emprestimo.getLivro() != null) {
                Livro livro = emprestimo.getLivro();

                if (livro.getExemplaresDisponiveis() == 0) {
                    livro.devolver();
                    gambiarraDevolver = true;
                }
                
                Emprestimo realizaEmprestimo = new Emprestimo(cliente, livro, dataDev);
                Biblioteca.getInstancia().adicionarEmprestimoLivroLista(emprestimo);
                int novaQuantidade =  livro.getExemplaresDisponiveis() + 1;
                livro.setExemplaresDisponiveis(novaQuantidade); 
                
  
                if (gambiarraDevolver) {
                    livro.emprestar();
                    gambiarraDevolver = false;
                }
                
                

                if (emprestimo.devolverItem(emprestimo.getCliente(), emprestimo, livro, dataDev)) {
                    double multa = emprestimo.calcularMulta(dataDev);
                    emprestimoDAO.atualizar(emprestimo);

                    LivroDAO livroDAO = new LivroDAO();
                    livroDAO.atualizarExemplaresId(livro.getExemplaresDisponiveis(), livro.getId());

                    if (multa > 0) {
                        JOptionPane.showMessageDialog(view, "Pague a multa de R$ " + multa);
                    }
                    JOptionPane.showMessageDialog(view, "Livro devolvido com sucesso!");
                }

            } else {
                Midia midia = emprestimo.getMidia();

                if (midia.getExemplaresDisponiveis() == 0) {
                    midia.devolver();
                    gambiarraDevolver = true;
                }

            	Emprestimo realizaEmprestimo = new Emprestimo(cliente, midia, dataDev);
            	Biblioteca.getInstancia().adicionarEmprestimoLivroLista(emprestimo);
            	int novaQuantidade = midia.getExemplaresDisponiveis() + 1;
            	midia.setExemplaresDisponiveis(novaQuantidade);
               
                if (gambiarraDevolver) {
                    midia.emprestar();
                    gambiarraDevolver = false;
                }
                
                

                if (emprestimo.devolverItem(emprestimo.getCliente(), emprestimo, midia, dataDev)) {
                    double multa = emprestimo.calcularMulta(dataDev);
                    emprestimoDAO.atualizar(emprestimo);

                    MidiaDAO midiaDAO = new MidiaDAO();
                    midiaDAO.atualizarExemplaresId(midia.getExemplaresDisponiveis(), midia.getId());

                    if (multa > 0) {
                        JOptionPane.showMessageDialog(view, "Pague a multa de R$ " + multa);
                    }
                    JOptionPane.showMessageDialog(view, "Mídia devolvida com sucesso!");
                }
            }
            
            view.limparCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos corretamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage());
        }
    }
}

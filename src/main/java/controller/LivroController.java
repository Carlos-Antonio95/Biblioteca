package controller;

import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.LivroDAO;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;
import model.Cliente;
import model.Livro;
import view.ViewCliente;
import view.ViewLivro;



public class LivroController {

    private ViewLivro view;

    public LivroController(ViewLivro view) {
        this.view = view;
    }

    public void salvarLivro() {
        try {
            String titulo = view.getTitulo();
            String itens = view.getItens();
            String dia = view.getTxtDia();
            String categoria = view.getCategoria();
            String autor = view.getAutor();
            String mes = view.getTextMes();
            String ano = view.getTextAno();

            int diaAtualizado = Integer.parseInt(dia);
            int mesAtualizado = Integer.parseInt(mes);
            int anoAtualizado = Integer.parseInt(ano);
            int quantidadeExemplares = Integer.parseInt(itens);

            LocalDate data = LocalDate.of(anoAtualizado, mesAtualizado, diaAtualizado);

            Livro livro = new Livro(titulo, data, quantidadeExemplares, categoria, autor);
            LivroDAO livroDAO = new LivroDAO();
            livroDAO.criarTabela();
            livroDAO.salvar(livro);

            JOptionPane.showMessageDialog(view, "Livro cadastrado com sucesso com ID: " + livro.getId());
            view.limparCampos1();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Erro: informe apenas números nos campos de data e quantidade.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(view, "Data inválida. Verifique o dia, mês e ano informados.", "Erro de data", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionSQL e) {
            JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void TelaLivro() {
	    ViewLivro viewLivro = new ViewLivro(); // Cria a tela de cadastro
	    viewLivro.setVisible(true);               // Exibe a tela
	}

		
            
    }

	


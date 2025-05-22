
package controller;

import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.MidiaDAO;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;
import model.Cliente;
import model.Midia;
import view.ViewCliente;
import view.ViewMidia;



public class MidiaController {

    private ViewMidia view;

    public MidiaController(ViewMidia view) {
        this.view = view;
    }
    
    public void salvarMidia()  {
    	 try {
    	        String titulo = view.getTitulo();
    	        String itens = view.getItens();
    	        String dia = view.getTxtDia();
    	        String categoria = view.getCategoria();
    	        String diretor = view.getDiretor();
    	        String mes = view.getTextMes();
    	        String ano = view.getTextAno();

    	        int diaAtualizado = Integer.parseInt(dia);
    	        int mesAtualizado = Integer.parseInt(mes);
    	        int anoAtualizado = Integer.parseInt(ano);
    	        int quantidadeExemplares = Integer.parseInt(itens);

    	        LocalDate data = LocalDate.of(anoAtualizado, mesAtualizado, diaAtualizado);

    	        Midia midia = new Midia(titulo, data, quantidadeExemplares, categoria, diretor);
    	        MidiaDAO midiaDAO = new MidiaDAO();
    	        midiaDAO.criarTabela();
    	        midiaDAO.salvar(midia);

    	        JOptionPane.showMessageDialog(view, "Mídia cadastrada com sucesso com ID: " + midia.getId());

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
    
    public void TelaMidia() {
	    ViewMidia viewMidia = new ViewMidia(); // Cria a tela de cadastro
	    viewMidia.setVisible(true);               // Exibe a tela
    }
    

    }
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import dao.MultaDAO;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;
import model.Cliente;
import model.Emprestimo;
import model.Livro;
import model.Midia;
import model.Biblioteca;
import view.ViewEmprestimo;

public class EmprestimoController {

    private ViewEmprestimo view;

    public EmprestimoController(ViewEmprestimo view) {
        this.view = view;
    }
        
    public static void abrirTelaEmprestimo() {
        ViewEmprestimo viewEmprestimo = new ViewEmprestimo();
        viewEmprestimo.setVisible(true);
    }

    public void realizarEmprestimo() {
        try {
            // Buscar cliente
            String tipoIdCpf = (String) view.getIdCpfCliente().getSelectedItem();
            String idCpfValue = view.getTextidOuCpfCliente().getText().trim();

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = null;

            if (tipoIdCpf.equals("CPF")) {
                cliente = clienteDAO.buscarPorCpf(idCpfValue);
            } else {
                cliente = clienteDAO.buscarPorId(Integer.parseInt(idCpfValue));
            }

            if (cliente == null) {
                JOptionPane.showMessageDialog(view, "Cliente não encontrado.");
                return;
            }

            // 2. Buscar item (livro ou mídia)
            String tipoItem = (String) view.getLivroMidia().getSelectedItem();
            String tipoBusca = (String) view.getIdOuTitulo().getSelectedItem();
            String valorBusca = view.getTextIdTitulo().getText().trim();

            Emprestimo emprestimo = null;

            if (tipoItem.equals("Livro")) {
                LivroDAO livroDAO = new LivroDAO();
                Livro livro = null;

                if (tipoBusca.equals("ID")) {
                    livro = livroDAO.buscarPorId(Integer.parseInt(valorBusca));
                } else {
                    livro = livroDAO.buscarPorTitulo(valorBusca);
                }

                if (livro == null) {
                    JOptionPane.showMessageDialog(view, "Livro não encontrado.");
                    return;
                }

                int novaQuant = livro.getExemplaresDisponiveis() - 1;
                emprestimo = new Emprestimo(cliente, livro, LocalDate.now());
                livroDAO.atualizarExemplaresId(novaQuant, livro.getId());
                livro.setExemplaresDisponiveis(novaQuant);

                
                Biblioteca.getInstancia().adicionarEmprestimoLivroLista(emprestimo);
                // 3. Salvar empréstimo
                EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
                MultaDAO  multadao = new MultaDAO();
                multadao.criarTabela();
                emprestimoDAO.criarTabela();
                emprestimoDAO.salvar(emprestimo);

                JOptionPane.showMessageDialog(view, "Empréstimo realizado com sucesso!\nID: " + emprestimo.getId());
                view.limparCampos();

            } else if (tipoItem.equals("Midia")) {
                MidiaDAO midiaDAO = new MidiaDAO();
                Midia midia = null;

                if (tipoBusca.equals("ID")) {
                    midia = midiaDAO.buscarPorId(Integer.parseInt(valorBusca));
                } else {
                    midia = midiaDAO.buscarPorTitulo(valorBusca);
                }

                if (midia == null) {
                    JOptionPane.showMessageDialog(view, "Mídia não encontrada.");
                    return;
                }

                int novaQuant = midia.getExemplaresDisponiveis() - 1;
                midiaDAO.atualizarExemplaresId(novaQuant, midia.getId());

                emprestimo = new Emprestimo(cliente, midia, LocalDate.now());
                Biblioteca.getInstancia().adicionarEmprestimoMidiaLista(emprestimo);
                // 3. Salvar empréstimo
                EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
                MultaDAO  multadao = new MultaDAO();
                multadao.criarTabela();
                emprestimoDAO.criarTabela();
                emprestimoDAO.salvar(emprestimo);

                JOptionPane.showMessageDialog(view, "Empréstimo realizado com sucesso!\nID: " + emprestimo.getId());
                view.limparCampos();
            }

           

        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionSQL e) {
            JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID inválido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar empréstimo:\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}

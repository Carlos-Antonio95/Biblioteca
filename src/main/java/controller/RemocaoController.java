package controller;

import dao.*;
import exception.ExceptionSQL;
import view.ViewEmprestimo;
import view.ViewRemocao;

import java.sql.SQLException;

import javax.swing.*;

public class RemocaoController {

    private ViewRemocao view;

    public RemocaoController(ViewRemocao view) {
        this.view = view;
    }

    public static void abrirTelaRemocao() {
        ViewRemocao viewRemocao = new ViewRemocao();
        viewRemocao.setVisible(true);
    }

    public void removerRegistro() throws SQLException {
        String tipo = (String) view.getComboTipo().getSelectedItem();
        String entrada = view.getTextIdOuCpf().getText().trim();

        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha o campo ID ou CPF.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            boolean sucesso = false;

            switch (tipo) {
                case "Livro":
                    int idLivro = Integer.parseInt(entrada);
                    sucesso = new LivroDAO().excluirPorId(idLivro);
                    break;

                case "Mídia":
                    int idMidia = Integer.parseInt(entrada);
                    sucesso = new MidiaDAO().excluirPorId(idMidia);
                    break;

                case "Cliente":
                    ClienteDAO clienteDAO = new ClienteDAO();
                    if (entrada.length() > 5) {
                        sucesso = clienteDAO.deletarPorCpf(entrada);
                    } else {
                        int idCliente = Integer.parseInt(entrada);
                        sucesso = clienteDAO.deletarPorId(idCliente);
                    }
                    break;

                case "Empréstimo":
                    int idEmp = Integer.parseInt(entrada);
                    sucesso = new EmprestimoDAO().deletar(idEmp);
                    break;
            }

            if (sucesso) {
                JOptionPane.showMessageDialog(view, tipo + " removido com sucesso!");
                view.limparCampos();
            } else {
                JOptionPane.showMessageDialog(view, tipo + " não encontrado.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Entrada inválida. Digite um número para ID ou um CPF válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionSQL e) {
            JOptionPane.showMessageDialog(view, "Erro ao remover: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

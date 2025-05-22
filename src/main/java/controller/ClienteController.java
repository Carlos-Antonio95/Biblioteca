package controller;

import dao.ClienteDAO;
import model.Cliente;
import exception.ExceptionsPadrao;
import exception.ExceptionSQL;
import view.ViewCliente;
import exception.ExceptionsPadrao;

import javax.swing.JOptionPane;

public class ClienteController {

    private ViewCliente view;

    public ClienteController(ViewCliente view) {
        this.view = view;
    }
    
    
    public void salvarCliente() {
        String nome = view.getNome();
        String cpf = view.getCpf();
        String endereco = view.getEndereco();
        String telefone = view.getTelefone();
        String email = view.getEmail();

        try {
            Cliente cliente = new Cliente(nome, cpf, endereco, telefone, email);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.criarTabela();
            clienteDAO.salvar(cliente);

            JOptionPane.showMessageDialog(view, "Cliente cadastrado com sucesso com ID: " + cliente.getId());
            
            view.limparCampos();

        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ExceptionSQL e) {
            JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

	public void abrirTelaCliente() {
	    ViewCliente viewCliente = new ViewCliente(); // Cria a tela de cadastro
	    viewCliente.setVisible(true);               // Exibe a tela
	}
}

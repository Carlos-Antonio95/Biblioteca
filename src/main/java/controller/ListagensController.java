package controller;


import javax.swing.*;

import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import exception.ExceptionsPadrao;
import model.Biblioteca;
import model.Cliente;
import model.Emprestimo;
import model.Livro;
import model.Midia;
import view.ViewListagem;

import java.awt.HeadlessException;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListagensController {
    private ViewListagem view;
    private Biblioteca biblioteca;

    public void TelaListagensController() {
    	view = new ViewListagem();
    	biblioteca = Biblioteca.getInstancia();

    	view.getBtnClientes().addActionListener(e -> {
    	    try {
    	        listarClientes();
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(view, "ID inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(view, "Erro inesperado ao listar clientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	        ex.printStackTrace();
    	    }
    	});

    	view.getBtnLivros().addActionListener(e -> {
    	    try {
    	        listarLivros();
    	    } catch (ExceptionsPadrao ex) {
    	        JOptionPane.showMessageDialog(view, "Erro ao listar livros: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(view, "ID inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(view, "Erro inesperado ao listar livros: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	        ex.printStackTrace();
    	    }
    	});

    	view.getBtnMidias().addActionListener(e -> {
    	    try {
    	        listarMidias();
    	    } catch (ExceptionsPadrao ex) {
    	        JOptionPane.showMessageDialog(view, "Erro ao listar mídias: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(view, "ID inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(view, "Erro inesperado ao listar mídias: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	        ex.printStackTrace();
    	    }
    	});

    	view.getBtnEmprestimos().addActionListener(e -> {
    	    try {
    	        listarEmprestimos();
    	    } catch (ExceptionsPadrao ex) {
    	        JOptionPane.showMessageDialog(view, "Erro ao listar empréstimos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (SQLException ex) {
    	        JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(view, "Erro inesperado ao listar empréstimos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	        ex.printStackTrace();
    	    }
    	});

        view.getBtnVoltar().addActionListener(e -> view.dispose());

        view.setVisible(true);
    }
    private void listarClientes() {
        try {
            ClienteDAO dao = new ClienteDAO();
            dao.criarTabela();
            String[] opcoes = {"Por ID", "Por CPF", "Todos"};
            int escolha = JOptionPane.showOptionDialog(view, "Escolha a forma de listagem:",
                    "Listar Clientes", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0 -> {
                	//Emprestimo emprestimo = null;
                	
                    String input = JOptionPane.showInputDialog("Digite o ID do cliente:");
                    int id = Integer.parseInt(input);
                    Cliente cliente = dao.buscarPorId(id);
                  
                    
                    
                    if (cliente != null) {
                      
                    	EmprestimoDAO emprestimodao = new EmprestimoDAO();
                        List<Emprestimo> listaemprestimo = new ArrayList<>();
                        listaemprestimo = null;
                        listaemprestimo = emprestimodao.listarTodos();
                        for (Emprestimo emprestimo : listaemprestimo) {
                            if (emprestimo != null && emprestimo.getCliente().getId() == cliente.getId() && emprestimo.getDataDevolucao() == null) {
                                if (emprestimo.getLivro() != null) {
                                    cliente.adicionarLivroEmprestado(emprestimo.getLivro());                       
            
                                   
                                } else {
                                    cliente.adicionarMidiaEmprestada(emprestimo.getMidia());                            
                                }
                            }
                        }
                    JOptionPane.showMessageDialog(view, cliente.toString());
            
                    
                    }else {
                        JOptionPane.showMessageDialog(view, "Cliente não encontrado.");
                    }
                }
                case 1 -> {
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                    Cliente cliente = dao.buscarPorCpf(cpf);

                    if (cliente != null) {
                        EmprestimoDAO emprestimodao = new EmprestimoDAO();                      
                        List<Emprestimo> listaemprestimo = new ArrayList<Emprestimo>();
                        listaemprestimo = null;
                        listaemprestimo =emprestimodao.listarTodos();
                        
                        for (Emprestimo emprestimo : listaemprestimo) {
                            if (emprestimo != null && 
                                emprestimo.getCliente().getCpf().equals(cliente.getCpf()) && 
                                emprestimo.getDataDevolucao() == null) {
                                
                                if (emprestimo.getLivro() != null) {
                                    cliente.adicionarLivroEmprestado(emprestimo.getLivro());
                                } else {
                                    cliente.adicionarMidiaEmprestada(emprestimo.getMidia());
                                }
                            }
                        }

                        JOptionPane.showMessageDialog(view, cliente.toString());
                    } else {
                        JOptionPane.showMessageDialog(view, "Cliente não encontrado.");
                    }
                }

                case 2 -> {
                    List<Cliente> clientes = dao.listarTodos();
                    EmprestimoDAO emprestimodao = new EmprestimoDAO();
                    
                    List<Emprestimo> listaemprestimo = new ArrayList<Emprestimo>();
                    listaemprestimo = null;
                    listaemprestimo = emprestimodao.listarTodos();

                    for (Cliente cliente : clientes) {
                        for (Emprestimo emprestimo : listaemprestimo) {
                            if (emprestimo != null &&
                                emprestimo.getCliente().getId() == cliente.getId() &&
                                emprestimo.getDataDevolucao() == null) {

                                if (emprestimo.getLivro() != null) {
                                    cliente.adicionarLivroEmprestado(emprestimo.getLivro());
                                } else {
                                    cliente.adicionarMidiaEmprestada(emprestimo.getMidia());
                                }
                            }
                        }
                    }

                    biblioteca.setListaClientes(clientes);
                    JOptionPane.showMessageDialog(view, biblioteca.getListaClientes().toString());
                }

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID inválido.");
        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void listarLivros() throws ExceptionsPadrao {
    	try {
        LivroDAO dao = new LivroDAO();
        dao.criarTabela();
        String[] opcoes = {"Por ID", "Por Título", "Todos"};
        int escolha = JOptionPane.showOptionDialog(view, "Escolha a forma de listagem:",
                "Listar Livros", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0 -> {
                String input = JOptionPane.showInputDialog("Digite o ID do livro:");
                try {
                    int id = Integer.parseInt(input);
                    Livro livro = dao.buscarPorId(id);
                    if (livro != null)
                        JOptionPane.showMessageDialog(view, livro.toString());
                    else
                        JOptionPane.showMessageDialog(view, "Livro não encontrado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "ID inválido.");
                }
            }
            case 1 -> {
                String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
                Livro livro = dao.buscarPorTitulo(titulo);
                if (livro != null)
                    JOptionPane.showMessageDialog(view, livro.toString());
                else
                    JOptionPane.showMessageDialog(view, "Livro não encontrado.");
            }
            case 2 -> {
                List<Livro> livros = dao.listarTodos();
                biblioteca.setListaLivros(livros);
                JOptionPane.showMessageDialog(view, biblioteca.getListaLivros().toString());
            	}
            }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "ID inválido.");
            } catch (ExceptionsPadrao e) {
                JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            
        
    }

    private void listarMidias() throws ExceptionsPadrao {
    	try {
        MidiaDAO dao = new MidiaDAO();
        dao.criarTabela();
        String[] opcoes = {"Por ID", "Por Título", "Todos"};
        int escolha = JOptionPane.showOptionDialog(view, "Escolha a forma de listagem:",
                "Listar Mídias", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0 -> {
                String input = JOptionPane.showInputDialog("Digite o ID da mídia:");
                try {
                    int id = Integer.parseInt(input);
                    Midia midia = dao.buscarPorId(id);
                    if (midia != null)
                        JOptionPane.showMessageDialog(view, midia.toString());
                    else
                        JOptionPane.showMessageDialog(view, "Mídia não encontrada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "ID inválido.");
                }
            }
            case 1 -> {
                String titulo = JOptionPane.showInputDialog("Digite o título da mídia:");
                Midia midia = dao.buscarPorTitulo(titulo);
                if (midia != null)
                    JOptionPane.showMessageDialog(view, midia.toString());
                else
                    JOptionPane.showMessageDialog(view, "Mídia não encontrada.");
            }
            case 2 -> {
                List<Midia> midias = dao.listarTodos();
                biblioteca.setListaMidias(midias);
                JOptionPane.showMessageDialog(view,  biblioteca.getListaMidias().toString());
            }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID inválido.");
        } catch (ExceptionsPadrao e) {
            JOptionPane.showMessageDialog(view, "Erro de validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        }
    
    private void listarEmprestimos() throws SQLException, ExceptionsPadrao {
        EmprestimoDAO dao = new EmprestimoDAO();
        biblioteca.setListaEmprestimosLivrosMidia(dao.listarTodos());
        JOptionPane.showMessageDialog(view, biblioteca.getListaEmprestimosLivrosMidia().toString());
    }
}

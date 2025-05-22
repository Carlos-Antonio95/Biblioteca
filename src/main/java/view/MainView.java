package view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AtualizaController;
import controller.ClienteController;
import controller.ConfiguracoesController;
import controller.DevolucaoEmprestimoController;
import controller.EmprestimoController;
import controller.ListagensController;
import controller.LivroController;
import controller.MidiaController;
import controller.RelatorioController;
import controller.RemocaoController;
import dao.SistemaDAO;
import database.ConexaoSQLite;
import exception.ExceptionsPadrao;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Color;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainView frame = new MainView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainView() {
		

		try {
			Connection conn = ConexaoSQLite.conectar();
			ConexaoSQLite.criarTabelas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Erro de Conexão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	
		
		
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setTitle("Sistema Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 1000);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Botões do menu lateral
		JButton btnCliente = new JButton("Cadastrar Cliente");
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCliente.setBounds(10, 140, 200, 50);
		contentPane.add(btnCliente);
		btnCliente.addActionListener(e -> {
			ClienteController controller = new ClienteController(null);
			controller.abrirTelaCliente();
		});

		JButton btnLivro = new JButton("Cadastrar Livro");
		btnLivro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLivro.setBounds(10, 210, 200, 50);
		contentPane.add(btnLivro);
		btnLivro.addActionListener(e -> {
			LivroController controller = new LivroController(null);
			controller.TelaLivro();
		});

		JButton btnMidia = new JButton("Cadastrar Mídia");
		btnMidia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMidia.setBounds(10, 280, 200, 50);
		contentPane.add(btnMidia);
		btnMidia.addActionListener(e ->{
			MidiaController controller = new MidiaController(null);
			controller.TelaMidia();
		});

		JButton btnEmprestimo = new JButton("Realizar Empréstimo");
		btnEmprestimo.setBounds(10, 349, 200, 50);
		contentPane.add(btnEmprestimo);
			btnEmprestimo.addActionListener(e -> {
			    ViewEmprestimo viewEmprestimo = new ViewEmprestimo();
			    EmprestimoController controller = new EmprestimoController(viewEmprestimo);
			    viewEmprestimo.setVisible(true);
			});
		JButton btnDevolver = new JButton("Devolver Empréstimo");
		btnDevolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDevolver.setBounds(10, 419, 200, 50);
		contentPane.add(btnDevolver);
		btnDevolver.addActionListener(e -> {
		    ViewDevolucaoEmprestimo viewDevolucaoEmprestimo = new ViewDevolucaoEmprestimo();
		    DevolucaoEmprestimoController controller = new DevolucaoEmprestimoController(viewDevolucaoEmprestimo);
		    viewDevolucaoEmprestimo.setVisible(true);
		});
		
		JButton btnListagens = new JButton("Listagens");
		btnListagens.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListagens.setBounds(10, 489, 200, 50);
		contentPane.add(btnListagens);
		btnListagens.addActionListener(e -> {
		    ViewListagem viewListagem = new ViewListagem();
		    ListagensController controller = new ListagensController();
		    controller.TelaListagensController();
		});
		

		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemover.setBounds(10, 559, 200, 50);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(e -> {
		    ViewRemocao viewRemocao = new ViewRemocao();
		    RemocaoController controller = new RemocaoController(viewRemocao);
		    viewRemocao.setVisible(true);
		});

		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRelatorio.setBounds(10, 629, 200, 50);
		contentPane.add(btnRelatorio);
		btnRelatorio.addActionListener(e ->{
			RelatorioController controller = new RelatorioController(null);
			controller.abrirTelaRelatorio();
			
		});

		JButton btnConfiguracoes = new JButton("Configurações");
		btnConfiguracoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfiguracoes.setBounds(10, 769, 200, 50);
		contentPane.add(btnConfiguracoes);
		btnConfiguracoes.addActionListener(e ->{
			ConfiguracoesController controller = new ConfiguracoesController(null);
			controller.abrirTelaConfiguracoes();
			
		});

		// Mensagem de boas-vindas
		JTextPane txtpnSejaBemvindoAo = new JTextPane();
		txtpnSejaBemvindoAo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnSejaBemvindoAo.setText( "Seja bem-vindo ao Sistema de Gerenciamento da Biblioteca!\n\n" +
			    "Com este sistema, você poderá realizar de forma simples e eficiente:\n" +
			    "✔ Cadastro de clientes, livros e mídias\n" +
			    "✔ Empréstimos e devoluções com controle de datas e multas\n" +
			    "✔ Atualização de registros existentes\n" +
			    "✔ Visualização de relatórios com dados e estatísticas\n" +
			    "✔ Listagens completas para facilitar o gerenciamento\n\n" +
			    "Nosso objetivo é tornar sua administração mais prática, rápida e organizada.\n\n" +
			    "➡ Use o menu lateral para acessar as funcionalidades!");
		txtpnSejaBemvindoAo.setEditable(false);
		txtpnSejaBemvindoAo.setBackground(SystemColor.inactiveCaption);
		txtpnSejaBemvindoAo.setBounds(376, 197, 730, 300);
		contentPane.add(txtpnSejaBemvindoAo);

		// Título
		JTextPane txtpnSistemaDaBiblioteca = new JTextPane();
		txtpnSistemaDaBiblioteca.setBackground(SystemColor.inactiveCaption);
		txtpnSistemaDaBiblioteca.setForeground(SystemColor.desktop);
		txtpnSistemaDaBiblioteca.setFont(new Font("Tahoma", Font.BOLD, 36));
		txtpnSistemaDaBiblioteca.setText("Sistema da Biblioteca");
		txtpnSistemaDaBiblioteca.setEditable(false);
		txtpnSistemaDaBiblioteca.setBounds(450, 51, 500, 60);
		contentPane.add(txtpnSistemaDaBiblioteca);
		
		JButton btnAtualizar = new JButton("Atualizar Dados");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAtualizar.setBounds(10, 699, 200, 50);
		contentPane.add(btnAtualizar);
		btnAtualizar.addActionListener(e ->{
			AtualizaController controller = new AtualizaController();
			controller.abrirTelaAtualizar();
			
		});
		
	}
}

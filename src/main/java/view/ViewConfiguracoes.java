package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConfiguracoesController;
import controller.LivroController;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class ViewConfiguracoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ConfiguracoesController controller;
	private JTextField textTempoEmprestimo;
	private JTextField textQuantMaximaEmpresti;
	private JTextField textDefinirValorMulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConfiguracoes frame = new ViewConfiguracoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewConfiguracoes() {
	     setTitle("Configurações do Sistema");
	        setSize(500, 500);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(null);
	        
	        JLabel lblTempoEmprestimo = new JLabel("Definir tempo padrão empréstimo:");
	        lblTempoEmprestimo.setBounds(20, 34, 198, 24);
	        getContentPane().add(lblTempoEmprestimo);
	        
	        JLabel lblQuantMaxEmprestimo = new JLabel("Definir Quantidade Máxima empréstimos:");
	        lblQuantMaxEmprestimo.setBounds(20, 69, 264, 24);
	        getContentPane().add(lblQuantMaxEmprestimo);
	        
	        JLabel lblValorMulta = new JLabel("Definir valor multa:");
	        lblValorMulta.setBounds(20, 104, 206, 24);
	        getContentPane().add(lblValorMulta);
	        
	        JButton btnSalvar = new JButton("Salvar");
	        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        btnSalvar.setBounds(92, 167, 103, 36);
	        getContentPane().add(btnSalvar);
	        btnSalvar.addActionListener(e -> 
	        controller.salvarConfiguracoes());
	        
	        JButton btnLimpar = new JButton("Limpar");
	        btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        btnLimpar.setBounds(238, 167, 97, 36);
	        getContentPane().add(btnLimpar);
	        btnLimpar.addActionListener(e -> {
	            limpTela();
	        });
	        
	        textTempoEmprestimo = new JTextField();
	        textTempoEmprestimo.setBounds(307, 36, 86, 20);
	        getContentPane().add(textTempoEmprestimo);
	        textTempoEmprestimo.setColumns(10);
	        
	        textQuantMaximaEmpresti = new JTextField();
	        textQuantMaximaEmpresti.setColumns(10);
	        textQuantMaximaEmpresti.setBounds(307, 71, 86, 20);
	        getContentPane().add(textQuantMaximaEmpresti);
	        
	        textDefinirValorMulta = new JTextField();
	        textDefinirValorMulta.setColumns(10);
	        textDefinirValorMulta.setBounds(307, 106, 86, 20);
	        getContentPane().add(textDefinirValorMulta);

	        controller = new ConfiguracoesController(this); // <- passa a view para o controller
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ConfiguracoesController getController() {
		return controller;
	}

	public JTextField getTextTempoEmprestimo() {
		return textTempoEmprestimo;
	}

	public JTextField getTextQuantMaximaEmpresti() {
		return textQuantMaximaEmpresti;
	}

	public JTextField getTextDefinirValorMulta() {
		return textDefinirValorMulta;
	}
	
	public void limpTela() {
	    textTempoEmprestimo.setText("");
	    textQuantMaximaEmpresti.setText("");
	    textDefinirValorMulta.setText("");
	};

}

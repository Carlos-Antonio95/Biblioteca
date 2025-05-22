package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.LivroController;
import java.awt.Color;
import java.awt.Font;

public class ViewLivro extends JFrame {

    private JTextField txtTitulo, txtDia, txtItens, txtCategoria, txtAutor;
    private LivroController controller;
    private JTextField textMes;
    private JTextField textAno;

    public ViewLivro() {
        setTitle("Cadastro de Livros");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        controller = new LivroController(this); // <- passa a view para o controller

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 100, 25);
        getContentPane().add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(200, 20, 200, 25);
        getContentPane().add(txtTitulo);

        JLabel lblData = new JLabel("Data de Publicação:");
        lblData.setBounds(20, 60, 150, 25);
        getContentPane().add(lblData);

        txtDia = new JTextField();
        txtDia.setBounds(200, 60, 38, 25);
        getContentPane().add(txtDia);

        JLabel lblItens = new JLabel("Itens disponíveis:");
        lblItens.setBounds(20, 100, 150, 25);
        getContentPane().add(lblItens);

        txtItens = new JTextField();
        txtItens.setBounds(200, 100, 200, 25);
        getContentPane().add(txtItens);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 140, 80, 25);
        getContentPane().add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(200, 140, 200, 25);
        getContentPane().add(txtCategoria);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 180, 80, 25);
        getContentPane().add(lblAutor);

        txtAutor = new JTextField();
        txtAutor.setBounds(200, 180, 200, 25);
        getContentPane().add(txtAutor);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSalvar.setBounds(200, 229, 150, 40);
        getContentPane().add(btnSalvar);
        
        textMes = new JTextField();
        textMes.setBounds(274, 60, 38, 25);
        getContentPane().add(textMes);
        
        textAno = new JTextField();
        textAno.setBounds(343, 60, 58, 25);
        getContentPane().add(textAno);
        
        JLabel lblDia = new JLabel("Dia:");
        lblDia.setBounds(176, 65, 46, 14);
        getContentPane().add(lblDia);
        
        JLabel lblMes = new JLabel("Mês:");
        lblMes.setBounds(243, 65, 46, 14);
        getContentPane().add(lblMes);
        
        JLabel lblAno = new JLabel("Ano:");
        lblAno.setBounds(315, 65, 46, 14);
        getContentPane().add(lblAno);

        // Ação do botão chama o método no controller
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.salvarLivro();}
        });
    }

    // Getters para os campos
    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getAutor() {
    	return txtAutor.getText().trim();
    }
    
    public String getItens() {
        return txtItens.getText().trim();
    }

    public String getTxtDia() {
		return txtDia.getText().trim();
	}

	public String getTextMes() {
		return textMes.getText().trim();
	}

	public String getTextAno() {
		return textAno.getText().trim();
	}

	public String getCategoria() {
        return txtCategoria.getText().trim();
    }

    public String getAno() {
        return textAno.getText().trim();
    }
    
    public void limparCampos1() {
        txtTitulo.setText("");
        txtDia.setText("");
        txtItens.setText("");
        txtCategoria.setText("");
        txtAutor.setText("");
        textMes.setText("");
        txtDia.setText("");
        textAno.setText("");
        
    }
}

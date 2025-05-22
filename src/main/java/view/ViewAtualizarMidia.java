// ViewAtualizarMidia.java
package view;

import javax.swing.*;
import java.awt.*;
import controller.AtualizaController;

public class ViewAtualizarMidia extends JFrame {

    private JTextField idField;
    private JTextField tituloField;
    private JTextField dataPublicacaoField;
    private JTextField exemplaresField;
    private JTextField categoriaField;
    private JTextField diretorField;
    private JTextField diaField;
    private JTextField mesField;
    private JTextField anoField;

    public ViewAtualizarMidia() {
        setTitle("Atualizar Mídia");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);

        JLabel label = new JLabel("ID da Mídia:");
        label.setBounds(20, 3, 239, 46);
        panel.add(label);
        idField = new JTextField();
        idField.setBounds(244, 3, 239, 46);
        panel.add(idField);

        JLabel label_1 = new JLabel("Título:");
        label_1.setBounds(20, 54, 239, 46);
        panel.add(label_1);
        tituloField = new JTextField();
        tituloField.setBounds(244, 54, 239, 46);
        panel.add(tituloField);

        JLabel label_2 = new JLabel("Dia da Publicação:");
        label_2.setBounds(20, 105, 239, 46);
        panel.add(label_2);
        diaField = new JTextField();
        diaField.setBounds(244, 105, 239, 46);
        panel.add(diaField);

        JLabel label_3 = new JLabel("Mês da Publicação:");
        label_3.setBounds(20, 156, 239, 46);
        panel.add(label_3);
        mesField = new JTextField();
        mesField.setBounds(244, 156, 239, 46);
        panel.add(mesField);

        JLabel label_4 = new JLabel("Ano da Publicação:");
        label_4.setBounds(20, 207, 239, 46);
        panel.add(label_4);
        anoField = new JTextField();
        anoField.setBounds(244, 207, 239, 46);
        panel.add(anoField);

        JLabel label_5 = new JLabel("Exemplares Disponíveis:");
        label_5.setBounds(20, 258, 239, 46);
        panel.add(label_5);
        exemplaresField = new JTextField();
        exemplaresField.setBounds(244, 258, 239, 46);
        panel.add(exemplaresField);

        JLabel label_6 = new JLabel("Categoria:");
        label_6.setBounds(20, 309, 239, 46);
        panel.add(label_6);
        categoriaField = new JTextField();
        categoriaField.setBounds(244, 309, 239, 46);
        panel.add(categoriaField);

        JLabel label_7 = new JLabel("Diretor:");
        label_7.setBounds(20, 360, 239, 46);
        panel.add(label_7);
        diretorField = new JTextField();
        diretorField.setBounds(244, 360, 239, 46);
        panel.add(diretorField);

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setBounds(254, 410, 214, 46);
        atualizarButton.addActionListener(e -> {
            AtualizaController controller = new AtualizaController();
            controller.atualizarMidia(this);
        });
        panel.add(atualizarButton);

        getContentPane().add(panel);
    }

    public String getDia() { return diaField.getText(); }
    public String getMes() { return mesField.getText(); }
    public String getAno() { return anoField.getText(); }

    public String getId() { return idField.getText(); }
    public String getTitulo() { return tituloField.getText(); }
 
    public String getExemplares() { return exemplaresField.getText(); }
    public String getCategoria() { return categoriaField.getText(); }
    public String getDiretor() { return diretorField.getText(); }
}
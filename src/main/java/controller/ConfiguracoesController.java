package controller;

import javax.swing.JOptionPane;

import dao.SistemaDAO;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;
import model.Sistema;
import view.ViewConfiguracoes;

public class ConfiguracoesController {

    private ViewConfiguracoes view;

    public ConfiguracoesController(ViewConfiguracoes view) {
        this.view = view;
    }

    public void salvarConfiguracoes() {
        try {
            String tempoStr = view.getTextTempoEmprestimo().getText();
            String maxStr = view.getTextQuantMaximaEmpresti().getText();
            String multaStr = view.getTextDefinirValorMulta().getText();

            if (tempoStr.isEmpty() || maxStr.isEmpty() || multaStr.isEmpty()) {
                throw new IllegalStateException("Todos os campos devem ser preenchidos.");
            }

            int tempo = Integer.parseInt(tempoStr);
            int maxEmprestimos = Integer.parseInt(maxStr);
            double multa = Double.parseDouble(multaStr);

            Sistema sistema = new Sistema();

            sistema.definirTempoPadraoEmprestimo(tempo);
            sistema.definirMaximoEmprestimo(maxEmprestimos);
            sistema.definirValorMulta(multa);

            SistemaDAO sistemaDAO = new SistemaDAO();
            sistemaDAO.salvar(sistema);

            JOptionPane.showMessageDialog(view, "Configurações salvas com sucesso!");
            view.limpTela();

        } catch (ExceptionSQL e) {
            JOptionPane.showMessageDialog(view, "Erro no banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Erro: Informe valores numéricos válidos.");   
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(view, "Erro: " + e.getMessage());
        }
    }

    public void abrirTelaConfiguracoes() {
        ViewConfiguracoes viewConfiguracoes = new ViewConfiguracoes();
        viewConfiguracoes.setVisible(true);
    }
}

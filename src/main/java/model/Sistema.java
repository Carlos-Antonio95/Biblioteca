package model;

/**
 * Classe Sistema, responsavel pera regra de négocio, definindo regras como: tempo de emprestimo, valor multa e quantidade maxima de emprestimo por item
 */
public class Sistema implements Configuravel {

    private static Sistema instancia;
    private Integer tempoPadraoEmprestimo;
    private Integer maximoEmprestimos;
    private Double valorMulta;


    /**
     * Método estatico para criar uma instacia do sitema.
     * @return
     */
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public void setTempoPadraoEmprestimo(Integer diasMaximoEmprestimo) {
        if (diasMaximoEmprestimo == null || diasMaximoEmprestimo <= 0) {
            throw new IllegalArgumentException("O tempo padrão de empréstimo deve ser maior que zero.");
        }
        this.tempoPadraoEmprestimo = diasMaximoEmprestimo;
    }

    public Integer getTempoPadraoEmprestimo() {
        if (tempoPadraoEmprestimo == null) {
            throw new IllegalStateException("Tempo padrão de empréstimo não definido.");
        }
        return tempoPadraoEmprestimo;
    }

    public void setMaximoEmprestimos(Integer qtdTotalEmprestimo) {
        if (qtdTotalEmprestimo == null || qtdTotalEmprestimo <= 0) {
            throw new IllegalArgumentException("A quantidade máxima de empréstimo deve ser maior que zero.");
        }
        this.maximoEmprestimos = qtdTotalEmprestimo;
    }

    public Integer getMaximoEmprestimos() {
        if (maximoEmprestimos == null) {
            throw new IllegalStateException("Máximo de empréstimos não definido.");
        }
        return maximoEmprestimos;
    }

    public void setValorMulta(Double valorMulta) {
        if (valorMulta == null || valorMulta <= 0) {
            throw new IllegalArgumentException("O valor da multa deve ser maior que zero.");
        }
        this.valorMulta = valorMulta;
    }

    public Double getValorMulta() {
        if (valorMulta == null) {
            throw new IllegalStateException("Valor da multa não definido.");
        }
        return valorMulta;
    }

    @Override
    public Integer definirTempoPadraoEmprestimo(Integer dias) {
        this.setTempoPadraoEmprestimo(dias);
        return this.getTempoPadraoEmprestimo();
    }

    @Override
    public Integer definirMaximoEmprestimo(Integer qtdLivros) {
        this.setMaximoEmprestimos(qtdLivros);
        return this.getMaximoEmprestimos();
    }

    @Override
    public Double definirValorMulta(Double valor) {
        this.setValorMulta(valor);
        return this.getValorMulta();
    }
}

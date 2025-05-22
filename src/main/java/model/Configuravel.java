package model;

//Classe Configuravem uma interface para ser implementada no Sistema
public interface Configuravel {

    /**
     * Método que define o tempo padrão para empretimos em dias
     * @param dias dias maximo de emprestimo
     * @return  retonar os dias ja sendo tratado pelo set
     */
    public Integer definirTempoPadraoEmprestimo(Integer dias);
    /**
     * Método que define a quantidade maxima de emprestimos por tipo de produto
     * @param quantidade    quantidade maxima de emprestimos
     * @return retornar a quantidade ja sendo tratada pelo set
     */
    public Integer definirMaximoEmprestimo(Integer quantidade);
    /**
     * Método que define o valor de multa por dia
     * @param valorMulta valor da multa(double)
     * @return retorna o valor da multa ja tratada pelo set
    */
    public Double definirValorMulta(Double valorMulta);
}

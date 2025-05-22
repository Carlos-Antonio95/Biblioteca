package model;

import java.time.LocalDate;

import exception.ExceptionsPadrao;

/**
 * Classe midia contem todas as inormações sobre a midia
 */
public class Midia extends Item {

    private String diretor;

    public Midia(int id, String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria, String diretor) throws ExceptionsPadrao {
        super(id, titulo, dataDePublicacao, exemplaresDisponiveis, categoria);
        this.setDiretor(diretor);
        Biblioteca.getInstancia().adicionarMidiaLista(this);
    }

    public Midia(String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria, String diretor) throws ExceptionsPadrao {
        super( titulo, dataDePublicacao, exemplaresDisponiveis, categoria);
        this.setDiretor(diretor);
        Biblioteca.getInstancia().adicionarMidiaLista(this);
    }
    
   


    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) throws ExceptionsPadrao {
        if (diretor != null && !diretor.isEmpty()) {
            this.diretor = diretor;
        } else {
            throw new ExceptionsPadrao("Diretor não pode ser nullo/vazio");
        }
       
    }

    /**
     * Método para verificar se tem exemplares disponveis, caso exista decrementa 1
     * @return retorna true caso tenha sido "emprestada"
     * @throws ExceptionsPadrao 
     */
    public boolean emprestarItem() throws ExceptionsPadrao{
        if(getExemplaresDisponiveis() > 0){
            emprestar();//decrementa um mídia
            return true; //mídia emprestado com sucesso
        }else{
            throw  new ExceptionsPadrao("ERRO: Não a exemplares dispinveis para o Midia: "+getTitulo());
        }
        
    }
    
   
    @Override
    public void devolver() throws ExceptionsPadrao {
        try {
			this.setExemplaresDisponiveis(getExemplaresDisponiveis() + 1);
		} catch (ExceptionsPadrao e) {
			throw new ExceptionsPadrao("Exemplar não foi incrementado");
		}
    }


    @Override
    public void emprestar() throws ExceptionsPadrao {
       try {
		this.setExemplaresDisponiveis(getExemplaresDisponiveis()- 1);
	} catch (ExceptionsPadrao e) {
		throw new ExceptionsPadrao("Exemplar não foi decrementado");
	}
    }


    @Override
    public String toString() {
        return "Informções da Midia:\n"+
        		"Id: "+getId()+ "\n"
             + "Título: " + getTitulo() + "\n"
             + "Autor: " + getDiretor() + "\n"
             + "Data de Publicaçao: " + getDataDePublicacao() + "\n"
             + "Categoria: " + getCategoria() + "\n"
             + "Exemplares Disponíveis: " + getExemplaresDisponiveis()+"\n";
    }
}

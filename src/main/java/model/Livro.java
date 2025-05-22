package model;

import java.time.LocalDate;

import exception.ExceptionsPadrao;
import model.Biblioteca;

/**
 * Classe Livro contem todas as infromações sobre o livro
 */
public class Livro extends Item {

    private String autor;

    public Livro() {}
    public Livro(int id,String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria, String autor) throws ExceptionsPadrao {
        super(id, titulo, dataDePublicacao, exemplaresDisponiveis, categoria);
        this.setAutor(autor);
        Biblioteca.getInstancia().adicionarLivrosLista(this);
        
    }
    
    
    public Livro(String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria, String autor) throws ExceptionsPadrao {
        super(titulo, dataDePublicacao, exemplaresDisponiveis, categoria);
        this.setAutor(autor);
        Biblioteca.getInstancia().adicionarLivrosLista(this);
        
    }
    



    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws ExceptionsPadrao {
        if (autor != null && !autor.isEmpty()) {
            this.autor = autor;
        } else {
            throw new ExceptionsPadrao("Autor não pode ser nullo/vazio");
        }
        
    }

    /**
     * Método verefica se existem exemplares disponveis, caso exista ele decrementa em 1
     * @return returna verdadero se "emprestar"
     * @throws ExceptionsPadrao 
     */
    public boolean emprestarItem() throws ExceptionsPadrao{
        if(getExemplaresDisponiveis() > 0){
            emprestar();//decrementa um livro
            return true; //livro emprestado com sucesso
        }else{
            throw new ExceptionsPadrao("Não a exemplares dispinveis para o livro: "+getTitulo());
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
        return "Informações do Livro:\n"+
        		"Id: "+getId()+ "\n"
             + "Título: " + getTitulo() + "\n"
             + "Autor: " + getAutor() + "\n"
             + "Data de Publicação: " + getDataDePublicacao() + "\n"
             + "Categoria: " + getCategoria() + "\n"
             + "Exemplares Disponíveis: " + getExemplaresDisponiveis();
    }
}

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.ExceptionsPadrao;

public abstract class Item implements ItemBiblioteca{
    //Atributos
	private int id;
    private String titulo;
    private LocalDate dataDePublicacao;
    private int exemplaresDisponiveis;
    private String categoria;

    public Item() {}
    
    /// Construtor com id (para recuperar do banco)
    public Item(int id, String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria) throws ExceptionsPadrao {
    	  this.id = id;
          this.setTitulo(titulo);
          this.setDataDePublicacao(dataDePublicacao);
          this.setExemplaresDisponiveis(exemplaresDisponiveis);
          this.setCategoria(categoria);
      }

      public Item(String titulo, LocalDate dataDePublicacao, int exemplaresDisponiveis, String categoria) throws ExceptionsPadrao {
          this.setTitulo(titulo);
          this.setDataDePublicacao(dataDePublicacao);
          this.setExemplaresDisponiveis(exemplaresDisponiveis);
          this.setCategoria(categoria);
      }
   //Métodos acessores 


public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws ExceptionsPadrao{
        if(titulo != null && !titulo.isEmpty()){
            this.titulo = titulo;
        }else{
            throw new ExceptionsPadrao("Titulo não pode ser nulo");
        }
        
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    //verifica se a data de publicação passada no parametro é igual ou inferior a data atual
    public void setDataDePublicacao(LocalDate dataDePublicacao) throws ExceptionsPadrao {
        if(dataDePublicacao.isEqual(LocalDate.now()) || dataDePublicacao.isBefore(LocalDate.now())){
            this.dataDePublicacao = dataDePublicacao;
        }else if(dataDePublicacao.isAfter(LocalDate.now())){
            throw new ExceptionsPadrao("Data de publicação não pode ser superior a data de "+ LocalDate.now());
        }else if(this.dataDePublicacao.getYear() < 0) {
        	throw new ExceptionsPadrao("Ano de publicação invalida");
        }else {
        	throw new ExceptionsPadrao("Data de publicação invalida");
        }
        
    }
    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
        //
    }

    //impede que exemplares disponveis sejam vazios
    public void setExemplaresDisponiveis(int exemplaresDisponiveis) throws ExceptionsPadrao {
        if(exemplaresDisponiveis >= 0){
            this.exemplaresDisponiveis = exemplaresDisponiveis;
        }else{
            throw  new ExceptionsPadrao("Quantidade exemplares disponveis não pode ser negativo");
        }
       
    }

    public String getCategoria() {
        return categoria;
    }

    //Impede que seja setada uma categoria nulla/vazia
    public void setCategoria(String categoria) throws ExceptionsPadrao {
        if (categoria != null && !categoria.isEmpty()) {
            this.categoria = categoria;
        } else {
            throw new ExceptionsPadrao("Categoria não pode ser nullo");
        }
        
    }

     //Métodos abstratos serão implementados nas classes filhas
     @Override
     public abstract void devolver() throws ExceptionsPadrao;
     @Override
     public abstract void emprestar() throws ExceptionsPadrao;

     @Override
     public String toString() {
        return "Item [titulo=" + titulo + ", dataDePublicacao=" + dataDePublicacao + ", exemplaresDisponiveis="
                + exemplaresDisponiveis + ", categoria=" + categoria + "]";
     }

 

}
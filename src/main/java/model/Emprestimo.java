	package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dao.MultaDAO;
import exception.ExceptionsPadrao;
/**
 * Classe emprestimo, responsavel por realizar os emprestimos, com datas e fazer toda a junção dos outros métodos ja existentes assimr realizando o emprestimo
 */
public class Emprestimo {
	private int id;
    private Cliente cliente;
    private Livro livro;
    private Midia midia;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(){

    }
    // Construtor
    public Emprestimo(int id,Cliente cliente, Midia midia ,LocalDate dataEmprestimo)throws ExceptionsPadrao {
    	this.id = id;
        this.setCliente(cliente);
        this.setMidia(midia);
        this.setDataEmprestimo(dataEmprestimo);
       this.emprestimoItem(cliente, midia);
        Biblioteca.getInstancia().adicionarEmprestimoMidiaLista(this); 
        
    }
    
    public Emprestimo(int id,Cliente cliente, Livro livro ,LocalDate dataEmprestimo)throws ExceptionsPadrao {
    	this.id = id;
        this.setCliente(cliente);
        this.setLivro(livro);
        this.setDataEmprestimo(dataEmprestimo);
        this.emprestimoItem(cliente, livro);
        Biblioteca.getInstancia().adicionarEmprestimoLivroLista(this); 
        
    }
    
    // Construtor
    public Emprestimo(Cliente cliente, Midia midia ,LocalDate dataEmprestimo)throws ExceptionsPadrao {
        this.setCliente(cliente);
        this.setMidia(midia);
        this.setDataEmprestimo(dataEmprestimo);
       this.emprestimoItem(cliente, midia);
        Biblioteca.getInstancia().adicionarEmprestimoMidiaLista(this); 
        
    }

     public Emprestimo(Cliente cliente, Livro livro ,LocalDate dataEmprestimo) throws ExceptionsPadrao{
       this.setCliente(cliente);
        this.setLivro(livro);
        this.setDataEmprestimo(dataEmprestimo);
        this.emprestimoItem(cliente, livro) ;
        Biblioteca.getInstancia().adicionarEmprestimoLivroLista(this);
        
    }

// Getters e Setters        
  
     public int getId() {
    	    return id;
    	}

    	public void setId(int id) {
    	    this.id = id;
    	}
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente)throws ExceptionsPadrao {
        if (cliente != null){
            this.cliente = cliente;
        }else{
            throw new ExceptionsPadrao("Cliente não pode ser nulo");
       }
    }

     public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) throws ExceptionsPadrao {
        if(livro != null){
            this.livro = livro;
        }else{
            throw new ExceptionsPadrao("Livro não pode ser nulo");
        }
    }
        
    public Midia getMidia() {
        return midia;
    }
    public void setMidia(Midia midia) throws ExceptionsPadrao {
        if(midia != null){
             this.midia = midia;
        }else {
        	throw  new ExceptionsPadrao("Midia não pode ser nulo");
        }
       
    }
    

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    //recebe data de emprestimo setada(set) e ja define a data prevista de devolução(set())
    public void setDataEmprestimo(LocalDate dataEmprestimo) throws ExceptionsPadrao {
       LocalDate dataPrevistaDevolucao;
        if (dataEmprestimo != null){
            this.dataEmprestimo = dataEmprestimo;
            dataPrevistaDevolucao = dataEmprestimo.plusDays(Sistema.getInstancia().getTempoPadraoEmprestimo());//plusDays()soma a quantidade (de dias que esta no getTempoPadrãoEmprestimo())
            this.setDataPrevistaDevolucao(dataPrevistaDevolucao);//ja insere a data de devolução prevista
            

        }else{
            throw new ExceptionsPadrao("Data de empréstimo não pode ser nula.");
        }
    }
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) throws ExceptionsPadrao {
        if (dataDevolucao == null) throw new ExceptionsPadrao("Data de devolução não pode ser nula.");
        if (dataDevolucao.isBefore(dataEmprestimo)) {
            throw new ExceptionsPadrao("Data de devolução não pode ser anterior à data de empréstimo.");
        }
        this.dataDevolucao = dataDevolucao;
    }

   

    /**
     * Método que emprestar livro, utiliza os métodos ja existntem em cliente e livro, porem ele ja cria um emprestimo adicionando infromações como data e previção de devolução
     * @param cliente o cliente que ira fazer o emprestimo
     * @param livro o livro que sera emprestado
     * @throws ExceptionsPadrao 
     */
    public  void emprestimoItem(Cliente cliente, Livro livro) throws ExceptionsPadrao{
        if(cliente.emprestarLivro(livro)){

            //LocalDate datahoje = LocalDate.now();//LocalDate.now() pega a data do computador e vai inseir
            //Emprestimo emprestimo = new Emprestimo(cliente, livro, datahoje);//pega os paramentros ja passado no método principal e a data de hoje para criar o emprestimo(objeto)
            //LocalDate prevista = emprestimo.getDataPrevistaDevolucao();
        }else{
        	throw new ExceptionsPadrao("Emprestimo não realizado");
        }
    }

    /**
     * Método que emprestar midia, utiliza os métodos ja existntem em cliente e midia, porem ele ja cria um emprestimo adicionando infromações como data e previção de devolução
     * @param cliente o cliente que ira fazer o emprestimo
     * @param midia a midia que sera emprestado
     * @throws ExceptionsPadrao 
     */
    public  void emprestimoItem(Cliente cliente, Midia midia) throws ExceptionsPadrao{
        if (cliente.emprestarMidia(midia)) {
           // LocalDate dataHoje = LocalDate.now();//LocalDate.now() pega a data do computador e vai inseir
            //Emprestimo emprestimo = new Emprestimo(cliente, midia, dataHoje);//pega os paramentros ja passado no método principal e a data de hoje para criar o emprestimo(objeto)
           // LocalDate prevista = emprestimo.getDataPrevistaDevolucao();
          
        } else {
        	throw new ExceptionsPadrao("Emprestimo não realizado");
        }
      }

      /**
       * Método devolver midia faz as validações necessarias(se realmente existe emprestimo,cliente e midia)
       * @param cliente cliente que possuio emprestimo
       * @param emprestimo id do emprestimo(referencia na mémoria)
       * @param midia midia que foi emprestada
       * @return retorna true se foi devolvido
       */
        public boolean devolverItem(Cliente cliente, Emprestimo emprestimo,Midia midia,LocalDate dataDevolucao) throws ExceptionsPadrao{
            if(!Biblioteca.getInstancia().getListaClientes().contains(cliente)){
                throw  new ExceptionsPadrao("Cliente invalido!");
            }
            if (!Biblioteca.getInstancia().getListaEmprestimosLivrosMidia().contains(emprestimo)) {
                throw  new ExceptionsPadrao("Emprestimo não encontrado");
            }
            if(!Biblioteca.getInstancia().getListaMidias().contains(midia)){
                throw new ExceptionsPadrao("Midia invalida");
            }if(cliente.devolverMidia(midia)){
                midia.devolver();    
                emprestimo.calcularMulta(dataDevolucao);
                emprestimo.setDataDevolucao(dataDevolucao);
      
                return true;
            }
            return false;
        }

        /**
         * Método devolver livro faz as validações necessárias (se realmente existe empréstimo, cliente e livro)
         * @param cliente cliente que possui o empréstimo
         * @param emprestimo empréstimo a ser devolvido
         * @param livro livro que foi emprestado
         * @return retorna true se foi devolvido com sucesso
         */
        public boolean devolverItem(Cliente cliente, Emprestimo emprestimo, Livro livro, LocalDate dataDevolucao) throws ExceptionsPadrao {
            if (!Biblioteca.getInstancia().getListaClientes().contains(cliente)) {
                throw new ExceptionsPadrao("Cliente inválido!");
            }
            if (!Biblioteca.getInstancia().getListaEmprestimosLivrosMidia().contains(emprestimo)) {
                throw new ExceptionsPadrao("Empréstimo não encontrado");
            }
            if (!Biblioteca.getInstancia().getListaLivros().contains(livro)) {
                throw new ExceptionsPadrao("Livro inválido");
            }
            
            if (cliente.devolverLivro(livro)) {
            	livro.devolver();
            	emprestimo.calcularMulta(dataDevolucao);
            	emprestimo.setDataDevolucao(dataDevolucao);
              
            
                return true;
            }

            return false;
        }


      

      /**
       * Método que ira calcular os dias do emprestimo
       *  O método ChronoUnit.DAYS.between, calcula a diferença em dias entre duas datas fornecidas Ele retorna um valor do tipo long, que representa o número de dias entre as duas datas.
       * @param dataEmprestimo data que foi realizado o emprestimo
       * @param dataDevolucao data que foi devolvido
       * @return retornar a direfença entre as duas datas(dias)
       */
    public long calcularDiasEmprestimo(LocalDate dataDevolucao) throws ExceptionsPadrao {
        if (this.getDataEmprestimo() != null && dataDevolucao != null) {
            return ChronoUnit.DAYS.between(this.getDataEmprestimo(), dataDevolucao);
        } else {
            throw new ExceptionsPadrao("Datas de empréstimo e devolução devem estar definidas.");

        }
    }   

    /**
     * Método que retonar uma String informando a data  prevista para devolução
     * @param dataemprestimo data do emprestimo
     * @return data prevista para devolução
     */
    public String dataPrevistaDevolucao(LocalDate dataemprestimo){
        LocalDate dataPrevistaDevolucao = dataemprestimo.plusDays(Sistema.getInstancia().getTempoPadraoEmprestimo());
        return "Data prevista para devolução: "+ dataPrevistaDevolucao;
    }

         
          
    /**
     * Método para calcular multa, faz todo tratamento de erro verificando valores nulos, calcula os dias emprestados, multiplica o pela multa caso tenha multa e retorna o valor da multa
     * @param dataEmprestimo data de realização do emprestimo
     * @param dataDevolucao data de devolução do emprestimo
     * @return retonra o valor da multa caso tenha
     */
    public double calcularMulta(LocalDate dataDevolucao) throws ExceptionsPadrao {
    	MultaDAO dao = new MultaDAO();
        if (Sistema.getInstancia().getMaximoEmprestimos() == null) {
            throw new ExceptionsPadrao("Valores do sistema não pode ser nullo");
        }
        if (this.getDataEmprestimo() == null) {
            throw new ExceptionsPadrao("Data de empréstimo não foi definida.");
        }
        if (dataDevolucao == null) {
            throw new ExceptionsPadrao("Data de devolução não pode ser nula.");
        }

        long dias = calcularDiasEmprestimo(dataDevolucao);
        long diasExcedentes = dias - Sistema.getInstancia().getTempoPadraoEmprestimo();

        if (diasExcedentes > 0) {
            double valorMulta = diasExcedentes * Sistema.getInstancia().getValorMulta();
            dao.criarTabela();
            if (!dao.multaJaRegistrada(getId())) {
                dao.registrarMulta(getId(), valorMulta, dataDevolucao);
            }
            return valorMulta;
        } else {
            return 0.0;
        }
    }
  
   @Override
    public String toString() {
    return "\nInformações do Empréstimo:\n"
    	+ "Id: " + getId() +"\n"
        + "Cliente:" + (cliente != null ? cliente.getNome() : "cliente nulo")+"\n"
        + "Livro: " + (livro != null ? livro.getTitulo() : "Não informado") + "\n"
        + "Mídia: " + (midia != null ? midia.getTitulo() : "Não informado") + "\n"
        + "Data do Empréstimo: " + dataEmprestimo + "\n"
        + "Data Prevista de Devolução: " + dataPrevistaDevolucao + "\n"
        + "Data da Devolução: " + (dataDevolucao != null ? dataDevolucao : "Ainda não devolvido")+"\n"
        + "----------------------------";
    }

 

}
   
   

   
    

   

   
    






package model;

import java.util.ArrayList;
import java.util.List;

import dao.EmprestimoDAO;
import exception.ExceptionsPadrao;

/**
 * Classe cliente, contem todas as informações sobre o cliente, como nome,cpf, listas dos livros e midias emprestados, como também lista com apenas os nomes dos titulos
 */
public class Cliente {
	private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private List<Livro> livrosEmprestados = new ArrayList<>();
    private List<Midia> midiasEmprestadas = new ArrayList<>();
    private List<String> nomesLivros = new ArrayList<>();
    private List<String> nomesMidias = new ArrayList<>();

    public List<String> getNomesLivros() {
        return nomesLivros;
    }

   
    // Construtor registra automaticamente na biblioteca singleton
    public Cliente(int id, String nome, String cpf, String endereco, String telefone, String email) throws ExceptionsPadrao {
    	this.id = id;
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
        Biblioteca.getInstancia().adicionarClientesLista(this);
    }

    public Cliente(String nome, String cpf, String endereco, String telefone, String email) throws ExceptionsPadrao {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
        Biblioteca.getInstancia().adicionarClientesLista(this);
    }

   

	/**
     * Método para emprestar livro, verifica se respeita as regras do sistema sobre quantidade maxima de emprestimos e se o livro ja não esta emprestado para o mesmo, caso tudo esteja ok realiza emprestimo
     * @param livro livro que sera emprestado
     * @return retorna um true caso ocorra o emprestimo
	 * @throws ExceptionsPadrao 
     */
    public boolean  emprestarLivro(Livro livro) throws ExceptionsPadrao {
       	EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    	int totalEmprestimosAtual = emprestimoDAO.contarEmprestimosPorCliente(getId());
        int max = Sistema.getInstancia().getMaximoEmprestimos();
        if (totalEmprestimosAtual >= max) {
            throw new IllegalArgumentException(
                "Cliente atingiu o limite de empréstimos permitidos de: " + max
            );
        }
        if (livrosEmprestados.contains(livro)) {
          // System.out.println( "Erro: O livro \"" + livro.getTitulo() + "\" já foi emprestado para " + nome + ".");
           return false;
        } else {
            livro.emprestarItem();
            livrosEmprestados.add(livro);
            nomesLivros.add(livro.getTitulo());
           // System.out.println("O livro \"" + livro.getTitulo() + "\" foi emprestado para " + nome + ".");
            return true;
        }
    }

    /**
     * Método para emprestar midia, verifica se respeita as regras do sistema sobre quantidade maxima de emprestimos e se o midia ja não esta emprestado para o mesmo, caso tudo esteja ok realiza emprestimo
     * @param midia midia que sera emprestado
     * @return retorna um true caso ocorra o emprestimo
     * @throws ExceptionsPadrao 
     */
    public boolean emprestarMidia(Midia midia) throws ExceptionsPadrao {
    	EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    	int totalEmprestimosAtual = emprestimoDAO.contarEmprestimosPorCliente(getId());
        int max = Sistema.getInstancia().getMaximoEmprestimos();
        if (totalEmprestimosAtual >= max) {
            throw new IllegalArgumentException(
                "Cliente atingiu o limite de empréstimos permitidos de: " + max
            );
        }if (midiasEmprestadas.contains(midia)) {
           // System.out.println( "Erro: A mídia \"" + midia.getTitulo() + "\" já foi emprestada para " + nome + ".");
            return false;
        }else{
            midia.emprestarItem();
            midiasEmprestadas.add(midia);
            nomesMidias.add(midia.getTitulo());
           // System.out.println( "A mídia \"" + midia.getTitulo() + "\" foi emprestada para " + nome + ".");
            return true;
        }
        
    }
    /**
     * Método para devolver livro, retira o livro da lista de livros emprestados
     * @param livro livro que sera retirado
     */
    public boolean  devolverLivro(Livro livro) {
        if (!livrosEmprestados.contains(livro)) {
           // System.out.println("Erro: O livro \"" + livro + "\" não está emprestado para " + nome + ".");
           return false;
        } else {
            livrosEmprestados.remove(livro);
            nomesLivros.remove(livro.getTitulo());
            return true;
           // System.out.println("O livro \"" + livro + "\" foi devolvido por " + nome + ".");
        }
    }

    /**
     * Método para devovler midia, retira a midia da lista de emprestados
     * @param midia midia que sera devolvida
     */
    public boolean devolverMidia(Midia midia) {
        if (!midiasEmprestadas.contains(midia)) {
           // System.out.println("Erro: A midia \"" + midia + "\" não está emprestada para " + nome + ".");
           return false;
        } else {
            midiasEmprestadas.remove(midia);
            nomesMidias.remove(midia.getTitulo());
            return true;
            //System.out.println("A midia \"" + midia + "\" foi devolvida por " + nome + ".");

        }
    }

    // Lista sempre a própria lista (nunca null)
    public List<Livro> listarLivrosEmprestados() {
        if (livrosEmprestados.isEmpty()) {
            System.out.println(nome + " não possui livros emprestados.");
        }
        return livrosEmprestados;
    }

    // toString
    @Override
    public String toString() {
        return "Informações do Cliente:\n" +
        		"Id: "+getId()+ "\n"+
               "Nome: " + getNome() + "\n"+
               "Cpf: " + getCpf() + "\n" +
               "Endereço: " + getEndereco() + "\n"+
               "Telefone: " + getTelefone() + "\n" +
               "Email: " + getEmail() + "\n"+
               "LivrosEmprestados: " + livrosEmprestados.size() +" "+
               "Titulo: "+getNomesLivros()+" \n"+
               "MidiasEmprestadas: " + midiasEmprestadas.size()+" "+
               "Titulo: "+getNomesMidias()+" \n";
    }
    
    public void adicionarLivroEmprestado(Livro livro) {
        livrosEmprestados.add(livro);
        nomesLivros.add(livro.getTitulo());
    }

    public void adicionarMidiaEmprestada(Midia midia) {
        midiasEmprestadas.add(midia);
        nomesMidias.add(midia.getTitulo());
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ExceptionsPadrao {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ExceptionsPadrao("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ExceptionsPadrao {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ExceptionsPadrao("CPF deve conter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) throws ExceptionsPadrao {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new ExceptionsPadrao("Endereço não pode ser vazio.");
        }
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ExceptionsPadrao {
        if (telefone == null || telefone.trim().length() < 8) {
            throw new ExceptionsPadrao("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ExceptionsPadrao {
        if (email == null || !email.matches(".+@.+\\..+")) {
            throw new ExceptionsPadrao("Email inválido.");
        }
        this.email = email;
    }


    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

    public List<Midia> getMidiasEmprestadas() {
        return midiasEmprestadas;
    }

    public void setMidiasEmprestadas(List<Midia> midiasEmprestadas) {
        this.midiasEmprestadas = midiasEmprestadas;
    }

    public List<String> getNomesMidias() {
        return nomesMidias;
    }
}

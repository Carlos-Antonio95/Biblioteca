package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**Classe biblioteca, sera responsavel para ligar todas as outras classes, toda a listagem e sera o intermediador para realizar os emprestimos
 * relizara remoção de clientes, de livros e midias do banco de dados. chamara o emprestimo para ser realizado e na devolução calculara a multa.
*/
public class Biblioteca {
    private Emprestimo emprestimo;
     // Singleton
    private static final Biblioteca instancia = new Biblioteca();
    public static Biblioteca getInstancia() { return instancia; }

    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Livro> listaLivros = new ArrayList<>();
    private List<Midia> listaMidias = new ArrayList<>();
    private List<Emprestimo> listaEmprestimosLivrosMidia = new ArrayList<>();



    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }

    public List<Midia> getListaMidias() {
        return listaMidias;
    }

    public void setListaMidias(List<Midia> listaMidias) {
        this.listaMidias = listaMidias;
    }

    


    /**
     * Método para adicionar Clientes a Lista
     * @param c o cliente
     */
    public void adicionarClientesLista(Cliente c) { listaClientes.add(c); }
    /**
     * Método para adicionar livros a lista
     * @param l o livro
     */
    public void adicionarLivrosLista(Livro l)     { listaLivros.add(l); }
    /**
     * Método para adiocnar midia a lista
     * @param m a midia
     */
    public void adicionarMidiaLista(Midia m)      { listaMidias.add(m); }

  
    /**
     * Método para adicionar emprestimos realizado de livros
     * @param e o emprestimo(o objeto em si)
     */
    public void adicionarEmprestimoLivroLista(Emprestimo e) { listaEmprestimosLivrosMidia.add(e); }
    /**
     * Método para adicionar emprestimos realizados de midias
     * @param e o emprestimo(o objeto em si)
     */
    public void adicionarEmprestimoMidiaLista(Emprestimo e) { listaEmprestimosLivrosMidia.add(e); }

    /**
     * Método para remover a midia da lsita de emprestimos
     * @param e o emprestimo(o objeto em si)
     */
    public void removerEmprestimoMidiaLista(Emprestimo e) { listaEmprestimosLivrosMidia.remove(e); }

    /**
     * Método para remover o livro da lista de emprestimos
     * @param e o emprestimos(o objeto em si)
     */
    public void removerEmprestimoLivroLista(Emprestimo e) { listaEmprestimosLivrosMidia.remove(e); }

    
    /**
     * Método que vai listar todos os clientes que existem na lista
     * Vai printar o toString()
     */
    public void listarTodosOsClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Não há Clientes cadastrados na biblioteca.");
        } else {
            System.out.println("=== Lista de Clientes Cadastrados ===");
            for (Cliente c : listaClientes) {
                System.out.println(c);
                System.out.println("----------------------------------");
            }
        }
    }

    /**
     * Método que vai listar todos os livros que existem
     * Vai printar o toString();
     */
    public void listarTodosOsLivros() {
        if (listaLivros.isEmpty()) {
            System.out.println("Não há livros cadastrados na biblioteca.");
        } else {
            System.out.println("=== Lista de Livros Cadastrados ===");
            for (Livro l : listaLivros) {
                System.out.println(l);
                System.out.println("----------------------------------");
            }
        }
    }

    /**
     * Método que vai listar todos as midias que existem
     * Vai printar o toString()
     */
    public void listarTodasAsMidias() {
        if (listaMidias.isEmpty()) {
            System.out.println("Não há mídias cadastradas na biblioteca.");
        } else {
            System.out.println("=== Lista de Mídias Cadastradas ===");
            for (Midia m : listaMidias) {
                System.out.println(m);
                System.out.println("----------------------------------");
            }
        }
    }

    /**
     * Método que vai listar todos os emprestimos de livros
     * printa os emprestimos -> toString() = de emprestimo
     */
    public void listaTodosEmprestimoLivroMidia() {
        if (listaEmprestimosLivrosMidia.isEmpty()) {
            System.out.println("Não há empréstimos de livro na biblioteca.");
        } else {
            System.out.println("=== Empréstimos de Livros ===");
            for (Emprestimo e : listaEmprestimosLivrosMidia) {
                System.out.println(e);
                System.out.println("----------------------------------");
            }
        }
    }


    /**
     * Método remover cliente por cpf
     * @param cpf cpf do cliente
     * @return true ou false
     */
    public boolean removerClientePorCpf(String cpf) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                listaClientes.remove(cliente);
                return true; // Sucesso
            }
        }
        return false; // Não encontrado
    }

	public List<Emprestimo> getListaEmprestimosLivrosMidia() {
		return listaEmprestimosLivrosMidia;
	}

	public void setListaEmprestimosLivrosMidia(List<Emprestimo> listaEmprestimosLivrosMidia) {
		this.listaEmprestimosLivrosMidia = listaEmprestimosLivrosMidia;
	}


  

}



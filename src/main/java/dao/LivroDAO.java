package dao;

import model.Cliente;
import model.Livro;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConexaoSQLite;

public class LivroDAO {
	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}


    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS livro ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "titulo TEXT NOT NULL, "
                   + "data_publicacao TEXT NOT NULL, "
                   + "exemplares_disponiveis INTEGER NOT NULL, "
                   + "categoria TEXT NOT NULL, "
                   + "autor TEXT NOT NULL"
                   + ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao criar tabela de livros: " + e.getMessage());
        }
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro(titulo, data_publicacao, exemplares_disponiveis, categoria, autor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getDataDePublicacao().toString());
            stmt.setInt(3, livro.getExemplaresDisponiveis());
            stmt.setString(4, livro.getCategoria());
            stmt.setString(5, livro.getAutor());
            stmt.executeUpdate();
         // Obtém o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getInt(1)); // Atribui o ID gerado ao livro
            }
        } catch (SQLException e) {
        	if (e.getMessage().contains("Invalid value for MonthOfYear")) {
        		throw new ExceptionSQL("Mês invalido ");
				
			} else if(e.getMessage().contains("Invalid value for DayOfMonth")){
					throw new ExceptionSQL("Dia invalido");
			}else {
				throw new RuntimeException("Erro ao salvar livro: "+livro.getTitulo());
			}
           
        } 
    }

    public Livro buscarPorTitulo(String titulo) throws ExceptionsPadrao {
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                    rs.getInt("id"), // <-- ID aqui
                    rs.getString("titulo"),
                    LocalDate.parse(rs.getString("data_publicacao")),
                    rs.getInt("exemplares_disponiveis"),
                    rs.getString("categoria"),
                    rs.getString("autor")
                );
            }
        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao buscar livro por título: " + e.getMessage());
        }
        return null;
    }

    public Livro buscarPorId(int id) throws ExceptionsPadrao{
    	String sql = "SELECT * FROM livro WHERE id = ?";
    	try(Connection conn = conectar();
    		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
				return new Livro(
						rs.getInt("id"),
						rs.getString("titulo"),
		                 LocalDate.parse(rs.getString("data_publicacao")),
		                 rs.getInt("exemplares_disponiveis"),
		                 rs.getString("categoria"),
		                 rs.getString("autor")
					);
			} 
    		
    	} catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao buscar livro por ID: " + e.getMessage());
        }
    	return null;
    }
    
     
    public List<Livro> listarTodos() throws ExceptionsPadrao {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("id"), // <-- ID aqui
                    rs.getString("titulo"),
                    LocalDate.parse(rs.getString("data_publicacao")),
                    rs.getInt("exemplares_disponiveis"),
                    rs.getString("categoria"),
                    rs.getString("autor")
                );
                lista.add(livro);
            }
        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao listar livros: "+e.getMessage() );
        }
        return lista;
    }


    public void atualizarLivroPorId(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, data_publicacao = ?, exemplares_disponiveis = ?, categoria = ?, autor = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getDataDePublicacao().toString());
            stmt.setInt(3, livro.getExemplaresDisponiveis());
            stmt.setString(4, livro.getCategoria());
            stmt.setString(5, livro.getAutor());
            stmt.setInt(6, livro.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new ExceptionSQL("Nenhum livro encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao atualizar livro por ID: " + e.getMessage());
        }
    }

    public void atualizarExemplaresTitulo(String titulo, int novoValor) {
        String sql = "UPDATE livro SET exemplares_disponiveis = ? WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoValor);
            stmt.setString(2, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao atualizar exemplares por título: "+e.getMessage());
        }
    }
    

    public void atualizarExemplaresId(int novoValor, int id) {
        String sql = "UPDATE livro SET exemplares_disponiveis = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao atualizar exemplares por ID: "+e.getMessage());
        }
    }

    public void excluirPorTitulo(String titulo) {
        String sql = "DELETE FROM livro WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao excluir livro por título: "+e.getMessage());
        }
    }
    
    public boolean excluirPorId(int id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao excluir livro por ID: "+e.getMessage());
          
        }
    }
    
    public int contarTodos() {
        int quantidade = 0;
        String sql = "SELECT COUNT(*) FROM livro";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                quantidade = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao contar livros: "+e.getMessage());
        }

        return quantidade;
    }
}

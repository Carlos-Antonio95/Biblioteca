package dao;

import model.Midia;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConexaoSQLite;

public class MidiaDAO {

	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}


    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS midia ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "titulo TEXT NOT NULL, "
                   + "data_publicacao TEXT NOT NULL, "
                   + "exemplares_disponiveis INTEGER NOT NULL, "
                   + "categoria TEXT NOT NULL, "
                   + "diretor TEXT NOT NULL"
                   + ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao criar a tabela midia: "+e.getMessage());
        }
    }

    public void salvar(Midia midia) {
        String sql = "INSERT INTO midia(titulo, data_publicacao, exemplares_disponiveis, categoria, diretor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, midia.getTitulo());
            stmt.setString(2, midia.getDataDePublicacao().toString());
            stmt.setInt(3, midia.getExemplaresDisponiveis());
            stmt.setString(4, midia.getCategoria());
            stmt.setString(5, midia.getDiretor());
            stmt.executeUpdate();
         // Obtém o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                midia.setId(rs.getInt(1)); // Atribui o ID gerado a midia
            }
        } catch (SQLException e) {
        	if (e.getMessage().contains("Invalid value for MonthOfYear")) {
        		throw new ExceptionSQL("Mês invalido ");
				
			} else if(e.getMessage().contains("Invalid value for DayOfMonth")){
					throw new ExceptionSQL("Dia invalido");
			}else {
				throw new RuntimeException("Erro ao salvar midia: "+midia.getTitulo());
			}
        }
    }

    public Midia buscarPorTitulo(String titulo) throws ExceptionsPadrao {
        String sql = "SELECT * FROM midia WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Midia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    LocalDate.parse(rs.getString("data_publicacao")),
                    rs.getInt("exemplares_disponiveis"),
                    rs.getString("categoria"),
                    rs.getString("diretor")
                );
            }
        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao buscar midia por título: " + e.getMessage());
        }
        return null;
    }
    
    public Midia buscarPorId(int id) throws ExceptionsPadrao {
        String sql = "SELECT * FROM midia WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Midia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    LocalDate.parse(rs.getString("data_publicacao")),
                    rs.getInt("exemplares_disponiveis"),
                    rs.getString("categoria"),
                    rs.getString("diretor")
                );
            }
        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao buscar midia por ID: "+e.getMessage());
        }
        return null;
    }

    public List<Midia> listarTodos() throws ExceptionsPadrao {
        List<Midia> lista = new ArrayList<>();
        String sql = "SELECT * FROM midia";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Midia midia = new Midia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    LocalDate.parse(rs.getString("data_publicacao")),
                    rs.getInt("exemplares_disponiveis"),
                    rs.getString("categoria"),
                    rs.getString("diretor")
                );
                lista.add(midia);
            }
        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao listar midias: "+e.getMessage());
        }
        return lista;
    }
    
    public void atualizarMidia(Midia midia) {
        String sql = "UPDATE midia SET titulo = ?, data_publicacao = ?, exemplares_disponiveis = ?, categoria = ?, diretor = ? WHERE id = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, midia.getTitulo());
            stmt.setString(2, midia.getDataDePublicacao().toString());
            stmt.setInt(3, midia.getExemplaresDisponiveis());
            stmt.setString(4, midia.getCategoria());
            stmt.setString(5, midia.getDiretor());
            stmt.setInt(6, midia.getId());
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas == 0) {
                throw new ExceptionSQL("Nenhuma mídia foi atualizada. ID pode estar incorreto.");
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao atualizar mídia: " + e.getMessage());
        }
    }


    public void atualizarExemplaresTitulo(String titulo, int novoValor) {
        String sql = "UPDATE midia SET exemplares_disponiveis = ? WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoValor);
            stmt.setString(2, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new ExceptionSQL("Erro ao atualizar exemplares por título: " + e.getMessage());
        }
    }

    public void atualizarExemplaresId(int novoValor, int id) {
        String sql = "UPDATE midia SET exemplares_disponiveis = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	 throw new ExceptionSQL("Erro ao atualizar exemplares por ID: " + e.getMessage());
        }
    }
    public void excluirPorTitulo(String titulo) {
        String sql = "DELETE FROM midia WHERE titulo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	throw new ExceptionSQL("Erro ao excluir livro por título: " + e.getMessage());
        
        }
    }
    
    public boolean excluirPorId(int id) {
        String sql = "DELETE FROM midia WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao excluir midia por ID: "+e.getMessage());
          
        }
    }
    
    public int contarTodos() {
        int quantidade = 0;
        String sql = "SELECT COUNT(*) FROM midia";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                quantidade = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao contar midias: "+e.getMessage());
        }

        return quantidade;
    }
}

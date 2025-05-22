package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.ConexaoSQLite;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;

public class ClienteDAO {

	
	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}

    
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cliente (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nome TEXT NOT NULL, " +
                     "cpf TEXT UNIQUE NOT NULL, " +
                     "endereco TEXT, " +
                     "telefone TEXT, " +
                     "email TEXT)";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
        	throw new ExceptionSQL("ERRO ao criartabela cliente"+e.getMessage());
        }
    }

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.executeUpdate();

            // Obtém o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1)); // Atribui o ID gerado ao cliente
            }
        } catch (SQLException e) {
        	if (e.getMessage().contains("UNIQUE constraint failed")) {
        		throw new ExceptionSQL("Ja exixte um cliente cadastrado com esse cpf: "+cliente.getCpf());
				
			} else {
				throw new RuntimeException("Erro ao salvar cliente: " + cliente.getNome(), e);
			}
        	 
        }
    }

    public Cliente buscarPorCpf(String cpf) throws ExceptionsPadrao{
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"), // Recupera o id
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("endereco"),	
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            } else {
                throw new ExceptionsPadrao("Cliente com CPF " + cpf + " não encontrado.");
                
            }
           

        } catch (SQLException e) {
            throw new ExceptionsPadrao("Erro ao buscar cliente por CPF: " + cpf+""+ e.getMessage());
       
        }
       
    }

    public Cliente buscarPorId(int id) throws ExceptionsPadrao{
    	String sql = "SELECT * FROM cliente WHERE id = ?";
    	try(Connection conn = conectar();
    		PreparedStatement stmt = conn.prepareStatement(sql)) {
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
				return new Cliente(
						rs.getInt("id"),
						rs.getString("nome"),
						rs.getString("cpf"),
						rs.getString("endereco"),
						rs.getString("telefone"),
						rs.getString("email")
					);
    		} else {
                throw new ExceptionsPadrao("Cliente com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao buscar cliente por ID: " + id, e);
        }
    }
    
    public List<Cliente> listarTodos() throws ExceptionsPadrao {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id"), // Recupera o id
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new ExceptionSQL("Nenhum cliente foi atualizado. Verifique se o ID é válido.");
            }

        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao atualizar cliente com ID: " + cliente.getId(), e);
        }
    }


    public boolean deletarPorId(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Deleta baseado no id
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deletarPorCpf(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf=?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf); // Deleta baseado no id
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int contarTodos() throws ExceptionsPadrao {
        int quantidade = 0;
        String sql = "SELECT COUNT(*) FROM cliente";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                quantidade = rs.getInt(1);
            } else {
                throw new ExceptionsPadrao("Falha ao contar registros.");
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao contar clientes.", e);
        }

        return quantidade;
    }
}
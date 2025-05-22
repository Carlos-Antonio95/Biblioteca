package dao;

import java.sql.*;
import java.time.LocalDate;

import database.ConexaoSQLite;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;

public class MultaDAO {

	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}


    // Criar tabela de multas
    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS multas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                emprestimo_id INTEGER,
                valor REAL,
                data_multa TEXT,
                FOREIGN KEY (emprestimo_id) REFERENCES emprestimo(id) ON DELETE CASCADE ON UPDATE CASCADE
            );
        """;
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
           throw new ExceptionSQL("Erro ao criar tabela multas: "+e.getMessage());
        }
    }

    // Registrar uma multa
    public void registrarMulta(int emprestimoId, double valor, LocalDate dataMulta) {
        String sql = "INSERT INTO multas (emprestimo_id, valor, data_multa) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimoId);
            stmt.setDouble(2, valor);
            stmt.setString(3, dataMulta.toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao registro multa: "+ e.getMessage());
        }
    }

    // Contar total de multas
    public int contarMultas() {
        String sql = "SELECT COUNT(*) FROM multas";
        int total = 0;

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
        	throw new ExceptionSQL("Erro ao contar multa: "+ e.getMessage());
        }

        return total;
    }
    
    // Contar total de multas
    public double contarMultasValor() {
        String sql = "SELECT  SUM(valor) FROM multas";
        double total = 0;

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao somar valores das multas: "+ e.getMessage());
        }

        return total;
    }
    
    public boolean multaJaRegistrada(int emprestimoId) {
        String sql = "SELECT COUNT(*) FROM multas WHERE emprestimo_id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao verificar multa existente: " + e.getMessage());
        }
       return false;
    }

}
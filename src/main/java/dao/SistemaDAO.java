package dao;

import model.Sistema;

import java.sql.*;

import database.ConexaoSQLite;
import exception.ExceptionSQL;

public class SistemaDAO {

	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS sistema ("
                   + "id INTEGER PRIMARY KEY CHECK(id = 1), "
                   + "tempo_padrao_emprestimo INTEGER NOT NULL, "
                   + "maximo_emprestimos INTEGER NOT NULL, "
                   + "valor_multa REAL NOT NULL"
                   + ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao criar tabela sistema: "+ e.getMessage());
        }
    }

    public void salvar(Sistema sistema) {
        String sqlInsert = "INSERT OR IGNORE INTO sistema (id, tempo_padrao_emprestimo, maximo_emprestimos, valor_multa) VALUES (1, ?, ?, ?)";
        String sqlUpdate = "UPDATE sistema SET tempo_padrao_emprestimo = ?, maximo_emprestimos = ?, valor_multa = ? WHERE id = 1";

        try (Connection conn = conectar()) {
            // Tenta inserir, se já existir, faz update
            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                stmtInsert.setInt(1, sistema.getTempoPadraoEmprestimo());
                stmtInsert.setInt(2, sistema.getMaximoEmprestimos());
                stmtInsert.setDouble(3, sistema.getValorMulta());
                int affectedRows = stmtInsert.executeUpdate();

                if (affectedRows == 0) { // já existe, atualiza
                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                        stmtUpdate.setInt(1, sistema.getTempoPadraoEmprestimo());
                        stmtUpdate.setInt(2, sistema.getMaximoEmprestimos());
                        stmtUpdate.setDouble(3, sistema.getValorMulta());
                        stmtUpdate.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao salvar dados do sistema: "+ e.getMessage());
        }
    }

    public Sistema carregar() {
        String sql = "SELECT tempo_padrao_emprestimo, maximo_emprestimos, valor_multa FROM sistema WHERE id = 1";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Sistema sistema = Sistema.getInstancia();
                sistema.setTempoPadraoEmprestimo(rs.getInt("tempo_padrao_emprestimo"));
                sistema.setMaximoEmprestimos(rs.getInt("maximo_emprestimos"));
                sistema.setValorMulta(rs.getDouble("valor_multa"));
                return sistema;
            }
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao carregar dados do sistema: "+e.getMessage());
        }
        return null; // ou criar sistema padrão se preferir
    }
}

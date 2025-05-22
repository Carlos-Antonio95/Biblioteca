package dao;

import model.Emprestimo;
import model.Cliente;
import model.Livro;
import model.Midia;
import exception.ExceptionSQL;
import exception.ExceptionsPadrao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConexaoSQLite;

public class EmprestimoDAO {

	private Connection conectar() throws SQLException {
	    return ConexaoSQLite.conectar(); // 
	}

    // Criar tabela empréstimo
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS emprestimo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "cliente_id INTEGER NOT NULL, "
                + "livro_id INTEGER DEFAULT NULL, "
                + "midia_id INTEGER DEFAULT NULL, "
                + "data_emprestimo TEXT NOT NULL, "
                + "data_prevista_devolucao TEXT NOT NULL, "
                + "data_devolucao TEXT DEFAULT NULL, "
                + "FOREIGN KEY(cliente_id) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                + "FOREIGN KEY(livro_id) REFERENCES livro(id) ON DELETE SET NULL ON UPDATE CASCADE, "
                + "FOREIGN KEY(midia_id) REFERENCES midia(id) ON DELETE SET NULL ON UPDATE CASCADE"
                + ");";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao criar tabela emprestimos: "+e.getMessage());
        }
    }

    // Salvar empréstimo (livro ou midia)
    public void salvar(Emprestimo emprestimo) throws SQLException {

        String sql = "INSERT INTO emprestimo (cliente_id, livro_id, midia_id, data_emprestimo, data_prevista_devolucao, data_devolucao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); 
        	PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, emprestimo.getCliente().getId());
            
            if (emprestimo.getLivro() != null) {
                stmt.setInt(2, emprestimo.getLivro().getId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (emprestimo.getMidia() != null) {
                stmt.setInt(3, emprestimo.getMidia().getId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            
            stmt.setString(4, emprestimo.getDataEmprestimo().toString());
            stmt.setString(5, emprestimo.getDataPrevistaDevolucao().toString());
            
            if (emprestimo.getDataDevolucao() != null) {
                stmt.setString(6, emprestimo.getDataDevolucao().toString());
            } else {
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.executeUpdate();
            
            // Recupera o id gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emprestimo.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Buscar empréstimo por ID
    public Emprestimo buscarPorId(int id) throws SQLException, ExceptionsPadrao {

        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.setId(rs.getInt("id"));

                    // Buscar cliente por ID 
                    int clienteId = rs.getInt("cliente_id");
                    Cliente cliente = new ClienteDAO().buscarPorId(clienteId); 
                    emprestimo.setCliente(cliente);

                    int livroId = rs.getInt("livro_id");
                    if (!rs.wasNull()) {
                        Livro livro = new LivroDAO().buscarPorId(livroId);
                        emprestimo.setLivro(livro);
                    }

                    int midiaId = rs.getInt("midia_id");
                    if (!rs.wasNull()) {
                        Midia midia = new MidiaDAO().buscarPorId(midiaId);
                        emprestimo.setMidia(midia);
                    }

                    emprestimo.setDataEmprestimo(LocalDate.parse(rs.getString("data_emprestimo")));
                    emprestimo.setDataPrevistaDevolucao(LocalDate.parse(rs.getString("data_prevista_devolucao")));

                    String dataDevolucaoStr = rs.getString("data_devolucao");
                    if (dataDevolucaoStr != null) {
                        emprestimo.setDataDevolucao(LocalDate.parse(dataDevolucaoStr));
                    }

                    return emprestimo;
                }
            }
        }
        return null; // não encontrado
    }

    // Atualizar empréstimo (ex: marcar devolução)
    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (emprestimo.getDataDevolucao() != null) {
                stmt.setString(1, emprestimo.getDataDevolucao().toString());
            } else {
                stmt.setNull(1, Types.VARCHAR);
            }
            stmt.setInt(2, emprestimo.getId());
            stmt.executeUpdate();
        }
    }
    
    public void atualizarDataDevolucao(int id, LocalDate dataDevolucao) throws SQLException {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (dataDevolucao != null) {
                stmt.setString(1, dataDevolucao.toString());
            } else {
                stmt.setNull(1, Types.VARCHAR);
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Deletar empréstimo
    public boolean deletar(int id) throws SQLException {

        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        }
        
    }

    // Listar todos empréstimos
    public List<Emprestimo> listarTodos() throws SQLException, ExceptionsPadrao {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                int clienteId = rs.getInt("cliente_id");
                Cliente cliente = new ClienteDAO().buscarPorId(clienteId);
                if (cliente == null) {
                    System.err.println("Erro: Cliente com ID " + clienteId + " não encontrado no banco.");
                    continue; // pula para o próximo registro
                }
                emprestimo.setCliente(cliente);

                int livroId = rs.getInt("livro_id");
                if (!rs.wasNull()) {
                    Livro livro = new LivroDAO().buscarPorId(livroId);
                    emprestimo.setLivro(livro);
                }

                int midiaId = rs.getInt("midia_id");
                if (!rs.wasNull()) {
                    Midia midia = new MidiaDAO().buscarPorId(midiaId);
                    emprestimo.setMidia(midia);
                }

                emprestimo.setDataEmprestimo(LocalDate.parse(rs.getString("data_emprestimo")));
                emprestimo.setDataPrevistaDevolucao(LocalDate.parse(rs.getString("data_prevista_devolucao")));

                String dataDevolucaoStr = rs.getString("data_devolucao");
                if (dataDevolucaoStr != null) {
                    emprestimo.setDataDevolucao(LocalDate.parse(dataDevolucaoStr));
                }

                lista.add(emprestimo);
            }
        }
        return lista;
    }

    public int contarTodos() {
        int quantidade = 0;
        String sql = "SELECT COUNT(*) FROM emprestimo";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                quantidade = rs.getInt(1);
            }
            
            return quantidade;
            
        } catch (SQLException e) {
            throw new ExceptionSQL("Erro ao contar emprestimos: "+e.getMessage());
        }

    }
    
    public int contarEmprestimosPorCliente(int clienteId) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE cliente_id = ? AND data_devolucao IS NULL";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
           throw new ExceptionSQL("Erro ao contar emprestimos por cliente: "+ e.getMessage());
        }

        return total;
    }

}

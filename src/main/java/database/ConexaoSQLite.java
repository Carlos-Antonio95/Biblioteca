package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import dao.MultaDAO;
import dao.SistemaDAO;

import java.io.File;

public class ConexaoSQLite {

    private static final String NOME_ARQUIVO = "banco.db";

    public static Connection conectar() throws SQLException {
        try {
            // Cria uma pasta "data" no diretório do projeto ou ao lado do .jar
            File pasta = new File("data");
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            // Caminho para o banco dentro da pasta "data"
            String url = "jdbc:sqlite:" + pasta.getAbsolutePath() + File.separator + NOME_ARQUIVO;

            // Conexão
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
    
    public static void criarTabelas() {
    	ClienteDAO dao = new ClienteDAO();
    	EmprestimoDAO daoe = new EmprestimoDAO();
    	LivroDAO daol = new LivroDAO();
    	MidiaDAO daom = new MidiaDAO();
    	MultaDAO daomu = new MultaDAO();
    	SistemaDAO daos = new SistemaDAO();
    	dao.criarTabela();
    	daoe.criarTabela();
    	daol.criarTabela();
    	daom.criarTabela();
    	daomu.criarTabela();
    	daos.criarTabela();
    }
}

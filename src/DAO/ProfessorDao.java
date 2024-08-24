package DAO;

import Model.Professor;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ProfessorDao {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/DAO/configuracaobd.properties");
    private static final String sqllistaProfessor = """
                                                    SELECT 
                                                        p.id AS professor_id,
                                                        p.nome AS professor_nome,
                                                        p.email AS professor_email,
                                                        p.paluno AS professor_paluno
                                                    FROM 
                                                        professor p;""";

    private static final String sqlconsultaProfessorRegistro = """
                                                            SELECT 
                                                                p.id AS professor_id,
                                                                p.nome AS professor_nome,
                                                                p.email AS professor_email,
                                                                p.paluno AS professor_paluno
                                                            FROM 
                                                                professor p
                                                            WHERE 
                                                                p.email = ?;""";

    public ProfessorDao() {

    }

    public boolean CriaConexao() {
        try {
            JDBCUTIL.init(config_file);
            connection = JDBCUTIL.getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.
            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());
            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }

    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean Inserir(Professor P) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            //pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            CallableStatement cstdados = connection.prepareCall("{call inserir(?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, P.getNome());
            cstdados.setString(2, P.getEmail());
            cstdados.setString(3, P.getpAluno());
            int resposta = cstdados.executeUpdate();
            cstdados.close();
            connection.commit();
            FechaConexao();
            return true;

        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro);
        }
        return false;
    }

    public boolean Alterar(Professor p) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            CallableStatement cstdados = connection.prepareCall("{call Atualizar(?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, p.getNome());
            cstdados.setString(2, p.getEmail());
            cstdados.setString(3, p.getpAluno());
            int resposta = cstdados.executeUpdate();
            cstdados.close();
            connection.commit();
            FechaConexao();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro na execução da atualização = " + erro);
        }
        return false;
    }

    public int Excluir(String codigo) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(null, tipo, concorrencia);
            pstdados.setString(1, codigo);
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            connection.commit();
            FechaConexao();
            return resposta;
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
        }
        return 0;
    }

    public List<Professor> listRC(String codigo, int opt) {
        try {
            CriaConexao();
            String query = "";
            switch (opt) {
                case 1:
                    query = sqlconsultaProfessorRegistro;
                    break;
                case 2:
                    query = sqllistaProfessor;
                    break;
                default:
                    break;
            }
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(query, tipo, concorrencia);
            if (opt != 2) {
                pstdados.setString(1, codigo);
            }
            rsdados = pstdados.executeQuery();
            List<Professor> professor = new ArrayList<>();
            while (rsdados.next()) {
                Professor p = new Professor();
                p.setNome(rsdados.getString("professor_nome"));
                p.setEmail(rsdados.getString("professor_email"));
                p.setpAluno(rsdados.getString("professor_paluno"));
                professor.add(p);
            }
            return professor;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        FechaConexao();
        return null;
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }
}

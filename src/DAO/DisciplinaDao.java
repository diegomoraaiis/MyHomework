package DAO;

import Model.Disciplina;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class DisciplinaDao {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/DAO/configuracaobd.properties");
    private static final String sqllistaDisciplina = """
                                                    SELECT 
                                                        d.id AS disciplina_id,
                                                        d.disciplina AS disciplina_nome,
                                                        d.codigo AS disciplina_codigo,
                                                        d.turma AS disciplina_turma
                                                    FROM 
                                                        disciplina d;""";
    private static final String sqlconsultaDisciplinaRegistro = """
                                                            SELECT 
                                                                d.id AS disciplina_id,
                                                                d.disciplina AS disciplina_nome,
                                                                d.codigo AS disciplina_codigo,
                                                                d.turma AS disciplina_turma
                                                            FROM 
                                                                disciplina d
                                                            WHERE 
                                                                d.codigo = ?;""";

    public DisciplinaDao() {

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

    public boolean Inserir(Disciplina d) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            //pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            CallableStatement cstdados = connection.prepareCall("{call inserir(?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, d.getDisciplina());
            cstdados.setString(2, d.getCodigo());
            cstdados.setString(3, d.getTurma());
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

    public boolean Alterar(Disciplina d) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            CallableStatement cstdados = connection.prepareCall("{call Atualizar(?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, d.getCodigo());
            cstdados.setString(2, d.getDisciplina());
            cstdados.setString(3, d.getTurma());
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

    public List<Disciplina> listRC(String codigo, int opt) {
        try {
            CriaConexao();
            String query = "";
            switch (opt) {
                case 1:
                    query = sqlconsultaDisciplinaRegistro;
                    break;
                case 2:
                    query = sqllistaDisciplina;
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
            List<Disciplina> disciplina = new ArrayList<>();
            while (rsdados.next()) {
                Disciplina d = new Disciplina();
                d.setDisciplina(rsdados.getString("disciplina_nome"));
                d.setCodigo(rsdados.getString("disciplina_codigo"));
                d.setTurma(rsdados.getString("disciplina_turma"));
                disciplina.add(d);
            }
            return disciplina;
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

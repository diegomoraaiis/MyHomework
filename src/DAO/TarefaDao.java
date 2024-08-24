package DAO;

import Model.Tarefa;
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
public class TarefaDao {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/DAO/configuracaobd.properties");
    private static final String sqllistatarefa = """
                                                    SELECT 
                                                        t.id AS tarefa_id,
                                                        t.descricao AS tarefa_descricao,
                                                        t.status AS tarefa_status,
                                                        t.prazo AS tarefa_prazo,
                                                        p.id AS professor_id,
                                                        p.nome AS professor_nome,
                                                        p.email AS professor_email,
                                                        p.paluno AS professor_paluno,
                                                        d.id AS disciplina_id,
                                                        d.disciplina AS disciplina_nome,
                                                        d.codigo AS disciplina_codigo,
                                                        d.turma AS disciplina_turma
                                                    FROM 
                                                        tarefa t
                                                    JOIN 
                                                        professor p ON t.professor_id = p.id
                                                    JOIN 
                                                        disciplina d ON p.disciplina_id = d.id""";
    private static final String sqlconsultatarefaStatus = """
                                                            SELECT 
                                                                t.id AS tarefa_id,
                                                                t.descricao AS tarefa_descricao,
                                                                t.status AS tarefa_status,
                                                                t.prazo AS tarefa_prazo,
                                                                p.id AS professor_id,
                                                                p.nome AS professor_nome,
                                                                p.email AS professor_email,
                                                                p.paluno AS professor_paluno,
                                                                d.id AS disciplina_id,
                                                                d.disciplina AS disciplina_nome,
                                                                d.codigo AS disciplina_codigo,
                                                                d.turma AS disciplina_turma
                                                            FROM 
                                                                tarefa t
                                                            JOIN 
                                                                professor p ON t.professor_id = p.id
                                                            JOIN 
                                                                disciplina d ON p.disciplina_id = d.id
                                                            WHERE 
                                                                t.status = ?;""";
    private static final String sqlconsultatarefaRegistro = """
                                                            SELECT 
                                                                t.id AS tarefa_id,
                                                                t.descricao AS tarefa_descricao,
                                                                t.status AS tarefa_status,
                                                                t.prazo AS tarefa_prazo,
                                                                p.id AS professor_id,
                                                                p.nome AS professor_nome,
                                                                p.email AS professor_email,
                                                                p.paluno AS professor_paluno,
                                                                d.id AS disciplina_id,
                                                                d.disciplina AS disciplina_nome,
                                                                d.codigo AS disciplina_codigo,
                                                                d.turma AS disciplina_turma
                                                            FROM 
                                                                tarefa t
                                                            JOIN 
                                                                professor p ON t.professor_id = p.id
                                                            JOIN 
                                                                disciplina d ON p.disciplina_id = d.id
                                                            WHERE 
                                                                d.codigo = ?;""";
    private static final String sqlalterar = """
                                             DO $$
                                             DECLARE
                                                 v_disciplina_id INT;
                                             BEGIN
                                                 UPDATE disciplina
                                                 SET disciplina = '?', turma = '?'
                                                 WHERE codigo = '?'
                                                 RETURNING id INTO v_disciplina_id;
                                                 
                                                 IF v_disciplina_id IS NOT NULL THEN
                                                     UPDATE professor
                                                     SET nome = '?', email = '?', paluno = '?'
                                                     WHERE professor.disciplina_id = v_disciplina_id;
                                                     
                                                     UPDATE tarefa
                                                     SET descricao = '?', status = '?', prazo = '?'
                                                     WHERE tarefa.professor_id IN (SELECT professor.id FROM professor WHERE professor.disciplina_id = v_disciplina_id);
                                                 ELSE
                                                     RAISE NOTICE 'Nenhuma disciplina encontrada com o código especificado.';
                                                 END IF;
                                             END $$;
                                             """;
    private static final String sqlexcluir = "DELETE FROM disciplina WHERE codigo = ?";

    public TarefaDao() {

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

    public boolean Inserir(Tarefa t) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            //pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            CallableStatement cstdados = connection.prepareCall("{call inserir(?,?,?,?,?,?,?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, t.getDisciplina());
            cstdados.setString(2, t.getCodigo());
            cstdados.setString(3, t.getTurma());
            cstdados.setString(4, t.getNome());
            cstdados.setString(5, t.getEmail());
            cstdados.setString(6, t.getpAluno());
            cstdados.setString(7, t.getDescricao());
            cstdados.setString(8, t.getStatus());
            cstdados.setString(9, t.getPrazo());
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

    public boolean Alterar(Tarefa t) {
        try {
            CriaConexao();
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            CallableStatement cstdados = connection.prepareCall("{call Atualizar(?,?,?,?,?,?,?,?,?)}", tipo, concorrencia);
            cstdados.setString(1, t.getCodigo());
            cstdados.setString(2, t.getDisciplina());
            cstdados.setString(3, t.getTurma());
            cstdados.setString(4, t.getNome());
            cstdados.setString(5, t.getEmail());
            cstdados.setString(6, t.getpAluno());
            cstdados.setString(7, t.getDescricao());
            cstdados.setString(8, t.getStatus());
            cstdados.setString(9, t.getPrazo());
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
            pstdados = connection.prepareStatement(sqlexcluir, tipo, concorrencia);
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

    public List<Tarefa> listRC(String codigo, int opt) {
        try {
            CriaConexao();
            String query = "";
            switch (opt) {
                case 1:
                    query = sqlconsultatarefaRegistro;
                    break;
                case 2:
                    query = sqlconsultatarefaStatus;
                    break;
                case 3:
                    query = sqllistatarefa;
                    break;
                default:
                    break;
            }
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(query, tipo, concorrencia);
            if (opt != 3) {
                pstdados.setString(1, codigo);
            }
            rsdados = pstdados.executeQuery();
            List<Tarefa> tarefas = new ArrayList<>();
            while (rsdados.next()) {
                Tarefa t = new Tarefa();
                t.setDescricao(rsdados.getString("tarefa_descricao"));
                t.setStatus(rsdados.getString("tarefa_status"));
                t.setPrazo(rsdados.getString("tarefa_prazo"));
                t.setNome(rsdados.getString("professor_nome"));
                t.setEmail(rsdados.getString("professor_email"));
                t.setpAluno(rsdados.getString("professor_paluno"));
                t.setDisciplina(rsdados.getString("disciplina_nome"));
                t.setCodigo(rsdados.getString("disciplina_codigo"));
                t.setTurma(rsdados.getString("disciplina_turma"));
                tarefas.add(t);
            }
            return tarefas;
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

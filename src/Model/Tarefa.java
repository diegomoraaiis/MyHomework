package Model;

import java.util.StringTokenizer;

/**
 *
 * @author diego
 */
public class Tarefa extends Professor implements java.io.Serializable {
    //Declaração de variáveis
    private static long serialVersionUID = 95956251L;
    private String descricao;
    private String status;
    private String prazo;

    // contrutor
    public Tarefa(String nome, String status, String prazo) {
        this.descricao = nome;
        this.status = status;
        this.prazo = prazo;
    }
    
    // getters e setters
    public Tarefa() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    @Override
    public String toString() {
        return disciplina + ";" + codigo + ";" + turma + ";" + nome + ";" + email + ";" + pAluno + ";" + descricao + ";" + status + ";" + prazo + ";\n";
    }

    public String retornaTarefa() {

        return "+++++++++++++++++++++++++\nDisciplina..:" + disciplina + "\nCódigo..:" + codigo + "\nTurma..:" + turma + "\nProfessor..:" + nome + "\nEmail..:" + email + "\nPaluno..:" + pAluno + "\nDescrição..:" + descricao + "\nStatus..:" + status + "\nPrazo..:" + prazo + "\n+++++++++++++++++++++++++\n\n";
    }

}

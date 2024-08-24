/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.StringTokenizer;

/**
 *
 * @author diego
 */
public class Disciplina implements java.io.Serializable {

    //Declaração das variáveis
    private static long serialVersionUID = 23213L;
    protected String disciplina;
    protected String codigo;
    protected String turma;

    // metodo contrutor
    public Disciplina(StringTokenizer st) {
        this.disciplina = st.nextToken();
        this.codigo = st.nextToken();
        this.turma = st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
    }

    public Disciplina() {

    }

    // getters e setters
    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String nome) {
        this.disciplina = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Tarefa{"
                + "codigo='" + codigo + '\''
                + ", disciplina='" + disciplina + '\''
                + ", turma='" + turma + '\''
                + '}';
    }

    public String retornaDisciplinas() {
        return "Disciplina\n-=-=-=-=-==--=-=-= \n" + "Disciplina: " + disciplina + "\nCodigo: " + codigo + "\nTurma: " + turma + "\n-=-=-=-=-==--=-=-=\n\n";
    }

}

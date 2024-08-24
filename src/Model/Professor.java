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
public class Professor extends Disciplina implements java.io.Serializable {

    //Declaração de variáveis
    protected String nome;
    protected String email;
    protected String pAluno;

    // Declaração do contrutor
    public Professor(StringTokenizer st) {
        st.nextToken();
        st.nextToken();
        st.nextToken();
        this.nome = st.nextToken();
        this.email = st.nextToken();
        this.pAluno = st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
    }

    public Professor() {

    }
    // Declaração getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpAluno() {
        return pAluno;
    }

    public void setpAluno(String pAluno) {
        this.pAluno = pAluno;
    }

    @Override
    public String toString() {
        return "Tarefa{"
                + ", nome='" + nome + '\''
                + ", Email='" + email + '\''
                + ", PAluno='" + pAluno + '\''
                + '}';
    }

    public String retornaProfesores() {
        return "Professor(a)\n-=-=-=-=-==--=-=-= \n" + "nome: " + nome + "\nEmail: " + email + "\nPAluno: " + pAluno + "\n-=-=-=-=-==--=-=-=\n\n";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

//import Controller.ControllerProfessor;
import DAO.TarefaDao;
import Model.Tarefa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import View.Professores;
import View.Disciplinas;
import View.ConsultarPorRegistro;

/**
 *
 * @author diego
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    public void tabTarefas() {
        //Criando a tabela para mostrar as informações.
        TarefaDao ct = new TarefaDao();
        List<Tarefa> taref = new ArrayList();
        taref = ct.listRC(null, 3);
        DefaultTableModel TabTarefa = new DefaultTableModel();
        TabTarefa = (DefaultTableModel) jTableListar.getModel();
        TabTarefa.setRowCount(0);
        int i = 0;
        for (Tarefa t : taref) {
            TabTarefa.insertRow(i, new Object[]{t.getDisciplina(), t.getStatus(), t.getPrazo(), t.getDescricao(), t.getCodigo()});
            i++;
        }
    }

    public void validaDados() {
        String disciplina = jTextFieldDisciplina.getText().trim();
        String descricao = jTextFieldDescricao.getText().trim();
        String nomeProfessor = jTextFieldNomeProfessor.getText().trim();
        String status = jComboBoxStatus.getSelectedItem().toString();
        String prazo = jTextFieldPrazo.getText().trim();
        String turma = jTextFieldTurma.getText().trim();
        String pAluno = jTextFieldPaluno.getText().trim();
        String codigo = jTextFieldCodigo.getText().trim();
        String email = jTextFieldEmail.getText().trim();
        if (disciplina.isEmpty() || descricao.isEmpty() || nomeProfessor.isEmpty() || status.isEmpty() || prazo.isEmpty() || turma.isEmpty() || pAluno.isEmpty() || codigo.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            Tarefa tarefa = new Tarefa();
            TarefaDao ct = new TarefaDao();
            if (ct.listRC(jTextFieldCodigo.getText(), 1).isEmpty()) {
                tarefa.setDisciplina(jTextFieldDisciplina.getText());
                tarefa.setDescricao(jTextFieldDescricao.getText());
                tarefa.setNome(jTextFieldNomeProfessor.getText());
                tarefa.setStatus(jComboBoxStatus.getSelectedItem().toString());
                tarefa.setPrazo(jTextFieldPrazo.getText());
                tarefa.setTurma(jTextFieldTurma.getText());
                tarefa.setpAluno(jTextFieldPaluno.getText());
                tarefa.setCodigo(jTextFieldCodigo.getText());
                tarefa.setEmail(jTextFieldEmail.getText());
                ct.Inserir(tarefa);
                JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos(1);
            } else {
                JOptionPane.showMessageDialog(null, "Codigo já existe!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                limparCampos(2);
            }

        }
    }

    public void limparCampos(int valor) {
        if (valor == 1) {
            jTextFieldDisciplina.setText("");
            jTextFieldTurma.setText("");
            jTextFieldDescricao.setText("");
            jTextFieldCodigo.setText("");
            jTextFieldPaluno.setText("");
            jTextFieldEmail.setText("");
            jTextFieldNomeProfessor.setText("");
            jTextFieldPrazo.setText("");
            jTextFieldDisciplina.requestFocus();
        } else if (valor == 2) {
            jTextFieldCodigo.setText("");
            jTextFieldCodigo.requestFocus();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelMHW = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListar = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldTurma = new javax.swing.JTextField();
        jTextFieldNomeProfessor = new javax.swing.JTextField();
        jTextFieldDisciplina = new javax.swing.JTextField();
        jLabelDisciplina = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelTurma = new javax.swing.JLabel();
        jLabelNomeProfessor = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldPaluno = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jLabelPaluno = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldDescricao = new javax.swing.JTextField();
        jLabelPrazp = new javax.swing.JLabel();
        jTextFieldPrazo = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jLabelMyHomework = new javax.swing.JLabel();
        jButtonLimpar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonConsultar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonListarTarefas = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jSeparator7 = new javax.swing.JSeparator();
        jButtonProfessores = new javax.swing.JButton();
        jButtonDisciplinas = new javax.swing.JButton();
        jButtonBuscarRegistros = new javax.swing.JButton();
        jButtonrRelatorios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(100);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabelMHW.setFont(new java.awt.Font("Reem Kufi", 1, 50)); // NOI18N
        jLabelMHW.setForeground(new java.awt.Color(255, 0, 51));
        jLabelMHW.setText("M H W");

        jTableListar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTableListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Disciplina", "Status", "Prazo", "Descrição", "CÓD. / ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableListar.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(jTableListar);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("INSERIR TAREFA");

        jTextFieldCodigo.setBackground(new java.awt.Color(255, 204, 204));
        jTextFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoActionPerformed(evt);
            }
        });

        jTextFieldNomeProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeProfessorActionPerformed(evt);
            }
        });

        jLabelDisciplina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelDisciplina.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDisciplina.setText("DISCIPLINA");

        jLabelCodigo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelCodigo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCodigo.setText("CÓDIGO/ID");

        jLabelTurma.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelTurma.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTurma.setText("TURMA");

        jLabelNomeProfessor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelNomeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeProfessor.setText("PROFESSOR");

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmail.setText("EMAIL");

        jLabelPaluno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelPaluno.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPaluno.setText("PALUNO");

        jLabelDescricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelDescricao.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescricao.setText("DESCRIÇÃO");

        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setText("STATUS");

        jLabelPrazp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelPrazp.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPrazp.setText("PRAZO");

        jButtonCadastrar.setBackground(new java.awt.Color(255, 0, 0));
        jButtonCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabelMyHomework.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMyHomework.setText("My homework");

        jButtonLimpar.setBackground(new java.awt.Color(255, 0, 0));
        jButtonLimpar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonLimpar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonConsultar.setBackground(new java.awt.Color(255, 0, 0));
        jButtonConsultar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonConsultar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConsultar.setText("CONSULTA POR STATUS");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jButtonEditar.setBackground(new java.awt.Color(255, 0, 0));
        jButtonEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonEditar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditar.setText("EDITAR");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonExcluir.setBackground(new java.awt.Color(255, 0, 0));
        jButtonExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonExcluir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExcluir.setText("EXCLUIR");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonListarTarefas.setBackground(new java.awt.Color(255, 0, 0));
        jButtonListarTarefas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonListarTarefas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListarTarefas.setText("LISTAR TAREFA");
        jButtonListarTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarTarefasActionPerformed(evt);
            }
        });

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fazer", "Fazendo", "Feito", " " }));

        jButtonProfessores.setBackground(new java.awt.Color(255, 0, 0));
        jButtonProfessores.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonProfessores.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProfessores.setText("PROFESSORES");
        jButtonProfessores.setMaximumSize(new java.awt.Dimension(79, 23));
        jButtonProfessores.setMinimumSize(new java.awt.Dimension(79, 23));
        jButtonProfessores.setPreferredSize(new java.awt.Dimension(79, 23));
        jButtonProfessores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfessoresActionPerformed(evt);
            }
        });

        jButtonDisciplinas.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDisciplinas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDisciplinas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDisciplinas.setText("DISCIPLINAS");
        jButtonDisciplinas.setMaximumSize(new java.awt.Dimension(79, 23));
        jButtonDisciplinas.setMinimumSize(new java.awt.Dimension(79, 23));
        jButtonDisciplinas.setPreferredSize(new java.awt.Dimension(79, 23));
        jButtonDisciplinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisciplinasActionPerformed(evt);
            }
        });

        jButtonBuscarRegistros.setBackground(new java.awt.Color(255, 0, 0));
        jButtonBuscarRegistros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonBuscarRegistros.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBuscarRegistros.setText("CONSULTA POR REGISTRO");
        jButtonBuscarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarRegistrosActionPerformed(evt);
            }
        });

        jButtonrRelatorios.setBackground(new java.awt.Color(255, 0, 0));
        jButtonrRelatorios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonrRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        jButtonrRelatorios.setText("GERAR RELATÓRIOS");
        jButtonrRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonrRelatoriosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabelMHW))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabelMyHomework))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonConsultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonListarTarefas, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(jSeparator7)
                                    .addComponent(jButtonBuscarRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonrRelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jButtonDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelNomeProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabelDescricao)
                                    .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPaluno)
                                    .addComponent(jLabelPrazp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPrazo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPaluno, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNomeProfessor, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTurma, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDisciplina, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxStatus, 0, 189, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(70, 70, 70))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelDisciplina))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCodigo))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelTurma))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNomeProfessor))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEmail))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldPaluno, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPaluno))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPrazp))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelDescricao))
                                        .addGap(24, 24, 24)
                                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelStatus, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(39, 41, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelMHW, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMyHomework, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonListarTarefas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonrRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonListarTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarTarefasActionPerformed
        tabTarefas();
    }//GEN-LAST:event_jButtonListarTarefasActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        new Excluir().setVisible(true);
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        new Editar().setVisible(true);
        JOptionPane.showMessageDialog(null, "O código da tarefa não poderá ser mudado. Favor inserir um código existente", "Observação", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        new Consultar().setVisible(true);
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparCampos(1);
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        // TODO add your handling code here:
        validaDados();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jTextFieldNomeProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeProfessorActionPerformed

    private void jButtonProfessoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProfessoresActionPerformed
        new Professores().setVisible(true);
    }//GEN-LAST:event_jButtonProfessoresActionPerformed

    private void jButtonDisciplinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisciplinasActionPerformed
        new Disciplinas().setVisible(true);
    }//GEN-LAST:event_jButtonDisciplinasActionPerformed

    private void jButtonBuscarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarRegistrosActionPerformed
        // TODO add your handling code here:
        new ConsultarPorRegistro().setVisible(true);
    }//GEN-LAST:event_jButtonBuscarRegistrosActionPerformed

    private void jTextFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoActionPerformed

    private void jButtonrRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonrRelatoriosActionPerformed
        new JanelaRelatorio().setVisible(true);
    }//GEN-LAST:event_jButtonrRelatoriosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarRegistros;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonDisciplinas;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonListarTarefas;
    private javax.swing.JButton jButtonProfessores;
    private javax.swing.JButton jButtonrRelatorios;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelMHW;
    private javax.swing.JLabel jLabelMyHomework;
    private javax.swing.JLabel jLabelNomeProfessor;
    private javax.swing.JLabel jLabelPaluno;
    private javax.swing.JLabel jLabelPrazp;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTurma;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTableListar;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldDisciplina;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNomeProfessor;
    private javax.swing.JTextField jTextFieldPaluno;
    private javax.swing.JTextField jTextFieldPrazo;
    private javax.swing.JTextField jTextFieldTurma;
    // End of variables declaration//GEN-END:variables
}

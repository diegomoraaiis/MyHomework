/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.ProfessorDao;
import Model.Professor;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Professores extends javax.swing.JFrame {

    /**
     * Creates new form Professores
     */
    public Professores() {
        initComponents();
    }

    public void listar(int opt, String valor) {
        ProfessorDao p = new ProfessorDao();

        if (valor == null) {
            List<Professor> professores = p.listRC(null, opt);
            if (professores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há Professores cadastrados!.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            String texto = "";
            for (Professor pro : professores) {
                texto = texto + pro.retornaProfesores();
            }
            jTextAreaProfessores.setText(texto);
        } else {
            List<Professor> professores = p.listRC(valor, opt);
            if (professores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há Professores cadastrados!.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            String texto = "";
            for (Professor pro : professores) {
                texto = texto + pro.retornaProfesores();
            }
            jTextAreaProfessores.setText(texto);
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

        jPaneProfessores = new javax.swing.JPanel();
        jLabelTituloProfessores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaProfessores = new javax.swing.JTextArea();
        jButtonListarProfessores = new javax.swing.JButton();
        jButtonListarListarDisciplinasPorRegistro = new javax.swing.JButton();
        jTextFieldListarPorRegistro = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jPaneProfessores.setBackground(new java.awt.Color(102, 102, 102));

        jLabelTituloProfessores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTituloProfessores.setForeground(new java.awt.Color(255, 0, 51));
        jLabelTituloProfessores.setText("PROFESSORES");

        jTextAreaProfessores.setColumns(20);
        jTextAreaProfessores.setRows(5);
        jTextAreaProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextAreaProfessoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaProfessores);

        jButtonListarProfessores.setBackground(new java.awt.Color(255, 0, 51));
        jButtonListarProfessores.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonListarProfessores.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListarProfessores.setText("LISTAR TUDO");
        jButtonListarProfessores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarProfessoresActionPerformed(evt);
            }
        });

        jButtonListarListarDisciplinasPorRegistro.setBackground(new java.awt.Color(255, 0, 51));
        jButtonListarListarDisciplinasPorRegistro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonListarListarDisciplinasPorRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListarListarDisciplinasPorRegistro.setText("BUSCAR POR REGISTRO");
        jButtonListarListarDisciplinasPorRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarListarDisciplinasPorRegistroActionPerformed(evt);
            }
        });

        jTextFieldListarPorRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldListarPorRegistroActionPerformed(evt);
            }
        });

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmail.setText("Email");

        javax.swing.GroupLayout jPaneProfessoresLayout = new javax.swing.GroupLayout(jPaneProfessores);
        jPaneProfessores.setLayout(jPaneProfessoresLayout);
        jPaneProfessoresLayout.setHorizontalGroup(
            jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                .addGroup(jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                                .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTituloProfessores))
                            .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                                .addComponent(jTextFieldListarPorRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonListarListarDisciplinasPorRegistro)))
                        .addGap(66, 66, 66)
                        .addComponent(jButtonListarProfessores)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPaneProfessoresLayout.setVerticalGroup(
            jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                .addGroup(jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneProfessoresLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelTituloProfessores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneProfessoresLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPaneProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonListarProfessores)
                    .addComponent(jButtonListarListarDisciplinasPorRegistro)
                    .addComponent(jTextFieldListarPorRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneProfessores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPaneProfessores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextAreaProfessoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaProfessoresMouseClicked

    }//GEN-LAST:event_jTextAreaProfessoresMouseClicked

    private void jButtonListarProfessoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarProfessoresActionPerformed
        listar(2, null);
    }//GEN-LAST:event_jButtonListarProfessoresActionPerformed

    private void jButtonListarListarDisciplinasPorRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarListarDisciplinasPorRegistroActionPerformed
        listar(1, jTextFieldListarPorRegistro.getText());
    }//GEN-LAST:event_jButtonListarListarDisciplinasPorRegistroActionPerformed

    private void jTextFieldListarPorRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldListarPorRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldListarPorRegistroActionPerformed

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
            java.util.logging.Logger.getLogger(Professores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Professores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Professores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Professores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Professores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonListarListarDisciplinasPorRegistro;
    private javax.swing.JButton jButtonListarProfessores;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelTituloProfessores;
    private javax.swing.JPanel jPaneProfessores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaProfessores;
    private javax.swing.JTextField jTextFieldListarPorRegistro;
    // End of variables declaration//GEN-END:variables
}

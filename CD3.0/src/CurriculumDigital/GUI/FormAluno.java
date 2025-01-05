/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CurriculumDigital.GUI;

import CurriculumDigital.Core.CD_exe;
import CurriculumDigital.Core.Evento;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

/**
 *
 * @author cristiane
 */
public class FormAluno extends javax.swing.JFrame {

    String myUser;
    // Lista de eventos que é mantida durante a execução
    private List<Evento> lstBuffer;
    private DefaultListModel listModel;
    private CD_exe sistema;
    List<Evento> eventosDoAluno;

    /**
     * Creates new form FormAluno
     */
    public FormAluno(String u, List<Evento> eventosDoAluno) throws Exception {
        this(); // Chama o construtor padrão
        this.myUser = u;
        this.pessoaField.setText(u); // Preenche o campo entidade com o nome do utilizador
        this.eventosDoAluno = eventosDoAluno;
        // Atualiza a interface com os eventos do utilizador logado
        atualizarInterface();
    }

    private void atualizarInterface() throws Exception {

        // Atualiza o modelo do JList (lstEventos)
        listModel.clear(); // Limpa a lista atual
        for (Evento evento : eventosDoAluno) {
            listModel.addElement(evento.toString());

        }

        // Atualiza o JList com o novo modelo
        lstEventos.setModel(listModel);
    }

    public FormAluno() {
        initComponents();
        // Inicializar o modelo da lista
        listModel = new DefaultListModel();

        // Associar o listModel à lista de pessoas
        lstEventos.setModel(listModel);

        // Inicializar a barra de pesquisa
        inicializarPesquisa();
        setLocationRelativeTo(null);

    }

    private void inicializarPesquisa() {
        barraPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLista();
            }
        });
    }

    private void filtrarLista() {
        String textoPesquisa = barraPesquisa.getText().toLowerCase();
        DefaultListModel<String> modeloFiltrado = new DefaultListModel<>();

        // Filtrar os itens com base no texto
        for (int i = 0; i < listModel.getSize(); i++) {
            String item = (String) listModel.getElementAt(i); // Converte explicitamente para String
            if (item.toLowerCase().contains(textoPesquisa)) {
                modeloFiltrado.addElement(item);
            }
        }

        // Atualizar a JList com o modelo filtrado
        lstEventos.setModel(modeloFiltrado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPAlunos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstEventos = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCurriculums = new javax.swing.JTextArea();
        barraPesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pessoaField = new javax.swing.JLabel();
        btLogout1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPAlunos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lstEventos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEventosValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstEventos);

        jPAlunos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 520, 350));

        txtCurriculums.setEditable(false);
        txtCurriculums.setColumns(20);
        txtCurriculums.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtCurriculums.setRows(5);
        txtCurriculums.setBorder(javax.swing.BorderFactory.createTitledBorder("Curriculums"));
        txtCurriculums.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCurriculumsKeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(txtCurriculums);

        jPAlunos.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 550, 390));
        jPAlunos.add(barraPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 360, 30));

        jLabel2.setText("Pesquisa por entidade:");
        jPAlunos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 40));

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.hoverBorderColor"));

        pessoaField.setFont(new java.awt.Font("Trattatello", 1, 24)); // NOI18N
        pessoaField.setText("Aluno");
        pessoaField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 204, 255), null, null));

        btLogout1.setText("LogOut");
        btLogout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogout1btAcercaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trattatello", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Curriculum Digital");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pessoaField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(pessoaField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addComponent(btLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(397, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPAlunos, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstEventosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEventosValueChanged
        // TODO add your handling code here:
        // Capturar a pessoa selecionada

    }//GEN-LAST:event_lstEventosValueChanged

    private void txtCurriculumsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCurriculumsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurriculumsKeyTyped

    private void btLogout1btAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogout1btAcercaActionPerformed
        try {
            new FormLoginAntigo().setVisible(true); // Exibe o formulário de login
            this.dispose(); // Fecha o formulário atual
        } catch (Exception ex) {
            Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLogout1btAcercaActionPerformed

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
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barraPesquisa;
    private javax.swing.JButton btLogout1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPAlunos;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JList<String> lstEventos;
    private javax.swing.JLabel pessoaField;
    private javax.swing.JTextArea txtCurriculums;
    // End of variables declaration//GEN-END:variables
}

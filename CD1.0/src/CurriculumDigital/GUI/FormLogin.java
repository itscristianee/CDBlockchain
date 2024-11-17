/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CurriculumDigital.GUI;

import CurriculumDigital.Core.User;
import java.io.File;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author cristiane
 */
public class FormLogin extends javax.swing.JFrame {

    /**
     * Creates new form FormLogin
     */
    public FormLogin() throws Exception {

        initComponents();
        loadUsersFromFiles();
        // Centralizar o formulário na tela
        setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLoginPass = new javax.swing.JPasswordField();
        txtLoginUser = new javax.swing.JTextField();
        btLogin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPublicKey = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstUsers = new javax.swing.JList<>();
        panel2 = new java.awt.Panel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRegisterUser = new javax.swing.JTextField();
        txtRegisterPass = new javax.swing.JPasswordField();
        btRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setText("Username");

        jLabel2.setText("password");

        txtLoginPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtLoginUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btLogin.setText("Login");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        txtPublicKey.setEditable(false);
        txtPublicKey.setColumns(20);
        txtPublicKey.setRows(5);
        jScrollPane1.setViewportView(txtPublicKey);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));

        lstUsers.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lstUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstUsersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstUsers);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtLoginPass, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoginPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Login", panel1);

        jLabel4.setText("password");

        jLabel3.setText("Username");

        txtRegisterUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtRegisterPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btRegister.setText("Registar");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRegisterPass))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRegisterUser, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRegisterUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRegisterPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btRegister)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registar", panel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed

        try {

            // Verifica se o campo de login ou senha está vazio
            if (txtLoginUser.getText().isEmpty() || txtLoginPass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos"); // Corrigido o showMessageDialog
                return;  // Sai do método se os campos não estiverem preenchidos
            }

            User u = new User(txtLoginUser.getText());
            u.load(new String(txtLoginPass.getPassword()));

            String pub = Base64.getEncoder().encodeToString(u.getPubKey().getEncoded());
            txtPublicKey.setText(pub);
            new FormCD(u).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "User errado");
        }

    }//GEN-LAST:event_btLoginActionPerformed

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        
        
        try {

            // Verifica se o campo de login ou senha está vazio
            if (txtRegisterUser.getText().isEmpty() || txtRegisterPass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos"); // Corrigido o showMessageDialog
                return;  // Sai do método se os campos não estiverem preenchidos
            }
            User u = new User(txtRegisterUser.getText());
            u.generateKeys();
            u.save(new String(txtRegisterPass.getPassword()));
            JOptionPane.showMessageDialog(this, "User criado!");

            // Atualiza a lista de usuários
            loadUsersFromFiles();

        } catch (Exception ex) {
            Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao criar o User!");
            txtRegisterUser.setText("");
            txtRegisterPass.setText("");
        }

    }//GEN-LAST:event_btRegisterActionPerformed

    private void lstUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstUsersValueChanged
        if (!evt.getValueIsAdjusting()) {
            // Obter o valor selecionado da lista
            String selectedUser = (String) lstUsers.getSelectedValue();

            // Verificar se há um item selecionado antes de definir o valor no campo de texto
            if (selectedUser != null) {
                txtLoginUser.setText(selectedUser);
            }
        }
    }//GEN-LAST:event_lstUsersValueChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        loadUsersFromFiles();
    }//GEN-LAST:event_jTabbedPane1StateChanged

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
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormLogin().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void loadUsersFromFiles() {
        try {
            // Defina o caminho correto onde os arquivos de usuários estão sendo salvos
            File dir = new File("/Users/cristiane/NetBeansProjects/CurriculumDigitalBlockchain/CD1.0/keys/");

            // Cria um novo modelo para a JList
            DefaultListModel<String> userListModel = new DefaultListModel<>();

            // Filtra os arquivos que possuem a extensão ".pub" (chaves públicas)
            File[] files = dir.listFiles((d, name) -> name.endsWith(".pub"));

            // Verifica se existem arquivos .pub encontrados
            if (files != null && files.length > 0) {
                for (File file : files) {
                    // Extrai o nome do arquivo sem a extensão .pub (nome do usuário)
                    String fileName = file.getName();
                    String userName = fileName.substring(0, fileName.lastIndexOf('.'));

                    // Adiciona o nome do usuário ao modelo
                    userListModel.addElement(userName);
                }

                // Define o modelo da JList com os nomes dos usuários
                lstUsers.setModel(userListModel);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum arquivo de usuário .pub encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários.");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lstUsers;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPasswordField txtLoginPass;
    private javax.swing.JTextField txtLoginUser;
    private javax.swing.JTextArea txtPublicKey;
    private javax.swing.JPasswordField txtRegisterPass;
    private javax.swing.JTextField txtRegisterUser;
    // End of variables declaration//GEN-END:variables
}

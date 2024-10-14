/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CurriculumDigital;

import blockchain.utils.Block;
import blockchain.utils.BlockChain;
import blockchain.utils.MerkleTree;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author cristiane
 */
public class NewJFrame extends javax.swing.JFrame {

    BlockChain bloco;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        bloco = new BlockChain();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jTabbedPanePessoas = new javax.swing.JTabbedPane();
        panel2 = new java.awt.Panel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtNovoBloco = new javax.swing.JTextArea();
        nomePessoaField = new javax.swing.JTextField();
        descricaoEventoField = new javax.swing.JTextField();
        entidadeField = new javax.swing.JTextField();
        spNovoBlockDificuldade = new javax.swing.JSpinner();
        addEventoButton1 = new javax.swing.JButton();
        txtFileName = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        btLoad = new javax.swing.JButton();
        viewCurrListPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstBlockchain = new javax.swing.JList<>();
        merkleGraphics1 = new blockchain.GUI.MerkleGraphics();
        panel3 = new java.awt.Panel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPessoas = new javax.swing.JList<>();
        btnVerCurriculum = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtNovoBloco1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPanePessoas.setToolTipText("");
        jTabbedPanePessoas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPanePessoasStateChanged(evt);
            }
        });

        txtNovoBloco.setEditable(false);
        txtNovoBloco.setColumns(20);
        txtNovoBloco.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtNovoBloco.setRows(5);
        txtNovoBloco.setBorder(javax.swing.BorderFactory.createTitledBorder("Elementos"));
        txtNovoBloco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNovoBlocoKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(txtNovoBloco);

        nomePessoaField.setToolTipText("");
        nomePessoaField.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome da Pessoa"));
        nomePessoaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomePessoaFieldActionPerformed(evt);
            }
        });

        descricaoEventoField.setToolTipText("");
        descricaoEventoField.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Evento"));
        descricaoEventoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoEventoFieldActionPerformed(evt);
            }
        });

        entidadeField.setToolTipText("");
        entidadeField.setBorder(javax.swing.BorderFactory.createTitledBorder("Entidade Certificadora"));
        entidadeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entidadeFieldActionPerformed(evt);
            }
        });

        spNovoBlockDificuldade.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        spNovoBlockDificuldade.setModel(new javax.swing.SpinnerNumberModel(3, 1, 7, 1));
        spNovoBlockDificuldade.setBorder(javax.swing.BorderFactory.createTitledBorder("Dificulty"));

        addEventoButton1.setText("Adicionar Evento(s)");
        addEventoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventoButton1ActionPerformed(evt);
            }
        });

        txtFileName.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtFileName.setText("./blockchain.obj");
        txtFileName.setBorder(javax.swing.BorderFactory.createTitledBorder("File Name"));
        txtFileName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFileNameKeyTyped(evt);
            }
        });

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        btLoad.setText("Load");
        btLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descricaoEventoField, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(entidadeField)
                    .addComponent(nomePessoaField)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(spNovoBlockDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addEventoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addComponent(txtFileName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(nomePessoaField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(descricaoEventoField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entidadeField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spNovoBlockDificuldade, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(addEventoButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6)))
                .addContainerGap())
        );

        spNovoBlockDificuldade.getAccessibleContext().setAccessibleName("Dificuldade");
        txtFileName.getAccessibleContext().setAccessibleName("Nome do Ficheiro");

        jTabbedPanePessoas.addTab("tab1", panel2);

        lstBlockchain.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBlockchainValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstBlockchain);

        javax.swing.GroupLayout merkleGraphics1Layout = new javax.swing.GroupLayout(merkleGraphics1);
        merkleGraphics1.setLayout(merkleGraphics1Layout);
        merkleGraphics1Layout.setHorizontalGroup(
            merkleGraphics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        merkleGraphics1Layout.setVerticalGroup(
            merkleGraphics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(500, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(187, Short.MAX_VALUE)
                    .addComponent(merkleGraphics1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(merkleGraphics1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout viewCurrListPanelLayout = new javax.swing.GroupLayout(viewCurrListPanel);
        viewCurrListPanel.setLayout(viewCurrListPanelLayout);
        viewCurrListPanelLayout.setHorizontalGroup(
            viewCurrListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCurrListPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        viewCurrListPanelLayout.setVerticalGroup(
            viewCurrListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCurrListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPanePessoas.addTab("ViewCurr", viewCurrListPanel);

        lstPessoas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPessoasValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstPessoas);

        btnVerCurriculum.setText("Ver Curriculum");
        btnVerCurriculum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCurriculumActionPerformed(evt);
            }
        });

        txtNovoBloco1.setEditable(false);
        txtNovoBloco1.setColumns(20);
        txtNovoBloco1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtNovoBloco1.setRows(5);
        txtNovoBloco1.setBorder(javax.swing.BorderFactory.createTitledBorder("Curriculums"));
        txtNovoBloco1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNovoBloco1KeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(txtNovoBloco1);
        txtNovoBloco1.getAccessibleContext().setAccessibleName("Curriculums");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(btnVerCurriculum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        btnVerCurriculum.getAccessibleContext().setAccessibleName("Ver Curriculum");

        jTabbedPanePessoas.addTab("tab3", panel3);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPanePessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanePessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPanePessoas.getAccessibleContext().setAccessibleName("AddEvento");
        jTabbedPanePessoas.getAccessibleContext().setAccessibleDescription("AddEvento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entidadeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entidadeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entidadeFieldActionPerformed

    private void descricaoEventoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoEventoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoEventoFieldActionPerformed

    private void nomePessoaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomePessoaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomePessoaFieldActionPerformed

    private void txtNovoBlocoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNovoBlocoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovoBlocoKeyTyped

    private void addEventoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventoButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String nomePessoa = nomePessoaField.getText();
            String descricao = descricaoEventoField.getText();
            String entidade = entidadeField.getText();

            if (!nomePessoa.isEmpty() && !descricao.isEmpty() && !entidade.isEmpty()) {
                /**
                 * Duvidas: Um bloco pode ter uma lista de eventos? - como seria
                 * se tivesse? - os dados seriam adicionados a blockchain no
                 * final?
                 */

                // Cria o evento e o formata como string
                Evento evento = new Evento(nomePessoa, descricao, entidade);
                String eventoStr = evento.toString(); // Formatação do evento

                // Adiciona o evento ao campo de texto para visualização
                txtNovoBloco.append(eventoStr + "\n"); // Usa "\n" para adicionar uma quebra de linha real.

                // Divide os elementos por linha (caso tenha múltiplos eventos)
                String[] elementos = txtNovoBloco.getText().split("\\n");

                // Cria a Merkle Tree para os elementos adicionados
                MerkleTree mt = new MerkleTree(elementos);
                merkleGraphics1.setMerkle(mt);

                /**
                 * Duvida: Armazena o evento real (não o hash da Merkle Tree) no
                 * bloco - Guardar o hash(mt.getRoot()) ou evento?
                 */
                bloco.add(eventoStr, (int) spNovoBlockDificuldade.getValue());

                // Salva a Merkle Tree em um arquivo (mantém esta funcionalidade)
                mt.saveToFile(bloco.getLastBlockHash() + ".mkt");

                // Atualiza a lista gráfica de blocos
                DefaultListModel model = new DefaultListModel();
                for (Block elem : bloco.getChain()) {
                    model.addElement(elem);
                }

                lstBlockchain.setModel(model);

            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addEventoButton1ActionPerformed

    private void txtFileNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFileNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFileNameKeyTyped

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser(new File("."));

        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                bloco.save(fc.getSelectedFile().getAbsolutePath());
                txtFileName.setText(fc.getSelectedFile().getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoadActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser(new File(txtFileName.getText()));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                // Carregar a blockchain a partir do arquivo selecionado
                bloco.load(fc.getSelectedFile().getAbsolutePath());
                txtFileName.setText(fc.getSelectedFile().getAbsolutePath());

                // Limpar o campo de texto para garantir que ele esteja vazio antes de listar os eventos
                txtNovoBloco.setText("");

                // Atualizar a lista gráfica de blocos
                DefaultListModel model = new DefaultListModel();
                for (Block elem : bloco.getChain()) {
                    model.addElement(elem);

                    // Use o método getData() para acessar o conteúdo de 'data' no bloco
                    String eventoStr = elem.getData();  // Agora usando o getter
                    txtNovoBloco.append(eventoStr + "\n");
                }

                lstBlockchain.setModel(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btLoadActionPerformed

    private void lstBlockchainValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBlockchainValueChanged
        // TODO add your handling code here:
        try {
            Block b = (Block) lstBlockchain.getSelectedValues()[0];
            MerkleTree mt = MerkleTree.loadFromFile(b.calculateHash() + ".mkt");
            //txtNovoBloco.setText(mt.getElementsString());
            merkleGraphics1.setMerkle(mt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstBlockchainValueChanged

    private void jTabbedPanePessoasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPanePessoasStateChanged
        // TODO add your handling code here:

        // Verifica se 'bloco' foi inicializado
        if (bloco == null) {
            return;  // Saia do método se bloco for nulo
        }

        // Usamos um Set para evitar nomes duplicados
        Set<String> nomesPessoas = new HashSet<>();

        // Percorre todos os blocos na blockchain
        for (Block bloco : bloco.getChain()) {
            // O campo data contém a string no formato "nomePessoa | descricao | entidade"
            String eventoStr = bloco.getData();  // Obtém a string de dados do bloco

            // Verifica se o campo data contém a estrutura correta
            if (eventoStr != null && !eventoStr.isEmpty()) {
                // Divide a string para separar os campos (nomePessoa, descricao, entidade)
                String[] partesEvento = eventoStr.split(" \\| ");
                if (partesEvento.length > 0) {
                    String nomePessoa = partesEvento[0];  // Nome da pessoa está na primeira parte
                    nomesPessoas.add(nomePessoa);  // Adiciona o nome ao Set (sem duplicatas)
                }
            }
        }
        
        // Criar o modelo para a JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String nome : nomesPessoas) {
            listModel.addElement(nome);
        }
        lstPessoas.setModel(listModel);
    }//GEN-LAST:event_jTabbedPanePessoasStateChanged

    private void lstPessoasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPessoasValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lstPessoasValueChanged

    private void txtNovoBloco1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNovoBloco1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovoBloco1KeyTyped

    private void btnVerCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCurriculumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerCurriculumActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventoButton1;
    private javax.swing.JButton btLoad;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btnVerCurriculum;
    private javax.swing.JTextField descricaoEventoField;
    private javax.swing.JTextField entidadeField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPanePessoas;
    private javax.swing.JList<String> lstBlockchain;
    private javax.swing.JList<String> lstPessoas;
    private blockchain.GUI.MerkleGraphics merkleGraphics1;
    private javax.swing.JTextField nomePessoaField;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private javax.swing.JSpinner spNovoBlockDificuldade;
    private javax.swing.JTextField txtFileName;
    private javax.swing.JTextArea txtNovoBloco;
    private javax.swing.JTextArea txtNovoBloco1;
    private javax.swing.JPanel viewCurrListPanel;
    // End of variables declaration//GEN-END:variables
}

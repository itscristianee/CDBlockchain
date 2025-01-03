/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CurriculumDigital.GUI;

import CurriculumDigital.Core.CD_exe;
import CurriculumDigital.Core.Evento;
import CurriculumDigital.Core.User;
import blockchain.utils.Block;
import blockchain.utils.MerkleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author cristiane
 */
public class FormCurriculumDigital extends javax.swing.JFrame {

    User myUser;
    // Lista de eventos que é mantida durante a execução
    private List<Evento> lstEventos;
    private List<Evento> lstBuffer;
    private DefaultListModel listModel;
    private CD_exe sistema;

    /**
     * Creates new form NewJFrame
     */
    public FormCurriculumDigital() {
        initComponents();

        // Inicializar o modelo da lista
        listModel = new DefaultListModel();

        // Associar o listModel à lista de pessoas
        lstPessoas.setModel(listModel);

        String blockchainFile = "./blockchain.obj";
        sistema = new CD_exe(blockchainFile);

        try {
            sistema.load(blockchainFile);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar blockchain: " + e.getMessage());
        }

        // Inicializar a barra de pesquisa
        inicializarPesquisa();
        lstBuffer = new ArrayList<>();
        lstEventos = new ArrayList<>();
        jPAdd.setVisible(true);
        jPVerProva.setVisible(false);
        jPPessoas.setVisible(false);
        setLocationRelativeTo(null);
    }

    public FormCurriculumDigital(User u) throws Exception {
        this(); // Chama o construtor padrão
        this.myUser = u;
        this.entidadeField.setText(u.getName()); // Preenche o campo entidade com o nome do utilizador

        // Atualiza a interface com os eventos do utilizador logado
        atualizarInterface(u.getName());
    }

    private void atualizarInterface(String entidade) throws Exception {
        // Limpa a área de texto antes de exibir os novos eventos
        txtEventos.setText("");

        // Obtém os eventos relacionados à entidade
        List<Evento> eventosFiltrados = sistema.getEventosPorEntidade(entidade);

        // Adiciona os eventos ao campo de texto
        for (Evento evento : eventosFiltrados) {
            // Adicionar o nome da pessoa ao modelo da JList (evitar duplicatas)
            if (!listModel.contains(evento.getNomePessoa())) {
                listModel.addElement(evento.getNomePessoa());
            }
        }
        // Atualizar a JList de nomes
        lstPessoas.setModel(listModel);
        // Atualiza a lista de blocos na interface
        atualizarBlockchainUI();
    }

    private void atualizarBlockchainUI() {
        try {
            // Atualiza a lista gráfica de blocos
            DefaultListModel model = new DefaultListModel();
            for (Block elem : sistema.getBlockchain().getChain()) {
                model.addElement(elem);
            }

            lstBlockchain.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar lista de blocos: " + e.getMessage());
        }
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
        lstPessoas.setModel(modeloFiltrado);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        btPessoas = new javax.swing.JButton();
        btAcerca2 = new javax.swing.JButton();
        btVerProva = new javax.swing.JButton();
        btLogout1 = new javax.swing.JButton();
        jPAdd = new javax.swing.JPanel();
        nomePessoaField = new javax.swing.JTextField();
        descricaoEventoField = new javax.swing.JTextField();
        entidadeField = new javax.swing.JTextField();
        spNovoBlockDificuldade = new javax.swing.JSpinner();
        addEventoButton = new javax.swing.JButton();
        btnGerarBloco = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtEventos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtMinado = new javax.swing.JLabel();
        jPVerProva = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstBlockchain = new javax.swing.JList<>();
        merkleGraphics1 = new blockchain.GUI.MerkleGraphics();
        jPPessoas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPessoas = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCurriculums = new javax.swing.JTextArea();
        barraPesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.hoverBorderColor"));

        jLabel3.setFont(new java.awt.Font("Trattatello", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Curriculum Digital");

        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarjButton1ActionPerformed(evt);
            }
        });

        btPessoas.setText("Pessoas");
        btPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPessoasActionPerformed(evt);
            }
        });

        btAcerca2.setText("Acerca");
        btAcerca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAcerca2btAcercaActionPerformed(evt);
            }
        });

        btVerProva.setText("Ver Prova");
        btVerProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerProvaActionPerformed(evt);
            }
        });

        btLogout1.setText("LogOut");
        btLogout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogout1btAcercaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btVerProva, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAcerca2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btVerProva, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btAcerca2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPAdd.setBackground(new java.awt.Color(255, 255, 255));
        jPAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomePessoaField.setToolTipText("");
        nomePessoaField.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome da Pessoa"));
        nomePessoaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomePessoaFieldActionPerformed(evt);
            }
        });
        jPAdd.add(nomePessoaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 238, -1));

        descricaoEventoField.setToolTipText("");
        descricaoEventoField.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Evento"));
        descricaoEventoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoEventoFieldActionPerformed(evt);
            }
        });
        jPAdd.add(descricaoEventoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 238, -1));

        entidadeField.setEditable(false);
        entidadeField.setToolTipText("");
        entidadeField.setBorder(javax.swing.BorderFactory.createTitledBorder("Entidade Certificadora"));
        entidadeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entidadeFieldActionPerformed(evt);
            }
        });
        jPAdd.add(entidadeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 238, -1));

        spNovoBlockDificuldade.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        spNovoBlockDificuldade.setModel(new javax.swing.SpinnerNumberModel(3, 1, 7, 1));
        spNovoBlockDificuldade.setBorder(javax.swing.BorderFactory.createTitledBorder("Dificulty"));
        jPAdd.add(spNovoBlockDificuldade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 70));

        addEventoButton.setText("Adicionar Evento(s)");
        addEventoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventoButtonActionPerformed(evt);
            }
        });
        jPAdd.add(addEventoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 151, 70));

        btnGerarBloco.setText("Gerar Bloco");
        btnGerarBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarBlocoActionPerformed(evt);
            }
        });
        jPAdd.add(btnGerarBloco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 238, 43));

        txtEventos.setEditable(false);
        txtEventos.setColumns(20);
        txtEventos.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtEventos.setRows(5);
        txtEventos.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de eventos"));
        txtEventos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEventosKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(txtEventos);

        jPAdd.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 16, 330, 410));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome da pessoa | Descrição | Entidade");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPAdd.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 330, -1));

        txtMinado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPAdd.add(txtMinado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 230, 30));

        jPVerProva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lstBlockchain.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstBlockchainValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstBlockchain);

        jPVerProva.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 390));

        javax.swing.GroupLayout merkleGraphics1Layout = new javax.swing.GroupLayout(merkleGraphics1);
        merkleGraphics1.setLayout(merkleGraphics1Layout);
        merkleGraphics1Layout.setHorizontalGroup(
            merkleGraphics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        merkleGraphics1Layout.setVerticalGroup(
            merkleGraphics1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jPVerProva.add(merkleGraphics1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 380, 390));

        jPPessoas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lstPessoas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPessoasValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstPessoas);

        jPPessoas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 390));

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

        jPPessoas.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 60, 360, 390));
        jPPessoas.add(barraPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 360, 30));

        jLabel2.setText("Pesquisa por nome:");
        jPPessoas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPVerProva, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(170, Short.MAX_VALUE)
                    .addComponent(jPPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(378, 378, 378))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPVerProva, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPPessoas, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomePessoaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomePessoaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomePessoaFieldActionPerformed

    private void descricaoEventoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoEventoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoEventoFieldActionPerformed

    private void entidadeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entidadeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entidadeFieldActionPerformed

    private void addEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventoButtonActionPerformed
        // TODO add your handling code here:
// Desabilita os botões enquanto a operação está em andamento
        btnGerarBloco.setEnabled(false);
        addEventoButton.setEnabled(false);

        new Thread(() -> {
            try {
                // Obtém os dados do formulário
                String nomePessoa = nomePessoaField.getText().trim();
                String descricao = descricaoEventoField.getText().trim();

                if (nomePessoa.isEmpty() || descricao.isEmpty()) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos."));
                    return;
                }

                System.out.println("Criando evento...");
                Evento evento = new Evento(myUser, nomePessoa, descricao);

                // Adiciona o evento ao sistema e salva a blockchain
                System.out.println("Adicionando evento ao sistema...");
                sistema.addEvento(evento);

                System.out.println("Salvando blockchain...");

                sistema.save("./blockchain.obj");

                // Atualiza a interface no EDT (Event Dispatch Thread)
                SwingUtilities.invokeLater(() -> {
                    try {
                        atualizarInterface(entidadeField.getText());
                        atualizarBlockchainUI();

                        // Adiciona o nome da pessoa à lista, evitando duplicados
                        if (!listModel.contains(nomePessoa)) {
                            listModel.addElement(nomePessoa);
                            lstPessoas.setModel(listModel); // Atualiza a JList
                        }

                        // Exibe o evento na área de texto
                        txtEventos.append(evento.toString() + "\n");

                        // Limpa os campos de entrada
                        nomePessoaField.setText("");
                        descricaoEventoField.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar interface: " + ex.getMessage());
                    } finally {
                        // Reabilita os botões após a operação
                        addEventoButton.setEnabled(true);
                        btnGerarBloco.setEnabled(true);
                    }
                });

            } catch (Exception ex) {
                // Mostra o erro na GUI
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Erro ao adicionar evento: " + ex.getMessage()));
                ex.printStackTrace();
            } finally {
                // Reabilita os botões em caso de erro
                SwingUtilities.invokeLater(() -> {
                    addEventoButton.setEnabled(true);
                    btnGerarBloco.setEnabled(true);
                });
            }
        }).start();


    }//GEN-LAST:event_addEventoButtonActionPerformed

    private void btnGerarBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarBlocoActionPerformed
        // TODO add your handling code here:

//        new Thread(new Runnable(){
//            @Override
//            public void run(){
//                
//            }
//        }).start();
//        
//        ou, o que é exatamente a mesma coisa
        btnGerarBloco.setEnabled(false);

        new Thread(() -> {
            try {
                sistema.gerarBloco((int) spNovoBlockDificuldade.getValue());

                SwingUtilities.invokeLater(() -> {
                    try {
                        atualizarInterface(entidadeField.getText());
                        atualizarBlockchainUI(); // Atualiza a lista de blocos
                    } catch (Exception ex) {
                        Logger.getLogger(FormCurriculumDigital.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtMinado.setText("Novo bloco gerado!");
                    btnGerarBloco.setEnabled(true);
                });

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar bloco: " + ex.getMessage());
            }
        }).start();


    }//GEN-LAST:event_btnGerarBlocoActionPerformed

    private void txtEventosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEventosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEventosKeyTyped

    private void lstBlockchainValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstBlockchainValueChanged
        // TODO add your handling code here:
        try {

            Block b = (Block) lstBlockchain.getSelectedValues()[0];
            SwingUtilities.invokeLater(() -> {
                try {
                    MerkleTree mt = MerkleTree.loadFromFile(b.calculateHash() + ".mkt");
                    //txtNovoBloco.setText(mt.getElementsString());
                    merkleGraphics1.setMerkle(mt);
                } catch (Exception ex) {
                    Logger.getLogger(FormCurriculumDigital.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(FormCurriculumDigital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lstBlockchainValueChanged

    private void lstPessoasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPessoasValueChanged
        // TODO add your handling code here:
        // Capturar a pessoa selecionada
        String pessoaSelecionada = lstPessoas.getSelectedValue();

        if (pessoaSelecionada != null) {
            txtCurriculums.setText("");

            try {
                for (Evento evento : sistema.getEventos()) {
                    if (evento.getNomePessoa().equals(pessoaSelecionada)) {
                        txtCurriculums.append(evento.toString() + "\n");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao exibir currículos: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_lstPessoasValueChanged

    private void txtCurriculumsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCurriculumsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurriculumsKeyTyped

    private void btVerProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerProvaActionPerformed
        // TODO add your handling code here:

        jPVerProva.setVisible(true);
        jPPessoas.setVisible(false);
        jPAdd.setVisible(false);
    }//GEN-LAST:event_btVerProvaActionPerformed

    private void btAcerca2btAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAcerca2btAcercaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        // Mostra uma janela de diálogo com as informações dos membros do grupo
        JOptionPane.showMessageDialog(this,
                "Trabalho desenvolvido por:\n Cristiane Mayabanza - 24639  \n Joao Campos - 25269 ",
                "Acerca de...",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btAcerca2btAcercaActionPerformed

    private void btPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPessoasActionPerformed
        // TODO add your handling code here:
        jPPessoas.setVisible(true);
        jPVerProva.setVisible(false);
        jPAdd.setVisible(false);
    }//GEN-LAST:event_btPessoasActionPerformed

    private void btAdicionarjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarjButton1ActionPerformed
        // TODO add your handling code here:
        jPAdd.setVisible(true);
        jPPessoas.setVisible(false);
        jPVerProva.setVisible(false);
    }//GEN-LAST:event_btAdicionarjButton1ActionPerformed

    private void btLogout1btAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogout1btAcercaActionPerformed
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            new FormPrincipal().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FormCurriculumDigital.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(FormCurriculumDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCurriculumDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCurriculumDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCurriculumDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCurriculumDigital().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventoButton;
    private javax.swing.JTextField barraPesquisa;
    private javax.swing.JButton btAcerca2;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btLogout1;
    private javax.swing.JButton btPessoas;
    private javax.swing.JButton btVerProva;
    private javax.swing.JButton btnGerarBloco;
    private javax.swing.JTextField descricaoEventoField;
    private javax.swing.JTextField entidadeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPAdd;
    private javax.swing.JPanel jPPessoas;
    private javax.swing.JPanel jPVerProva;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JList<String> lstBlockchain;
    private javax.swing.JList<String> lstPessoas;
    private blockchain.GUI.MerkleGraphics merkleGraphics1;
    private javax.swing.JTextField nomePessoaField;
    private javax.swing.JSpinner spNovoBlockDificuldade;
    private javax.swing.JTextArea txtCurriculums;
    private javax.swing.JTextArea txtEventos;
    private javax.swing.JLabel txtMinado;
    // End of variables declaration//GEN-END:variables
}

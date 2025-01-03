/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resg_Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AuthServerGUI extends JFrame {
    private JTextField txtPort;
    private JTextArea txtLog;
    private JButton btnStart;

    public AuthServerGUI() {
        setTitle("Servidor de Autenticação");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblPort = new JLabel("Porta:");
        lblPort.setBounds(10, 10, 80, 25);
        panel.add(lblPort);

        txtPort = new JTextField("10010");
        txtPort.setBounds(100, 10, 100, 25);
        panel.add(txtPort);

        btnStart = new JButton("Iniciar Servidor");
        btnStart.setBounds(10, 50, 150, 25);
        panel.add(btnStart);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtLog);
        scrollPane.setBounds(10, 90, 360, 160);
        panel.add(scrollPane);

        add(panel);

        // Ação do botão para iniciar o servidor
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int port = Integer.parseInt(txtPort.getText());
                    startServer(port);
                } catch (Exception ex) {
                    appendLog("Erro ao iniciar o servidor: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void startServer(int port) throws Exception {
        LocateRegistry.createRegistry(port); // Inicia o registo RMI na porta especificada
        AuthServiceImpl authService = new AuthServiceImpl();
        String address = "rmi://192.168.1.230:" + port + "/AuthService";
        Naming.rebind(address, authService); // Liga o serviço RMI
        appendLog("Servidor iniciado em: " + address);
        btnStart.setEnabled(false);
    }

    private void appendLog(String message) {
        txtLog.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthServerGUI serverGUI = new AuthServerGUI();
            serverGUI.setVisible(true);
        });
    }
}

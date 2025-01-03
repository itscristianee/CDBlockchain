/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resg_Log;

import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.rmi.RemoteException;

public class AuthServiceImpl extends UnicastRemoteObject implements AuthService {
    private final Map<String, String> users = new HashMap<>(); // Armazena utilizadores e palavras-passe encriptadas

    public AuthServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized boolean register(String username, String password) throws RemoteException {
        if (users.containsKey(username)) {
            return false; // Nome de utilizador já existe
        }
        users.put(username, hashPassword(password));
        System.out.println("Novo utilizador registado: " + username);
        return true;
    }

    @Override
    public synchronized boolean authenticate(String username, String password) throws RemoteException {
        String hashedPassword = users.get(username);
        if (hashedPassword == null) {
            return false; // Utilizador não encontrado
        }
        return hashedPassword.equals(hashPassword(password));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encriptar a palavra-passe", e);
        }
    }
}

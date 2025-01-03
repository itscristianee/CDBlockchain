/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resg_Log;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AuthServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(10010); // Inicia o registo RMI na porta 1099
            AuthServiceImpl authService = new AuthServiceImpl();
            Naming.rebind("rmi://192.168.1.230/AuthService", authService); // Liga o serviço RMI
            System.out.println("Servidor de autenticação iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

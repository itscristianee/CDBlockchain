/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resg_Log;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthService extends Remote {
    boolean register(String username, String password) throws RemoteException;
    boolean authenticate(String username, String password) throws RemoteException;
}

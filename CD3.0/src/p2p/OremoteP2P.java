//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2024   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package p2p;

import CurriculumDigital.Core.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import blockchain.utils.RMI;

/**
 * Created on 27/11/2024, 17:48:32
 *
 * @author manso - computer
 */
public class OremoteP2P extends UnicastRemoteObject implements IremoteP2P {

    

    String address;
    CopyOnWriteArrayList<IremoteP2P> network;
    CopyOnWriteArraySet<String> transactions;
    CopyOnWriteArraySet<User> users ;
    P2Plistener listener;

    public OremoteP2P(String address, P2Plistener listener) throws RemoteException {
        super(RMI.getAdressPort(address));
        this.address = address;
        this.network = new CopyOnWriteArrayList<>();
        this.users = new CopyOnWriteArraySet<>();
        transactions = new CopyOnWriteArraySet<>();
        
        // addNode(this);
        this.listener = listener;
        listener.onStart("Object " + address + " listening");

        System.out.println("Object " + address + " listening");

    }

    @Override
    public String getAdress() throws RemoteException {
        return address;
    }

    private boolean isInNetwork(String adress) {
        for (int i = network.size() - 1; i >= 0; i--) {
            try {
                if (network.get(i).getAdress().equals(adress)) {
                    return true;
                }
            } catch (RemoteException ex) {
                network.remove(i);
            }
        }
        return false;
    }

    @Override
    public void addNode(IremoteP2P node) throws RemoteException {
        //se já tiver o nó 
        // não faz nada
        if (isInNetwork(node.getAdress())) {
            System.out.println("Já tenho o endereço " + node.getAdress());
            return;
        }

        //adicionar o no
        network.add(node);
        listener.onConect(node.getAdress());
        System.out.println("Adicionei o " + node.getAdress());
        node.addNode(this);

        //propagar o no
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addNode(node);

        }

        sinchronizeTransactions(node);
        System.out.println("Rede p2p");
        for (IremoteP2P iremoteP2P : network) {
            System.out.println(iremoteP2P.getAdress());

        }

        // Sincronize usuários
        for (User user : node.getUsers()) {
            this.registerUser(user);
        }

    }

    @Override
    public List<IremoteP2P> getNetwork() throws RemoteException {
        return new ArrayList<>(network);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void addTransaction(String data) throws RemoteException {
        if (transactions.contains(data)) {
            listener.onTransaction("Transaçao repetida " + data);
            System.out.println("Transaçao repetida " + data);
            return;
        }
        transactions.add(data);
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addTransaction(data);
        }

        System.out.println("Transactions");
        for (String t : transactions) {
            System.out.println(t);
        }

    }

    public List<String> getTransactions() throws RemoteException {
        return new ArrayList<>(transactions);
    }

    public void removeTransaction(String data) throws RemoteException {
        if (!transactions.contains(data)) {
            System.out.println("Transaçao Não existe " + data);
            return;
        }
        transactions.remove(data);
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.removeTransaction(data);
        }
        System.out.println("Transactions");
        for (String t : transactions) {
            System.out.println(t);
        }
    }

    public boolean isListEqual(List<String> other) {
        for (String t : transactions) {
            if (!other.contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sinchronizeTransactions(IremoteP2P node) throws RemoteException {
//         if( isListEqual(node.getTransactions()))
//             return;
//         //juntar as transações no set
//         transactions.addAll(node.getTransactions());
//         node.sinchronizeTransactions(this);
//         
//         for (IremoteP2P iremoteP2P : network) {
//             iremoteP2P.sinchronizeTransactions(node);        
//         }
//         
//          System.out.println("Transactions");
//        for (String t : transactions) {
//            System.out.println(t);
//        }
        this.transactions.addAll(node.getTransactions());
        listener.onTransaction(address);

    }

    @Override
    public void registerUser(User user) throws RemoteException {
        if (users.contains(user)) {
            System.out.println("Usuário já registrado: " + user.getName());
            return;
        }
        users.add(user);
        System.out.println("Usuário registrado: " + user.getName());

        // Propaga o novo usuário para os outros nós
        for (IremoteP2P node : network) {
            node.registerUser(user);
        }
    }

    public boolean validateUser(String username, String passwordHash) throws RemoteException {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPasswordHash().equals(passwordHash)) {
                return true;
            }
        }
        return false;
    }

//   s

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * @param users the users to set
     */
    public void setUsers(CopyOnWriteArraySet<User> users) {
        this.users = users;
    }

}

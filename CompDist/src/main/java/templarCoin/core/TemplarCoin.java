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
//::                                                               (c)2020   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package templarCoin.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manso
 */
public class TemplarCoin implements Serializable {

    private ArrayList<Transaction> ledger;

    public TemplarCoin() {
        ledger = new ArrayList<>();
        ledger.add(new Transaction("System", "Master", 1000));
    }

    public List<Transaction> getLedger() {
        return ledger;
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (Transaction transaction : ledger) {
            txt.append(transaction.toString() + "\n");
        }
        return txt.toString();
    }

    public void save(String fileName) throws IOException {
        try ( ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    public static TemplarCoin load(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (TemplarCoin) in.readObject();
        }
    }

    public double getAmount(String user) {
        double total = 0;

        for (Transaction transaction : ledger) {
            if (transaction.getFrom().equals(user)) {
                total -= transaction.getValue();
            } else if (transaction.getTo().equals(user)) {
                total += transaction.getValue();
            }
        }

        return total;
    }

    public boolean isValid(Transaction t) throws Exception {
        if (t.getValue() <= 0) {
            throw new Exception("Value " + t.getValue() + " is invalid");
        }
        if (t.getFrom().trim().isEmpty()) {
            throw new Exception("From user is empty");
        }
        if (t.getTo().trim().isEmpty()) {
            throw new Exception("To user is empty");
        }
        double value = getAmount(t.getFrom());
        if (t.getValue() > value) {
            throw new Exception(t.getFrom() + " not have founds \n"
                    + t.getValue() + " is larger than " + value);
        }
        return t.getValue() <= value;
    }

    public void add(Transaction t) throws Exception {
        if (isValid(t)) {
            ledger.add(t);
        } else {
            throw new Exception("Transaction not valid");
        }
    }

    public List<String> getUsers() {
        ArrayList<String> users = new ArrayList<>();
        //get Users
        for (Transaction transaction : ledger) {
            if (!users.contains(transaction.getFrom())) {
                users.add(transaction.getFrom());
            }
            if (!users.contains(transaction.getTo())) {
                users.add(transaction.getTo());
            }
        }
        //get ampunt of users
        ArrayList<String> balance = new ArrayList<>();
        for (String user : users) {
            balance.add( String.format("%8.2f - %s", getAmount(user), user));
        }
        return balance;
    }

    public static long serialVersionUID = 123;

}

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
package CurriculumDigital.Core;

import blockchain.utils.Block;
import blockchain.utils.BlockChain;
import blockchain.utils.ObjectUtils;
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
public class Curriculum implements Serializable {

    private ArrayList<Evento> eventos;
    blockchain.utils.BlockChain bloco;
    public static int DIFICULTY = 4;

    public Curriculum(BlockChain bloco) throws Exception {
        this.bloco = bloco;
    }

    public int quantEventos(String user) {
        int total = 0;

        for (Evento evento : eventos) {
            if (evento.getEntidade().equals(user)) {
                total++;
            }
        }

        return total;
    }

    public boolean isValid(Evento e) throws Exception {
        if (e.getNomePessoa().trim().isEmpty()) {
            throw new Exception("Name is empty");
        }
        if (e.getEntidade().trim().isEmpty()) {
            throw new Exception("Entidade is empty");
        }
        if (e.getDescricao().trim().isEmpty()) {
            throw new Exception("Descricao is empty");
        }

        return true;
    }

    public void add(Evento e) throws Exception {
        if (isValid(e)) {
            ledger.add(t);
            String txtTransaction = ObjectUtils.convertObjectToBase64(t);
            bloco.add(txtTransaction, DIFICULTY);
        } else {
            throw new Exception("Transaction not valid");
        }
    }

    public List<Evento> getEventosBlockchain() throws Exception {
        List<Evento> lst = new ArrayList<>();
        for (Block b : bloco.getChain()) {
            Evento t = (Evento) ObjectUtils.convertBase64ToObject(b.getData());
            lst.add(t);
        }
        return lst;
    }

    public List<String> getUsers() {
        ArrayList<String> users = new ArrayList<>();
        //get Users
        for (Evento evento : bloco) {
            if (!users.contains(transaction.getFrom())) {
                users.add(transaction.getFrom());
            }
            if (!users.contains(transaction.getTo())) {
                users.add(transaction.getTo());
            }
        }

        return users;
    }
}

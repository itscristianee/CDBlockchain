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

    blockchain.utils.BlockChain bc ;
    public static int DIFICULTY = 4;

    public Curriculum() throws Exception {
       }

    

    public void save(String fileName) throws IOException {
        try ( ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    public static Curriculum load(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (Curriculum) in.readObject();
        }
    }

    

    
    

    public List<String> getUsers() {
        ArrayList<String> users = new ArrayList<>();
        
        //get Users
        for (Evento evento : eventos) {
            if (!users.contains(evento.getEntidade())) {
                users.add(evento.getEntidade());
            }
            
        }
        
        return users;
    }



}

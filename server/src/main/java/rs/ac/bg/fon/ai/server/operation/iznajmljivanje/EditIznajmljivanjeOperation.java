/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class EditIznajmljivanjeOperation extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        
        if(i.getStavkaIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati barem jednu stavku!");
        }
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        
        StavkaIznajmljivanja st = new StavkaIznajmljivanja();
        st.setIznajmljivanje(i);
        
        List<StavkaIznajmljivanja> stavke = broker.getAll(st, null);
        
        
        for(StavkaIznajmljivanja stavka : stavke) {
            stavka.setIznajmljivanje(i);
            broker.delete(stavka);
        }
        
        broker.edit((Iznajmljivanje)parametar);
        
        for(StavkaIznajmljivanja stavka : i.getStavkaIznajmljivanja()) {
            stavka.setIznajmljivanje(i);
            broker.add(stavka);
        }
        
        
        
    }
    
}

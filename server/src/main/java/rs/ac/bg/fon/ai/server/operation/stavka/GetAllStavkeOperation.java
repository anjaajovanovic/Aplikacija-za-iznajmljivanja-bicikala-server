/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.stavka;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetAllStavkeOperation extends AbstractGenericOperation {
    
    private List<StavkaIznajmljivanja> stavke = new ArrayList<>();

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    
    
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if (!(parametar instanceof StavkaIznajmljivanja)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaIznajmljivanje!");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        StavkaIznajmljivanja si = (StavkaIznajmljivanja) parametar;
        String uslov = " WHERE iznajmljivanje = " + si.getIznajmljivanje().getId();
        stavke = broker.getAll(parametar, uslov);
    }
    
}

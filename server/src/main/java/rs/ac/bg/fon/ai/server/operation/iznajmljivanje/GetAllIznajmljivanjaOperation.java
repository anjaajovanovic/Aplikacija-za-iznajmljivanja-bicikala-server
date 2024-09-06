/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetAllIznajmljivanjaOperation extends AbstractGenericOperation {
    
    private List<Iznajmljivanje> iznajmljivanja;

    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        iznajmljivanja = broker.getAll(new Iznajmljivanje(), null);
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
    
    
}

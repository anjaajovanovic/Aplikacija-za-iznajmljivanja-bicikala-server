/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetIznajmljivanjeOperation extends AbstractGenericOperation{
    
    private Iznajmljivanje iznajmljivanje;
    
    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        String uslov = " WHERE i.id = " + i.getId();
        iznajmljivanje = (Iznajmljivanje) broker.getAll(new Iznajmljivanje(), uslov).get(0);
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }
    
}

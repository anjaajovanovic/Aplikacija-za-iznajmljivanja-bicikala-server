/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class DeleteIznajmljivanjeOperation extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem ne moze da obrise iznajmljivanje.");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {        
        broker.delete((Iznajmljivanje)parametar);
    }
    
}

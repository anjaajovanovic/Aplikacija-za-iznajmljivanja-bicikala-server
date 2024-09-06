/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class EditKorisnikOperation extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem nije mogao da izmeni korisnika.");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        broker.edit((Korisnik)parametar);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

import org.hamcrest.core.IsInstanceOf;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetKorisnikOperation extends AbstractGenericOperation {
    
    private Korisnik korisnik;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem nije mogao da ucita korisnika.");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        Korisnik k = (Korisnik) parametar;
        String uslov = " WHERE id = " + k.getId();
        korisnik = (Korisnik) broker.getAll((Korisnik)parametar, uslov).get(0);
    }
    
}

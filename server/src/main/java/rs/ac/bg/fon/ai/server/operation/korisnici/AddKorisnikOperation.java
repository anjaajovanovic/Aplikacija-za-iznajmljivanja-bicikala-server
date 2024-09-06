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
public class AddKorisnikOperation extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(parametar == null) {
            throw new Exception("Sistem nije mogao da doda korisnika.");
        }
        Korisnik k = (Korisnik) parametar;
        if(k.getIme() == null || k.getIme().isEmpty()) {
            throw new Exception("Sistem nije mogao da doda korisnika, greska u imenu.");
        }
        if(k.getPrezime() == null || k.getPrezime().isEmpty()) {
            throw new Exception("Sistem nije mogao da doda korisnika, greska u prezimenu.");
        }
        if(k.getEmail() == null || k.getEmail().isEmpty()) {
            throw new Exception("Sistem nije mogao da doda korisnika, greska u emailu.");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        broker.add((Korisnik)parametar);
    }
    
}

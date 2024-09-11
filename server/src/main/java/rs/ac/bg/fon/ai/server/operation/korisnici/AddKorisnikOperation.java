/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja dodaje novog korisnika u bazu podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za dodavanje korisnika u bazu podataka.
 * 
 * @author Anja Jovanovic
 */
public class AddKorisnikOperation extends AbstractGenericOperation {

	/**
     * Proverava preduslove za izvršavanje operacije dodavanja korisnika.
     * 
     * Ova metoda proverava da li je prosleđeni parametar instanca klase {@link Korisnik}.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova.
     * @throws Exception Ako parametar nije objekat klase {@link Korisnik}.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem nije mogao da doda korisnika.");
        }
    }

    /**
     * Izvršava operaciju dodavanja korisnika u bazu podataka.
     * 
     * Dodaje objekat tipa {@link Korisnik} u bazu podataka koristeći {@link broker}.
     * 
     * @param parametar Parametar koji sadrži korisnika koji treba dodati.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom dodavanja korisnika u bazu podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        broker.add((Korisnik)parametar);
    }
    
}

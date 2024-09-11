/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.*;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja briše korisnika iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za brisanje korisnika iz baze podataka.
 * 
 * @author Anja Jovanovic
 */
public class DeleteKorisnikOperation extends AbstractGenericOperation {

	/**
     * Proverava preduslove za izvršavanje operacije brisanja korisnika.
     * 
     * Ova metoda proverava da li je prosleđeni parametar instanca klase {@link Korisnik}.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova.
     * @throws Exception Ako parametar nije objekat klase {@link Korisnik}.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem ne moze da obrise korisnika.");
        }
    }

    /**
     * Izvršava operaciju brisanja korisnika iz baze podataka.
     * 
     * Briše objekat tipa {@link Korisnik} iz baze podataka koristeći {@link broker}.
     * 
     * @param parametar Parametar koji sadrži korisnika koji treba obrisati.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom brisanja korisnika iz baze podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        broker.delete((Korisnik)parametar);
    }
    
}

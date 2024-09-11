/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja ažurira podatke o korisniku u bazi podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za izmenu korisnika u bazi podataka.
 * 
 * @author Anja Jovanovic
 */
public class EditKorisnikOperation extends AbstractGenericOperation {

	/**
     * Proverava preduslove za izvršavanje operacije izmena korisnika.
     * 
     * Ova metoda proverava da li je prosleđeni parametar instanca klase {@link Korisnik}.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova.
     * @throws Exception Ako parametar nije objekat klase {@link Korisnik}.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem nije mogao da izmeni korisnika.");
        }
    }

    /**
     * Izvršava operaciju izmene korisnika u bazi podataka.
     * 
     * Ažurira podatke o korisniku u bazi podataka koristeći {@link broker}.
     * 
     * @param parametar Parametar koji sadrži korisnika čije podatke treba izmeniti.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom izmene korisnika u bazi podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        broker.edit((Korisnik)parametar);
    }
    
}

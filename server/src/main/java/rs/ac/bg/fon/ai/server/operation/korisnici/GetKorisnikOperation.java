/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

import org.hamcrest.core.IsInstanceOf;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja preuzima korisnika iz baze podataka na osnovu njegovog identifikatora.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za preuzimanje korisnika iz baze podataka na osnovu ID-a korisnika.
 * 
 * @author Anja Jovanovic
 */
public class GetKorisnikOperation extends AbstractGenericOperation {
    
	/** 
	 * Korisnik preuzet iz baze podataka. 
	 */
    private Korisnik korisnik;

    /**
     * Vraća korisnika preuzetog iz baze podataka.
     * 
     * @return Korisnik.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Proverava preduslove za izvršavanje operacije preuzimanja korisnika.
     * 
     * Ova metoda osigurava da je prosleđeni objekat instanca klase {@link Korisnik}.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova.
     * @throws Exception Ako parametar nije objekat klase Korisnik.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem nije mogao da ucita korisnika.");
        }
    }

    /**
     * Izvršava operaciju preuzimanja korisnika iz baze podataka na osnovu njegovog ID-a.
     * 
     * Ova metoda koristi {@link broker} za preuzimanje korisnika na osnovu uslova 
     * koji je postavljen na osnovu ID-a korisnika, i čuva preuzetog korisnika u 
     * privatnoj promenljivoj.
     * 
     * @param parametar Parametar koji sadrži objekat klase Korisnik sa ID-om koji se koristi za preuzimanje.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom preuzimanja korisnika iz baze podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        Korisnik k = (Korisnik) parametar;
        String uslov = " WHERE id = " + k.getId();
        korisnik = (Korisnik) broker.getAll((Korisnik)parametar, uslov).get(0);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja preuzima sve korisnike iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za preuzimanje svih korisnika iz baze podataka.
 * 
 * @author PC
 */
public class GetAllKorisniciOperation extends AbstractGenericOperation {
    
	/**
	 * Lista korisnika preuzetih iz baze podataka.
	 */
    private List<Korisnik> korisnici;

    /**
     * Vraća listu korisnika preuzetih iz baze podataka.
     * 
     * @return Lista korisnika.
     */
    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    /**
     * Proverava preduslove za izvršavanje operacije preuzimanja korisnika.
     * 
     * Ova metoda ne vrši nikakve specifične provere preduslova za ovu operaciju.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako se javi greška tokom provere preduslova (nije primenljivo za ovu operaciju).
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    /**
     * Izvršava operaciju preuzimanja svih korisnika iz baze podataka.
     * 
     * Ova metoda koristi {@link broker} za preuzimanje svih korisnika i čuva ih u 
     * privatnoj listi.
     * 
     * @param parametar Parametar koji se koristi za izvršavanje operacije (nije primenljiv u ovoj operaciji).
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom preuzimanja korisnika iz baze podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        korisnici = broker.getAll(new Korisnik(), null);
    }
    
}

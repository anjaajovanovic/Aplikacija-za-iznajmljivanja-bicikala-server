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
 * Operacija koja briše iznajmljivanje iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metod za brisanje objekta tipa {@link Iznajmljivanje} iz baze podataka.
 * 
 * @author Anja Jovanovic
 */
public class DeleteIznajmljivanjeOperation extends AbstractGenericOperation{

	/**
     * Proverava preduslove za izvršavanje operacije brisanja iznajmljivanja.
     * 
     * @param parametar Objekat koji se koristi za proveru preduslova. Treba biti instanca klase {@link Korisnik}.
     * @throws Exception Ako objekat nije instanca klase {@link Korisnik}.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Korisnik)) {
            throw new Exception("Sistem ne moze da obrise iznajmljivanje.");
        }
    }

    /**
     * Izvršava operaciju brisanja iznajmljivanja iz baze podataka.
     * 
     * @param parametar Objekat tipa {@link Iznajmljivanje} koji se briše iz baze.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljivo ovde).
     * @throws Exception Ako dođe do greške tokom brisanja iznajmljivanja.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {        
        broker.delete((Iznajmljivanje)parametar);
    }
    
}

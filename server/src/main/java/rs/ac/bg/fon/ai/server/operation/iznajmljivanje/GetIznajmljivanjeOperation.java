/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja vraća jedno iznajmljivanje na osnovu njegovog identifikatora.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za preuzimanje jednog objekta tipa {@link Iznajmljivanje} iz baze podataka
 * na osnovu njegovog ID-a.
 * 
 * @author Anja Jovanovic
 */
public class GetIznajmljivanjeOperation extends AbstractGenericOperation{
    
	/**
	 * Iznajmljivanje koje je učitano iz baze.
	 */
    private Iznajmljivanje iznajmljivanje;
    
    /**
     * Proverava preduslove za izvršavanje operacije preuzimanja iznajmljivanja.
     * 
     * Ova metoda proverava da li je prosleđeni parametar instanca klase {@link Iznajmljivanje}.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova.
     * @throws Exception Ako parametar nije objekat klase {@link Iznajmljivanje}.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
    	if(!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Parametar nije objekat klase Iznajmljivanje.");
        }
    }

    /**
     * Izvršava operaciju preuzimanja jednog iznajmljivanja iz baze podataka.
     * 
     * Preuzima jedan objekat tipa {@link Iznajmljivanje} koristeći {@link broker} na osnovu
     * identifikatora iz objekta prosleđenog kao parametar.
     * 
     * @param parametar Parametar koji sadrži ID iznajmljivanja koje treba preuzeti.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom preuzimanja iz baze podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        String uslov = " WHERE i.id = " + i.getId();
        iznajmljivanje = (Iznajmljivanje) broker.getAll(new Iznajmljivanje(), uslov).get(0);
    }

    /**
     * Vraća iznajmljivanje preuzeto iz baze podataka.
     * 
     * @return Objekat tipa {@link Iznajmljivanje}.
     */
    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }
    
}

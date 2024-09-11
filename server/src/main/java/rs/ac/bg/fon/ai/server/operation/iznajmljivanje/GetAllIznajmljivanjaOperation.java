/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja vraća sve iznajmljivanja iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za preuzimanje svih objekata tipa {@link Iznajmljivanje} iz baze podataka.
 * 
 * @author Anja Jovanovic
 */
public class GetAllIznajmljivanjaOperation extends AbstractGenericOperation {
    
	/**
	 * Lista svih iznajmljivanja dobijena iz baze podataka.
	 */
    private List<Iznajmljivanje> iznajmljivanja;

    
    /**
     * Proverava preduslove za izvršavanje operacije preuzimanja iznajmljivanja.
     * 
     * Ova metoda ne vrši nikakve specifične provere.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova (nije korišćen u ovoj klasi).
     * @throws Exception Ako se javi greška tokom provere preduslova.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    /**
     * Izvršava operaciju preuzimanja svih iznajmljivanja iz baze podataka.
     * 
     * Preuzima sve objekte tipa {@link Iznajmljivanje} koristeći {@link broker} i čuva ih u privatnoj listi.
     * 
     * @param parametar Parametar koji se koristi za izvršenje operacije (nije korišćen u ovoj klasi).
     * @param key Ključ koji se koristi za identifikaciju (nije primenljiv u ovoj operaciji).
     * @throws Exception Ako dođe do greške tokom preuzimanja iz baze podataka.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        iznajmljivanja = broker.getAll(new Iznajmljivanje(), null);
    }

    /**
     * Vraća listu svih iznajmljivanja preuzetih iz baze podataka.
     * 
     * @return Lista objekata tipa {@link Iznajmljivanje}.
     */
    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
    
    
}

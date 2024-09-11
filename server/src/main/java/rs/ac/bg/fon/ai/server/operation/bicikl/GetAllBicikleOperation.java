/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.bicikl;

import rs.ac.bg.fon.ai.zajednicki.domain.Bicikl;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja vraća sve bicikle iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi 
 * metod za izvršavanje operacije nad objektima tipa {@link Bicikl}. 
 * Nakon uspešnog izvršavanja operacije, lista svih bicikala je dostupna preko metode {@link #getBicikle()}.
 * 
 * @author Ana Jovanovic
 */
public class GetAllBicikleOperation extends AbstractGenericOperation {
    
	/**
     * Lista svih bicikala dobijena iz baze podataka.
     */
    private List<Bicikl> bicikle;

    /**
     * Vraća listu bicikala koji su dobijeni nakon izvršavanja operacije.
     * 
     * @return lista bicikala iz baze podataka
     */
    public List<Bicikl> getBicikle() {
        return bicikle;
    }
    
    /**
     * Proverava preduslove za izvršavanje operacije.
     * U ovoj implementaciji ne postoje specifični preduslovi za ovu operaciju.
     * 
     * @param parametar Parametar koji se koristi za proveru preduslova (nije primenljivo u ovoj operaciji).
     * @throws Exception Ako preduslovi nisu ispunjeni (nema u ovoj operaciji).
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
    }

    /**
     * Izvršava operaciju učitavanja svih bicikala iz baze podataka.
     * Koristi se broker za pristup bazi i učitavanje svih instanci klase {@link Bicikl}.
     * 
     * @param parametar Parametar koji se koristi za izvršavanje operacije (nije primenljivo ovde).
     * @param key Ključ koji se koristi za identifikaciju (nije primenljivo ovde).
     * @throws Exception Ako dođe do greške tokom izvršavanja operacije.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        bicikle = broker.getAll(new Bicikl(), null);
    }
    
}

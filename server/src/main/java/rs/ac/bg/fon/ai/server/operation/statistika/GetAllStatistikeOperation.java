/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.statistika;

import rs.ac.bg.fon.ai.zajednicki.domain.Statistika;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja vraća sve statistike iz baze podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi 
 * metod za izvršavanje operacije nad objektima tipa {@link Statistika}. 
 * Nakon uspešnog izvršavanja operacije, lista svih statistika je dostupna preko metode {@link #getStatistike()}.
 * 
 * @author Anja Jovanovic
 */
public class GetAllStatistikeOperation extends AbstractGenericOperation {
    
	/**
     * Lista svih statistika dobijena iz baze podataka.
     */
    private List<Statistika> statistike;

    /**
     * Vraća listu statistika koja je dobijena nakon izvršavanja operacije.
     * 
     * @return lista statistika iz baze podataka
     */
    public List<Statistika> getStatistike() {
        return statistike;
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
     * Izvršava operaciju učitavanja svih statistika iz baze podataka.
     * Koristi se broker za pristup bazi i učitavanje svih instanci klase {@link Statistika}.
     * 
     * @param parametar Parametar koji se koristi za izvršavanje operacije (nije primenljivo ovde).
     * @param key Ključ koji se koristi za identifikaciju (nije primenljivo ovde).
     * @throws Exception Ako dođe do greške tokom izvršavanja operacije.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        statistike = broker.getAll(new Statistika(), null);
    }
    
}

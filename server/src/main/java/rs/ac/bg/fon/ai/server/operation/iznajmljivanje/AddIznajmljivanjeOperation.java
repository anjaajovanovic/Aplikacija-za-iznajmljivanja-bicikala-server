/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import java.sql.*;

/**
 * Operacija koja dodaje novo iznajmljivanje u bazu podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metod za dodavanje objekta tipa {@link Iznajmljivanje} u bazu podataka. 
 * Nakon uspešnog dodavanja iznajmljivanja, dodaje se i odgovarajuće stavke iznajmljivanja.
 * 
 * @author Anja Jovanovic
 */
public class AddIznajmljivanjeOperation extends AbstractGenericOperation {

	/**
     * Proverava preduslove za izvršavanje operacije dodavanja iznajmljivanja.
     * 
     * @param parametar Objekat koji se koristi za proveru preduslova. Treba biti instanca klase {@link Iznajmljivanje}.
     * @throws Exception Ako objekat nije instanca klase {@link Iznajmljivanje} ili ako iznajmljivanje nema barem jednu stavku.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if (!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        
        if (i.getStavkaIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati barem jednu stavku!");
        }
    }

    /**
     * Izvršava operaciju dodavanja iznajmljivanja u bazu podataka.
     * 
     * @param parametar Objekat tipa {@link Iznajmljivanje} koji se dodaje u bazu.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljivo ovde).
     * @throws Exception Ako dođe do greške tokom dodavanja iznajmljivanja ili stavki iznajmljivanja.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        
        PreparedStatement ps = broker.add((Iznajmljivanje) parametar);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        System.out.println(tableKeys);
        tableKeys.next();
        int iznajmljivanjeId = tableKeys.getInt(1);
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        i.setId(iznajmljivanjeId);
        
        for(StavkaIznajmljivanja si : i.getStavkaIznajmljivanja()) {
            si.setIznajmljivanje(i);
            broker.add(si);
        }
    }
    
}

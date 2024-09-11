/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.stavka;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Klasa koja omogućava izvršavanje operacije preuzimanja svih stavki iznajmljivanja iz sistema.
 * 
 * Ova klasa nasleđuje {@link AbstractGenericOperation} i implementira
 * logiku za preuzimanje stavki iz baze podataka na osnovu prosleđenog objekta
 * {@link StavkaIznajmljivanja} i id-a iznajmljivanja.
 * 
 * @author Anja Jovanovic
 */
public class GetAllStavkeOperation extends AbstractGenericOperation {
    
	/**
     * Lista svih stavki iznajmljivanja.
     */
    private List<StavkaIznajmljivanja> stavke = new ArrayList<>();

    /**
     * Vraća listu svih stavki iznajmljivanja.
     * 
     * @return Lista stavki iznajmljivanja
     */
    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    /**
     * Proverava preduslove za izvršenje operacije.
     * 
     * @param parametar Objekat koji treba da bude instanca {@link StavkaIznajmljivanja}
     * @throws Exception Ako parametar nije instanca klase {@link StavkaIznajmljivanja}
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if (!(parametar instanceof StavkaIznajmljivanja)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaIznajmljivanje!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja svih stavki iznajmljivanja.
     * 
     * Kreira uslov za pretragu na osnovu id-a iznajmljivanja i koristi {@link broker} 
     * za preuzimanje svih stavki koje odgovaraju tom uslovu.
     * 
     * @param parametar Objekat {@link StavkaIznajmljivanja} koji se koristi za pretragu
     * @param key Ključ koji se koristi za pretragu u bazi podataka
     * @throws Exception Ako dođe do greške tokom pretrage
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        StavkaIznajmljivanja si = (StavkaIznajmljivanja) parametar;
        String uslov = " WHERE iznajmljivanje = " + si.getIznajmljivanje().getId();
        stavke = broker.getAll(parametar, uslov);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Operacija koja ažurira iznajmljivanje u bazi podataka.
 * 
 * Ova klasa nasleđuje apstraktnu klasu {@link AbstractGenericOperation} i koristi
 * metode za ažuriranje objekta tipa {@link Iznajmljivanje} u bazi podataka. Takođe,
 * briše postojeće stavke iznajmljivanja pre nego što doda nove.
 * 
 * @author Anja Jovanovic
 */
public class EditIznajmljivanjeOperation extends AbstractGenericOperation {

	/**
     * Proverava preduslove za izvršavanje operacije ažuriranja iznajmljivanja.
     * 
     * @param parametar Objekat koji se koristi za proveru preduslova. Treba biti instanca klase {@link Iznajmljivanje}.
     * @throws Exception Ako objekat nije instanca klase {@link Iznajmljivanje} ili ako iznajmljivanje nema nijednu stavku.
     */
    @Override
    protected void preconditions(Object parametar) throws Exception {
        if(!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        
        if(i.getStavkaIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati barem jednu stavku!");
        }
    }

    /**
     * Izvršava operaciju ažuriranja iznajmljivanja u bazi podataka.
     * 
     * Prvo se brišu sve postojeće stavke iznajmljivanja, zatim se ažurira iznajmljivanje,
     * i na kraju se dodaju nove stavke iznajmljivanja.
     * 
     * @param parametar Objekat tipa {@link Iznajmljivanje} koji se ažurira.
     * @param key Ključ koji se koristi za identifikaciju (nije primenljivo ovde).
     * @throws Exception Ako dođe do greške tokom ažuriranja ili brisanja stavki iznajmljivanja.
     */
    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        
        
        Iznajmljivanje i = (Iznajmljivanje) parametar;
        
        StavkaIznajmljivanja st = new StavkaIznajmljivanja();
        st.setIznajmljivanje(i);
        
        List<StavkaIznajmljivanja> stavke = broker.getAll(st, null);
        
        
        for(StavkaIznajmljivanja stavka : stavke) {
            stavka.setIznajmljivanje(i);
            broker.delete(stavka);
        }
        
        broker.edit((Iznajmljivanje)parametar);
        
        for(StavkaIznajmljivanja stavka : i.getStavkaIznajmljivanja()) {
            stavka.setIznajmljivanje(i);
            broker.add(stavka);
        }
        
        
        
    }
    
}

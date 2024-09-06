/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.korisnici;

import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetAllKorisniciOperation extends AbstractGenericOperation {
    
    private List<Korisnik> korisnici;

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        korisnici = broker.getAll(new Korisnik(), null);
    }
    
}

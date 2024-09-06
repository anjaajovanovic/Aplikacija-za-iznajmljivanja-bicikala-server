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
 *
 * @author PC
 */
public class AddIznajmljivanjeOperation extends AbstractGenericOperation {

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

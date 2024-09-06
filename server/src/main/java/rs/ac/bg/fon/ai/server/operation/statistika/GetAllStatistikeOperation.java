/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.statistika;

import rs.ac.bg.fon.ai.zajednicki.domain.Statistika;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetAllStatistikeOperation extends AbstractGenericOperation {
    
    private List<Statistika> statistike;

    public List<Statistika> getStatistike() {
        return statistike;
    }
    
    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        statistike = broker.getAll(new Statistika(), null);
    }
    
}

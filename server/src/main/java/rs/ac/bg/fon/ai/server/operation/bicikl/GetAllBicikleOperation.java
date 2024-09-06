/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.bicikl;

import rs.ac.bg.fon.ai.zajednicki.domain.Bicikl;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class GetAllBicikleOperation extends AbstractGenericOperation {
    
    private List<Bicikl> bicikle;

    public List<Bicikl> getBicikle() {
        return bicikle;
    }
    
    @Override
    protected void preconditions(Object parametar) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        bicikle = broker.getAll(new Bicikl(), null);
    }
    
}

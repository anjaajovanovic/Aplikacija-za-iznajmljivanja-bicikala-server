/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation;

import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;

/**
 *
 * @author PC
 */
public abstract class AbstractGenericOperation {
    protected final Repository broker;

    public AbstractGenericOperation() {
        this.broker = new DBRepositoryGeneric();
    }
    
    public final void execute(Object object, String key) throws Exception {
        try {
            preconditions(object);
            startTransaction();
            executeOperation(object, key);
            confirmTransaction();
        } catch (Exception e) {
            cancelTransaction();
            throw e;
        } finally {
//            closeConnection();
        }
    }
    
    protected abstract void preconditions(Object parametar) throws Exception;
    
    protected abstract void executeOperation(Object parametar, String key) throws Exception;
    
    private void startTransaction() throws Exception {
        ((DBRepository) broker).connect();
    }
    
    private void confirmTransaction() throws Exception {
        ((DBRepository) broker).commit();
    }
    
    private void cancelTransaction() throws Exception {
        ((DBRepository) broker).rollback();
    }
    
    private void closeConnection() throws Exception {
        ((DBRepository) broker).disconnect();
    }
    
}

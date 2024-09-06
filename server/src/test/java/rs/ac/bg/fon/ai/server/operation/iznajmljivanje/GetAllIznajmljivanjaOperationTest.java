package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.GetAllIznajmljivanjaOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;

class GetAllIznajmljivanjaOperationTest {

    private GetAllIznajmljivanjaOperation getAllIznajmljivanjaOperation;
    private Repository<Iznajmljivanje> repositoryMock;

    @BeforeEach
    void setUp() throws Exception {
        getAllIznajmljivanjaOperation = new GetAllIznajmljivanjaOperation();
        repositoryMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getAllIznajmljivanjaOperation, repositoryMock);
    }

    @Test
    void testExecuteOperation() throws Exception {
        List<Iznajmljivanje> expectedList = new ArrayList<>();
        expectedList.add(new Iznajmljivanje()); 
        
        when(repositoryMock.getAll(any(Iznajmljivanje.class), isNull())).thenReturn(expectedList);

        getAllIznajmljivanjaOperation.executeOperation(null, null);

        List<Iznajmljivanje> actualList = getAllIznajmljivanjaOperation.getIznajmljivanja();

        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0), actualList.get(0));
    }
    
    
}


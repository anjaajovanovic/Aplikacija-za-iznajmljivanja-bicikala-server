package rs.ac.bg.fon.ai.server.operation.stavka;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.operation.stavka.GetAllStavkeOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;

class GetAllStavkeOperationTest {

    private GetAllStavkeOperation getAllStavkeOperation;
    private Repository<StavkaIznajmljivanja> repositoryMock;

    @BeforeEach
    void setUp() throws Exception {
        getAllStavkeOperation = new GetAllStavkeOperation();
        repositoryMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getAllStavkeOperation, repositoryMock);
    }
    
    @Test
    void testPreconditions() {
        StavkaIznajmljivanja validParam = new StavkaIznajmljivanja();

        assertDoesNotThrow(() -> getAllStavkeOperation.preconditions(validParam));
    }
    
    @Test
    void testPreconditionsInvalidParameter() {
        Object invalidParam = new Object();

        Exception exception = assertThrows(Exception.class, () -> {
            getAllStavkeOperation.preconditions(invalidParam);
        });

        assertEquals("Prosledjeni objekat nije instanca klase StavkaIznajmljivanje!", exception.getMessage());
    }

    @Test
    void testExecuteOperationSuccess() throws Exception {
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        iznajmljivanje.setId(3);
        StavkaIznajmljivanja stavka1 = new StavkaIznajmljivanja(iznajmljivanje, 1, 5, 3000.0, null);
        StavkaIznajmljivanja stavka2 = new StavkaIznajmljivanja(iznajmljivanje, 2, 3, 2000.0, null);
        List<StavkaIznajmljivanja> dummyStavke = Arrays.asList(stavka1, stavka2);

        when(repositoryMock.getAll(any(StavkaIznajmljivanja.class), anyString())).thenReturn(dummyStavke);

        StavkaIznajmljivanja param = new StavkaIznajmljivanja();
        param.setIznajmljivanje(iznajmljivanje);

        getAllStavkeOperation.executeOperation(param, "");

        List<StavkaIznajmljivanja> result = getAllStavkeOperation.getStavke();
        assertEquals(2, result.size());
        assertEquals(stavka1, result.get(0));
        assertEquals(stavka2, result.get(1));

        verify(repositoryMock, times(1)).getAll(any(StavkaIznajmljivanja.class), anyString());
    }

}


package rs.ac.bg.fon.ai.server.operation.bicikl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.operation.bicikl.GetAllBicikleOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Bicikl;

import java.lang.reflect.Field;

class GetAllBicikleOperationTest {

    private GetAllBicikleOperation getAllBicikleOperation;
    private Repository<Bicikl> repositoryMock;

    @BeforeEach
    void setUp() throws Exception {
        getAllBicikleOperation = new GetAllBicikleOperation();
        repositoryMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getAllBicikleOperation, repositoryMock);
    }

    @Test
    void testExecuteOperation() throws Exception {
        Bicikl bicikl1 = new Bicikl(1, "Bicikl1", "Model1", 2020, 100, null, null);
        Bicikl bicikl2 = new Bicikl(2, "Bicikl2", "Model2", 2021, 120, null, null);
        List<Bicikl> dummyBicikli = Arrays.asList(bicikl1, bicikl2);

        when(repositoryMock.getAll(any(Bicikl.class), isNull())).thenReturn(dummyBicikli);

        getAllBicikleOperation.executeOperation(null, "");

        List<Bicikl> result = getAllBicikleOperation.getBicikle();
        assertEquals(2, result.size());
        assertEquals(bicikl1, result.get(0));
        assertEquals(bicikl2, result.get(1));

        verify(repositoryMock, times(1)).getAll(any(Bicikl.class), isNull());
    }

    @Test
    void testExecuteOperationEmptyList() throws Exception {
        when(repositoryMock.getAll(any(Bicikl.class), isNull())).thenReturn(Arrays.asList());

        getAllBicikleOperation.executeOperation(null, "");

        List<Bicikl> result = getAllBicikleOperation.getBicikle();
        assertTrue(result.isEmpty());

        verify(repositoryMock, times(1)).getAll(any(Bicikl.class), isNull());
    }
}

package rs.ac.bg.fon.ai.server.operation.statistika;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Statistika;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class GetAllStatistikeOperationTest {
	
	private GetAllStatistikeOperation getAllStatistikeOperation;
    private Repository<Statistika> repositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		getAllStatistikeOperation = new GetAllStatistikeOperation();
        repositoryMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getAllStatistikeOperation, repositoryMock);
	}

	@Test
    void testExecuteOperationSuccess() throws Exception {
        Statistika statistika1 = new Statistika(1, 2, null, null);
        Statistika statistika2 = new Statistika(2, 4, null, null);
        List<Statistika> dummyStatistike = Arrays.asList(statistika1, statistika2);

        when(repositoryMock.getAll(any(Statistika.class), isNull())).thenReturn(dummyStatistike);

        getAllStatistikeOperation.executeOperation(null, "");

        List<Statistika> result = getAllStatistikeOperation.getStatistike();
        assertEquals(2, result.size());
        assertEquals(statistika1, result.get(0));
        assertEquals(statistika2, result.get(1));

        verify(repositoryMock, times(1)).getAll(any(Statistika.class), isNull());
    }

    @Test
    void testExecuteOperationEmptyList() throws Exception {
        when(repositoryMock.getAll(any(Statistika.class), isNull())).thenReturn(Arrays.asList());

        getAllStatistikeOperation.executeOperation(null, "");

        List<Statistika> result = getAllStatistikeOperation.getStatistike();
        assertTrue(result.isEmpty());

        verify(repositoryMock, times(1)).getAll(any(Statistika.class), isNull());
    }

}

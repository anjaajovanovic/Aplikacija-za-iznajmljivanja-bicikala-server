package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;

class GetIznajmljivanjeOperationTest {
	
	private GetIznajmljivanjeOperation getIznajmljivanjeOperation;
	private Repository<Iznajmljivanje> repositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		getIznajmljivanjeOperation = new GetIznajmljivanjeOperation();
		repositoryMock = mock(Repository.class);
		
		Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getIznajmljivanjeOperation, repositoryMock);
	}

	@Test
	void testPreconditions() {
		Iznajmljivanje i = new Iznajmljivanje();
		
		assertDoesNotThrow(() -> getIznajmljivanjeOperation.preconditions(i));
	}
	
	@Test
	void testPreconditionsInvalidParam() {
		Object i = new Object();
		
		Exception exception = assertThrows(Exception.class, () -> {
			getIznajmljivanjeOperation.preconditions(i);
        });

        assertEquals("Parametar nije objekat klase Iznajmljivanje.", exception.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Iznajmljivanje inputIznajmljivanje= new Iznajmljivanje();
		inputIznajmljivanje.setId(1);
        Iznajmljivanje expectedIznajmljivanje = new Iznajmljivanje();
        expectedIznajmljivanje.setId(1);

        when(repositoryMock.getAll(any(Iznajmljivanje.class), anyString())).thenReturn(Collections.singletonList(expectedIznajmljivanje));

        getIznajmljivanjeOperation.executeOperation(inputIznajmljivanje, "");

        assertEquals(expectedIznajmljivanje, getIznajmljivanjeOperation.getIznajmljivanje());
        verify(repositoryMock).getAll(any(Iznajmljivanje.class), eq(" WHERE i.id = 1"));
	}

}


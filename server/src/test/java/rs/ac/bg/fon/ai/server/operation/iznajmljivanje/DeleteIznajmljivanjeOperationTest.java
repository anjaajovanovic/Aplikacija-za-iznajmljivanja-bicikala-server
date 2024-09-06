package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;

class DeleteIznajmljivanjeOperationTest {
	
	private DeleteIznajmljivanjeOperation deleteIznajmljivanjeOperation;
    private DBRepositoryGeneric dbRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		deleteIznajmljivanjeOperation = new DeleteIznajmljivanjeOperation();
        dbRepositoryMock = mock(DBRepositoryGeneric.class);
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(deleteIznajmljivanjeOperation, dbRepositoryMock);
	}

	@Test
	void testPreconditions() {
		Exception exception = assertThrows(Exception.class, () -> {
            deleteIznajmljivanjeOperation.preconditions(null);
        });
        assertEquals("Sistem ne moze da obrise iznajmljivanje.", exception.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
       
        deleteIznajmljivanjeOperation.executeOperation(iznajmljivanje, null);

        verify(dbRepositoryMock).delete(iznajmljivanje);
	}

}

package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

class EditIznajmljivanjeOperationTest {
	
	private EditIznajmljivanjeOperation editIznajmljivanjeOperation;
    private DBRepositoryGeneric dbRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		editIznajmljivanjeOperation = new EditIznajmljivanjeOperation();
        dbRepositoryMock = mock(DBRepositoryGeneric.class);
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(editIznajmljivanjeOperation, dbRepositoryMock);
	}

	@Test
	void testPreconditions() {
		Exception exception = assertThrows(Exception.class, () -> {
            editIznajmljivanjeOperation.preconditions(null);
        });
        assertEquals("Prosledjeni objekat nije instanca klase Iznajmljivanje!", exception.getMessage());
	}
	
	@Test
	void testPreconditionsEmpty() {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
		ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<StavkaIznajmljivanja>();
		iznajmljivanje.setStavkaIznajmljivanja(stavke);
        Exception exceptionEmptyStavke = assertThrows(Exception.class, () -> {
            editIznajmljivanjeOperation.preconditions(iznajmljivanje);
        });
        assertEquals("Iznajmljivanje mora imati barem jednu stavku!", exceptionEmptyStavke.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<StavkaIznajmljivanja>();
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
        stavke.add(stavka);
        iznajmljivanje.setStavkaIznajmljivanja(stavke);

        List<AbstractDomainObject> abstractDomainStavke = new ArrayList<>(stavke);
        
        when(dbRepositoryMock.getAll(any(StavkaIznajmljivanja.class), isNull())).thenReturn(abstractDomainStavke);
        
        editIznajmljivanjeOperation.executeOperation(iznajmljivanje, null);

        verify(dbRepositoryMock).edit(iznajmljivanje);
        verify(dbRepositoryMock).delete(stavka);
        verify(dbRepositoryMock).add(stavka);
	}

}

package rs.ac.bg.fon.ai.server.operation.iznajmljivanje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;

class AddIznajmljivanjeOperationTest {
	
	private AddIznajmljivanjeOperation addIznajmljivanjeOperation;
    private DBRepository<AbstractDomainObject> dbRepositoryMock;
    private PreparedStatement preparedStatementMock;
    private ResultSet resultSetMock;

	@BeforeEach
	void setUp() throws Exception {
		addIznajmljivanjeOperation = new AddIznajmljivanjeOperation();
        dbRepositoryMock = mock(DBRepositoryGeneric.class);
        preparedStatementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        when(dbRepositoryMock.add(any(Iznajmljivanje.class))).thenReturn(preparedStatementMock);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(addIznajmljivanjeOperation, dbRepositoryMock);
	}

	@Test
    void testPreconditionsInvalidParametar() {

        Exception exception = assertThrows(Exception.class, () -> {
            addIznajmljivanjeOperation.preconditions(new Object());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Iznajmljivanje!", exception.getMessage());
    }
	
	@Test
    void testPreconditionsEmptyList() {
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        ArrayList<StavkaIznajmljivanja> stavke = new ArrayList<StavkaIznajmljivanja>();
        iznajmljivanje.setStavkaIznajmljivanja(stavke);

        Exception exception = assertThrows(Exception.class, () -> {
            addIznajmljivanjeOperation.preconditions(iznajmljivanje);
        });

        assertEquals("Iznajmljivanje mora imati barem jednu stavku!", exception.getMessage());
    }

	@Test
	void testExecuteOperation() throws Exception {
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
        iznajmljivanje.setStavkaIznajmljivanja(new ArrayList<>());

        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
        iznajmljivanje.getStavkaIznajmljivanja().add(stavka);

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(1);

        addIznajmljivanjeOperation.executeOperation(iznajmljivanje, null);

        verify(dbRepositoryMock).add(iznajmljivanje);
        verify(preparedStatementMock).getGeneratedKeys();
        verify(resultSetMock).next();
        verify(resultSetMock).getInt(1);

        assertEquals(1, iznajmljivanje.getId());

        verify(dbRepositoryMock).add(stavka);
	}

}

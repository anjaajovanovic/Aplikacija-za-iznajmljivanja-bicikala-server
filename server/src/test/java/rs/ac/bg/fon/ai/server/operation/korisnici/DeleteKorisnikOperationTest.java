package rs.ac.bg.fon.ai.server.operation.korisnici;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.DeleteIznajmljivanjeOperation;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

class DeleteKorisnikOperationTest {
	
	private DeleteKorisnikOperation deleteKorisnikOperation;
    private DBRepositoryGeneric dbRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		dbRepositoryMock = mock(DBRepositoryGeneric.class);
        deleteKorisnikOperation = new DeleteKorisnikOperation();
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(deleteKorisnikOperation, dbRepositoryMock);
	}

	@Test
	void testPreconditions() {
		Exception exception = assertThrows(Exception.class, () -> {
			deleteKorisnikOperation.preconditions(null);
        });
        assertEquals("Sistem ne moze da obrise korisnika.", exception.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Korisnik korisnik = new Korisnik();
	       
		deleteKorisnikOperation.executeOperation(korisnik, null);

        verify(dbRepositoryMock).delete(korisnik);
	}

}

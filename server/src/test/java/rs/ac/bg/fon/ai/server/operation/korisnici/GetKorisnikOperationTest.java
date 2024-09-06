package rs.ac.bg.fon.ai.server.operation.korisnici;

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
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

class GetKorisnikOperationTest {
	
	private GetKorisnikOperation getKorisnikOperation;
	private Repository<Korisnik> repositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		getKorisnikOperation = new GetKorisnikOperation();
		repositoryMock = mock(Repository.class);
		
		Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getKorisnikOperation, repositoryMock);
	}

	@Test
	void testPreconditions() {
		Korisnik k = new Korisnik();
		
		assertDoesNotThrow(() -> getKorisnikOperation.preconditions(k));
	}
	
	@Test
	void testPreconditionsInvalidParam() {
		Object k = new Object();
		
		Exception exception = assertThrows(Exception.class, () -> {
			getKorisnikOperation.preconditions(k);
        });

        assertEquals("Sistem nije mogao da ucita korisnika.", exception.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Korisnik inputKorisnik = new Korisnik();
        inputKorisnik.setId(1);
        Korisnik expectedKorisnik = new Korisnik();
        expectedKorisnik.setId(1);

        when(repositoryMock.getAll(any(Korisnik.class), anyString())).thenReturn(Collections.singletonList(expectedKorisnik));

        getKorisnikOperation.executeOperation(inputKorisnik, "");

        assertEquals(expectedKorisnik, getKorisnikOperation.getKorisnik());
        verify(repositoryMock).getAll(any(Korisnik.class), eq(" WHERE id = 1"));
	}

}

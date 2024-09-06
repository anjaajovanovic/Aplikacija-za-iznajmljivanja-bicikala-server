package rs.ac.bg.fon.ai.server.operation.korisnici;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

import static org.mockito.Mockito.*;

class AddKorisnikOperationTest {
	
	private AddKorisnikOperation addKorisnikOperation;
    private DBRepository<AbstractDomainObject> dbRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		addKorisnikOperation = new AddKorisnikOperation();
        dbRepositoryMock = mock(DBRepositoryGeneric.class);
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(addKorisnikOperation, dbRepositoryMock);
	}

	@Test
    void testPreconditions() {
        Exception exception = assertThrows(Exception.class, () -> {
            addKorisnikOperation.preconditions(new Object());
        });
        assertEquals("Sistem nije mogao da doda korisnika.", exception.getMessage());
    }
	
	

	@Test
    void testExecuteOperation() throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme("Marko");
        korisnik.setPrezime("Marković");
        korisnik.setEmail("marko@example.com");

        addKorisnikOperation.executeOperation(korisnik, null);

        verify(dbRepositoryMock).add(korisnik);
    }

}

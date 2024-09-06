package rs.ac.bg.fon.ai.server.operation.korisnici;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DBRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

class EditKorisnikOperationTest {
	
	private EditKorisnikOperation editKorisnikOperation;
    private DBRepository<AbstractDomainObject> dbRepositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		editKorisnikOperation = new EditKorisnikOperation();
        dbRepositoryMock = mock(DBRepositoryGeneric.class);
        
        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(editKorisnikOperation, dbRepositoryMock);
	}

	@Test
	void testPreconditions() {
		Exception exception = assertThrows(Exception.class, () -> {
            editKorisnikOperation.preconditions(null);
        });
        assertEquals("Sistem nije mogao da izmeni korisnika.", exception.getMessage());
	}

	@Test
	void testExecuteOperation() throws Exception {
		Korisnik korisnik = new Korisnik();
        korisnik.setIme("Ana");
        korisnik.setPrezime("Anić");
        korisnik.setEmail("ana@example.com");

        editKorisnikOperation.executeOperation(korisnik, null);

        verify(dbRepositoryMock).edit(korisnik);
	}

}

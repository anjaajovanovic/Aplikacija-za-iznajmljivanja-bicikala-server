package rs.ac.bg.fon.ai.server.operation.korisnici;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Bicikl;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;

class GetAllKorisniciOperationTest {
	
	private GetAllKorisniciOperation getAllKorisniciOperation;
    private Repository<Korisnik> repositoryMock;

	@BeforeEach
	void setUp() throws Exception {
		getAllKorisniciOperation = new GetAllKorisniciOperation();
        repositoryMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(getAllKorisniciOperation, repositoryMock);
	}

	@Test
    void testExecuteOperation() throws Exception {
        Korisnik korisnik1 = new Korisnik(1, "Ime1", "Prezime1", "email1@example.com");
        Korisnik korisnik2 = new Korisnik(2, "Ime2", "Prezime1", "email2@example.com");
        List<Korisnik> dummyKorisnici = Arrays.asList(korisnik1, korisnik2);

        when(repositoryMock.getAll(any(Korisnik.class), isNull())).thenReturn(dummyKorisnici);

        getAllKorisniciOperation.executeOperation(null, "");

        List<Korisnik> result = getAllKorisniciOperation.getKorisnici();
        assertEquals(2, result.size());
        assertEquals(korisnik1, result.get(0));
        assertEquals(korisnik2, result.get(1));

        verify(repositoryMock, times(1)).getAll(any(Korisnik.class), isNull());
    }
	
	@Test
    void testExecuteOperationEmptyList() throws Exception {
        when(repositoryMock.getAll(any(Korisnik.class), isNull())).thenReturn(Arrays.asList());

        getAllKorisniciOperation.executeOperation(null, "");

        List<Korisnik> result = getAllKorisniciOperation.getKorisnici();
        assertTrue(result.isEmpty());

        verify(repositoryMock, times(1)).getAll(any(Korisnik.class), isNull());
    }

}

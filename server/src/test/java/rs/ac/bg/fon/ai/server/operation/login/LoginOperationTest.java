package rs.ac.bg.fon.ai.server.operation.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.zajednicki.domain.Zaposleni;

class LoginOperationTest {

    private LoginOperation loginOperation;
    private Repository brokerMock;

    @BeforeEach
    void setUp() throws Exception {
        loginOperation = new LoginOperation();
        brokerMock = mock(Repository.class);

        Field brokerField = AbstractGenericOperation.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(loginOperation, brokerMock);
    }

    @Test
    void testPreconditionsNull() {
        Exception exception = assertThrows(Exception.class, () -> {
            loginOperation.preconditions(null);
        });

        assertEquals("Sistem nije mogao da pronadje zaposlenog.", exception.getMessage());
    }

    @Test
    void testPreconditions() {
        assertDoesNotThrow(() -> {
            loginOperation.preconditions(new Zaposleni());
        });
    }

    @Test
    void testExecuteOperation_SuccessfulLogin() throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setId(1);
        zaposleni.setIme("Pera");
        
        List<Zaposleni> sviZaposleni = new ArrayList<>();
        sviZaposleni.add(zaposleni);
        
        when(brokerMock.getAll(any(Zaposleni.class), isNull())).thenReturn(sviZaposleni);

        loginOperation.executeOperation(zaposleni, null);
        assertEquals(zaposleni, loginOperation.getZaposleni());
    }

    @Test
    void testExecuteOperation_UnsuccessfulLogin() throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setId(1);
        zaposleni.setIme("Pera");
        
        when(brokerMock.getAll(any(Zaposleni.class), isNull())).thenReturn(new ArrayList<>());

        loginOperation.executeOperation(zaposleni, null);
        
        assertNull(loginOperation.getZaposleni());
    }
}

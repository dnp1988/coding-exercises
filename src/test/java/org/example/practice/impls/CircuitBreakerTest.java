package org.example.practice.impls;

import org.example.practice.impls.mock.MockInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CircuitBreakerTest {

    public static final String MOCK_STRING = "mockString";

    @Test
    public void testCircuitBreakerNoError() throws Throwable {
        MockInterface mock = Mockito.mock(MockInterface.class);

        Mockito.when(mock.retrieveId()).thenReturn(MOCK_STRING);

        CircuitBreaker circuitBreaker = new CircuitBreaker(() -> mock.retrieveId(), 5L, 2L);

        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(2)).retrieveId();
    }

    @Test
    public void testCircuitBreakerPersistentError() throws Throwable {
        MockInterface mock = Mockito.mock(MockInterface.class);
        RuntimeException runtimeException = new RuntimeException();

        Mockito.when(mock.retrieveId()).thenThrow(runtimeException);

        CircuitBreaker circuitBreaker = new CircuitBreaker(() -> mock.retrieveId(), 5L, 2L);

        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(2)).retrieveId();

        Thread.sleep(6000L);

        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(3)).retrieveId();
    }

    @Test
    public void testRecoverFromError() throws Throwable {
        MockInterface mock = Mockito.mock(MockInterface.class);
        RuntimeException runtimeException = new RuntimeException();

        Mockito.when(mock.retrieveId()).thenThrow(runtimeException)
                .thenThrow(runtimeException)
                .thenReturn(MOCK_STRING);

        CircuitBreaker circuitBreaker = new CircuitBreaker(() -> mock.retrieveId(), 5L, 2L);

        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(2)).retrieveId();

        Thread.sleep(3000L);

        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(2)).retrieveId();

        Thread.sleep(3000L);

        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(3)).retrieveId();
    }

    @Test
    public void testIntermittentError() throws Throwable {
        MockInterface mock = Mockito.mock(MockInterface.class);
        RuntimeException runtimeException = new RuntimeException();

        Mockito.when(mock.retrieveId()).thenThrow(runtimeException)
                .thenReturn(MOCK_STRING)
                .thenThrow(runtimeException)
                .thenReturn(MOCK_STRING)
                .thenThrow(runtimeException)
                .thenReturn(MOCK_STRING);

        CircuitBreaker circuitBreaker = new CircuitBreaker(() -> mock.retrieveId(), 5L, 2L);

        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Assertions.assertThrows(RuntimeException.class, () -> circuitBreaker.call());
        Assertions.assertEquals(MOCK_STRING, circuitBreaker.call());
        Mockito.verify(mock, Mockito.times(6)).retrieveId();
    }
}

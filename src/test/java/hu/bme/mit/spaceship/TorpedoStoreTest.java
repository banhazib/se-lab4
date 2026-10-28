package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void constructor_SetsInitialTorpedoCount() {
        // Arrange + Act
        TorpedoStore store = new TorpedoStore(5);

        // Assert
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0); // never fails

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedos_DecreasesCountByThatMany() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 0.0);

        // Act
        boolean result = store.fire(3);

        // Assert
        assertTrue(result);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedos_EmptiesStore() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act
        boolean result = store.fire(2);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_Failure_ReturnsFalseAndKeepsTorpedos() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 1.0); // always fails

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(10, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedos_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeTorpedos_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_FromEmptyStore_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void isEmpty_StoreWithTorpedos_ReturnsFalse() {
        assertFalse(new TorpedoStore(1, 0.0).isEmpty());
    }

    @Test
    void isEmpty_StoreWithZeroTorpedos_ReturnsTrue() {
        assertTrue(new TorpedoStore(0, 0.0).isEmpty());
    }

    @Test
    void isEmpty_NegativeCount_ReturnsTrue() {
        assertTrue(new TorpedoStore(-1, 0.0).isEmpty());
    }
}
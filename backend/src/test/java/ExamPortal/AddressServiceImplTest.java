package ExamPortal;

import ExamPortal.entities.Address;
import ExamPortal.repositories.AddressRepository;
import ExamPortal.services.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAddress() {
        // Arrange
        Address expectedAddress = new Address();
        when(addressRepository.save(any(Address.class))).thenReturn(expectedAddress);

        // Act
        Address addedAddress = addressService.addAddress(new Address());

        // Assert
        assertNotNull(addedAddress);
        assertEquals(expectedAddress, addedAddress);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void testUpdateAddress() {
        // Arrange
        Address address = new Address();
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        // Act
        Address updatedAddress = addressService.updateAddress(new Address());

        // Assert
        assertNotNull(updatedAddress);
        assertEquals(address, updatedAddress);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void testGetAddressById() {
        // Arrange
        int addressId = 1;
        Address expectedAddress = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(expectedAddress));

        // Act
        Address retrievedAddress = addressService.getAddressById(addressId);

        // Assert
        assertNotNull(retrievedAddress);
        assertEquals(expectedAddress, retrievedAddress);
        verify(addressRepository, times(1)).findById(addressId);
    }

}

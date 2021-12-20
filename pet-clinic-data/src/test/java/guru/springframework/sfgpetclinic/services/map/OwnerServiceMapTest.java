package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetServiceMap(), new PetTypeServiceMap());
        ownerServiceMap.save(Owner.builder().id(id).lastName("lastName").build());
    }

    @Test
    void findById() {
        assertEquals(id, ownerServiceMap.findById(id).getId());
    }

    @Test
    void saveId() {
        Owner bob = ownerServiceMap.save(Owner.builder().id(2L).lastName("bob").build());
        assertEquals(2L, bob.getId());
    }

    @Test
    void saveNoId() {
        Owner potter = ownerServiceMap.save(Owner.builder().lastName("Potter").build());
        assertNotNull(potter);
        assertNotNull(potter.getId());
    }

    @Test
    void findAll() {
        assertFalse(ownerServiceMap.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertNull(ownerServiceMap.findById(id));
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertNull(ownerServiceMap.findById(id));
    }

    @Test
    void findByLastName() {
        assertNotNull(ownerServiceMap.findByLastName("lastName"));
    }
}
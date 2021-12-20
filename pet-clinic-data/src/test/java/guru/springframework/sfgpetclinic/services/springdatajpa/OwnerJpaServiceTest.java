package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @Mock
    OwnerRepository ownerRepository;

    Owner owner;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(id).build();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(eq(id))).thenReturn(Optional.of(owner));
        assertNotNull(ownerJpaService.findById(id));
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        assertEquals(owner, ownerJpaService.save(owner));
        verify(ownerRepository).save(any());
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(Set.of(owner));
        assertFalse(ownerJpaService.findAll().isEmpty());
        verify(ownerRepository).findAll();
    }

    @Test
    void delete() {
        ownerJpaService.delete(owner);
        verify(ownerRepository).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(id);
        verify(ownerRepository).deleteById(id);
    }

    @Test
    void findByLastName() {
        String lastName = "lastName";
        ownerJpaService.findByLastName(lastName);
        verify(ownerRepository).findByLastName(eq(lastName));
    }
}
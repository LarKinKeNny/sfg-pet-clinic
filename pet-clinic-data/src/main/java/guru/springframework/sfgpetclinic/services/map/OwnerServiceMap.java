package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findById(Long id) {
        log.debug("findById id={}", id);
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            for (Pet pet : object.getPets()) {
                var petType = pet.getPetType();
                if (petType != null) {
                    if(petType.getId() == null)
                        petType.setId(petTypeService.save(petType).getId());
                    if (pet.getId() == null)
                        pet.setId(petService.save(pet).getId());
                }
                else
                    throw new RuntimeException("PetType is required");
            }
            return super.save(object);
        }
        return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.map.values().parallelStream()
                .filter(x -> x.getLastName().equals(lastName))
                .findAny().orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return null;
    }
}

package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        if(petTypeService.findAll().isEmpty())
            loadData();
    }

    private void loadData() {
        log.debug("Loading data");
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saved1 = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saved2 = specialityService.save(surgery);

        PetType dog = new PetType();
        dog.setName("dog");

        var savedDog = petTypeService.save(dog);

        var owner = new Owner();
        owner.setFirstName("Micheal");
        owner.setLastName("Weston");
        owner.setAddress("123 Brickerel");
        owner.setCity("Miami");
        owner.setTelephone("43554737");

        Pet fluff = new Pet();
        fluff.setPetType(savedDog);
        fluff.setBirthDate(LocalDate.now());
        fluff.setOwner(owner);
        fluff.setName("fluff");
        owner.getPets().add(fluff);

        ownerService.save(owner);

        Visit visit = new Visit();
        visit.setPet(fluff);
        visit.setDate(LocalDate.now());
        visit.setDescription("Cut them off");

        visitService.save(visit);

        var vet = new Vet();
        vet.setFirstName("Sam");
        vet.setLastName("Axe");
        vet.getSpecialities().add(saved1);
        vet.getSpecialities().add(saved2);

        vetService.save(vet);

        log.debug("data loaded");
    }
}

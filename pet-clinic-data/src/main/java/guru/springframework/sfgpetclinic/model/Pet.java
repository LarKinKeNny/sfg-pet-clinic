package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, Set<Visit> visits, PetType petType, LocalDate birthDate, String name) {
        super(id);
        if(visits != null)
            this.visits = visits;
        this.petType = petType;
        this.birthDate = birthDate;
        this.name = name;
    }

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    @ManyToOne
    private PetType petType;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Owner owner;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;

    private String name;

    @Override
    public String toString() {
        return name;
    }
}

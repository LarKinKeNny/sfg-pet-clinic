package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Speciality extends BaseEntity {

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "specialities")
    private Set<Vet> vets = new HashSet<>();

    private String description;
}

package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "petType")
    private Set<Pet> pets = new HashSet<>();

    private String name;

    @Override
    public String toString() {
        return name;
    }
}

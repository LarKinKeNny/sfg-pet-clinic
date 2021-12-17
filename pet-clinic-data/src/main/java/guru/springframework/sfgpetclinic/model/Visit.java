package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Visit extends BaseEntity {
    private LocalDate date;
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Pet pet;
}

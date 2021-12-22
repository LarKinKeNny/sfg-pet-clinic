package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Visit extends BaseEntity {
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Pet pet;
}

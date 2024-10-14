package nl.pancompany.unicorn.adapter.unicorn.out.persistence.database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;


@Data
@NoArgsConstructor
@Entity
@Table(name = "UNICORN")
public class UnicornJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "UNICORN_UUID")
    private String unicornId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "unicorn", cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UnicornLegJpaEntity> legs = new HashSet<>();

    public void addLeg(UnicornLegJpaEntity leg) {
        if (legs == null) {
            legs = new HashSet<>();
        }
        legs.add(leg);
        leg.setUnicorn(this);
    }

}

package nl.pancompany.unicorn.adapter.unicorn.out.persistence.database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "UNICORN_LEG")
public class UnicornLegJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "UNICORN_ID", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UnicornJpaEntity unicorn;

    @Column(name = "LEG_POSITION")
    private String legPosition;

    @Column(name = "LEG_SIZE")
    private String legSize;

    @Column(name = "COLOR")
    private String color;
}

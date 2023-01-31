package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tblFood")
@Inheritance(strategy = InheritanceType.JOINED)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_food_seq")
    @SequenceGenerator(name = "tbl_food_seq", sequenceName = "tbl_food_seq", initialValue = 100, allocationSize = 10)
    private Long id;
    @Column(unique = true)
    private String name;
    private String type;
    private double price;
    private String description;

}

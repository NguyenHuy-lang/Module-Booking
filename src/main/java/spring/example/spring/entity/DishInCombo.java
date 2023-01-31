package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name="tblDishInCombo")
public class DishInCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quanity;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="dish_id")
    private Dish dish;
}

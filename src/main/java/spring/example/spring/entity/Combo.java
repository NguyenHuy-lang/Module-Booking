package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tblCombo")
@PrimaryKeyJoinColumn(name = "food_id")
public class Combo extends Food{
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name="combo_id")
    private List<DishInCombo> dishes;
}

package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "food_id")
@ToString
@Table(name ="tblDish", uniqueConstraints={@UniqueConstraint(columnNames={"food_id"})})
public class Dish extends Food{

}

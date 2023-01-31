package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tblUsedFood")
public class UsedFood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tbl_usedFood_seq")
    @SequenceGenerator(name = "tbl_usedFood_seq",
            sequenceName = "tbl_usedFood_seq",
            initialValue = 100, allocationSize = 10)
    private Long id;
    private Double unitPrice;
    private Integer quantity;
    private Double subTotal;
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;
}

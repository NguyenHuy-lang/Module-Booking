package spring.example.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@jakarta.persistence.Table(name="tblBookedTable")
public class BookedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tbl_bookedTable_seq")
    @SequenceGenerator(name = "tbl_bookedTable_seq",
            sequenceName = "tbl_bookedTable_seq",
            initialValue = 100, allocationSize = 10)
    private Long id;
    private Float total;
    @ManyToOne
    @JoinColumn(name="table_id")
    private Table table;
    @OneToMany
    @JoinColumn(name = "booked_table_id")
    private List<UsedFood> listUsedFood;

}

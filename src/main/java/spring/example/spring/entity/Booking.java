package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tbl_booking_seq")
    @SequenceGenerator(name = "tbl_booking_seq",
            sequenceName = "tbl_booking_seq",
            initialValue = 100, allocationSize = 10)
    private Long id;
    private Date bookTime;
    private Date checkinAt;
    private Date checkoutAt;
    private Integer numOfCustomer;
    private Float totalAmount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_id")
    private List<BookedTable> bookedTable;



}

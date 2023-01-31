package spring.example.spring.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="tblBill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private Double discountAmount;
    private Double mustPaidAmount;
    private Date paymentTime;
    private String paymentMethod;
    private String note;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_id")

    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

}

package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.Voucher;
@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}

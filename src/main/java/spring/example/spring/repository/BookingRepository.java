package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.Combo;
@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
}

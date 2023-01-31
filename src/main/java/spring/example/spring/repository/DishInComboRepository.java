package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.DishInCombo;
@Repository
public interface DishInComboRepository extends JpaRepository<DishInCombo, Long> {
}

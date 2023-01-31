package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.UsedFood;
@Repository
public interface UsedFoodRepository extends JpaRepository<UsedFood, Long> {
}

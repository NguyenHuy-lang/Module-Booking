package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Dish;

@Service
public interface DishRepository extends JpaRepository<Dish, Long> {

}

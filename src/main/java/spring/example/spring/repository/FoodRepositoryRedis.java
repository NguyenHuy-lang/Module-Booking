package spring.example.spring.repository;

import org.springframework.data.repository.CrudRepository;
import spring.example.spring.entity.Food;

public interface FoodRepositoryRedis extends CrudRepository<Food, Long> {
    boolean existsById(String id);

    void deleteById(String id);
}

package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Dish;
import spring.example.spring.repository.DishRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    public List<Dish> getAllDish() {
        return dishRepository.findAll();
    }
    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
    public Dish getDishById(Long id) {
        return dishRepository.getReferenceById(id);
    }
}

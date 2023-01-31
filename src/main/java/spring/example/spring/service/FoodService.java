package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Food;
import spring.example.spring.repository.FoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    public Food getFoodById(Long id) {
        return foodRepository.getById(id);
    }
    public void saveFood(Food food) {
        foodRepository.save(food);
    }
    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }
    public List<Food> getAllFoodByName(String name) {
        return foodRepository.findAllByName(name);
    }
}

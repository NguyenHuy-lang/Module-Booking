package spring.example.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Combo;
import spring.example.spring.entity.Dish;
import spring.example.spring.entity.DishInCombo;
import spring.example.spring.repository.ComboRepository;
import spring.example.spring.repository.DishInComboRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {
    private final ComboRepository comboRepository;
    private final DishInComboRepository dishInComboRepository;
    public List<Combo> getAllCombo(){
        return comboRepository.findAll();
    }
    public Combo getComboById(Long id) {
        return comboRepository.getReferenceById(id);
    }
    @Transactional
    public void saveCombo(Combo combo) {
        List<DishInCombo> dishes = combo.getDishes();
        List<DishInCombo> newDishes = new ArrayList<>();
        for (DishInCombo dishInCombo : dishes) {
            DishInCombo detailDishInCombo = dishInComboRepository
                    .save(dishInCombo);
            newDishes.add(detailDishInCombo);
        }
        combo.setDishes(newDishes);
        comboRepository.save(combo);
    }
    public void deleteComboById(Long id) {
        comboRepository.deleteById(id);
    }
}

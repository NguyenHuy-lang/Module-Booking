package spring.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.example.spring.entity.Combo;
import spring.example.spring.entity.Dish;
import spring.example.spring.entity.Food;
import spring.example.spring.service.ComboService;
import spring.example.spring.service.DishService;
import spring.example.spring.service.FoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/food")
public class FoodController {
    private final FoodService foodService;
    private final DishService dishService;
    private final ComboService comboService;
    @GetMapping("/")
    public List<Food> getAllFood() {
        return foodService.getAllFood();
    }
    @GetMapping("/get-all-dish")
    public List<Dish> getAllDish() {
        return dishService.getAllDish();
    }
    @GetMapping("/get-all-combo")
    public List<Combo> getAllCombo() {
        return comboService.getAllCombo();
    }
    @PostMapping("/save-dish")
    public String saveDish(@RequestBody Dish dish) {
        System.out.println("value " + dish.getName() + " " + dish.getPrice() + " " + dish.getDescription());
        dishService.saveDish(dish);
        return "save dish done";
    }
    @PostMapping("/save-combo")
    public String saveCombo(@RequestBody Combo combo) {
        System.out.println(combo.getName());
        System.out.println(combo.getDishes());
        comboService.saveCombo(combo);
        return "save combo done";
    }

}

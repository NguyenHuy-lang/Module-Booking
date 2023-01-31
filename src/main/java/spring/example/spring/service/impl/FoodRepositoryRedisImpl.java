package spring.example.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.Food;
import spring.example.spring.repository.FoodRepositoryRedis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryRedisImpl implements FoodRepositoryRedis {
    private  final RedisTemplate<String, Food> redisTemplate;

    @Override
    public boolean existsById(String id) {
        return redisTemplate.hasKey(id);
    }

    @Override
    public void deleteById(String id) {
        redisTemplate.delete(id);
    }

    @Override
    public <S extends Food> S save(S entity) {
        redisTemplate.opsForHash().put("food", entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Food> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(food -> redisTemplate.opsForHash().put("food", String.valueOf(food.getId()), food));
        return entities;
    }

    @Override
    public Optional<Food> findById(Long id) {
        return Optional.ofNullable((Food) redisTemplate.opsForHash().get("food", id));
    }

    @Override
    public boolean existsById(Long id) {
        return (redisTemplate.opsForHash().get("food", id) != null) ? true : false ;
    }

    @Override
    public Iterable<Food> findAll() {
        List<Object> listObject = (List<Object>) redisTemplate.opsForHash().values("food");
        List<Food> foodList = new ArrayList<>();
        for (Object obj : listObject) {
            LinkedHashMap hashMap = (LinkedHashMap) obj;

            Food food = new Food();
            food.setId(((Integer) hashMap.get("id")).longValue());
            food.setName((String) hashMap.get("name"));
            food.setDescription((String) hashMap.get("description"));
            food.setPrice((Double)hashMap.get("price"));
            food.setType((String) hashMap.get("type"));
            // set other properties of food using hashMap
            foodList.add(food);
        }
        return foodList;
    }


    @Override
    public Iterable<Food> findAllById(Iterable<Long> listId) {
        List<Food> listFood = new ArrayList<>();
        listId.forEach(id -> listFood.add((Food) redisTemplate.opsForHash().get("food", id)));
        return listFood;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete("food", id);
    }

    @Override
    public void delete(Food entity) {
        redisTemplate.opsForHash().delete("food", entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> listId) {
        listId.forEach(id -> redisTemplate.opsForHash().delete("food", id));
    }

    @Override
    public void deleteAll(Iterable<? extends Food> foods) {
        foods.forEach(food -> redisTemplate.opsForHash().delete("food", food.getId()));
    }

    @Override
    public void deleteAll() {
        redisTemplate.opsForHash().delete("food");
    }
}

package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.DishInCombo;
import spring.example.spring.repository.DishInComboRepository;

@Service
@RequiredArgsConstructor
public class DishInComboService {
    private final DishInComboRepository dishInComboRepository;
}

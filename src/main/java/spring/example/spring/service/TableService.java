package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Table;
import spring.example.spring.repository.TableRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    public Table getTableById(Long id) {
        return tableRepository.getReferenceById(id);
    }
    public void saveTable(Table table) {
        tableRepository.save(table);
    }
    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }
    public List<Table> getAllTable() {
        return tableRepository.findAll();
    }
}

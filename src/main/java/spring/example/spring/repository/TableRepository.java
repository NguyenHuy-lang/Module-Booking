package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.Table;
@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
}

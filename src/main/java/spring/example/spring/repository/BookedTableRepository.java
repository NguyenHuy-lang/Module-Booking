package spring.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.example.spring.entity.BookedTable;
@Repository
public interface BookedTableRepository extends JpaRepository<BookedTable, Long> {

}

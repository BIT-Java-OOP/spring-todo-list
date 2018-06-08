package pl.agh.bit.javaoop.springtodolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.agh.bit.javaoop.springtodolist.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.text like ?1")
    List<Todo> findByMessage(String text);
}

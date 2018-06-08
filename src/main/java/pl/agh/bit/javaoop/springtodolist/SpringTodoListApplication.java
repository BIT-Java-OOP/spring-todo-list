package pl.agh.bit.javaoop.springtodolist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.agh.bit.javaoop.springtodolist.model.Todo;
import pl.agh.bit.javaoop.springtodolist.repository.TodoRepository;

@SpringBootApplication
public class SpringTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTodoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(TodoRepository todoRepository) {
		return args -> {
			todoRepository.save(new Todo("Zrobic Jave", "17:50"));
			todoRepository.save(new Todo("Isc spac", "23:50"));
			todoRepository.save(new Todo("Zjesc cos", "14:30"));
		};
	}
}

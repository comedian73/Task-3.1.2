package ru.kata.spring.boot_security.demo.repo;

import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
     User findUserByUsername(String username);
}

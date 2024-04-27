package ru.edu.penzgtu.lab.repo;

import ru.edu.penzgtu.lab.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

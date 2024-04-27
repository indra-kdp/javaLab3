package ru.edu.penzgtu.lab.repo;

import ru.edu.penzgtu.lab.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByName(String name);
}

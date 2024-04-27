package ru.edu.penzgtu.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "musics")
public class Music {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 44)
    private String name;

    @Column(name = "genre", nullable = false, length = 44)
    private String genre;

    @Column(name = "date and time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @Column(name = "style", nullable = false, length = 155)
    private String style;

    @Column(name = "album", nullable = false, length = 55)
    private String album;

    @ManyToMany(mappedBy = "musics", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Platform> platforms = new ArrayList<>();
}

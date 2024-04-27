package ru.edu.penzgtu.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table (name = "Platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 66)
    @NotBlank
    private String name;

    @Column(name = "country", nullable = false, length = 66)
    @NotBlank
    private String country;

    @Column(name = "company", nullable = false, length = 66)
    private String company;

    @Column(name = "catalog", nullable = false, length = 66)
    private String catalog;

    @Column(name = "date and time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "platform_musics",
            joinColumns = @JoinColumn(name = "platform_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id",referencedColumnName = "id"))

    private List<Music> musics = new ArrayList<>();
}

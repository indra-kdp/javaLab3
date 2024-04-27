package ru.edu.penzgtu.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "Producers" )
public class Producer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 55)
    @NotBlank
    private String name;

    @Column(name = "sphere", nullable = false, length = 55)
    @NotBlank
    private String sphere;

    @Column(name = "region", nullable = false, length = 55)
    private String region;

    @Column(name = "age")
    @Positive(message = "Возраст должен быть положительным числом")
    private String age;

    @Column(name = "date and time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();
}

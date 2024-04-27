package ru.edu.penzgtu.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 77)
    private String name;

    @Column(name = "age", nullable = false, length = 77)
    private String age;

    @Column(name = "pseudonym", nullable = false, length = 77)
    private String pseudonym;

    @Column(name = "publisher", nullable = false, length = 77)
    private String publisher;

    @Column(name = "date and time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;
}

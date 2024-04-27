package ru.edu.penzgtu.lab.service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.ProducerDto;
import ru.edu.penzgtu.lab.entity.Producer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerMapper {
    public List<ProducerDto> toListDto(List<Producer> producers) {
        return producers.stream().map(this::toDto).toList();
    }

    public ProducerDto toDto(Producer producer) {
        return ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .sphere(producer.getSphere())
                .region(producer.getRegion())
                .age(producer.getAge())
                .localDateTime(producer.getLocalDateTime())
                .build();
    }

    public Producer toEntity(ProducerDto producerDto) {
        Producer producer = new Producer();

        producer.setId(producerDto.getId());
        producer.setName(producerDto.getName());
        producer.setSphere(producerDto.getSphere());
        producer.setLocalDateTime(producerDto.getLocalDateTime());
        producer.setRegion(producerDto.getRegion());
        producer.setAge(producerDto.getAge());

        return producer;
    }
}

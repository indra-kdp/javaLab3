package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.ProducerDto;
import ru.edu.penzgtu.lab.entity.Producer;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.ProducerRepository;
import ru.edu.penzgtu.lab.service.Mapper.ProducerMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;
    public List<ProducerDto> findAllProducers() {
        log.info("Найдены все существующие продюсеры в БД"); return producerMapper.toListDto(producerRepository.findAll());
    }

    public ProducerDto findProducerById(Long id) {
        log.info("Найден продюсер по id" + id);
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Продюсер с id" + id + "не найден"));
        return producerMapper.toDto(producer);
    }

    public void saveProducer(ProducerDto producerDto) {
        log.info("Продюсер сохранен");
        Producer producer = producerMapper.toEntity(producerDto);
        producer.setLocalDateTime(LocalDateTime.now());
        producerRepository.save(producer);
    }

    public void updateProducer(ProducerDto newProducer) {
        log.info("Данные о продюсере были обновлены");
        Producer oldProducer = producerRepository.findById(newProducer.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Продюсер не найден"));

        oldProducer.setName(newProducer.getName());
        oldProducer.setAge(newProducer.getAge());
        oldProducer.setSphere(newProducer.getSphere());
        oldProducer.setRegion(newProducer.getRegion());

        producerRepository.save(oldProducer);
    }

    public void deleteProducerById(Long id) {
        log.info("Удален продюсер с id:" + id); producerRepository.deleteById(id);
    }
}

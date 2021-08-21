package com.renanwillian.service;


import com.renanwillian.entity.Producer;
import com.renanwillian.repository.MovieRepository;
import com.renanwillian.repository.ProducerRepository;
import com.renanwillian.service.dto.ProducerAwardIntervalDTO;
import com.renanwillian.service.dto.ProducerDTO;
import com.renanwillian.service.exception.NoContentException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class ProducerService {

    @Inject
    ProducerRepository producerRepository;

    @Inject
    MovieRepository movieRepository;

    public ProducerDTO persist(ProducerDTO producerDTO) {
        Producer producer = new Producer();
        if (producerDTO.getProducerId() != null) {
            producer = producerRepository.findByIdOptional(producerDTO.getProducerId())
                                         .orElseThrow(NoContentException.build());
        }
        producer.setName(producerDTO.getName());
        producerRepository.persist(producer);
        return new ProducerDTO(producer);
    }

    public ProducerDTO find(Long producerId) {
        return new ProducerDTO(producerRepository.findByIdOptional(producerId)
                                                 .orElseThrow(NoContentException.build()));
    }

    public void delete(Long producerId) {
        Producer producer = producerRepository.findByIdOptional(producerId)
                                              .orElseThrow(NoContentException.build());
        if (movieRepository.existsMovieByProducer(producerId)) throw new IllegalStateException();
        producerRepository.delete(producer);
    }

    public List<ProducerDTO> list() {
        return producerRepository.listAll().stream().map(ProducerDTO::new).collect(Collectors.toList());
    }

    public ProducerAwardIntervalDTO getProducerAwardInterval() {
        return new ProducerAwardIntervalDTO.Builder()
                .withMax(producerRepository.listProducerWithMaxIntervalAward())
                .withMin(producerRepository.listProducerWithMinIntervalAward())
                .build();
    }
}

package com.dh.serie.service;

import com.dh.serie.event.SerieCreadaEventProducer;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final SerieCreadaEventProducer serieCreadaEventProducer;


    public SerieService(SerieRepository repository, SerieCreadaEventProducer serieCreadaEventProducer) {
        this.repository = repository;

        this.serieCreadaEventProducer = serieCreadaEventProducer;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        serieCreadaEventProducer.publishCrearSerie(serie);
        repository.save(serie);
        return serie.getId();
    }
}

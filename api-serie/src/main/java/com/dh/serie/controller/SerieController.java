package com.dh.serie.controller;

import com.dh.serie.event.SerieCreadaEventProducer;
import com.dh.serie.model.Serie;
import com.dh.serie.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;
    //private final SerieCreadaEventProducer serieCreadaEventProducer;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;

    }

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieService.create(serie);
        //serieCreadaEventProducer.publishCrearSerie(serie);
        return serie.getId();
    }

}

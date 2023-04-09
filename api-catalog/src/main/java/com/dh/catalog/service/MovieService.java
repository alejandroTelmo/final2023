package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Long create(MovieServiceClient.MovieDto movieDto){
        movieRepository.save(movieDto);
        return movieDto.getId();
    }
    public List<MovieServiceClient.MovieDto> getAll(){
        return movieRepository.findAll();
    }
}

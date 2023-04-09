package com.dh.catalog.service;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SerieService {

  private final   SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<SerieServiceClient.SerieDTO> getSeries(){
        return serieRepository.findAll();
    }
    public String create(SerieServiceClient.SerieDTO serieDTO){
        serieRepository.save(serieDTO);
        return serieDTO.getId();
    }
}

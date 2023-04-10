package com.dh.catalog.repository;

import com.dh.catalog.client.SerieServiceClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<SerieServiceClient.SerieDTO,String> {
    List<SerieServiceClient.SerieDTO> findByGenre(String genre);
}

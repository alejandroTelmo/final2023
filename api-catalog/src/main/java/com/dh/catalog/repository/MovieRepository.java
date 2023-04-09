package com.dh.catalog.repository;

import com.dh.catalog.client.MovieServiceClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<MovieServiceClient.MovieDto,Long> {
}

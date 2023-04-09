package com.dh.catalog.repository;

import com.dh.catalog.client.MovieServiceClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieServiceClient.MovieDto,Long> {
}

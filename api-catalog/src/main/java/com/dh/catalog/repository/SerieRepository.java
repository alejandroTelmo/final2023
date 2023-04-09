package com.dh.catalog.repository;

import com.dh.catalog.client.SerieServiceClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieServiceClient.SerieDTO,String> {
}

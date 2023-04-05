package com.dh.catalog.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="serie-service")
public interface SerieServiceClient {

  @GetMapping("/api/v1/series/{genre}")
    List<SerieDTO> getSerieByGenre(@PathVariable String genre);

  @GetMapping("/api/v1/series")
  List<SerieDTO> getSerie();
  @Setter
  @Getter
    class SerieDTO{
      private String id;
      private String name;
      private String genre;
      private List<SeasonDTO> seasons = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class SeasonDTO {

      private Integer seasonNumber;
      private List<ChapterDTO> chapters = new ArrayList<>();

      @AllArgsConstructor
      @NoArgsConstructor
      @Setter
      @Getter
      public static class ChapterDTO {

        private String name;
        private Integer number;
        private String urlStream;


      }

    }
  }
}

package com.dashboard.repository;

import com.dashboard.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DashboardRepository extends JpaRepository<Articles, Long> {

    @Query(value = "SELECT start_year, swot, COUNT(swot), topic FROM articles WHERE TRIM(start_year) != '' " +
            "AND TRIM(swot) != '' AND TRIM(topic) != '' GROUP BY start_year, swot, topic", nativeQuery = true)
    List<Map<String, Object>> findSwotData();

    @Query(value = "SELECT DISTINCT sector FROM articles WHERE TRIM(sector) != ''", nativeQuery = true)
    List<String> findAllSectors();

    @Query(value = "SELECT DISTINCT region FROM articles WHERE TRIM(region) != ''", nativeQuery = true)
    List<String> findAllRegion();

    @Query(value = "SELECT likelihood FROM articles", nativeQuery = true)
    List<Integer> findAllLikelihood();

    @Query(value = "SELECT DISTINCT country, COUNT(relevance) as relevance_count FROM articles GROUP BY country", nativeQuery = true)
    List<Map<String, Object>> findDistinctCountryWithRelevanceCount();

    @Query("SELECT DISTINCT d.city FROM Articles d")
    List<String> findAllCities();

    @Query("SELECT DISTINCT d.country FROM Articles d")
    List<String> findAllCountries();

    @Query("SELECT DISTINCT d.startYear FROM Articles d")
    List<Integer> findAllStartYear();

    @Query("SELECT DISTINCT d.endYear FROM Articles d")
    List<Integer> findAllEndYear();

    List<Articles> findAllByStartYear(int parseInt);

    List<Articles> findAllByEndYear(int parseInt);

    List<Articles> findAllByCountry(String filterValue);

    List<Articles> findAllByCity(String filterValue);

}

package com.dashboard.repository;

import com.dashboard.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DashboardRepository extends JpaRepository<Articles, Long> {

    @Query(value = "SELECT start_year, end_year, swot, COUNT(swot), topic FROM articles WHERE TRIM(start_year) != '' " +
            "AND TRIM(end_year) != '' AND TRIM(swot) != '' AND TRIM(topic) != '' GROUP BY start_year, swot, topic", nativeQuery = true)
    List<Map<String, Object>> findSwotData();

    @Query(value = "SELECT DISTINCT sector FROM articles WHERE TRIM(sector) != ''", nativeQuery = true)
    List<String> findAllSectors();

    @Query(value = "SELECT DISTINCT region FROM articles WHERE TRIM(region) != ''", nativeQuery = true)
    List<String> findAllRegion();

    @Query(value = "SELECT likelihood FROM articles", nativeQuery = true)
    List<Integer> findAllLikelihood();

    @Query("SELECT DISTINCT d.city FROM Articles d")
    List<String> findAllCities();

    @Query("SELECT DISTINCT d.country FROM Articles d")
    List<String> findAllCountries();

    @Query(value = "SELECT DISTINCT start_year FROM articles WHERE TRIM(start_year) !='' ORDER BY start_year ASC", nativeQuery = true)
    List<Integer> findAllStartYear();

    @Query(value = "SELECT DISTINCT end_year FROM articles WHERE TRIM(start_year) !='' ORDER BY end_year ASC", nativeQuery = true)
    List<Integer> findAllEndYear();

//    List<Articles> findAllByStartYear(int parseInt);
//
//    List<Articles> findAllByEndYear(int parseInt);
//
//    List<Articles> findAllByCountry(String filterValue);
//
//    List<Articles> findAllByCity(String filterValue);


    //Filter queries for Sector
    @Query(value = "SELECT sector, COUNT(SECTOR) as sector_count FROM articles WHERE CITY = :city AND TRIM(SECTOR) != '' GROUP BY SECTOR", nativeQuery = true)
    List<Map<String, Object>> findSectorCountByCity(String city);
    @Query(value = "SELECT sector, COUNT(SECTOR) as sector_count FROM articles WHERE COUNTRY = :country AND TRIM(SECTOR) != '' GROUP BY SECTOR", nativeQuery = true)
    List<Map<String, Object>> findSectorByCountry(String country);
    @Query(value = "SELECT sector, COUNT(SECTOR) as sector_count FROM articles WHERE start_year = :startYear AND TRIM(SECTOR) != '' GROUP BY SECTOR", nativeQuery = true)
    List<Map<String, Object>> findSectorByStartDate(Integer startYear);
    @Query(value = "SELECT sector, COUNT(SECTOR) as sector_count FROM articles WHERE end_year = :endYear AND TRIM(SECTOR) != '' GROUP BY SECTOR", nativeQuery = true)
    List<Map<String, Object>> findSectorByEndDate(Integer endYear);
    @Query(value = "SELECT sector, COUNT(SECTOR) as sector_count FROM articles WHERE TRIM(SECTOR) != '' GROUP BY SECTOR", nativeQuery = true)
    List<Map<String, Object>> findSectorAndCount();

    //Filter queries for Topic and Intensity chart
    @Query(value = "SELECT topic, sum(intensity) as intensity FROM articles WHERE start_year = :startYear AND TRIM(topic) !='' group by topic", nativeQuery = true)
    List<Map<String, Object>> findTopicIntensityByStartYear(int startYear);
    @Query(value = "SELECT topic, sum(intensity) as intensity FROM articles WHERE end_year = :endYear AND TRIM(topic) !='' group by topic", nativeQuery = true)
    List<Map<String, Object>>  findTopicIntensityByEndYear(int endYear);
    @Query(value = "SELECT topic, sum(intensity) as intensity FROM articles WHERE TRIM(topic) !='' group by topic", nativeQuery = true)
    List<Map<String, Object>> findAllTopicAndIntensity();


    //Filter queries for Country and Relevance chart
    @Query(value = "SELECT DISTINCT country, COUNT(relevance) as relevance_count FROM articles WHERE TRIM(country) != '' GROUP BY country", nativeQuery = true)
    List<Map<String, Object>> findDistinctCountryWithRelevanceCount();
    @Query(value = "SELECT country, COUNT(relevance) as relevance_count FROM articles WHERE start_year = :startYear AND country IS NOT NULL AND TRIM(country) != '' GROUP BY country", nativeQuery = true)
    List<Map<String, Object>> findCountryRelevanceByStartYear(Integer startYear);
    @Query(value = "SELECT country, COUNT(relevance) as relevance_count FROM articles WHERE end_year = :endYear AND country IS NOT NULL AND TRIM(country) != '' GROUP BY country", nativeQuery = true)
    List<Map<String, Object>> findCountryRelevanceByEndYear(Integer endYear);

    @Query(value = "SELECT country,  COUNT(*) as sector_count, SUM(intensity) as intensity, SUM(relevance) as relevance FROM articles  " +
            "WHERE country IS NOT NULL AND country <> '' GROUP BY country, sector ORDER BY sector_count ASC LIMIT 1;", nativeQuery = true)
    List<Object[]> findCountryWithLowestSector();

    @Query(value = "SELECT country,  COUNT(*) as sector_count, SUM(intensity) as intensity, SUM(relevance) as relevance FROM articles  " +
            "WHERE country IS NOT NULL AND country <> '' GROUP BY country, sector ORDER BY sector_count DESC LIMIT 1;", nativeQuery = true)
    List<Object[]> findCountryWithHighestSector();

}

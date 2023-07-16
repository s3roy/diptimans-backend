package com.dashboard.service;

import com.dashboard.model.Articles;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<Articles> getAllData();
    List<Map<String, Object>> findSwotData();
    List<String> findSectors();
    List<String> findAllRegion();
    List<Integer> findAllLikelihood();
    List<Map<String, Object>> getCountryWithRelevance(Integer startYear, Integer endYear);
    List<String> getAllCities();
    List<String> getAllCountries();
    List<Integer> getAllStartYear();
    List<Integer> getAllEndYear();
    List<Map<String, Object>> getCountBySector(String sortBy, String filterValue);
    public List<Map<String, Object>> getTopicAndIntensityMap(Integer startYear, Integer endYear);
    public List<Object[]> getCountryWithHighestSector();
    public List<Object[]> getCountryWithLowestSector();

}

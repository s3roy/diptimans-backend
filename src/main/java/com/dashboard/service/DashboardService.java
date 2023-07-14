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
    List<Map<String, Object>> findCountryWithRelevance();
    List<String> getAllCities();
    List<String> getAllCountries();
    List<Integer> getAllStartYear();
    List<Integer> getAllEndYear();
    Map<String, Integer> getCountBySector(String sortBy, String filterValue);
}

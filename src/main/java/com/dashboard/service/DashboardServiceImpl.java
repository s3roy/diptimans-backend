package com.dashboard.service;

import com.dashboard.model.Articles;
import com.dashboard.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Articles> getAllData(){
        return dashboardRepository.findAll();
    }

    public List<Map<String, Object>> findSwotData(){
        return dashboardRepository.findSwotData();
    }

    @Override
    public List<String> findSectors() {
        return dashboardRepository.findAllSectors();
    }

    @Override
    public List<String> findAllRegion() {
        return dashboardRepository.findAllRegion();
    }

    @Override
    public List<Integer> findAllLikelihood() {
        return dashboardRepository.findAllLikelihood();
    }

    @Override
    public List<Map<String, Object>> findCountryWithRelevance() {
        return dashboardRepository.findDistinctCountryWithRelevanceCount();
    }

    public List<String> getAllCities() {
        List<String> cities = dashboardRepository.findAllCities();
        return cities.stream()
                .filter(city -> !city.trim().isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> getAllCountries() {
        List<String> cities = dashboardRepository.findAllCountries();
        return cities.stream()
                .filter(city -> !city.trim().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Integer> getAllStartYear() {
        List<Integer> startYears = dashboardRepository.findAllStartYear();
        return startYears.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Integer> getAllEndYear() {
        List<Integer> endYears = dashboardRepository.findAllEndYear();
        return endYears.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getCountBySector(String sortBy, String filterValue) {
        List<Articles> dataPoints = fetchDataPointsFromDatabase(sortBy, filterValue);

        return dataPoints.stream()
                .collect(Collectors.toMap(Articles::getSector, v -> 1, Integer::sum));
    }

    private List<Articles> fetchDataPointsFromDatabase(String sortBy, String filterValue) {
        if (sortBy != null && filterValue != null) {
            switch (sortBy) {
                case "city":
                    return dashboardRepository.findAllByCity(filterValue);
                case "country":
                    return dashboardRepository.findAllByCountry(filterValue);
                case "startYear":
                    return dashboardRepository.findAllByStartYear(Integer.parseInt(filterValue));
                case "endYear":
                    return dashboardRepository.findAllByEndYear(Integer.parseInt(filterValue));
                default:
                    throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
            }
        } else {
            return dashboardRepository.findAll();
        }
    }


}

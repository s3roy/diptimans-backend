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

    public List<Map<String, Object>> getCountBySector(String sortBy, String filterValue) {
        return fetchDataPointsFromDatabase(sortBy,filterValue);

//        List<Articles> dataPoints = fetchDataPointsFromDatabase(sortBy, filterValue);
//
//        return dataPoints.stream()
//                .filter(article -> !article.getSector().isEmpty())
//                .collect(Collectors.groupingBy(Articles::getSector, Collectors.summingInt(article -> 1)));
    }

    public List<Map<String, Object>> getTopicAndIntensityMap(Integer startYear, Integer endYear) {
        return fetchStartAndEndDateForTopicIntensity(startYear,endYear);

//        List<Articles> articles = fetchStartAndEndDate(startYear, endYear);
//        return articles.stream()
//                .filter(article -> !article.getTopic().isEmpty())
//                .collect(Collectors.toMap(Articles::getTopic, Articles::getIntensity));
    }


    @Override
    public List<Map<String, Object>> getCountryWithRelevance(Integer startYear, Integer endYear) {
        return fetchStartAndEndDateForCountryRelevance(startYear,endYear);
    }

    private List<Map<String, Object>> fetchStartAndEndDateForTopicIntensity(Integer startYear, Integer endYear){
        if (startYear != null){
            return dashboardRepository.findTopicIntensityByStartYear(startYear);
        } else if (endYear != null) {
            return dashboardRepository.findTopicIntensityByEndYear(endYear);
        } else {
            return dashboardRepository.findAllTopicAndIntensity();
        }
    }

    private List<Map<String, Object>> fetchStartAndEndDateForCountryRelevance(Integer startYear, Integer endYear){
        if (startYear != null){
            return dashboardRepository.findCountryRelevanceByStartYear(startYear);
        } else if (endYear != null) {
            return dashboardRepository.findCountryRelevanceByEndYear(endYear);
        } else {
            return dashboardRepository.findDistinctCountryWithRelevanceCount();
        }
    }

    private List<Map<String, Object>> fetchDataPointsFromDatabase(String sortBy, String filterValue) {
        if (sortBy != null && filterValue != null) {
            switch (sortBy) {
                case "city":
                    return dashboardRepository.findSectorCountByCity(filterValue);
                case "country":
                    return dashboardRepository.findSectorByCountry(filterValue);
                case "startYear":
                    return dashboardRepository.findSectorByStartDate(Integer.parseInt(filterValue));
                case "endYear":
                    return dashboardRepository.findSectorByEndDate(Integer.parseInt(filterValue));
                default:
                    throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
            }
        } else {
            return dashboardRepository.findSectorAndCount();
        }
    }


}

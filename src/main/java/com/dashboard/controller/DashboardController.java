package com.dashboard.controller;

import com.dashboard.model.Articles;
import com.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/data")
    public List<Articles> getAllArticles() {
        return dashboardService.getAllData();
    }

    @GetMapping("/swot-data")
    public List<Map<String, Object>> getSwotData(){
        return dashboardService.findSwotData();
    }

    @GetMapping("/sectors")
    public List<String> getSectors(){
        return dashboardService.findSectors();
    }

    @GetMapping("/region")
    public List<String> getRegion(){
        return dashboardService.findAllRegion();
    }

    @GetMapping("/likelihood")
    public List<Integer> getLikelihood(){
        return dashboardService.findAllLikelihood();
    }

    @GetMapping("/country-relevance")
    public List<Map<String, Object>> getCountryWithRelevance(
            @RequestParam(required = false) Integer startYear,
            @RequestParam(required = false) Integer endYear
    ){
        return dashboardService.getCountryWithRelevance(startYear,endYear);
    }

    @GetMapping("/cities")
    public List<String> getAllCities() {
        return dashboardService.getAllCities();
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        return dashboardService.getAllCountries();
    }

    @GetMapping("/start-years")
    public List<Integer> getAllStartYear() {
        return dashboardService.getAllStartYear();
    }

    @GetMapping("/end-years")
    public List<Integer> getAllEndYear() {
        return dashboardService.getAllEndYear();
    }

    @GetMapping("/topic-intensity")
    public List<Map<String, Object>> getTopicIntensity(
            @RequestParam(required = false) Integer startYear,
            @RequestParam(required = false) Integer endYear
    ){
        return dashboardService.getTopicAndIntensityMap(startYear,endYear);
    }

    @GetMapping("/sectors/count")
    public List<Map<String, Object>> countUniqueSectors(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String filterValue
    ) {
        return dashboardService.getCountBySector(sortBy, filterValue);
    }

    @GetMapping("/country/highest-sector")
    public List<Object[]> countryHighestSector(){
        return dashboardService.getCountryWithHighestSector();
    }

    @GetMapping("/country/lowest-sector")
    public List<Object[]> countryLowestSector(){
        return dashboardService.getCountryWithLowestSector();
    }
}
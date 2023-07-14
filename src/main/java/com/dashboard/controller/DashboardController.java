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
    public List<Map<String, Object>> getCountryWithRelevance(){
        return dashboardService.findCountryWithRelevance();
    }

    @GetMapping("/cities")
    public List<String> getAllCities() {
        System.out.println("List of cities endpoint is hit");
        return dashboardService.getAllCities();
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        System.out.println("List of countries endpoint is hit");
        return dashboardService.getAllCountries();
    }

    @GetMapping("/start-years")
    public List<Integer> getAllStartYear() {
        System.out.println("List of startdate endpoint is hit");
        return dashboardService.getAllStartYear();
    }

    @GetMapping("/end-years")
    public List<Integer> getAllEndYear() {
        System.out.println("List of enddate endpoint is hit");
        return dashboardService.getAllEndYear();
    }

    @GetMapping("/sectors/count")
    public Map<String, Integer> countUniqueSectors(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String filterValue
    ) {
        return dashboardService.getCountBySector(sortBy, filterValue);
    }
}
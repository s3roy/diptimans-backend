package com.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "citylng")
    private String cityLng;

    @Column(name = "citylat")
    private String cityLat;

    @Column(name = "intensity")
    private Integer Intensity;

    @Column(name = "sector")
    private String sector;

    @Column(name = "topic")
    private String topic;

    @Column(name = "insight", columnDefinition = "TEXT")
    private String insight;

    @Column(name = "swot", columnDefinition = "TEXT")
    private String swot;

    @Column(name = "url")
    private String url;

    @Column(name = "region")
    private String region;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "impact", columnDefinition = "TEXT")
    private String impact;

    @Column(name = "added")
    private String added;

    @Column(name = "published")
    private String published;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "relevance")
    private Integer relevance;

    @Column(name = "pestle")
    private String pestle;

    @Column(name = "source")
    private String source;

    @Column(name = "title", length = 500) // Increase the length to a suitable value
    private String title;


    @Column(name = "likelihood")
    private Integer likelihood;

}


package com.wxwstepan;

public class City {
    int id;
    String name;
    String region;
    String district;
    int population;
    String foundation;

    public City(int id, String name, String region, String district, int population, String foundation) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation=" + foundation +
                '}';
    }
}

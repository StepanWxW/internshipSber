package com.wxwstepan;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CityUtils {
    static int count = 0;

    public static List<City> parce(){
        List<City> cityList = new ArrayList<>();

        try(Scanner scanner = new Scanner(Paths.get("city_ru.csv"))) {
            scanner.useDelimiter(System.getProperty("line.separator"));
            while(scanner.hasNext()) {
                City city = parseLine(scanner.next());
                cityList.add(city);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cityList;

    }
    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    private static City parseLine(String line) {
        String[] cityArray = line.split(";");
        String idString = cityArray[0];
        String name = cityArray[1];
        String region = cityArray[2];
        String district = cityArray[3];
        String populationString = cityArray[4];
        String foundation = null;
        if (cityArray.length == 6) {
            foundation = cityArray[5];
        }
        int id = Integer.parseInt(idString);
        int population = Integer.parseInt(populationString);
        return new City(id,name,region,district,population,foundation);
    }
    public static List<City> sortNameCity(List<City> cityList) {
        return cityList.stream().sorted(Comparator.comparing(city -> city.name)).collect(Collectors.toList());
    }
    public static List<City> sortDistrictAndCity (List<City> cityList) {
        return cityList.stream().sorted(Comparator.comparing(city -> city.name)).
                sorted(Comparator.comparing(city -> city.district)).
                collect(Collectors.toList());
    }
    public static List<City> sortNameCityLambda (List<City> cityList) {
        cityList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return cityList;
    }
    public static List<City> sortNameCityComparator (List<City> cityList) {
        cityList.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return cityList;
    }

    public static void searchMaxPeople(List<City> cityList) {
        int maxPeople = 0;
        City maxCity = null;
        for(City city : cityList) {
            if (city.getPopulation() > maxPeople) {
                maxPeople = city.getPopulation();
                maxCity = city;
            }
        }
        System.out.println(maxCity.getId() + " = " + maxCity.getPopulation());
    }
    public static void searchMaxPeopleArray(List<City> cityList) {
        City[] array = new City[cityList.size()];
        cityList.toArray(array);
        City current = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].getPopulation() > current.getPopulation()) {
                current = array[i];
            }
        }
        System.out.println(current.getId() + " = " + current.getPopulation());

    }
    public static void sortDistrict(List<City> cityList) {
        List<City> cities =  cityList.stream().
                sorted(Comparator.comparing(city -> city.region)).
                collect(Collectors.toList());
        City citySave = new City();
        for (City city : cities) {
            if (citySave.region == null) {
                citySave.region = city.region;
                count++;
            } else{
                if (citySave.region.equals(city.region)){
                    count++;
                } else {
                    System.out.println(citySave.getRegion() + " - " + count);
                    citySave.region = city.region;
                    count = 1;
                }
            }
        }
    }
    public static void findCountCityByRegionV2(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));
    }
}

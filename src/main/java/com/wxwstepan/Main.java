package com.wxwstepan;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(Paths.get("city_ru.csv"));
        scanner.useDelimiter(System.getProperty("line.separator"));
        while(scanner.hasNext()){
            City city = parseLine(scanner.next());
            System.out.println(city);
        }
        scanner.close();

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
}
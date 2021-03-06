package systems.vostok.algo.bhargava._1_sails_person.implementations;

import systems.vostok.algo.bhargava._1_sails_person.data.DataSupplier;
import systems.vostok.algo.bhargava._1_sails_person.calculator.DistanceCalculator;
import systems.vostok.algo.bhargava._1_sails_person.dao.Point;
import systems.vostok.algo.bhargava._1_sails_person.permutations.heaps.HeapsPermGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * heaps perm - 9 elements - 2_700
 * heaps perm - 10 elements - 28_500
 * BenchmarkRunner.sailsPersonSimple i3  ss   10    37,484 ± 9,199   s/op
 * BenchmarkRunner.sailsPersonSimple i5  ss   10    16,227 ± 4,184   s/op
 */
public class SailsPersonSimpleForStyle {
    public static void main(String[] args) {
        Map<String, Point> data = DataSupplier.getMapData();

        Map<List<String>, Double> distances = calculateDistances(data, findAllPossibleRoutes(data));

        Map.Entry<List<String>, Double> minDistance = null;

        for (Map.Entry<List<String>, Double> distance : distances.entrySet()) {
            //System.out.println(distance.getKey() + "=" + distance.getValue());

            if (minDistance == null || minDistance.getValue().compareTo(distance.getValue()) > 0) {
                minDistance = distance;
            }
        }

        System.out.println("Min dist " + minDistance);
    }

    static List<List<String>> findAllPossibleRoutes(Map<String, Point> data) {
        //List<String> cities = new ArrayList<>(data.keySet());
        //return new SimplePermGenerator<String>().generate(cities);

        List<String> cities = new ArrayList<>(data.keySet());
        return new HeapsPermGenerator<String>().generate(cities);
    }

    static Map<List<String>, Double> calculateDistances(Map<String, Point> data, List<List<String>> possibleRoutes) {
        DistanceCalculator calculator = new DistanceCalculator(data);

        Map<List<String>, Double> distances = new HashMap<>();

        for (List<String> possibleRoute : possibleRoutes) {
            distances.put(possibleRoute, calculator.calculate(possibleRoute));
        }

        return distances;
    }
}

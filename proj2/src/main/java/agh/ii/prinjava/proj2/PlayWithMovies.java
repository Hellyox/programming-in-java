package agh.ii.prinjava.proj2;


import agh.ii.prinjava.proj2.dal.ImdbTop250;
import agh.ii.prinjava.proj2.model.Movie;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static agh.ii.prinjava.proj2.utils.Utils.oneToManyByActor;
import static agh.ii.prinjava.proj2.utils.Utils.oneToManyByDirector;
import static java.util.stream.Collectors.*;
import static javax.swing.UIManager.get;

interface PlayWithMovies {

    /**
     * Returns the movies (only titles) directed (or co-directed) by a given director
     */
    static List<String> ex01(String director) {
        System.out.println("\nSearching movies by "+director+"...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<String> directorMovies = optMovies.orElseThrow().stream()
                .filter(movie -> movie.directors().contains(director))
                        .map(Movie::title)
                                .collect(Collectors.toList());

        System.out.println(directorMovies);
        return directorMovies;
    }

    /**
     * Returns the movies (only titles) in which an actor played
     * Exactly the same code as Ex1, as the demand is the same
     */
    static List<String> ex02(String actor) {
        System.out.println("\nSearching movies with "+actor+"...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<String> actorMovies = optMovies.orElseThrow().stream()
                .filter(movie -> movie.actors().contains(actor))
                .map(Movie::title)
                .collect(Collectors.toList());

        System.out.println(actorMovies);
        return actorMovies;
    }

    /**
     * Returns the number of movies per director (as a map)
     */
    static Map<String, Long> ex03() {
        System.out.println("\nCalculating the number of movies per director...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        Map<String, Long> numberofmovies = optMovies.orElseThrow().stream()
                .flatMap(m -> oneToManyByDirector(m).stream())
                .collect(groupingBy(Movie::directors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> (long) e.getValue().size()));

        System.out.println(numberofmovies);
        return numberofmovies;
    }

    /**
     * Returns the 10 directors with the most films on the list
     */
    static List<Map.Entry<String, Long>> ex04() {
        System.out.println("\nLooking for the 10 directors with the most films");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<Map.Entry<String, Long>> topDirectors = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByDirector(m).stream())
                .collect(groupingBy(Movie::directors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> (long) e.getValue().size()))
                .entrySet()
                .stream()
                .sorted((l1, l2) -> Long.compare(l2.getValue(), l1.getValue()))
                .limit(10)
                .toList();
        System.out.println(topDirectors);
        return topDirectors;



    }

    /**
     * Returns the movies (only titles) made by each of the 10 directors found in {@link PlayWithMovies#ex04 ex04}
     */
    static List<Map.Entry<String, List<String>>> ex05() {
        throw new RuntimeException("ex05 is not implemented!");
    }

    /**
     * Returns the number of movies per actor (as a map)
     * same as ex03
     */
    static Map<String, Long> ex06() {
        System.out.println("\nCalculating the number of movies per actor...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        Map<String, Long> numberofmovies = optMovies.orElseThrow().stream()
                .flatMap(m -> oneToManyByActor(m).stream())
                .collect(groupingBy(Movie::actors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> (long) e.getValue().size()));

        System.out.println(numberofmovies);
        return numberofmovies;
    }

    /**
     * Returns the 9 actors with the most films on the list
     */
    static List<Map.Entry<String, Long>> ex07() {
        System.out.println("\nLooking for the 9 actors with the most films");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<Map.Entry<String, Long>> topActors = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByActor(m).stream())
                .collect(groupingBy(Movie::actors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> (long) e.getValue().size()))
                .entrySet()
                .stream()
                .sorted((l1, l2) -> Long.compare(l2.getValue(), l1.getValue()))
                .limit(10)
                .toList();
        System.out.println(topActors);
        return topActors;
    }

    /**
     * Returns the movies (only titles) of each of the 9 actors from {@link PlayWithMovies#ex07 ex07}
     */
    static List<Map.Entry<String, List<String>>> ex08() {
        throw new RuntimeException("ex08 is not implemented!");
    }

    /**
     * Returns the 5 most frequent actor partnerships (i.e., appearing together most often)
     */
    static List<Map.Entry<String, Long>> ex09() {
        throw new RuntimeException("ex09 is not implemented!");
    }

    /**
     * Returns the movies (only titles) of each of the 5 most frequent actor partnerships
     */
    static List<Map.Entry<String, List<String>>> ex10() {
        throw new RuntimeException("ex10 is not implemented!");
    }
}


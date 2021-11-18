package agh.ii.prinjava.proj2;

import agh.ii.prinjava.proj2.dal.ImdbTop250;
import agh.ii.prinjava.proj2.model.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static agh.ii.prinjava.proj2.utils.Utils.*;
import static java.util.stream.Collectors.*;

/**Using the stream API the code get pretty clear, as each function explains itself with its name
 * This Interface was pretty easy to code, as similar functions shared the same code, just needing to change director to actor
 */


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
     * Exactly the same code as Ex1, as the demand is the same but with actors
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
    static List<Map.Entry<String, List<String>>> ex05(){
        System.out.println("\nLooking for the movies of the 10 directors with the most movies...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<String> topDirectors = ex04().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        List<Map.Entry<String, List<String>>> topMovies = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByDirector(m).stream())
                .collect(Collectors.groupingBy(Movie::directors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> e.getValue()
                        //here we compare the rank of the movies to return a list in order
                        .stream()
                        .map(Movie::title)
                        .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .filter(e -> topDirectors.stream().anyMatch(entry -> entry.equals(e.getKey())))
                //The ex04 printed the result, so we also have an extra line printed  here
                .sorted((l1, l2) -> Integer.compare(l2.getValue().size(), l1.getValue().size()))
                .collect(Collectors.toList());
        System.out.println(topMovies);
        return topMovies;
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
     * Same as Ex04
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
                .limit(9)
                .toList();
        System.out.println(topActors);
        return topActors;
    }

    /**
     * Returns the movies (only titles) of each of the 9 actors from {@link PlayWithMovies#ex07 ex07}
     * Same as ex05
     */
    static List<Map.Entry<String, List<String>>> ex08() {
        System.out.println("\nLooking for the movies of the 9 actors with the most movies...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<String> topActors = ex07().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        //We re-use the previous exercise to already get the actors
        List<Map.Entry<String, List<String>>> topMovies = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByActor(m).stream())
                .collect(Collectors.groupingBy(Movie::actors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> e.getValue()
                        //here we compare the rank of the movies to return a list in order
                        .stream()
                        .map(Movie::title)
                        .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .filter(e -> topActors.stream().anyMatch(entry -> entry.equals(e.getKey())))
                //The ex07 printed the result, so we also have an extra line printed  here
                .sorted((l1, l2) -> Integer.compare(l2.getValue().size(), l1.getValue().size()))
                .collect(Collectors.toList());
        System.out.println(topMovies);
        return topMovies;
    }

    /**
     * Returns the 5 most frequent actor partnerships (i.e., appearing together most often)
     */
    static List<Map.Entry<String, Long>> ex09() {
        System.out.println("\nLooking for 5 most frequent actor partnerships...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<Map.Entry<String, Long>> buddies = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByActorDuo(m).stream())
                .collect(Collectors.groupingBy(Movie::actors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> (long) e.getValue().size()))
                .entrySet()
                .stream()
                .sorted((l1,l2) -> Long.compare((l2.getValue()), l1.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(buddies);
        return buddies;
    }

    /**
     * Returns the movies (only titles) of each of the 5 most frequent actor partnerships
     */
    static List<Map.Entry<String, List<String>>> ex10() {
        System.out.println("\nLooking for 5 most frequent actor partnerships films...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        List<Map.Entry<String, List<String>>> buddyMovie = optMovies.orElseThrow()
                .stream()
                .flatMap(m -> oneToManyByActorDuo(m).stream())
                .collect(Collectors.groupingBy(Movie::actors))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().get(0), e -> e.getValue()
                        .stream()
                        .map(Movie::title)
                        .collect(Collectors.toList())))
                .entrySet()
                .stream()
                .sorted((l1,l2) -> Integer.compare((l2.getValue().size()), l1.getValue().size()))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(buddyMovie);
        return buddyMovie;
    }
}


package agh.ii.prinjava.proj3.dal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;

class MovieDAOTest {

    private static final String dbURL = "jdbc:sqlite:datasources/imdb_top250.db";
    MovieDAO movieDAO = new MovieDAO(dbURL);

    @Test
    void moviesDirectedBy() {
        assertThat(movieDAO.moviesDirectedBy("Christopher Nolan").orElseThrow())
                .containsExactlyInAnyOrder("The Dark Knight", "Inception", "Interstellar",
                        "The Prestige", "Memento", "The Dark Knight Rises", "Batman Begins");

        assertThat(movieDAO.moviesDirectedBy("Guy Ritchie").orElseThrow())
                .containsExactlyInAnyOrder("Snatch", "Lock, Stock and Two Smoking Barrels");

        assertThat(movieDAO.moviesDirectedBy("Quentin Tarantino").orElseThrow())
                .containsExactlyInAnyOrder("Pulp Fiction", "Django Unchained", "Reservoir Dogs",
                        "Inglourious Basterds", "Kill Bill: Vol. 1", "Sin City");
    }

    @Test
    void moviesTheActorPlayedIn() {
        assertThat(movieDAO.moviesTheActorPlayedIn("Tom Hanks").orElseThrow())
                .containsExactlyInAnyOrder("Forrest Gump", "Saving Private Ryan", "The Green Mile",
                        "Toy Story 3", "Toy Story", "Catch Me If You Can");

        assertThat(movieDAO.moviesTheActorPlayedIn("Brad Pitt").orElseThrow())
                .containsExactlyInAnyOrder("Fight Club", "Inglourious Basterds", "Snatch");

        assertThat(movieDAO.moviesTheActorPlayedIn("Leonardo DiCaprio").orElseThrow())
                .containsExactlyInAnyOrder("Inception", "The Departed", "Django Unchained", "The Wolf of Wall Street",
                        "Shutter Island", "Catch Me If You Can", "Blood Diamond", "The Revenant");
    }

    @Test
    void numberOfMoviesPerDirector() {
        // We can check if the entry is present in the map or...
        assertThat(movieDAO.numberOfMoviesPerDirector().orElseThrow())
                .contains(Map.entry("David Fincher", 3L));

        // We can also test everything at once
        assertThat(movieDAO.numberOfMoviesPerDirector().orElseThrow())
                .containsAllEntriesOf(Map.of("Alfred Hitchcock", 9L, "James Gunn", 2L, "David Fincher", 3L));
    }

    @Test
    void numberOfMoviesPerTop10Director() {
        /*
        containsExactlyEntriesOf Verifies that the actual map contains only the entries of the given map
        and nothing else, in order.
        If we use this, it will be too long because we can't use Map.of, so we would have to create a new LinkedHashMap
        and put what we want to test one by one. Check the sequence of the entries is faster and check the same thing.

        containsExactlyInAnyOrderEntriesOf Verifies that the actual map contains only the given entries
        and nothing else, in any order.
        */
        assertThat(movieDAO.numberOfMoviesPerTop10Director().orElseThrow())
                .containsExactly(Map.entry("Alfred Hitchcock", 9L),
                        Map.entry("Stanley Kubrick", 8L),
                        Map.entry("Steven Spielberg", 7L),
                        Map.entry("Martin Scorsese", 7L),
                        Map.entry("Christopher Nolan", 7L),
                        Map.entry("Billy Wilder", 7L),
                        Map.entry("Quentin Tarantino", 6L),
                        Map.entry("Charles Chaplin", 5L),
                        Map.entry("Ridley Scott", 4L),
                        Map.entry("Frank Capra", 4L));

    }

    @Test
    void moviesPerTop10Director() {
        // We need to test the order of the key first, then if each key contains all the value as expected
        assertThat(movieDAO.moviesPerTop10Director().orElseThrow().keySet())
                .containsExactly("Alfred Hitchcock",
                        "Stanley Kubrick",
                        "Billy Wilder",
                        "Christopher Nolan",
                        "Martin Scorsese",
                        "Steven Spielberg",
                        "Quentin Tarantino",
                        "Charles Chaplin",
                        "Frank Capra",
                        "Ridley Scott");

        // We check if the given key contains exactly in order the expected values
        assertThat(movieDAO.moviesPerTop10Director().orElseThrow().get("Alfred Hitchcock"))
                .containsExactlyInAnyOrder("Psycho",
                        "Rear Window",
                        "North by Northwest",
                        "Vertigo",
                        "Dial M for Murder",
                        "Rebecca",
                        "Strangers on a Train",
                        "Notorious",
                        "Rope");


        assertThat(movieDAO.moviesPerTop10Director().orElseThrow().get("Charles Chaplin"))
                .containsExactlyInAnyOrder("City Lights",
                        "Modern Times",
                        "The Great Dictator",
                        "The Kid",
                        "The Gold Rush");

    }

    @Test
    void numberOfMoviesPerActor() {
        // Like in ex03 we can either check for a single entry or be quicker and check many entries
        assertThat(movieDAO.numberOfMoviesPerActor().orElseThrow())
                .contains(Map.entry("Leonardo DiCaprio", 8L));

        assertThat(movieDAO.numberOfMoviesPerActor().orElseThrow())
                .containsAllEntriesOf(Map.of("Leonardo DiCaprio", 8L,
                        "Clint Eastwood", 3L,
                        "Tom Hanks", 6L,
                        "Liam Neeson", 2L));
    }

    @Test
    void numberOfMoviesPerTop9Actor() {
        assertThat(movieDAO.numberOfMoviesPerTop9Actor().orElseThrow())
                .containsExactly(Map.entry("Leonardo DiCaprio", 8L),
                        Map.entry("Robert De Niro", 7L),
                        Map.entry("James Stewart", 7L),
                        Map.entry("Harrison Ford", 7L),
                        Map.entry("Tom Hanks", 6L),
                        Map.entry("William Holden", 5L),
                        Map.entry("Tom Hardy", 5L),
                        Map.entry("Paul Newman", 5L),
                        Map.entry("Cary Grant", 5L));
    }

    @Test
    void moviesPerTop9Actor() {
        // the DataBase is sorted by alphabetic order for actors with same numbers of movies
        assertThat(movieDAO.moviesPerTop9Actor().orElseThrow().keySet())
                .containsExactly("Leonardo DiCaprio",
                        "Harrison Ford",
                        "James Stewart",
                        "Robert De Niro",
                        "Tom Hanks",
                        "Cary Grant",
                        "Paul Newman",
                        "Tom Hardy",
                        "William Holden");

        assertThat(movieDAO.moviesPerTop9Actor().orElseThrow().get("Robert De Niro"))
                .containsExactlyInAnyOrder("The Godfather: Part II",
                        "Goodfellas",
                        "Once Upon a Time in America",
                        "Raging Bull",
                        "Heat",
                        "Casino",
                        "The Deer Hunter");


        assertThat(movieDAO.moviesPerTop9Actor().orElseThrow().get("William Holden"))
                .containsExactlyInAnyOrder("Sunset Boulevard",
                        "The Bridge on the River Kwai",
                        "Network",
                        "Stalag 17",
                        "The Wild Bunch");
    }

    @Test
    void top5FrequentActorPartnerships() {
        assertThat(movieDAO.top5FrequentActorPartnerships().orElseThrow())
                .containsExactly(Map.entry("Carrie Fisher, Harrison Ford", 4L), // 'Exactly' because it's a ranking
                        Map.entry("Carrie Fisher, Mark Hamill", 4L),
                        Map.entry("Harrison Ford, Mark Hamill", 4L),
                        Map.entry("Joe Pesci, Robert De Niro", 4L),
                        Map.entry("Christian Bale, Michael Caine", 3L));
    }

    @Test
    void moviesPerTop5ActorPartnerships() {
        // We need to test the order of the key first, then if each key contains all the value as expected
        assertThat(movieDAO.moviesPerTop5ActorPartnerships().orElseThrow().keySet())
                .containsExactly("Carrie Fisher, Harrison Ford",
                        "Carrie Fisher, Mark Hamill",
                        "Harrison Ford, Mark Hamill",
                        "Joe Pesci, Robert De Niro",
                        "Christian Bale, Michael Caine");

        assertThat(movieDAO.moviesPerTop5ActorPartnerships().orElseThrow().get("Carrie Fisher, Harrison Ford"))
                .containsExactlyInAnyOrder("Star Wars: Episode V - The Empire Strikes Back",
                        "Star Wars: Episode IV - A New Hope",
                        "Star Wars: Episode VI - Return of the Jedi",
                        "Star Wars: The Force Awakens");

        assertThat(movieDAO.moviesPerTop5ActorPartnerships().orElseThrow().get("Carrie Fisher, Mark Hamill"))
                .containsExactlyInAnyOrder("Star Wars: Episode V - The Empire Strikes Back",
                        "Star Wars: Episode IV - A New Hope",
                        "Star Wars: Episode VI - Return of the Jedi",
                        "Star Wars: The Force Awakens");

        assertThat(movieDAO.moviesPerTop5ActorPartnerships().orElseThrow().get("Christian Bale, Michael Caine"))
                .containsExactlyInAnyOrder("The Dark Knight",
                        "The Prestige",
                        "Batman Begins");
    }
}
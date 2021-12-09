package agh.ii.prinjava.proj3;

import com.sun.security.jgss.GSSUtil;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Movie Data Access Object
 *
 * @see <a href="https://www.oracle.com/java/technologies/dataaccessobject.html">Data Access Object</a>
 */
public class MovieDAO {
    private final String dbURL;

    public MovieDAO(String dbURL) {
        this.dbURL = dbURL;
    }

    /**
     * ex01 - the movies (only titles) directed (or co-directed) by a given director
     */
    public Optional<Set<String>> moviesDirectedBy(String directorName) {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfDirectorSQL)) {
            //TODO complete implementation of ex01
            stmt.setString(1, directorName);
            Set<String> set = new LinkedHashSet<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                set.add(title);
                System.out.println("From function :" + title);
            }
            return Optional.of(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();    // If we came to this point it means we didnt reach return in the try.
    }

    /**
     * ex02 - the movies (only titles) in which an actor played
     */
    public Optional<Set<String>> moviesTheActorPlayedIn(String actorName) {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfActorSQL)) {
            //TODO complete implementation of ex02
            stmt.setString(1, actorName);
            Set<String> set = new LinkedHashSet<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                set.add(title);
                System.out.println(title);
            }
            return Optional.of(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex03 - the number of movies per director (as a map)
     */
    public Optional<Map<String, Long>> numberOfMoviesPerDirector() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numberOfMoviesPerDirectorSQL)) {
            //TODO complete implementation of ex03
            Map<String, Long> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String director = rs.getString("director");
                Long nbrMovies = rs.getLong("numOfMovies");
                map.put(director, nbrMovies);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex04 - the 10 directors with the most films on the list
     */
    public Optional<Map<String, Long>> numberOfMoviesPerTop10Director() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numOfMoviesPerTop10DirectorSQL)) {
            //TODO complete implementation of ex04
            Map<String, Long> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String director = rs.getString("director");
                Long nbrMovies = rs.getLong("numOfMovies");
                map.put(director, nbrMovies);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex05 - the movies (only titles) made by each of the 10 directors
     */
    public Optional<Map<String, Set<String>>> moviesPerTop10Director() {
        try (Connection con = DriverManager.getConnection(dbURL); PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop10DirectorsSQL)) {
            Map<String, Set<String>> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String director = rs.getString("director");
                Set<String> set = new LinkedHashSet<>();

                var numOfMovies = rs.getInt("numOfMovies");

                for (int i = 0; i < numOfMovies - 1; i++) {
                    set.add(rs.getString("title"));
                    rs.next();
                }

                set.add(rs.getString("title"));
                map.put(director, set);
            }

            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex06 - the number of movies per actor (as a map)
     */
    public Optional<Map<String, Long>> numberOfMoviesPerActor() {
        //TODO complete implementation of ex06
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numberOfMoviesPerActorSQL)) {
            //TODO complete implementation of ex04
            Map<String, Long> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String actor = rs.getString("actor");
                Long nbrMovies = rs.getLong("numOfMovies");
                map.put(actor, nbrMovies);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex07 - the 9 actors with the most films on the list
     */
    public Optional<Map<String, Long>> numberOfMoviesPerTop9Actor() {
        //TODO complete implementation of ex07
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numOfMoviesPerTop9ActorSQL)) {
            Map<String, Long> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String actor = rs.getString("actor");
                Long nbrMovies = rs.getLong("numOfMovies");
                map.put(actor, nbrMovies);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex08 - the movies (only titles) of each of the 9 actors
     */
    public Optional<Map<String, Set<String>>> moviesPerTop9Actor() {
        //TODO complete implementation of ex08
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop9ActorSQL)) {
            Map<String, Set<String>> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String actor = rs.getString("actor");
                Set<String> set = new LinkedHashSet<>();
                set.add(rs.getString("title"));
                for (int i = 1; i < rs.getInt("numOfMovies"); i++) {
                    rs.next();
                    set.add(rs.getString("title"));
                }
                map.put(actor, set);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex09 - the 5 most frequent actor partnerships (i.e., appearing together most often)
     */
    public Optional<Map<String, Long>> top5FrequentActorPartnerships() {
        //TODO complete implementation of ex09
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.top5ActorPartnershipsSQL)) {
            Map<String, Long> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String duo = rs.getString("actor1") + ", " + rs.getString("actor2"); // output = "Joe Pesci, Robert De Niro"
                Long count = rs.getLong("cnt");
                map.put(duo, count);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * ex10 - the movies (only titles) of each of the 5 most frequent actor partnerships
     */
    public Optional<Map<String, Set<String>>> moviesPerTop5ActorPartnerships() {
        //TODO complete implementation of ex10
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop5ActorPartnershipsSQL)) {
            Map<String, Set<String>> map = new LinkedHashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String duo = rs.getString("actor1") + ", " + rs.getString("actor2");
                Set<String> set = new LinkedHashSet<>();
                set.add(rs.getString("title"));
                for (int i = 1; i < rs.getInt("cnt"); i++) {
                    rs.next();
                    set.add(rs.getString("title"));
                }
                map.put(duo, set);
            }
            System.out.println(map);
            return Optional.of(map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

class MovieSQLs {
    // ex01 - the movies (only titles) directed (or co-directed) by a given director
    static final String moviesOfDirectorSQL = """
            SELECT m.title AS title
            FROM movies m, movie_directors md, directors d
            WHERE m.id = md.movie_id AND md.director_id = d.id AND d.name = ?
            """;

    // ex02 - the movies (only titles) in which an actor played
    static final String moviesOfActorSQL = """
            SELECT m.title AS title
            FROM movies m, movie_actors ma, actors a
            WHERE m.id = ma.movie_id AND ma.actor_id = a.id AND a.name = ?
            """;

    // ex03 - the number of movies per director (as a map)
    static final String numberOfMoviesPerDirectorSQL = """
            SELECT d.name AS director, count (*) AS numOfMovies
            FROM directors d, movie_directors md
            WHERE d.id = md.director_id
            GROUP BY d.name
            """;

    // ex04 - the 10 directors with the most films on the list
    static final String numOfMoviesPerTop10DirectorSQL = """
            SELECT d.name AS director, count (*) AS numOfMovies
            FROM directors d, movie_directors md
            WHERE d.id = md.director_id
            GROUP BY d.name
            ORDER BY numOfMovies DESC
            LIMIT 10
            """;

    // ex05 - the movies (only titles) made by each of the 10 directors
    static final String moviesOfTop10DirectorsSQL = """
            SELECT d.name AS director, m.title AS title,
              (SELECT count(*) FROM movie_directors WHERE director_id = d.id GROUP BY director_id) AS numOfMovies
            FROM directors d, movie_directors md, movies m
            WHERE d.id = md.director_id
            AND md.movie_id = m.id
            AND d.id IN (SELECT d.id
                         FROM directors d, movie_directors md
                         WHERE d.id = md.director_id
                         GROUP BY d.id
                         ORDER BY count (*) DESC
                         LIMIT 10)
            ORDER BY numOfMovies DESC, d.name
            """;

    // ex06 - the number of movies per actor (as a map)
    static final String numberOfMoviesPerActorSQL = """
            SELECT a.name AS actor, count (*) AS numOfMovies
            FROM actors a, movie_actors ma
            WHERE a.id = ma.actor_id
            GROUP BY a.name
            """;

    // ex07 - the 9 actors with the most films on the list
    static final String numOfMoviesPerTop9ActorSQL = """
            SELECT a.name AS actor, count (*) AS numOfMovies
            FROM actors a, movie_actors ma
            WHERE a.id = ma.actor_id
            GROUP BY a.name
            ORDER BY numOfMovies DESC
            LIMIT 9
            """;

    // ex08 - the movies (only titles) of each of the 9 actors
    static final String moviesOfTop9ActorSQL = """
            SELECT a.name AS actor, m.title AS title,
              (SELECT count(*) FROM movie_actors WHERE actor_id = a.id GROUP BY actor_id) AS numOfMovies
            FROM actors a, movie_actors ma, movies m
            WHERE a.id = ma.actor_id
            AND ma.movie_id = m.id
            AND a.id IN (SELECT a.id
                         FROM actors a, movie_actors ma
                         WHERE a.id = ma.actor_id
                         GROUP BY a.id
                         ORDER BY count (*) DESC
                         LIMIT 9)
            ORDER BY numOfMovies DESC, a.name
            """;

    // ex09 - the 5 most frequent actor partnerships (i.e., appearing together most often)
    static final String top5ActorPartnershipsSQL = """
            SELECT t1.name AS actor1, t2.name AS actor2, count(*) cnt
            FROM (SELECT a.id, a.name, ma.movie_id
                  FROM actors a, movie_actors ma
                  WHERE a.id = ma.actor_id) t1
            JOIN (SELECT a.id, a.name, ma.movie_id
                  FROM actors a, movie_actors ma
                  WHERE a.id = ma.actor_id) t2
            ON t1.id < t2.id AND t1.movie_id = t2.movie_id
            GROUP BY 1,2
            ORDER BY cnt DESC
            LIMIT 5
            """;

    // ex10 - the movies (only titles) of each of the 5 most frequent actor partnerships
    static final String moviesOfTop5ActorPartnershipsSQL = """
            SELECT actor1, actor2, cnt, title
            FROM (SELECT t1.id AS a1_id, t1.name AS actor1, t2.id AS a2_id, t2.name as actor2, count(*) cnt
                  FROM (SELECT a.id, a.name, ma.movie_id
                        FROM actors a, movie_actors ma
                        WHERE a.id = ma.actor_id) t1
                  JOIN (SELECT a.id, a.name, ma.movie_id
                        FROM actors a, movie_actors ma
                        WHERE a.id = ma.actor_id) t2
                  ON t1.id < t2.id AND t1.movie_id = t2.movie_id
                  GROUP BY a1_id, a2_id
                  ORDER BY cnt DESC
                  LIMIT 5)
            JOIN (SELECT t3.title, t3.actor_id AS a3_id, t4.actor_id AS a4_id
                  FROM (SELECT m.title, m.id, ma.actor_id
                        FROM movies m, movie_actors ma
                        WHERE m.id = ma.movie_id) t3
                  JOIN (SELECT m.title, m.id, ma.actor_id
                        FROM movies m, movie_actors ma
                        WHERE m.id = ma.movie_id) t4
                  ON t3.actor_id < t4.actor_id AND t3.title = t4.title)
            ON (a1_id = a3_id AND a2_id = a4_id) OR (a1_id = a4_id AND a2_id = a3_id)
            ORDER BY cnt DESC, actor1, actor2
            """;
}


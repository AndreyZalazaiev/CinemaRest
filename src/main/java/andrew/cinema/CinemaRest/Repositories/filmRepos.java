package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface filmRepos extends CrudRepository<film,Integer> {

    @Query
    film findByIdfilm(Integer idfilm);
    @Query(value = "SELECT DISTINCT\n" +
            "    film.*\n" +
            "FROM\n" +
            "    cinema.film,\n" +
            "    cinema.session\n" +
            "WHERE\n" +
            "    (film.idfilm = session.idfilm\n" +
            "        AND session.Start >= CURDATE())\n" +
            "        OR film.idfilm NOT IN (SELECT DISTINCT\n" +
            "            session.idfilm\n" +
            "        FROM\n" +
            "            cinema.session) " +
            "order by idfilm;",nativeQuery = true)
    Iterable<film> getALL();
}

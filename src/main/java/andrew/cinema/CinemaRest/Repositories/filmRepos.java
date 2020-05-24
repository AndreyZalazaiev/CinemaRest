package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Query(value = "SELECT DISTINCT\n" +
            "    film.*\n" +
            "FROM\n" +
            "    cinema.film,\n" +
            "    cinema.session\n" +
            "WHERE\n" +
            "    film.idfilm = session.idfilm\n" +
            "        AND session.Start >= CURDATE()\n" +
            "order by idfilm;",nativeQuery = true)
    Iterable<film> getActual();

    @Query(value = "SELECT DISTINCT\n" +
            "    film.*\n" +
            "FROM\n" +
            "    cinema.film,\n" +
            "    cinema.session\n" +
            "WHERE\n" +
            "film.idfilm NOT IN (SELECT DISTINCT\n" +
            "            session.idfilm\n" +
            "        FROM\n" +
            "            cinema.session) " +
            "order by idfilm;",nativeQuery = true)
    Iterable<film> getSoon();

    @Query(value="Select AVG(review.mark)\n" +
            "from cinema.review,cinema.film\n" +
            "Where film.idfilm = review.idfilm\n" +
            "and film.idfilm =:id",nativeQuery = true)
    Double Rating(@Param("id") Integer idfilm);
}

package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.hall;
import andrew.cinema.CinemaRest.Entities.review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface reviewRepos extends CrudRepository<review,Integer> {
    @Query
    List<review> findDistinctByIdfilm(Integer idfilm);
}


package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ticketRepos extends CrudRepository<ticket,Integer> {

    @Query("FROM ticket group by place,rownum ")
    Iterable<ticket> getAllDistinct();
}

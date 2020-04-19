package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ticketRepos extends CrudRepository<ticket,Integer> {

    @Query("FROM ticket group by place,rownum ")
    Iterable<ticket> getAllDistinct();
    @Query("FROM ticket where idsession=:id group by place,rownum ")
    Iterable<ticket> getAllTicketsForSession(Integer id);
    @Query("FROM ticket where idaccount=:id group by place,rownum")
    Iterable<ticket>getTicketsForCurrentUser(@Param("id")String idaccout);
}

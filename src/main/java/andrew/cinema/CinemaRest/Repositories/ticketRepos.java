package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface ticketRepos extends CrudRepository<ticket,Integer> {

    @Query("FROM ticket group by place,rownum ")
    Iterable<ticket> getAllDistinct();
    @Query("FROM ticket where idsession=:id  group by place,rownum ")
    Iterable<ticket> getAllTicketsForSession(Integer id);
    @Query("FROM ticket where idaccount=:id group by place,rownum")
    Iterable<ticket>getTicketsForCurrentUser(@Param("id")String idaccout);
    @Modifying
    @Query("update ticket t set t.reservation=null,t.price=:price where t.idsession=:idsession and t.place=:place and t.rownum=:row")
    void   perfromBuying(@Param("idsession") Integer idsession, @Param("place") Integer place, @Param("row") Integer row,@Param("price") Float price );
    @Modifying
    @Query("DELETE from ticket where idticket=:id or idticket=:id+1")
    void   deleteCouple(@Param("id") Integer id );
}

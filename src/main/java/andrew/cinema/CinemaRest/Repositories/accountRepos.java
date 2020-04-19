package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface accountRepos extends CrudRepository<account,Integer> {
    @Query("from account a where a.Name = :name")
    List<account> searchByName(@Param("name") String name);
    @Query("from account a where a.Email = :email")
    List<account> searchByEmail(@Param("email") String email);
    //@Query
    //List<account>findByname(String Name);
    @Query
    account findByIdaccount(@Param("idaccount") String idaccount);

    @Query(" select Name,Picture,idaccount from account  ")
    Iterable<account> shortView();
    @Query(value="SELECT ticket.*, session.Start, session.End, film.Image, film.Name FName, hall.Name HName, hall.Type, cinema.Name CName, cinema.Adress\n" +
            "FROM cinema.ticket, cinema.session, cinema.film, cinema.hall,cinema.cinema\n" +
            "where ticket.idsession = session.idsession and \n" +
            "ticket.idaccount = ? and \n" +
            "session.idfilm = film.idfilm and\n" +
            "session.idhall = hall.idhall and\n" +
            "hall.idcinema = cinema.idcinema\n" +
            "group by ticket.Place, ticket.Rownum,ticket.idticket\n" +
            "order by ticket.idticket",
            nativeQuery = true)
   List<?> ticketsInfoAccount(String idaccount);
}

package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface accountRepos extends JpaRepository<account,Integer> {
    @Query("from account a where a.Name = :name")
    List<account> searchByName(@Param("name") String name);
    @Query("from account a where a.Email = :email")
    List<account> searchByEmail(@Param("email") String email);
    @Modifying
    @Query("update account a set a.DoB = :date  where a.idaccount = :idacc")
            void   setValue(@Param("date")String date,@Param("idacc") String idacc);
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
            "hall.idcinema = cinema.idcinema and\n" +
            "session.Start >=CURDATE() \n"+
            "group by ticket.Place, ticket.Rownum\n" +
            "order by ticket.idticket",
            nativeQuery = true)
   List<?> ticketsInfoAccount(String idaccount);
    @Modifying
    @Query("update account a set a.Bonus=:Bonus  where a.idaccount = :idacc")
    void   updateBonuses(@Param("Bonus")Integer Bonus,@Param("idacc") String idacc);
    @Modifying
    @Query("update account a set a.isresived=:isResived  where a.idaccount = :idacc")
    void   updateRecived(@Param("isResived")Integer isResived,@Param("idacc") String idacc);
    @Query(value = "select hour(session.End)-hour(session.Start)+((minute(session.End)-minute(session.Start))/60)\n" +
            "from cinema.session, cinema.ticket\n" +
            "where session.idsession = ticket.idsession and session.Start<=CURDATE() and \n" +
            "ticket.idaccount =:idaccount\n" +
            "group by session.idsession",nativeQuery = true)
    List<Double> Hours(@Param("idaccount") String idaccount);


}

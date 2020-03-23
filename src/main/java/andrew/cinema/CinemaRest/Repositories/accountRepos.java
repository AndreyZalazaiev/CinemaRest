package andrew.cinema.CinemaRest.Repositories;

import andrew.cinema.CinemaRest.Entities.account;
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
}

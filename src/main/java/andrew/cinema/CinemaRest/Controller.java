package andrew.cinema.CinemaRest;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.hall;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import andrew.cinema.CinemaRest.Repositories.cinemaRepos;
import andrew.cinema.CinemaRest.Repositories.filmRepos;
import andrew.cinema.CinemaRest.Repositories.hallRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @GetMapping(value = "/status")
    public String status()
    {
        return String.format("Server is responding ");
    }
    //region Account thing
    @Autowired
    private accountRepos accRep;
    @GetMapping(path="/accounts")
    public @ResponseBody
    Iterable<account> getAllUsers() {
        return accRep.findAll();}
    @RequestMapping("/accounts/add")
    public @ResponseBody String addNewAcc (@RequestParam String name
            , @RequestParam String pass,@RequestParam Integer bonus,@RequestParam String date,@RequestParam String email){
        account acc=new account();
        acc.setName(name);
        acc.setPass(pass);
        acc.setBonus(bonus);
        acc.setDoB(date);
        acc.setEmail(email);
        accRep.save(acc);
        return "Saved";
    }
    @RequestMapping("/accounts/find")
    public @ResponseBody Iterable<account> Find (@RequestParam String name)
    {
       // List<account> a = accRep.findByNameEquals(name);
        return accRep.searchByName(name);
    }
    @RequestMapping("/accounts/delete")
    public @ResponseBody String RemoveById (@RequestParam int id)
    {
        accRep.deleteById(id);
        return "deleted id:"+id;
    }
    //endregion
    //region Cinema thing
    @Autowired
    private cinemaRepos cinemaRep;
    @GetMapping(path="/cinema")
    public @ResponseBody
    Iterable<cinema> getAllCinema() {
        return cinemaRep.findAll();}
    @RequestMapping("/cinema/add")
    public @ResponseBody String addNewCinema (@RequestParam String name,@RequestParam String adress){
        cinema cm=new cinema();
        cm.setName(name);
        cm.setAdress(adress);
        cinemaRep.save(cm);
        return "Saved";
    }
    @RequestMapping("/cinema/delete")
    public @ResponseBody String RemoveCinemaById (@RequestParam int id)
    {
        cinemaRep.deleteById(id);
        return "deleted id:"+id;
    }
    //endregion
    //region Film thing
    @Autowired
    private filmRepos filmRep;
    @GetMapping(path="/films")
    public @ResponseBody
    Iterable<film> getAllFilms() {
        return filmRep.findAll();}
    @RequestMapping("/films/add")
    public @ResponseBody String addNewFilm (@RequestParam String name
            , @RequestParam String description,@RequestParam String image,@RequestParam String trailer){
        film fm=new film();
        fm.setName(name);
        fm.setDescription(description);
        fm.setImage(image);
        fm.setTrailer(trailer);
        filmRep.save(fm);
        return "Saved";
    }
    @RequestMapping("/films/delete")
    public @ResponseBody String RemoveFilm (@RequestParam int id)
    {
        filmRep.deleteById(id);
        return "deleted id:"+id;
    }
    //endregion

    @Autowired
    private hallRepos hallRep;
    @GetMapping(path="/halls")
    public @ResponseBody
    Iterable<hall> getAllHalls() {
        return hallRep.findAll();}
    @RequestMapping("/halls/add")
    public @ResponseBody String addNewHall (@RequestParam String name,String type,Integer idcinema ){
        hall hl=new hall();
        hl.setType(type);
        hl.setIdcinema(idcinema);
        hallRep.save(hl);
        return "Saved";
    }
    @RequestMapping("/halls/delete")
    public @ResponseBody String RemoveHall (@RequestParam int id)
    {
        hallRep.deleteById(id);
        return "deleted id:"+id;
    }
}

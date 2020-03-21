package andrew.cinema.CinemaRest;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.cinema;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import andrew.cinema.CinemaRest.Repositories.cinemaRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping(value = "/hello")
    public String sayHI(@RequestParam(defaultValue = "Andrew") String name) {
        return String.format("Hello %s!", name);
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
            , @RequestParam String pass,@RequestParam Integer bonus,@RequestParam String date){
        account acc=new account();
        acc.setName(name);
        acc.setPass(pass);
        acc.setBonus(bonus);
        acc.setDoB(date);
        accRep.save(acc);
        return "Saved";
    }
    @RequestMapping("/accounts/delete")
    public @ResponseBody String RemoveById (@RequestParam int id)
    {
        accRep.deleteById(id);
        return "deleted id:"+id;
    }
    //endregion

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
}

package andrew.cinema.CinemaRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import java.sql.Date;

@RestController
public class Controller {
    @GetMapping(value = "/hello")
    public String sayHI(@RequestParam(defaultValue = "Andrew") String name) {
        return String.format("Hello %s!", name);
    }
    @Autowired
    private accountRepos accRep;

    @GetMapping(path="/accounts")
    public @ResponseBody
    Iterable<account> getAllUsers() {
        return accRep.findAll();}
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewAcc (@RequestParam String name
            , @RequestParam String pass,@RequestParam Integer bonus,@RequestParam Date dob) {
        account acc=new account();
        acc.setName(name);
        acc.setPass(pass);
        acc.setBonus(bonus);
        acc.setDoB(dob);
        accRep.save(acc);
        return "Saved";
    }
}

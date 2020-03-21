package andrew.cinema.CinemaRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
    @RequestMapping("add") // Map ONLY POST Requests
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
}

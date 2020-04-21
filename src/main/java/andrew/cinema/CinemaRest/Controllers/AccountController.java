package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.review;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Transactional
@RestController
public class AccountController {
    @Autowired
    private accountRepos accRep;

    @GetMapping(path = "/accounts")
    public @ResponseBody
    Iterable<account> getAllUsers() {
        return accRep.findAll();
    }
    @GetMapping(path = "/accounts/update")
    public @ResponseBody
    String Update(@RequestParam String idaccount,@RequestParam String dob) {
        account check = accRep.findByIdaccount(idaccount);
        if(!check.equals(null)) {
            check.setDoB(dob);
            return "updated";
        }
        return "idaccount or you are disabled";
    }

    @RequestMapping("/accounts/add")
    public @ResponseBody
    String addNewAcc(@RequestParam String idaccount,@RequestParam String name
            , @RequestParam String picture, @RequestParam Integer bonus, @RequestParam String date, @RequestParam String email) {
        account acc = new account();
        acc.setIdaccount(idaccount);
        acc.setName(name);
        acc.setPicture(picture);
        acc.setBonus(bonus);
        acc.setDoB(date);
        acc.setEmail(email);
        accRep.save(acc);
        return "Saved";
    }

    @RequestMapping("/accounts/find")
    public @ResponseBody
    account Findbyid(@RequestParam String id) {
        return accRep.findByIdaccount(id);
    }


    @RequestMapping("/accounts/delete")
    public @ResponseBody
    String RemoveById(@RequestParam int id) {
        accRep.deleteById(id);
        return "deleted id:" + id;
    }
    @GetMapping(path = "/accounts/short")
    public @ResponseBody
    Iterable<account> shortAccounts() {
        return accRep.shortView();
    }
    @GetMapping(path = "/account/info")
    public @ResponseBody
    List<?> getAllTickets(@RequestParam String idaccount) {
        return accRep.ticketsInfoAccount(idaccount);
    }

}

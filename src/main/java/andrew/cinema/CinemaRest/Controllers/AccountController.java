package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private accountRepos accRep;

    @GetMapping(path = "/accounts")
    public @ResponseBody
    Iterable<account> getAllUsers() {
        return accRep.findAll();
    }

    @RequestMapping("/accounts/add")
    public @ResponseBody
    String addNewAcc(@RequestParam String name
            , @RequestParam String pass, @RequestParam Integer bonus, @RequestParam String date, @RequestParam String email) {
        account acc = new account();
        acc.setName(name);
        acc.setPass(pass);
        acc.setBonus(bonus);
        acc.setDoB(date);
        acc.setEmail(email);
        accRep.save(acc);
        return "Saved";
    }

    @RequestMapping("/accounts/find")
    public @ResponseBody
    Iterable<account> Find(@RequestParam String name) {
        return accRep.searchByName(name);
    }

    @RequestMapping("/accounts/auth")
    public @ResponseBody
    Iterable<account> Auth(@RequestParam String name, @RequestParam String pass) {
        return accRep.searchExistingAcc(name, pass);
    }

    @RequestMapping("/accounts/email")
    public @ResponseBody
    Iterable<account> FindById(@RequestParam String email) {
        return accRep.searchByEmail(email);
    }

    @RequestMapping("/accounts/delete")
    public @ResponseBody
    String RemoveById(@RequestParam int id) {
        accRep.deleteById(id);
        return "deleted id:" + id;
    }

}

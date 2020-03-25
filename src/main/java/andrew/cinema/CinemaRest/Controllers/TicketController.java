package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.*;
import andrew.cinema.CinemaRest.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TicketController {
    @Autowired
    private accountRepos accRep;
    @Autowired
    private ticketRepos ticketRep;
    @Autowired
    private sessionRepos sessionRep;

    @GetMapping(path = "/tickets")
    public @ResponseBody
    Iterable<ticket> getAll() {
        return ticketRep.findAll();
    }

    @RequestMapping("/tickets/add")
    public @ResponseBody
    String addNew(@RequestParam Integer idaccount, @RequestParam Integer idsession, @RequestParam float price,@RequestParam Integer place,@RequestParam Integer row) {
        ticket tk = new ticket();
        tk.setIdsession(idsession);
        tk.setIdaccount(idaccount);
        tk.setPrice(price);
        tk.setPlace(place);
        tk.setRownum(row);
        account ac= accRep.findByIdaccount(idaccount);
        session sn = sessionRep.findByIdsession(idsession);
        ac.setTk(tk);
        sn.setTk(tk);
        sessionRep.save(sn);
        accRep.save(ac);

        return "Saved";
    }

    @RequestMapping("/tickets/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        ticketRep.deleteById(id);
        return "deleted id:" + id;
    }
}
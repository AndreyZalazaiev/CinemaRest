package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.session;
import andrew.cinema.CinemaRest.Entities.ticket;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import andrew.cinema.CinemaRest.Repositories.filmRepos;
import andrew.cinema.CinemaRest.Repositories.sessionRepos;
import andrew.cinema.CinemaRest.Repositories.ticketRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
public class TicketController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private accountRepos accRep;
    @Autowired
    private ticketRepos ticketRep;
    @Autowired
    private sessionRepos sessionRep;
    @Autowired
    private andrew.cinema.CinemaRest.Repositories.filmRepos filmRepos;

    @GetMapping(path = "/tickets")
    public @ResponseBody
    Iterable<ticket> getAll() {
        return ticketRep.getAllDistinct();
    }


    @RequestMapping("/tickets/add")
    public @ResponseBody
    String addNew(@RequestParam String idaccount, @RequestParam Integer idsession, @RequestParam float price,@RequestParam Integer place,@RequestParam Integer row) {
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
    @RequestMapping("/tickets/addmany")
    String addTickets(@RequestParam String idaccount, @RequestParam Integer idsession, @RequestParam String price,@RequestParam String  place,@RequestParam String row,@RequestParam Integer bonus)
    {
        String [] prices = price.split(",");
        String [] places = place.split(",");
        String [] rows = row.split(",");
        String out="";
        StringBuilder mailText = new StringBuilder();
        mailText.append("Purchase information from best cinema in the world^\n");
        String to="";
        account cur=null;
        for(int i=0;i<prices.length;i++) {
            ticket tk = new ticket();
            tk.setIdsession(idsession);
            tk.setIdaccount(idaccount);
            tk.setPrice(Float.parseFloat(prices[i]));
            tk.setPlace(Integer.parseInt(places[i]));
            tk.setRownum(Integer.parseInt(rows[i]));
            account ac = accRep.findByIdaccount(idaccount);
            if(i==0)//один раз
            ac.setBonus(ac.getBonus()+bonus);
            to=ac.getEmail();
            session sn = sessionRep.findByIdsession(idsession);
            out+="place:"+places[i]+",row: "+rows[i]+"   ;";
            if(i==0){
                cur=ac;
                session s =sessionRep.findByIdsession(idsession);
                film f =filmRepos.findByIdfilm(s.getIdfilm());
                mailText.append("Film: "+f.getName()+"\n");
                mailText.append("Genre:"+f.getGenre()+"\n");
                mailText.append("Session time from "+ s.getStart() +" to "+s.getEnd()+"\n");
            }
            mailText.append("#"+(i+1)+"\t");
            mailText.append("Place: "+places[i] +"  and  Row: "+rows[i]+"   Price:"+prices[i]+"   Bonuses: "+(int)(Integer.parseInt(prices[i])/3)+"\n");
            ac.setTk(tk);
            sn.setTk(tk);
            sessionRep.save(sn);
            accRep.save(ac);
        }
        mailText.append("Final account bonuses value: "+cur.getBonus()+"\n");
        mailText.append("Have a nice day!\nDo not forget to flex every day");
        sendEmail(to,"Сinema Flex",mailText.toString());
        return "Saved: "+out;
    }
    @RequestMapping("/tickets/this")
    public @ResponseBody
    Iterable<ticket>getAllTicketsForSession(@RequestParam Integer idsession) {
       return ticketRep.getAllTicketsForSession(idsession);
    }
    @RequestMapping("/tickets/find")
    public @ResponseBody
    Iterable<ticket>currentUserTickets(@RequestParam String idaccount) {
        return ticketRep.getTicketsForCurrentUser(idaccount);
    }
    @RequestMapping("/tickets/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        ticketRep.deleteById(id);
        return "deleted id:" + id;
    }
    void sendEmail(String to,String subject,String text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}
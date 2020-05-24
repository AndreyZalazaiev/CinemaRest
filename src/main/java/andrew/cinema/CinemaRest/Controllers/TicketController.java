package andrew.cinema.CinemaRest.Controllers;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.film;
import andrew.cinema.CinemaRest.Entities.session;
import andrew.cinema.CinemaRest.Entities.ticket;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import andrew.cinema.CinemaRest.Repositories.sessionRepos;
import andrew.cinema.CinemaRest.Repositories.ticketRepos;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;


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
    String addNew(@RequestParam String idaccount, @RequestParam Integer idsession, @RequestParam float price, @RequestParam Integer place, @RequestParam Integer row) {
        ticket tk = new ticket();
        tk.setIdsession(idsession);
        tk.setIdaccount(idaccount);
        tk.setPrice(price);
        tk.setPlace(place);
        tk.setRownum(row);
        account ac = accRep.findByIdaccount(idaccount);
        session sn = sessionRep.findByIdsession(idsession);
        ac.setTk(tk);
        sn.setTk(tk);
        sessionRep.save(sn);
        accRep.save(ac);

        return "Saved";
    }

    @RequestMapping("/tickets/addmany")
    String addTickets(@RequestParam String idaccount, @RequestParam Integer idsession, @RequestParam String price, @RequestParam String place, @RequestParam String row, @RequestParam Integer bonus, @RequestParam Integer sent, @RequestParam Integer reserve) {
        String[] prices = price.split(",");
        String[] places = place.split(",");
        String[] rows = row.split(",");
        String out = "";
        StringBuilder mailText = new StringBuilder();
        mailText.append("<div><font size=\"6\">Purchase information from best cinema in the world^<br/>");
        String to = "";
        account cur = null;
        if (reserve == 0) {//tickets buying
            for (int i = 0; i < prices.length; i++) {
                ticketRep.perfromBuying(idsession, Integer.parseInt(places[i]), Integer.parseInt(rows[i]));
                account ac = accRep.findByIdaccount(idaccount);
                to = ac.getEmail();
                if (i == 0) {
                    ac.setBonus(ac.getBonus() + bonus);
                    cur = ac;
                    session s = sessionRep.findByIdsession(idsession);
                    film f = filmRepos.findByIdfilm(s.getIdfilm());
                    mailText.append("Film: <b>" + f.getName() + "</b></p><br/>");
                    mailText.append("<img src=\"" + f.getImage() + "\"><br/>");
                    mailText.append("Genre:" + f.getGenre() + "<br/>");
                    mailText.append("Session time from " + s.getStart() + " to " + s.getEnd() + "<br/>");
                }//инфо о фильме
                mailText.append("#" + (i + 1) + "\t");
                mailText.append("Place: " + places[i] + "  and  Row: " + rows[i] + "   Price:" + prices[i] + "<br/>");

            }
            mailText.append("Final account bonuses value: " + cur.getBonus() + "<br/>");
            mailText.append("Have a nice day!<br/>Do not forget to flex every day</font></p></div>");
            if (sent == 1)
                SendEmailHtml(to, "Сinema Flex", mailText.toString());
            return "Saved: " + out;
        } else {//reserve
            for (int i = 0; i < prices.length; i++) {
                ticket tk = new ticket();
                tk.setIdsession(idsession);
                tk.setIdaccount(idaccount);
                tk.setPrice(Float.parseFloat(prices[i]));
                tk.setPlace(Integer.parseInt(places[i]));
                tk.setReservation(LocalDateTime.now());
                tk.setRownum(Integer.parseInt(rows[i]));

                account ac = accRep.findByIdaccount(idaccount);
                session sn = sessionRep.findByIdsession(idsession);
                out += "place:" + places[i] + ",row: " + rows[i] + "   ;";

                ac.setTk(tk);
                sn.setTk(tk);
                sessionRep.save(sn);
                accRep.save(ac);
            }

        }
        return out;
    }

    @RequestMapping("/tickets/this")
    public @ResponseBody
    Iterable<ticket> getAllTicketsForSession(@RequestParam Integer idsession) {
        val tickets =ticketRep.getAllTicketsForSession(idsession);
        for (ticket tk :tickets
             ) {
            if(tk.getReservation()!=null)
            if(tk.getReservation().plusMinutes(15).compareTo(LocalDateTime.now())<=0)
            {
                ticketRep.deleteCouple(tk.getIdticket());

            }
        }
        return ticketRep.getAllTicketsForSession(idsession);
    }

    @RequestMapping("/tickets/find")
    public @ResponseBody
    Iterable<ticket> currentUserTickets(@RequestParam String idaccount) {
        return ticketRep.getTicketsForCurrentUser(idaccount);
    }

    @RequestMapping("/tickets/delete")
    public @ResponseBody
    String Remove(@RequestParam int id) {
        ticketRep.deleteById(id);
        return "deleted id:" + id;
    }

    void sendEmail(String to, String subject, String text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }//an old one , without a picture

    public void SendEmailHtml(final String to, final String subject, final String msg) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            message.setContent(msg, "text/html");
            helper.setTo(to);
            helper.setSubject(subject);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idaccount;
    @NotNull
    private String Name;
    @NotNull
    private String Pass;
    @NotNull
    private String Email;
    @NotNull
    private Integer Bonus;
    private Date DoB;
     public void setName (String name)
     {
         this.Name=name;
     }

    public void setBonus(Integer bonus) {
        Bonus = bonus;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public void setDoB(String doB) {
        SimpleDateFormat formatterFirst = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = formatterFirst.parse(doB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.DoB=date;

    }
    public void setEmail(String email) {
        Email = email;
    }

}

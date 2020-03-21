package andrew.cinema.CinemaRest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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

    public void setDoB(Date doB) {
         
        DoB = doB;
    }
}

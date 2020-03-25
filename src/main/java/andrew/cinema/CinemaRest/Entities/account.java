package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idaccount",referencedColumnName ="idaccount")
    List<ticket> tk;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idaccount",referencedColumnName ="idaccount")
    private List<review> rev;
    public void setReview(review rev) {
        this.rev.add(rev);
    }
    public void setTk(ticket tk) {
        this.tk.add(tk);
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

}

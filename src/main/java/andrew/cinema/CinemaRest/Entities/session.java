package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsession;
    @NotNull
    private Integer idfilm;
    @NotNull
    private Integer idhall;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idsession",referencedColumnName ="idsession")
    List<ticket> tk;
    public void setTk(ticket tk) {
        this.tk.add(tk);
    }
    public void setStart(String input) {
        SimpleDateFormat formatterFirst = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        Date date = null;
        try {
            date = formatterFirst.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.start = date;

    }

    public void setEnd(String input)
    {
        SimpleDateFormat formatterFirst = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        Date date=null;
        try {
            date = formatterFirst.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.end=date;

    }

}

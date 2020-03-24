package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class film {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idfilm;
    @NotNull
    private String Name;
    private String Description;
    private String image;
    private String trailer;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idfilm",referencedColumnName ="idfilm")
    private List<session> session;
    public void setSession(session sn) {
        this.session.add(sn);
    }
}

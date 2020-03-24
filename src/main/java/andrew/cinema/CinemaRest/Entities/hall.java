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
public class hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idhall;
    @NotNull
    private String Name;
    @NotNull
    private String Type;
    @NotNull
    private Integer idcinema;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idhall",referencedColumnName ="idhall")
    private List<session> session;
    public void setSession(session sn) {
        this.session.add(sn);
    }
}

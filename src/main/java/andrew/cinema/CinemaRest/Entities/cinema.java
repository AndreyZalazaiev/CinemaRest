package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "cinema")
public class cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcinema;
    @NotNull
    private String Name;
    @NotNull
    private String Adress;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcinema",referencedColumnName ="idcinema")
    private List<hall> hall;

    public void setHall(hall hl) {
        this.hall.add(hl);
    }

    public void setIdcinema(Integer idcinema) {
        this.idcinema = idcinema;
    }

    public List<hall> getHall() {
        return hall;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }


    public Integer getIdcinema() {
        return idcinema;
    }
}

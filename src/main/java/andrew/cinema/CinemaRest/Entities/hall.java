package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }
    public Integer getIdcinema() {
        return idcinema;
    }
    public void setIdcinema(Integer idcinema) {
        this.idcinema = idcinema;
    }
}

package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class cinema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idcinema;
    @NotNull
    private String Name;
    @NotNull
    private String Adress;

    public void setName(String name) {
        Name = name;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
}

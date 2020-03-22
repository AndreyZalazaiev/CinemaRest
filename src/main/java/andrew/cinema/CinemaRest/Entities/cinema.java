package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table (name = "cinema")
public class cinema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idcinema")
    private Integer idcinema;
    @NotNull
    private String Name;
    @NotNull
    private String Adress;
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "idcinema")
   // private Set<hall> halls;
    public void setName(String name) {
        Name = name;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }


}

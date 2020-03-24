package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idticket;
    @NotNull
    private Integer idaccount;
    @NotNull
    private Integer idsession;
    @NotNull
    private Double price;
    @NotNull
    private Integer place;
    @NotNull
    private Integer row;


}

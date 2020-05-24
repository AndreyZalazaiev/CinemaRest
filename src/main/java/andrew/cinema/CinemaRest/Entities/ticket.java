package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idticket;
    @NotNull
    private String idaccount;
    @NotNull
    private Integer idsession;
    @NotNull
    private float price;
    @NotNull
    private Integer place;
    @NotNull
    private Integer rownum;
    @JsonIgnore
    private LocalDateTime reservation;


}

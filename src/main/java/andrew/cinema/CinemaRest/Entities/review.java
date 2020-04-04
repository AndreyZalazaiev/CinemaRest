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
public class review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreview;
    @NotNull
    private Integer idfilm;
    @NotNull
    private String idaccount;
    @NotNull
    private String text;
    @NotNull
    private Integer mark;




}

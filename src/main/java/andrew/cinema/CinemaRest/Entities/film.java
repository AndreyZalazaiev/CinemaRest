package andrew.cinema.CinemaRest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    private String Image;
    private String Trailer;
    @Column(name="agelimit")
    private String AgeLimit;
    private String Genre;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idfilm",referencedColumnName ="idfilm")
    private List<session> session;
    public void setSession(session sn) {
        this.session.add(sn);
    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idfilm",referencedColumnName ="idfilm")
    private List<review> rev;
     public void setReview(review rev) {
         this.rev.add(rev);
     }
}

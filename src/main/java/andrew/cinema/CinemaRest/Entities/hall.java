package andrew.cinema.CinemaRest.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table (name = "hall")
public class hall {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idhall;
    @NotNull
    private String Name;
    @NotNull
    private String Type;
    @Column(name = "idcinema", insertable = false, updatable = false)
    private Integer idcinema;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idcinema", nullable = false)
    private cinema cnm;
    public void setName(String name) {
        Name = name;
    }
    public void setType(String type)
    {
        Type = type;
    }

    public void setCnm(cinema cnm) {
        this.cnm = cnm;
    }

    public cinema getCnm() {
        return cnm;
    }


    public void setIdcinema(Integer idcinema) {
        this.idcinema = idcinema;
    }
}

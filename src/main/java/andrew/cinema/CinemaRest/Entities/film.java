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
public class film {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idfilm;
    @NotNull
    private String Name;
    private String Description;
    private String image;
    private String trailer;
    public void setName(String name) {
        Name = name;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setDescription(String description) {
        Description = description;
    }

    public void setTrailer(String treiler) {
        this.trailer = treiler;
    }
}

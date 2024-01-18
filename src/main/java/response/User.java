package response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {


    @Id
    private String uuid;

    private String username;


    @OneToMany
    private List<FavoriteSong> favoriteSongs;
}

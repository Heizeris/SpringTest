package response;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@Slf4j
public class FavoriteSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String artist;
    private String title;
}

package response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistResponse {

    private String artist;

    private List<Artist> artistsSongs;
}

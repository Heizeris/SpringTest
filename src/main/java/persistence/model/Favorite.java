package persistence.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter public class Favorite {
    private String artist;
    private String title;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favourite = (Favorite) o;
        return Objects.equals(artist.toLowerCase().trim(), favourite.artist.toLowerCase().trim()) &&
                Objects.equals(title.toLowerCase().trim(), favourite.title.toLowerCase().trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist.toLowerCase().trim(), title.toLowerCase().trim());
    }

}

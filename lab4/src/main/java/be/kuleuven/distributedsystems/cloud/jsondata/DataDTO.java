package be.kuleuven.distributedsystems.cloud.jsondata;

import java.util.List;

/**
 * Corresponds to shows
 */
public class DataDTO {

  public List<ShowDTO> getShows() {
    return shows;
  }

  public void setShows(List<ShowDTO> shows) {
    this.shows = shows;
  }

  private List<ShowDTO> shows;
}

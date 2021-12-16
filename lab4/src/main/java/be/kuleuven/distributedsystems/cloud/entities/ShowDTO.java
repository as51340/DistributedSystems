package be.kuleuven.distributedsystems.cloud.entities;

import java.util.List;

public class ShowDTO {
  private String showID;
  private String name;
  private String location;
  private String image;
  private List<SeatDTO> seats;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<SeatDTO> getSeats() {
    return seats;
  }

  public void setSeats(List<SeatDTO> seats) {
    this.seats = seats;
  }
  /*
  * Model database such that new type of show is a new collection, take from
  * that collection and put on website
  *
   */

  public String getShowID() {
    return showID;
  }

  public void setShowID(String showID) {
    this.showID = showID;
  }
}

package be.kuleuven.distributedsystems.cloud.jsondata;

import be.kuleuven.distributedsystems.cloud.jsondata.SeatDTO;

import java.util.List;

public class ShowDTO {
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
}

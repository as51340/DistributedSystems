package be.kuleuven.distributedsystems.cloud.entities;

import java.util.UUID;

/**
 * The event that a customer can attend, which is hosted by one specific theatre company. A show
 * has a predetermined number of seats available. A show has properties such as its name and location.
 */
public class Show {
    private String company;
    private UUID showId;
    private String name;
    private String location;
    private String image;

    public Show() {}

    public Show(String company, UUID showId, String name, String location, String image) {
        this.company = company;
        this.showId = showId;
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public String getCompany() {
        return company;
    }

    public UUID getShowId() {
        return showId;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getImage() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Show)) {
            return false;
        }
        var other = (Show) o;
        return this.company.equals(other.company)
                && this.showId.equals(other.showId);
    }

    @Override
    public int hashCode() {
        return this.company.hashCode() * this.showId.hashCode();
    }
}

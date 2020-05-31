package model;

import java.util.Objects;

/**
 * Created by samosbor on 5/16/18.
 */
public class Event {
    /**
     * Unique identifier for this event
     */
    String eventID;
    /**
     * The user to which this person belongs
     */
    String descendant;
    /**
     * ID of the person to which this event belongs
     */
    String person;
    /**
     * Latitude of the event's location
     */
    Double latitude;
    /**
     * Longitude of the event's location
     */
    Double longitude;
    /**
     * Country in which the event occurred
     */
    String country;
    /**
     * City in which event occurred
     */
    String city;
    /**
     * Type of event
     */
    String eventType;
    /**
     * Year in which event occurred
     */
    int year;

    /**
     * Constructor for the event
     * @param eventID
     * @param descendant
     * @param person
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
    public Event(String eventID, String descendant, String person, Double latitude, Double longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.person = person;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Gets the unique ID for the event
     * @return the unique ID for the event
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * sets the unique ID for the event
     * @param eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the user that this person is related to
     * @return the user that this person is related to
     */
    public String getDescendant() {
        return descendant;
    }

    /**
     * Sets the user that this person is related to
     * @param descendant
     */
    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    /**
     * Gets the ID of the person to which this event belongs to
     * @return the ID of the person to which this event belongs to
     */
    public String getPerson() {
        return person;
    }

    /**
     * Sets the ID of the person to which this event belongs to
     * @param person
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * Gets the latitude of the event's location
     * @return the latitude of the event's location
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the event's location
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the event's location
     * @return the longitude of the event's location
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the event's location
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the country in which the event occurred
     * @return the country in which the event occurred
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country in which the event occurred
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the city in which the event occurred
     * @return the city in which the event occurred
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city in which the event occurred
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the type of event
     * @return the type of event
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the type of event
     * @param eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the year in which the event occurred
     * @return the year in which the event occurred
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year in which the event occurred
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Checks to see if an event is equal to another
     * @param o
     * @return true if the events are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getYear() == event.getYear() &&
                Objects.equals(getEventID(), event.getEventID()) &&
                Objects.equals(getDescendant(), event.getDescendant()) &&
                Objects.equals(getPerson(), event.getPerson()) &&
                Objects.equals(getLatitude(), event.getLatitude()) &&
                Objects.equals(getLongitude(), event.getLongitude()) &&
                Objects.equals(getCountry(), event.getCountry()) &&
                Objects.equals(getCity(), event.getCity()) &&
                Objects.equals(getEventType(), event.getEventType());
    }

    /**
     * Gets the hashcode value of the event
     * @return the hashcode value of the event
     */
    @Override
    public int hashCode() {

        return Objects.hash(getEventID(), getDescendant(), getPerson(), getLatitude(), getLongitude(), getCountry(), getCity(), getEventType(), getYear());
    }

    /**
     * Gets the string representation of the event object
     * @return the string that represents the event
     */
    @Override
    public String toString() {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", descendant='" + descendant + '\'' +
                ", person='" + person + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", eventType='" + eventType + '\'' +
                ", year=" + year +
                '}';
    }
}

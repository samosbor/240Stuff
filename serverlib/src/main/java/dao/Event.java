package dao;

public class Event {

    /**
     * Constructor for the Event Dao
     */
    public Event() {
    }

    /**
     * Creates an empty event table
     */
    public void createTable(){

    }

    /**
     * Adds a specified event to the table
     * @param event
     */
    public void addEvent(model.Event event){

    }

    /**
     * Removes a specified event
     * @param event
     */
    public void removeEvent(model.Event event){

    }

    /**
     * Get a event by eventID
     * @param eventID
     * @return the event object that corresponds with the eventID
     */
    public model.Event getEvent(String eventID){
        return null;
    }

    /**
     * Clears the table by dropping it then recreating it
     */
    public void clearTable(){
        //Drop
        this.createTable();
    }
}

package dao;

public class Person {

    /**
     * Constructor for the Person Dao
     */
    public Person() {
    }

    /**
     * Creates an empty person table
     */
    public void createTable(){

    }

    /**
     * Adds a specified person to the table
     * @param person
     */
    public void addPerson(model.Person person){

    }

    /**
     * Removes a specified person
     * @param person
     */
    public void removePerson(model.Person person){

    }

    /**
     * Get a person by personID
     * @param personID
     * @return the person object that corresponds with the personID
     */
    public model.Person getPerson(String personID){
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

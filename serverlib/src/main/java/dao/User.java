package dao;

public class User {
    /**
     * Constructor for the User Dao
     */
    public User() {
    }

    /**
     * Creates an empty user table
     */
    public void createTable(){

    }

    /**
     * Adds a specified user to the table
     * @param user
     */
    public void addUser(model.User user){

    }

    /**
     * Removes a specified user
     * @param user
     */
    public void removeUser(model.User user){

    }

    /**
     * Get a user by username
     * @param username
     * @return the user object that corresponds with the username
     */
    public model.User getUser(String username){
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

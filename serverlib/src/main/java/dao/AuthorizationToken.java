package dao;

public class AuthorizationToken {
    /**
     * Constructor for the AuthorizationToken Dao
     */
    public AuthorizationToken() {
    }

    /**
     * Creates an empty AuthorizationToken table
     */
    public void createTable(){

    }

    /**
     * Adds a specified AuthorizationToken to the table
     * @param token
     */
    public void addToke(model.AuthorizationToken token){

    }

    /**
     * Removes a specified AuthorizationToken
     * @param token
     */
    public void removeToken(model.AuthorizationToken token){

    }

    /**
     * Get an AuthorizationToken object by the token
     * @param token
     * @return the AuthorizationToken object that corresponds with the token
     */
    public model.AuthorizationToken getToken(String token){
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

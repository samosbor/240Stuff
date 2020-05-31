package model;

import java.util.Objects;

/**
 * Created by samosbor on 5/16/18.
 */
public class Person {
    /**
     * A person's unique ID
     */
    String personID;
    /**
     * The user to which this person belongs
     */
    String descendant;
    /**
     * A person's first name
     */
    String firstName;
    /**
     * A person's last name
     */
    String lastName;
    /**
     * A person's gender. Can only be 'm' or 'f'
     */
    String gender;
    /**
     * The ID of person's father (possibly null)
     */
    String father;
    /**
     * The ID of person's mother (possibly null)
     */
    String mother;
    /**
     * The ID of person's spouse (possibly null)
     */
    String spouse;

    /**
     * Constructor for a person. Father, mother, and spouse could be null. Other params must not be null.
     * @param personID
     * @param descendant
     * @param firstName
     * @param lastName
     * @param gender
     * @param father
     * @param mother
     * @param spouse
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    /**
     * Gets the person's unique ID
     * @return the person's unique ID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the person's unique ID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
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
     * Gets the person's first name
     * @return the person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the person's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets the person's last name
     * @return the person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the person's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Gets the person's gender
     * @return the person's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the person's gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Gets the ID of the person's father
     * @return the ID of the person's father
     */
    public String getFather() {
        return father;
    }

    /**
     * Sets the person's father
     * @param father
     */
    public void setFather(String father) {
        this.father = father;
    }
    /**
     * Gets the ID of the person's mother
     * @return the ID of the person's mother
     */
    public String getMother() {
        return mother;
    }

    /**
     * Sets the person's mother
     * @param mother
     */
    public void setMother(String mother) {
        this.mother = mother;
    }
    /**
     * Gets the ID of the person's spouse
     * @return the ID of the person's spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * Sets the person's spouse
     * @param spouse
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    /**
     * Checks to see if a person is equal to another
     * @param o
     * @return true if the persons are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getPersonID(), person.getPersonID()) &&
                Objects.equals(getDescendant(), person.getDescendant()) &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(getGender(), person.getGender()) &&
                Objects.equals(getFather(), person.getFather()) &&
                Objects.equals(getMother(), person.getMother()) &&
                Objects.equals(getSpouse(), person.getSpouse());
    }
    /**
     * Gets the hashcode value of the person
     * @return the hashcode value of the person
     */
    @Override
    public int hashCode() {

        return Objects.hash(getPersonID(), getDescendant(), getFirstName(), getLastName(), getGender(), getFather(), getMother(), getSpouse());
    }
    /**
     * Gets the string representation of the person object
     * @return the string that represents the person
     */
    @Override
    public String toString() {
        return "Person{" +
                "personID='" + personID + '\'' +
                ", descendant='" + descendant + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", spouse='" + spouse + '\'' +
                '}';
    }
}

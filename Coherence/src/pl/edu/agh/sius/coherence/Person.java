package pl.edu.agh.sius.coherence;

import java.io.Serializable;

public class Person implements Serializable, Comparable<Person> {

    private static final long serialVersionUID = 8670998317060911194L;
    
    private int id;
    private String surname;
    private String firstname;
    private String address;
    private int age;;
    private String gender;

    public Person(int id, String surname, String firstname, String address,
            int age, String gender) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return p.id == id && p.firstname.equals(firstname) &&
                    p.surname.equals(surname) && p.address.endsWith(address) &&
                    p.age == age && p.gender.equals(gender);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Person[id=%d] {\n" +
                "   firstname ->  %s\n" + 
                "   surname   ->  %s\n" +
                "   address   ->  %s\n" +
                "   age       ->  %d\n" +
                "   gender    ->  %s\n}",
                id, firstname, surname, address, age, gender);
    }

    @Override
    public int compareTo(Person o) {
        return surname.compareTo(o.surname) * 2
                + firstname.compareTo(o.firstname);
    }

}

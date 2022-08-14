package model;


//this class will contain variables for the users info
public class UsersInfo {

    //the variables that should display
    private int id;
    private String firstName, lastName, address;
    private int age, phone;
    //if user is an active customer or not
    private boolean isUserActive;

    //everything inside the body is needed for when we want to get all users info in data base
    public UsersInfo(int id, String firstName, String lastName, String address, int phone, int age, boolean isUserActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.isUserActive = isUserActive;
    }

    public UsersInfo() {

    }

    //to string to print out info on the UI side when called
    @Override
    public String toString() {
        return "UsersInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", isUserActive=" + isUserActive +
                '}';
    }

    //getter and setters needed to be able to "get" the variable name and also "set" the variable name

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserActive(boolean userActive) {
        isUserActive = userActive;
    }
}

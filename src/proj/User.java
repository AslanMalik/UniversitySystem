package proj;

import java.io.Serializable;
import java.util.Objects;

/**
* @generated
*/
public class User implements Comparable, Serializable {
    
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;

    public User() {

    }

    public User(String name, String surname, String birthDate, String phoneNumber, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = this.name.substring(0, 1).toLowerCase() + "_" + this.surname.toLowerCase() + "@kbtu.kz";
        this.password = password;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birthDate, user.birthDate) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, phoneNumber, email, login, password);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }


    //                          Operations                                  
    
    public String viewNewsTab() {
        String ans = "";
        int i = 0;
        for (News news : Database.news) {
            i ++;
            ans += i + ") News title: " + news.getTitle() 
            + "\n    Description: " + news.getText() 
            + "\n    Post Date: " + news.getPostDate() + "\n\n";
        }
        return ans;
    }
    /**
    * @generated
    */
    public boolean signIn(String login, String password) {
        if(login.equals(this.login) && password.equals(this.password)) {
            return true;
        }
        return false;
    }
    /**
    * @generated
    */
    public boolean changePassword(String oldPassword, String newPassword) {
        if(oldPassword.equals(this.password)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

package proj.models;

import proj.enums.Degree;
import proj.enums.Faculty;
import proj.enums.Managers;
import proj.enums.Status;
import proj.utils.Database;

public class Admin extends Employee {

    public Admin(String name, String surname, String birthDate, String phoneNumber, String email,
            String password) {
        super(name, surname, birthDate, phoneNumber, email, password);
    }

    public void createStudent(String name, String surname, String birthDate, String phoneNumber, String email, String password, String id, Integer yearOfStudy) {
        Student st = new Student(name, surname, birthDate, phoneNumber, email, password, id, yearOfStudy, Faculty.FIT, Degree.BACHELOR);
        Database.getUsers().add(st);
    }
    public void createTeacher(String name, String surname, String birthDate, String phoneNumber, String email, String password, String experience) {
        Teacher t = new Teacher(name, surname, birthDate, phoneNumber, email, password, Status.PROFESSOR, experience);
        Database.getUsers().add(t);
    }
    public void createManager(String name, String surname, String birthDate, String phoneNumber, String email, String password) {
        Manager m = new Manager(name, surname, birthDate, phoneNumber, email, password, Managers.DEPARTMENTS);
        Database.getUsers().add(m);
    }
    public void createLibrarian(String name, String surname, String birthDate, String phoneNumber, String email, String password) {
        Librarian l = new Librarian(name, surname, birthDate, phoneNumber, email, password);
        Database.getUsers().add(l);
    }

    public boolean deleteUser(String login) {
        for(User u: Database.getUsers()){
            if(u.getLogin().equals(login)){
                Database.getUsers().remove(u);
                return true;
            }
        }
        return false;
    }
    public String getUsers() {
        String s = "";
        for(User u : Database.getUsers()) {
            s += u.toString();
        }
        return s;
    }

    public void seeLogFiles() {
    }

}

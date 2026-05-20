package proj.utils;

import proj.enums.Degree;
import proj.enums.Faculty;
import proj.enums.Managers;
import proj.enums.Status;
import proj.interfaces.Researcher;
import proj.models.*;

public class UserFactory {
    public static User createUser(String type, String name, String surname,
                                  String birthDate, String phoneNumber,
                                  String email, String password, Object... extra) {
        switch (type.toLowerCase()) {
            case "student":
                return new Student(name, surname, birthDate, phoneNumber, email, password,
                                   (String) extra[0], (Integer) extra[1], 
                                   (Faculty) extra[2], (Degree) extra[3]);
            case "graduatestudent":
                return new GraduateStudent(name, surname, birthDate, phoneNumber, email, password,
                                           (String) extra[0], (Integer) extra[1],
                                           (Faculty) extra[2], (Degree) extra[3],
                                           (Researcher) extra[4]);
            case "teacher":
                return new Teacher(name, surname, birthDate, phoneNumber, email, password,
                                   (Status) extra[0], (String) extra[1]);
            case "manager":
                return new Manager(name, surname, birthDate, phoneNumber, email, password,
                                   (Managers) extra[0]);
            case "admin":
                return new Admin(name, surname, birthDate, phoneNumber, email, password);
            case "librarian":
                return new Librarian(name, surname, birthDate, phoneNumber, email, password);
            case "techsupport":
                return new TechSupportSpecialist(name, surname, birthDate, phoneNumber, email, password);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}
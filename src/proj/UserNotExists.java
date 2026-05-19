package proj;

public class UserNotExists extends Exception {
    public UserNotExists(String message){
        super(message);
    }
}

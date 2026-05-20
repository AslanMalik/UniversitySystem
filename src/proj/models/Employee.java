package proj.models;

import proj.utils.Database;
import proj.communication.Message;

public class Employee extends User {

    public Employee() {

    }

    public Employee(String name, String surname, String birthDate, String phoneNumber, String email,
            String password) {
        super(name, surname, birthDate, phoneNumber, email, password);
    }

    public void sendMessage(String messageFrom, String messageTo, String title, String text) {
        Message m = new Message(messageFrom, messageTo, title, text);
        Database.getMessages().add(m);
    }

    public String getMessages() {
        String ans = "";
        int msgCount = 0;
        for (Message message : Database.getMessages()) {
            if(message.getMessageTo().equals(this.getLogin())) {
                msgCount ++;
                ans += msgCount + ") Message from: " + message.getMessageFrom()
                + "\n    Title: " + message.getTitle()
                + "\n    Text: " + message.getText() + "\n\n";
            }
        }
        return ans;
    }

}

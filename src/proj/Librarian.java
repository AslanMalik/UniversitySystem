package proj;

import java.util.Map;

public class Librarian extends Employee {

    public Librarian(String name, String surname, String birthDate, String phoneNumber, String email,
            String password) {
        super(name, surname, birthDate, phoneNumber, email, password);
    }

    public void addBook(String title, String id, String author) {
        Book b = new Book(title, id, author);
        if(!Database.books.contains(b)) {
            Database.books.add(b);
        }
    }

    public void removeBook(String id) {
        for (Book book : Database.books) {
            if(book.getId().equals(id)) {
                Database.books.remove(book);
            }
        }
    }

    public String updateOrderBook(String studentId, String bookId, String request) {
        Book b = new Book();
        for (Book book : Database.books) {
            if(book.getId().equals(bookId)) {
                b = book;
            }
        }

        for(Map.Entry<String, Book> item : Database.orders.entrySet()) {
            if(item.getKey().equals(studentId) && item.getValue().equals(b)) {
                if(request.equals("ACCEPT")) {
                    Database.orders.remove(studentId, b);
                    return "Student's book is accepted";
                } else if(request.equals("REJECT")) {
                    return "Student's book is rejected";
                }
            } else return "This order does not exist";
        }
        return "Orders does not exist";
    }
}

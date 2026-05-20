package proj.utils;

import proj.models.User;

import java.util.Comparator;

public class NameComparator implements Comparator<User> {
    
    @Override
    public int compare(User s1, User s2) {
        return s2.getName().compareToIgnoreCase(s1.getName());
    }
}

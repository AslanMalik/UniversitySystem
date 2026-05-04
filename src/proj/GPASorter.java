package proj;

import java.util.Comparator;

public class GPASorter implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s2.getGPA().compareTo(s1.getGPA());
    }
    
}

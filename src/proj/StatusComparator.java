package proj;

import java.util.Comparator;

public class StatusComparator implements Comparator<Teacher> {

    @Override
    public int compare(Teacher t1, Teacher t2) {
        return t2.getTeacherStatus().compareTo(t1.getTeacherStatus());
    }
    
}

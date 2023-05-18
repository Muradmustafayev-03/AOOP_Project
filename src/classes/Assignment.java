package classes;

import java.io.Serializable;

public record Assignment(Student student, Destination destination) implements Serializable {
    public int getLoss() {
        if (destination == null) {return 1000;}
        if (this.student.getPreferences().contains(this.destination)) {
            return (int) Math.pow(this.student.getPreferences().indexOf(this.destination), 2);
        } else {
            return 10 * (int) Math.pow(this.student.getPreferences().size(), 2);
        }
    }
}

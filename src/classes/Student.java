package classes;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private final int id;
    private final String name;
    private List<Destination> preferences;

    public Student(int id, String name, List<Destination> preferences) {
        this.id = id;
        this.name = name;
        this.preferences = preferences;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Destination> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Destination> preferences) {
        this.preferences = preferences;
    }
}
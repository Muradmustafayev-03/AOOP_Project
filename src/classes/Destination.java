package classes;

import java.io.Serializable;
import java.util.Objects;

public record Destination(String name, int maxStudents) implements Serializable {
    public Destination {
        Objects.requireNonNull(name);
    }
}
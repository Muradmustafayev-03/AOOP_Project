package classes;

import java.util.ArrayList;

public class Solution extends ArrayList<Assignment> {
    public int getLoss() {
        int loss = 0;
        for (Assignment assignment: this) {
            loss += assignment.getLoss();
        }
        return loss;
    }
}

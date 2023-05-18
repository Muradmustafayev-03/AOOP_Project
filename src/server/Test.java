package server;

import classes.Destination;
import classes.Solution;
import classes.Student;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // Create 10 instances of client.Destination
        Destination dest1 = new Destination("Paris", 5);
        Destination dest2 = new Destination("London", 4);
        Destination dest3 = new Destination("Tokyo", 3);
        Destination dest4 = new Destination("New York", 2);
        Destination dest5 = new Destination("Sydney", 5);
        Destination dest6 = new Destination("Rome", 5);
        Destination dest7 = new Destination("Barcelona", 4);
        Destination dest8 = new Destination("Los Angeles", 6);
        Destination dest9 = new Destination("Dubai", 4);
        Destination dest10 = new Destination("Amsterdam", 2);

        // Create 40 instances of classes.Student
        Student student1 = new Student(1, "John",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student2 = new Student(2, "Emily",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student3 = new Student(3, "David",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student4 = new Student(4, "Emma",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student5 = new Student(5, "Michael",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student6 = new Student(6, "Sophia",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student7 = new Student(7, "William",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student8 = new Student(8, "Isabella",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student9 = new Student(9, "James",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student10 = new Student(10, "Charlotte",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student11 = new Student(11, "Benjamin",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student12 = new Student(12, "Mia",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student13 = new Student(13, "Ethan",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student14 = new Student(14, "Abigail",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student15 = new Student(15, "Oliver",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student16 = new Student(16, "Ava",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student17 = new Student(17, "Lucas",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student18 = new Student(18, "Harper",
                Arrays.asList(dest1, dest6,dest10, dest4, dest5));
        Student student19 = new Student(19, "Alexander",
                Arrays.asList(dest1, dest2, dest10, dest4, dest5));
        Student student20 = new Student(20, "Evelyn",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student21 = new Student(21, "Daniel",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student22 = new Student(22, "Victoria",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student23 = new Student(23, "Matthew",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student24 = new Student(24, "Grace",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student25 = new Student(25, "Jackson",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student26 = new Student(26, "Lily",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student27 = new Student(27, "Sebastian",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student28 = new Student(28, "Sofia",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student29 = new Student(29, "Aiden",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student30 = new Student(30, "Scarlett",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student31 = new Student(31, "Carter",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student32 = new Student(32, "Aria",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student33 = new Student(33, "Henry",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student34 = new Student(34, "Zoe",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student35 = new Student(35, "Joseph",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student36 = new Student(36, "Penelope",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student37 = new Student(37, "Levi",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student38 = new Student(38, "Nora",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));
        Student student39 = new Student(39, "David",
                Arrays.asList(dest1, dest2, dest3, dest4, dest5));
        Student student40 = new Student(40, "Chloe",
                Arrays.asList(dest1, dest6, dest7, dest8, dest9));

        List<Destination> destinations = Arrays.asList(dest1, dest2, dest3, dest4,
                dest5, dest6, dest7, dest8, dest9, dest10);

        List<Student> students = Arrays.asList(
                student1, student2, student3, student4, student5, student6, student7, student8, student9, student10,
                student11, student12, student13, student14, student15, student16, student17, student18, student19,
                student20, student21, student22, student23, student24, student25, student26, student27, student28,
                student29, student30, student31, student32, student33, student34, student35, student36, student37,
                student38, student39, student40
        );

//        server.GeneticAlgorithm = new server.GeneticAlgorithm(students, destinations, 300, 0.7);
//        int correct = 0;
//        for (int i = 0; i < 20; i++) {
//            classes.Solution solution = geneticAlgorithm.run(300);
//            if (solution.getLoss() == 261) {correct++; System.out.println("+");}
//            else {System.out.println("-");}
//            if (solution.getLoss() < 261) {System.out.println("ALERT: " + solution.getLoss());}
//        }
//        System.out.println(correct);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(students, destinations, 100, 0.5);
        Solution solution = geneticAlgorithm.run(100);
        System.out.println(solution.getLoss());
    }
}

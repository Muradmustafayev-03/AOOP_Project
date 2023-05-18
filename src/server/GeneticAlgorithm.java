package server;

import classes.Assignment;
import classes.Destination;
import classes.Solution;
import classes.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    private final List<Student> students; // List of students
    private final List<Destination> destinations; // List of destinations
    private final int populationSize; // Size of the population
    private final double mutationRate; // Rate of mutation

    public GeneticAlgorithm(List<Student> students, List<Destination> destinations, int populationSize, double mutationRate) {
        this.students = students;
        this.destinations = destinations;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
    }

    public Solution run(int iterations) {
        List<Solution> population = this.initializePopulation(); // Initialize the population

        for (int i = 0; i < iterations; i++) {
            population = this.evolvePopulation(population); // Evolve the population
//            System.out.println(getBestSolution(population).getLoss()); // Print the loss of the best solution in the population
        }

        return getBestSolution(population); // Return the best solution
    }

    private List<Solution> evolvePopulation(List<Solution> population) {
        Solution bestSolution = getBestSolution(population); // Get the best solution in the population

        // Crossover
        List<Solution> newPopulation = new ArrayList<>();
        while (newPopulation.size() < this.populationSize - 1) {
            Solution parent1 = this.selectParent(population); // Select parent 1
            Solution parent2 = this.selectParent(population); // Select parent 2
            Solution child = this.crossover(parent1, parent2); // Create a child solution through crossover
            newPopulation.add(child);
        }

        this.mutate(newPopulation); // Mutate the new population

        newPopulation.add(bestSolution); // Add the best solution to the new population

        return newPopulation; // Return the evolved population
    }

    private List<Solution> initializePopulation() {
        List<Solution> population = new ArrayList<>();
        List<Destination> destinationsList;

        for (int i = 0; i < this.populationSize; i++) {
            destinationsList = this.getDestinationsList(); // Get a list of available destinations
            Solution solution = new Solution();

            for (Student student : this.students) {
                Destination destination;

                if (destinationsList.size() > 0) {
                    destination = selectRandomDestination(destinationsList); // Select a random destination from the available list
                    destinationsList.remove(destination);
                } else {
                    destination = null;
                }

                Assignment assignment = new Assignment(student, destination);
                solution.add(assignment);
            }

            population.add(solution);
        }

        return population; // Return the initialized population
    }

    private List<Destination> getDestinationsList() {
        List<Destination> destinationsList = new ArrayList<>();

        for (Destination destination : this.destinations) {
            for (int i = 0; i < destination.maxStudents(); i++) {
                destinationsList.add(destination);
            }
        }

        return destinationsList; // Return the list of destinations
    }

    private static Destination selectRandomDestination(List<Destination> destinations) {
        Random rand = new Random();
        return destinations.get(rand.nextInt(destinations.size())); // Return a random destination from the given list
    }

    public static Solution getBestSolution(List<Solution> population) {
        Solution bestSolution = population.get(0);

        for (Solution solution : population) {
            if (solution.getLoss() < bestSolution.getLoss()) {
                bestSolution = solution;
            }
        }

        return bestSolution; // Return the best solution from the given population
    }

    private Solution selectParent(List<Solution> population) {
        double relativeProbabilitiesSum = 0.0;
        for (Solution solution : population) {
            relativeProbabilitiesSum += (1.0 / solution.getLoss());
        }

        double rand = Math.random() * relativeProbabilitiesSum;
        double cumulativeProbability = 0.0;
        for (Solution solution : population) {
            cumulativeProbability += (1.0 / solution.getLoss());
            if (cumulativeProbability >= rand) {
                return solution;
            }
        }
        return null;
    }

    private Solution crossover(Solution parent1, Solution parent2) {
        List<Destination> destinationsList = this.getDestinationsList();
        int crossoverPoint = (int) (Math.random() * this.students.size());

        Solution child = new Solution();

        int i = 0;
        for (; i < crossoverPoint; i++) {
            Assignment assignment = parent1.get(i);
            Destination destination = assignment.destination();
            child.add(assignment);
            destinationsList.remove(destination);
        }

        for (; i < this.students.size(); i++) {
            Assignment assignment = parent2.get(i);
            Destination destination = assignment.destination();

            if (!destinationsList.contains(destination)) {
                destination = selectRandomDestination(destinationsList);
                assignment = new Assignment(assignment.student(), destination);
            }

            child.add(assignment);
            destinationsList.remove(destination);
        }

        return child; // Return the crossover child solution
    }

    private void mutate(List<Solution> population) {
        for (Solution solution : population) {
            if (Math.random() > this.mutationRate) {
                continue;
            }
            Assignment assignment1 = selectRandomAssignment(solution);
            Assignment assignment2 = selectRandomAssignment(solution);
            solution.remove(assignment1);
            solution.remove(assignment2);
            solution.add(new Assignment(assignment1.student(), assignment2.destination()));
            solution.add(new Assignment(assignment2.student(), assignment1.destination()));
        }
    }

    private static Assignment selectRandomAssignment(List<Assignment> solution) {
        Random rand = new Random();
        return solution.get(rand.nextInt(solution.size())); // Return a random assignment from the given solution
    }
}

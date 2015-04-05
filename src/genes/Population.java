package genes;

import helicopter.GUI;

import java.util.ArrayList;
import java.util.TreeMap;

public class Population {

    private ArrayList<Chromosome> population;
    private TreeMap<Integer, Chromosome> sortedPop = new TreeMap<>();
    private int size;
    private double crossoverRate;
    private double mutationRate;
    private int codonLength;
    private int geneSize;
    private GUI game;

    public Population(int size, double crossoverRate, double mutationRate,
                      int codonLength, int geneSize, GUI game) {
        this.game = game;
        this.size = size;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.codonLength = codonLength;
        this.geneSize = geneSize;

        this.population = new ArrayList<>();
        createPopulation();
    }

    public Population() {
        this(10, 0.7, 0.001, 1, 100, null);
    }

    public void createPopulation() {
        int[] dimensions = {codonLength, geneSize};

        for (int i = 0; i < size; i++) {
            Chromosome chromosome = new Chromosome(dimensions);
            population.add(chromosome);

            if (game != null) {
                System.out.println("Testing " + i);
                chromosome.testFitness(game);
                sortedPop.put(chromosome.getFitness(), chromosome);
            }
        }
    }

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }

    public TreeMap<Integer, Chromosome> getSortedPop() {
        return sortedPop;
    }
}

package genes;

import java.util.ArrayList;

public class Population {

    private ArrayList<Chromosome> population;
    private int size;
    private double crossoverRate;
    private double mutationRate;
    private int codonLength;
    private int geneSize;

    public Population(int size, double crossoverRate, double mutationRate,
                      int codonLength, int geneSize) {
        this.size = size;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.codonLength = codonLength;
        this.geneSize = geneSize;

        this.population = new ArrayList<>();
        createPopulation();
    }

    public Population() {
        this(10, 0.7, 0.001, 1, 100);
    }

    public void createPopulation() {
        int[] dimensions = {codonLength, geneSize};

        for (int i = 0; i < size; i++) {
            Chromosome c = new Chromosome(dimensions);
            population.add(c);
        }
    }

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }
}

package genetics;

import helicopter.GUI;

import java.util.*;

public class Population {

    private GUI game;
    private Random rando;

    private ArrayList<Chromosome> population;
    private TreeMap<Integer, Chromosome> sortedPop;

    private int size;
    private double crossoverRate;
    private double mutationRate;
    private double[] geneDimensions;

    public Population(int size, double crossoverRate, double mutationRate,
                      double codonLength, double geneSize, GUI game) {
        this.game = game;
        this.size = size;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        geneDimensions = new double[]{codonLength, geneSize, mutationRate};

        rando = new Random();
        sortedPop = new TreeMap<>();

        this.population = new ArrayList<>();
        createPopulation();
    }

    // default
    public Population() {
        this(10, 0.7, 0.001, 1, 100, null);
    }

    public void createPopulation() {
        for (int i = 0; i < size; i++) {
            Chromosome chromosome = new Chromosome(geneDimensions);
            population.add(chromosome);

            if (game != null) {
                System.out.println("Testing " + String.valueOf(i + 1));
                chromosome.testFitness(game);
                sortedPop.put(chromosome.getFitness(), chromosome);
            }
        }
    }

    public Chromosome selectRandom() {
        return population.get(rando.nextInt(population.size()));
    }

    public Chromosome selectWeighted() {
        ArrayList<Integer> fitness = new ArrayList<>();
        fitness.addAll(sortedPop.keySet());
        int pick = rando.nextInt(fitness.stream().reduce(Integer::sum).get());
        int current = 0;

        for (Integer i : sortedPop.keySet()) {
            current += i;
            if (current > pick) return sortedPop.get(i);
        }
        return selectRandom();
    }

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }

    public TreeMap<Integer, Chromosome> getSortedPop() {
        return sortedPop;
    }
}

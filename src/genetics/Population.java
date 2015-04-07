package genetics;

import helicopter.GUI;
import helicopter.Play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

class Population {

    private GUI game;
    private Random rando;

    private ArrayList<Chromosome> population;
    private TreeMap<Integer, ArrayList<Chromosome>> fitPop;

    private int size;
    private double crossoverRate;
    private double[] geneDimensions;
    private int populationFitness;

    public Population(int size, double crossoverRate, double mutationRate,
                      double codonLength, double geneSize, GUI game) {
        this.game = game;
        this.size = size;
        this.crossoverRate = crossoverRate;
        geneDimensions = new double[]{codonLength, geneSize, mutationRate};

        rando = new Random();
        fitPop = new TreeMap<>();

        this.population = new ArrayList<>();
        createPopulation();
    }

    // default, no game
    @SuppressWarnings("WeakerAccess")
    public Population() {
        this(10, 0.7, 0.001, 1, 100, null);
    }

    // default, with game
    public Population(GUI game) {
        this();
        this.game = game;
    }

    // for testing one
    public Population(String testDNA, String testObs, GUI game) {
        this.game = game;
        game.getGame().readObstacleLocations(testObs);
        new Chromosome(testDNA, game);
    }

    private void createPopulation() {
        while (population.size() < size)
            population.add(new Chromosome(geneDimensions));

        if (game != null) assessPopulationFitness();
    }

    public int getPopulationFitness() {
        return populationFitness;
    }

    private Chromosome selectRandom() {
        return population.get(rando.nextInt(population.size()));
    }

    private Chromosome selectWeighted() {
        int pick = rando.nextInt(populationFitness);
        int current = 0;

        for (Integer i : fitPop.keySet()) {
            current += i;
            if (current > pick) return fitPop.get(i)
                    .get(rando.nextInt(fitPop.get(i).size()));
        }
        return selectRandom();
    }

    private ChromoPair crossOver(Chromosome C1, Chromosome C2) {
        if (rando.nextDouble() < crossoverRate) {
            int start = rando.nextInt(C1.getChromoLength());

            String tempC1SecondPart = C1.getStringDNA().substring(start);

            Chromosome newC1 = new Chromosome(geneDimensions,
                    C1.getStringDNA().substring(0, start) +
                            C2.getStringDNA().substring(start));

            Chromosome newC2 = new Chromosome(geneDimensions,
                    C2.getStringDNA().substring(0, start) +
                            tempC1SecondPart);

            return new ChromoPair(newC1, newC2);
        }
        return new ChromoPair(C1, C2);
    }

    public class ChromoPair {
        public final Chromosome C1;
        public final Chromosome C2;

        public ChromoPair(Chromosome c1, Chromosome c2) {
            this.C1 = c1;
            this.C2 = c2;
        }
    }

    public void nextGeneration() {
        ArrayList<Chromosome> newPop = new ArrayList<>(population.size());

        for (int i = 0; i < population.size() / 2; i++) {
            Chromosome uno = selectWeighted();
            Chromosome dos = selectWeighted();

            if (uno != dos) {
                ChromoPair newChromos = crossOver(uno.mutate(), dos.mutate());

                newPop.add(newChromos.C1);
                newPop.add(newChromos.C2);
            }
        }

        // bring population back up
        while (newPop.size() < population.size())
            newPop.add(new Chromosome(geneDimensions));

        population = newPop;
        assessPopulationFitness();

        System.out.println(this);
//        System.out.println(fitPop);
    }

    private void assessPopulationFitness() {
        TreeMap<Integer, ArrayList<Chromosome>> newFitPop =
                new TreeMap<Integer, ArrayList<Chromosome>>() {
            @Override
            public String toString() {
                String s = "";
                for (Integer c : this.keySet())
                    s += c + ": " + this.get(c) + '\n';
                return s.trim();
            }};

        populationFitness = 0;

        for (Chromosome c : population) {
            int fitness = c.testFitness(game);

            if (newFitPop.keySet().contains(fitness))
                newFitPop.get(fitness).add(c);
            else
                newFitPop.put(fitness,
                        new ArrayList<>(Collections.singletonList(c)));

            populationFitness += fitness;
        }
        fitPop = newFitPop;
    }

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }

    public TreeMap<Integer, ArrayList<Chromosome>> getFitPop() {
        return fitPop;
    }

    public Chromosome getBest() {
        return fitPop.lastEntry().getValue().get(0);
    }

    @Override
    public String toString() {

        //noinspection ConstantConditions
        return (Play.DEBUG ? '\n' + fitPop.toString() + '\n' : "") +
                String.format("Population fitness: %d, average: %d",
                populationFitness, populationFitness / size);
    }
}

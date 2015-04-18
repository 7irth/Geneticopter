package genetics;

import helicopter.GUI;
import helicopter.Play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

@SuppressWarnings("unused")
class Population {

    private GUI game;
    private final Random rando;

    private ArrayList<Chromosome> population;
    private TreeMap<Integer, ArrayList<Chromosome>> fitPop;

    private final int size;
    private final double crossoverRate;
    private final double[] geneDimensions;
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
            ArrayList<Chromosome> fitList = fitPop.get(i);
            current += i * fitList.size();
            if (current > pick)
                return new Chromosome(fitList.get(rando.nextInt(fitList.size())));
        }
        return selectRandom();
    }

    private void eugenicize(double percentile) {
        // remove bottom 95%, fill with copies of top 5%, then generate
//        for (int i = 0; i < population.size() * percentile; i++) {
//            print(i);
//            print(fitPop.descendingKeySet().pollFirst());
//        }

//        int i = 0;
//        while (i < population.size() * percentile) {
//            print(i);
//            Integer here = fitPop.descendingKeySet().descendingIterator();
//            for (Chromosome c : fitPop.get())
//        }
//        print("POP", population.size());
        int keep = (int) (population.size() * percentile);
//        print("KEEP", keep);

        ArrayList<Chromosome> newPop = new ArrayList<>(population.size());
        int i = 0;
        for (Integer thing : fitPop.descendingKeySet()) {
            for (Chromosome c : fitPop.get(thing)) {
                for (int j = 0; j < 1 / percentile; j++) {
//                    print(j);
                    newPop.add(c);
//                    print(c);
                }
                i++;
//                print("THING", i, keep);
                if (i == keep) break;
            }  // uggo loop guards
            if (i == keep) break;
        }
//        print("NEW", newPop);
        population = newPop;
        assessPopulationFitness();
//        print("FIT", fitPop);
    }

    private Chromosome selectBest() {
        Chromosome selected = popBest();
//        print("selected", selected);
        return selected.mutateEnd(10);
    }

    // pythonify printing
    public void print(Object... args) {
        for (Object arg : args) System.out.print(String.valueOf(arg) + " ");
        System.out.println();
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
        eugenicize(0.1);
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
        while (newPop.size() < population.size()) {
            print("adding noob");
            newPop.add(new Chromosome(geneDimensions));
        }

        population = newPop;
        assessPopulationFitness();

//        print("NEXT", newPop);
    }

    private void assessPopulationFitness() {
//        TreeMultimap<Integer, Chromosome> test;
        TreeMap<Integer, ArrayList<Chromosome>> newFitPop =
                new TreeMap<Integer, ArrayList<Chromosome>>() {
                    @Override
                    public String toString() {
                        String s = "";
                        for (Integer c : this.keySet())
                            s += String.format("%d: %s %d\n",
                                    c, this.get(c), this.get(c).size());
                        return s.trim();
                    }
                };

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
        ArrayList<Chromosome> bestest = fitPop.lastEntry().getValue();
        return bestest.get(rando.nextInt(bestest.size()));
    }

    public Chromosome popBest() {
        ArrayList<Chromosome> bestest = fitPop.lastEntry().getValue();

        if (bestest.size() == 0) {
            fitPop.pollLastEntry();
            bestest = fitPop.lastEntry().getValue();
        }

        Chromosome randoBest = bestest.get(rando.nextInt(bestest.size()));
        bestest.remove(randoBest);

        return randoBest;
    }

    @Override
    public String toString() {

        //noinspection ConstantConditions
        return (Play.DEBUG ? '\n' + fitPop.toString() + '\n' : "") +
                String.format("Population fitness: %d, average: %d",
                        populationFitness, populationFitness / size);
    }
}

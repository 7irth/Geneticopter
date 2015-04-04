package genes;

import java.util.Random;

public class Chromosome {

    private int codonSize;
    private int numberOfGenes;
    private int chromoLength;

    private static final int PERFECT_FIT = 999;
    
    private String dna;

    private int fitness;

    public Chromosome(int[] geneInfo, String dna) {
        Random rando = new Random();

        codonSize = geneInfo[0];
        numberOfGenes = geneInfo[1];
        chromoLength = codonSize * numberOfGenes;

        this.dna = dna;

        if (dna.length() == 0) while (this.dna.length() < chromoLength)
            this.dna += rando.nextBoolean() ? "1" : "0";

        fitness = 0;
    }

    public Chromosome(int[] geneInfo) {
        this(geneInfo, "");
    }

    public int getCodonSize() {
        return codonSize;
    }

    public void setCodonSize(int codonSize) {
        this.codonSize = codonSize;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public void setNumberOfGenes(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
    }

    public int getChromoLength() {
        return chromoLength;
    }

    public void setChromoLength(int chromoLength) {
        this.chromoLength = chromoLength;
    }

    public int getFitness() {
        return fitness;
    }

    public void upFitness() {
        fitness++;
    }

    @Override
    public String toString() {
        return dna;
    }
}

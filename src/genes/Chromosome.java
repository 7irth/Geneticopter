package genes;

public class Chromosome {

    private int codonSize;
    private int numberOfGenes;
    private int chromoLength;
    private static final int PERFECT_FIT = 999;
    private String dna;

    public Chromosome(int[] geneInfo) {
        codonSize = geneInfo[0];
        numberOfGenes = geneInfo[1];
        chromoLength = codonSize * numberOfGenes;
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
}

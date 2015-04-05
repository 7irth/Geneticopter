package genes;

import helicopter.GUI;
import helicopter.HelicopterGame;

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

    public void testFitness(GUI window) {
        window.getGame().initializeCave();

        for (char c : dna.toCharArray()) {
            try {
                if (c == '0') window.getGame().applyingGas(false);
                else window.getGame().applyingGas(true);

                Thread.sleep(GUI.DELAY);
                window.update();

                fitness++;

            } catch (HelicopterGame.CollisionException e) {
                System.out.println("Oh noes!");
                window.getGame().initializeCave();
                break;
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public int getCodonSize() {
        return codonSize;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public int getChromoLength() {
        return chromoLength;
    }

    public int getFitness() {
        return fitness;
    }

    public void upFitness() {
        fitness++;
    }

    public char[] getDNA() {
        return dna.toCharArray();
    }

    @Override
    public String toString() {
        return dna;
    }
}

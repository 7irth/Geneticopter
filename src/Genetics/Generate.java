package Genetics;

public class Generate {
    public static void main(String[] args) {
        int[] geneInfo = {10, 10};
        Chromosome c = new Chromosome(geneInfo);

        System.out.println(c.getChromoLength());
    }
}

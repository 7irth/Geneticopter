package helicopter;

import genes.Chromosome;
import genes.Population;

import java.util.ArrayList;
import java.util.TreeMap;

public class Play {

    private static HelicopterGame game;
    private static GUI gameUI;

    public static void main(String[] args) {
        game = new HelicopterGame(20, 150);
        gameUI = new GUI(game);

        javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        System.out.println(test(gameUI));
    }

    public static TreeMap<Integer, Chromosome> test(GUI window) {
        Population population = new Population();
        ArrayList<Chromosome> popPop = population.getPopulation();
        TreeMap<Integer, Chromosome> sortedPop = new TreeMap<>();

        int at = 0;

        for (Chromosome chromosome : popPop) {
            System.out.println(++at);

            for (char c : chromosome.toString().toCharArray())
                try {
                    if (c == '0') game.applyingGas(false);
                    else game.applyingGas(true);

                    Thread.sleep(100);
                    window.update();

                    chromosome.upFitness();

                } catch (HelicopterGame.CollisionException e) {
                    System.out.println("Oh noes!");
                    game.initializeCave();
                    break;
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

            sortedPop.put(chromosome.getFitness(), chromosome);
        }
        return sortedPop;
    }
}

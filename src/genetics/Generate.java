package genetics;

import helicopter.GUI;

public class Generate {
    public static void gen(GUI game) {

        Population popPop = new Population(10, 0.7, 1, 1, 50, game);
        System.out.println(popPop.getSortedPop());
        popPop.selectWeighted();
    }
}

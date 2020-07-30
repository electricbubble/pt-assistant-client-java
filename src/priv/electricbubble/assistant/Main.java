package priv.electricbubble.assistant;

import java.io.IOException;

/**
 * @author electricbubble
 */
public class Main {
    public static void main(String[] args) {
        PtAssistant ptAssistant = new PtAssistant();
        try {
            ptAssistant.init();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 3000; i++) {
            try {
//                System.out.println(ptAssistant.incr());
                ptAssistant.incr();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

        ptAssistant.end();
    }

}

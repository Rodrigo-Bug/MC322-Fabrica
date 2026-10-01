package PackInterfaces;

import java.util.Random;

public interface Aleatorio {

    default double numeroAleatorio(double min, double max) {
        Random random = new Random();

        return random.nextDouble(min, max);
    }
}



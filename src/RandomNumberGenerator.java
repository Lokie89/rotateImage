import java.util.Random;

public class RandomNumberGenerator {

    private int randomNumber;

    public RandomNumberGenerator() {
        randomNumber = new Random().nextInt(60) - 30;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

}

package selenium.test.framework.Utils.Data;

import java.util.Random;

public class DataGenerator {

    public int getRandomNumber(int min, int max){
        return new Random().nextInt(max - min + 1 ) + min;
    }

    public String getRandomText(int length){
        char[] chars = "abcdefghijklmnoprstqwxyz ".toCharArray();
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}

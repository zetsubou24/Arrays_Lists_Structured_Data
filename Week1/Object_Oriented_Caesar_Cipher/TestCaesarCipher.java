package Object_Oriented_Caesar_Cipher;

import edu.duke.FileResource;

import static java.lang.Math.abs;

public class TestCaesarCipher {
    public int[] countLetters(String text){
        int[] counts = new int[26];
        for(int i = 0; i < text.length(); i++){
            if(Character.isLetter(text.charAt(i))) {
                counts[Character.toLowerCase(text.charAt(i)) - 97] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values){
        int maxIndex = -1;
        int maxValue = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxValue){
                maxIndex = i;
                maxValue = values[i];
            }
        }
        return maxIndex;
    }

    public int getKey(int maxIndex){
        int key = 4 - maxIndex;
        if(key < 0) key += 26;
        return abs(key) % 26;
    }

    public void simpleTests(){
        CaesarCipher caesarCipher = new CaesarCipher(18);
        FileResource fileResource = new FileResource("TestData/Week1/message1.txt");
        String text = fileResource.asString();
        String ciphered = caesarCipher.encrypt(text);
        System.out.println(ciphered);
        String deciphered = caesarCipher.decrypt(ciphered);
        System.out.println(deciphered);
        String broken = breakCaesarCipher(ciphered);
        System.out.println(broken);
    }

    public String breakCaesarCipher(String input){
        int maxIndex = maxIndex(countLetters(input));
        CaesarCipher caeserCipher = new CaesarCipher(getKey(maxIndex));
        String deciphered = caeserCipher.encrypt(input);
        return deciphered;
    }

    public static void main(String[] args) {
        TestCaesarCipher testCaesarCipher = new TestCaesarCipher();
        testCaesarCipher.simpleTests();
    }
}

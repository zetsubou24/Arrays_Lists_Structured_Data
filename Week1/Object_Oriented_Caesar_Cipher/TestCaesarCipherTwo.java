package Object_Oriented_Caesar_Cipher;

import edu.duke.FileResource;

import static java.lang.Math.abs;

public class TestCaesarCipherTwo {
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


    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < message.length(); i += 2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public void simpleTests(){
        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(17, 3);
        FileResource fileResource = new FileResource("TestData/Week1/message1.txt");
        String text = fileResource.asString();
        String ciphered = caesarCipherTwo.encrypt(text);
        System.out.println(ciphered);
        String deciphered = caesarCipherTwo.decrypt(ciphered);
        System.out.println(deciphered);
        String broken = breakCaesarCipher(ciphered);
        System.out.println(broken);
    }

    public String breakCaesarCipher(String input){
        String encrypted1 = halfOfString(input,0);
        String encrypted2 = halfOfString(input,1);
        int maxIndex1 = maxIndex(countLetters(encrypted1));
        int maxIndex2 = maxIndex(countLetters(encrypted2));
        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(getKey(maxIndex1), getKey(maxIndex2));
        return caesarCipherTwo.encrypt(input);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo testCaesarCipherTwo = new TestCaesarCipherTwo();
        testCaesarCipherTwo.simpleTests();
    }
}

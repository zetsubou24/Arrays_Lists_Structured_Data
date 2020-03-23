import edu.duke.FileResource;

import static java.lang.Math.abs;

public class CaesarBreaker {
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

    public String decrypt(String text, int maxIndex){
        CaesarCipher caeserCipher = new CaesarCipher();
        String deciphered = caeserCipher.encrypt(text, getKey(maxIndex));
        return deciphered;
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

    public String decryptTwoKeys(String encrypted){
        CaesarCipher caesarCipher = new CaesarCipher();
        String encrypted1 = halfOfString(encrypted,0);
        String encrypted2 = halfOfString(encrypted,1);
        int maxIndex1 = maxIndex(countLetters(encrypted1));
        int maxIndex2 = maxIndex(countLetters(encrypted2));
        String deciphered = caesarCipher.encryptTwo(encrypted, getKey(maxIndex1), getKey(maxIndex2));
        return  deciphered;
    }

    public void tester(){
        FileResource fileResource = new FileResource("TestData/Week1/smallHamlet.txt");
        String text = fileResource.asString();
        System.out.println(text);
        CaesarCipher caesarCipher = new CaesarCipher();
        String cipher = caesarCipher.encrypt(text, 1);
        System.out.println(cipher);
        int[] counts = countLetters(cipher);
        int maxIndex = maxIndex(counts);
        System.out.println(maxIndex);
        System.out.println(decrypt(cipher,maxIndex));

        String cipher2 = caesarCipher.encryptTwo(text,17,21);
        System.out.println(cipher2);
        String deciphered2 = decryptTwoKeys(cipher2);
        System.out.println(deciphered2);
    }

    public static void main(String[] args) {
        CaesarBreaker caesarBreaker = new CaesarBreaker();
        caesarBreaker.tester();

    }
}

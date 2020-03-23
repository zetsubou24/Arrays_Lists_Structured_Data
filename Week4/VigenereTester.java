import edu.duke.FileResource;

import java.util.HashSet;

public class VigenereTester {
    private VigenereBreaker vigenereBreaker;

    public void testSliceString(){
        vigenereBreaker = new VigenereBreaker();
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 2, 5));
    }

    public void testTryKeyLength(){
        vigenereBreaker = new VigenereBreaker();
        FileResource fileResource = new FileResource("TestData/Week4/athens_keyflute.txt");
        String text = fileResource.asString();
        int[] keys = vigenereBreaker.tryKeyLength(text, 5, 'e');
        for(int key: keys){
            System.out.println(key);
        }
    }

    public void testBreakVigenere(){
        vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere();
    }

    public void testMostCommonCharIn(){
        vigenereBreaker = new VigenereBreaker();
        HashSet<String> dictionary = vigenereBreaker.readDictionary(new FileResource("TestData/Week4/dictionaries/English"));
        char ch = vigenereBreaker.mostCommonCharIn(dictionary);
        System.out.println(ch);
    }

    public static void main(String[] args) {
        VigenereTester vigenereTester = new VigenereTester();
//        vigenereTester.testSliceString();
//        vigenereTester.testTryKeyLength();
        vigenereTester.testBreakVigenere();
//        vigenereTester.testMostCommonCharIn();
    }
}

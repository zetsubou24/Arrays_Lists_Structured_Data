import edu.duke.*;

import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            int currKey = caesarCracker.getKey(slice);
            key[i] = currKey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fileResource1 = new FileResource("TestData/Week4/messages/secretmessage1.txt");
        String text = fileResource1.asString();
        HashMap<String, HashSet<String>> languages = readAllDictionaries();
        String deciphered = breakForAllLangs(text, languages);
        System.out.println(deciphered);
    }

    public HashSet<String> readDictionary(FileResource fileResource){
        HashSet<String> dictionary = new HashSet<>();
        for(String line: fileResource.lines()){
            dictionary.add(line.toLowerCase());
        }
        return  dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word: message.split("\\W+")){
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        VigenereCipher vigenereCipher;
        int maxCount = 0;
        String actualDeciphered = "";
        for(int i = 1; i <= 100; i++){
            int[] currKeys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            vigenereCipher = new VigenereCipher(currKeys);
            String currDeciphered = vigenereCipher.decrypt(encrypted);
            int currCount = countWords(currDeciphered, dictionary);
            if(currCount > maxCount){
                maxCount = currCount;
                actualDeciphered = currDeciphered;
            }

        }
        return actualDeciphered;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        int[] counter = new int[26];
        for(String word: dictionary){
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                if((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)){
                    int index = Character.toLowerCase(ch) - 97;
                    counter[index] += 1;
                }
            }
        }
        int maxVal = 0;
        char maxChar = 'e';
        for(int i = 0; i < counter.length; i++){
            if(counter[i] > maxVal){
                maxVal = counter[i];
                maxChar = (char) (i + 97);
            }
        }
        return maxChar;
    }

    public HashMap<String, HashSet<String>> readAllDictionaries(){
        HashMap<String, HashSet<String>> languages= new HashMap<>();
        String[] languageNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for(String language: languageNames){
            FileResource fileResource = new FileResource("TestData/Week4/dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(fileResource);
            languages.put(language, dictionary);
            }
        return languages;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String maxLanguage = "English";
        for (String language : languages.keySet()) {
            int currCount = countWords(encrypted, languages.get(language));
            if(currCount > maxCount){
                maxLanguage = language;
            }
        }
        String deciphered = breakForLanguage(encrypted, languages.get(maxLanguage));
        return deciphered;
    }
}

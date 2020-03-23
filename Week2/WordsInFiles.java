import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> counter = new HashMap<>();
    public WordsInFiles(){

    }

    private void addWordsFromFile(File file){
        FileResource fileResource = new FileResource(file);
        for(String word: fileResource.words()){
            if(!counter.containsKey(word)){
                ArrayList<String> fileList = new ArrayList<>();
                fileList.add(file.getName());
                counter.put(word, fileList);
            }
            else{
                counter.get(word).add(file.getName());
            }
        }
    }

    public void buildWordFileMap(){
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            addWordsFromFile(file);
        }
    }

    public int maxNumber(){
        int maxCount = 0;
        for(String key: counter.keySet()){
            if(counter.get(key).size() > maxCount){
                maxCount = counter.get(key).size();
            }
        }
        return maxCount;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> exactWords = new ArrayList<>();
        for(String key: counter.keySet()){
            if(counter.get(key).size() == number){
                exactWords.add(key);
            }
        }
        return exactWords;
    }

    public void printFilesIn(String word){
        if(!counter.containsKey(word)) return;
        for(String file: counter.get(word)){
            System.out.println(file);
        }
    }

    public void tester(){
        buildWordFileMap();
        System.out.println("The word occurring maximum number of times is " + maxNumber());
        ArrayList<String> exactWords = wordsInNumFiles(3);
        for(String word: exactWords){
            System.out.println(word);
        }
        printFilesIn("cats");
    }

    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }
}

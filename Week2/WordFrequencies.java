import edu.duke.FileResource;
import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fileResource = new FileResource("TestData/Week2/smallHamlet.txt");
        String text = fileResource.asString();
        String[] words = text.split(" ");
        for(String word: words){
            if(!myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            }
            int index = myWords.indexOf(word);
            myFreqs.set(index, myFreqs.get(index) + 1);
        }
    }

    public int indexOfMax(){
        int maxIndex = -1;
        int maxCount = 0;
        for(int i = 0; i < myWords.size(); i++){
            if(myFreqs.get(i) > maxCount){
                maxCount = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester(){
        findUnique();
        System.out.println(myWords.size());
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int index = indexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester();
    }
}

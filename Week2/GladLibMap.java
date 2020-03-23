import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private ArrayList<String> seenWords = new ArrayList<>();
    private HashMap<String, Integer> seenCategoriesCount = new HashMap<>();
    private HashMap<String, ArrayList<String>> wordsLists;
    private int count = 0;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "TestData/Week2/GladLibData/data";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        wordsLists = new HashMap<>();
        ArrayList<String> categories = new ArrayList<>(
                Arrays.asList("adjective","noun","color","country","name","animal","timeframe","verb","fruit")
        );
        for(String category: categories){
            ArrayList<String> words= new ArrayList<>();
            words = readIt(source + "/" + category + ".txt");
            wordsLists.put(category, words);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if(label.equals("number")){
                if(!seenCategoriesCount.containsKey(label)) seenCategoriesCount.put(label,1);
                else seenCategoriesCount.put(label, seenCategoriesCount.get(label) + 1);
                return ""+myRandom.nextInt(50)+5;
        }
        else if (!wordsLists.containsKey(label)) {
            return "**UNKNOWN**";
        }
        else{
            if(!seenCategoriesCount.containsKey(label)) seenCategoriesCount.put(label,1);
            else seenCategoriesCount.put(label, seenCategoriesCount.get(label) + 1);
            return randomFrom(wordsLists.get(label));
        }
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(seenWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        seenWords.add(sub);
        count += 1;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap(){
        int count = 0;
        for(String key: wordsLists.keySet()){
            count += wordsLists.get(key).size();
        }
        return count;
    }

    public void totalWordsConsidered(){
        for(String key: seenCategoriesCount.keySet()){
            System.out.println(key + " " + seenCategoriesCount.get(key));
        }
    }

    public void makeStory(){
        seenWords.clear();
        System.out.println();
        String story = fromTemplate("TestData/Week2/GladLibData/data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println(count + " words were replaced to make this story.");
        System.out.println("Total words in map are " + totalWordsInMap());
        totalWordsConsidered();
    }

    public static void main(String[] args) {
        GladLibMap gladLibMap = new GladLibMap();
        gladLibMap.makeStory();

    }

}

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource fileResource, int[] counts){
        String contents = fileResource.asString();
        String[] words = contents.split(" ");
        for(String word: words){
            int count = 0;
            for(int i = 0; i < word.length(); i++){
                if(Character.isLetter(word.charAt(i))) count += 1;
            }
            if(count >= counts.length) counts[counts.length - 1] += 1;
            else counts[count] += 1;
        }
        for(int i = 0; i < counts.length; i++){
            if(counts[i] == 0) continue;
            System.out.println(counts[i] + " words of length " + i);
        }
    }

    public int indexOfMax(int[] values){
        int maxIndex = -1;
        int maxCount = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxCount){
                maxCount = values[i];
                maxIndex = i;
            }
        }
        return maxIndex + 1;
    }

    public void testCountWordLengths(){
        FileResource fileResource = new FileResource("TestData/Week1/smallHamlet.txt");
        int[] counts = new int[12];
        countWordLengths(fileResource,counts);
        System.out.println(indexOfMax(counts));
    }

    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}

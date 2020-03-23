import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay{
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> freqs = new ArrayList<>();

    public void update(String person){
        if(!names.contains(person)){
            names.add(person);
            freqs.add(1);
        }
        int index = names.indexOf(person);
        freqs.set(index, freqs.get(index) + 1);
    }

    public void findAllCharacters(){
        FileResource fileResource = new FileResource("TestData/Week2/CommonWordsData/macbeth.txt");
        for(String line: fileResource.lines()){
            if(line.equals("")) continue;
            if(line.indexOf('.') != -1){
                int index = line.indexOf('.');
                String name = line.substring(0, index);
                update(name);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < names.size(); i++){
            if(freqs.get(i) >= num1 && freqs.get(i) <= num2){
                System.out.println(names.get(i) + " had exactly " + freqs.get(i) + " parts ");
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i = 0; i < names.size(); i++){
            if(freqs.get(i) > 5){
                System.out.println(names.get(i) + " had " + freqs.get(i) + " parts ");
            }
        }
        charactersWithNumParts(7,11);
    }

    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }
}
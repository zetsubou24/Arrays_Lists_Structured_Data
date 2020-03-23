import java.util.HashMap;

public class CodonCount {

    private HashMap<String, Integer> counter = new HashMap<>();

    public void buildCodonMap(String dna, int start){
        counter.clear();
        for(int i = start; i < dna.length() - 3; i += 3){
            String codon = dna.substring(i, i + 3);
            if(!counter.containsKey(codon)){
                counter.put(codon, 1);
            }
            else counter.put(codon, counter.get(codon) + 1);
        }
    }

    public String getMostCommonCodon(){
        int maxCount = 0;
        String maxCodon = "";
        for(String key: counter.keySet()){
            if(counter.get(key) > maxCount){
                maxCodon = key;
                maxCount = counter.get(key);
            }
        }
        return maxCodon;
    }

    public void printCodonCounts(int start, int end){
            for(String key: counter.keySet()){
                System.out.println(key + counter.get(key));

            }
    }

    public void tester(){
        String dna = "CGTTCAAGTTCAA";
        buildCodonMap(dna, 0);
        for(String key: counter.keySet()){
            System.out.println(key + counter.get(key));
        }
        System.out.println("The maximum occuring codon is " + getMostCommonCodon());
        printCodonCounts(0,0);
    }

    public static void main(String[] args) {
        CodonCount codonCount = new CodonCount();
        codonCount.tester();
    }
}

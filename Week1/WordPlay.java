public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        return vowels.contains(Character.toString(Character.toLowerCase(ch)));
    }

    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < phrase.length(); i++){
            if(isVowel(phrase.charAt(i))){
                sb.append(ch);
            }
            else{
                sb.append(phrase.charAt(i));
            }
        }
            return sb.toString();
    }

    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < phrase.length(); i++){
            if(phrase.charAt(i) == ch) {
                if (i % 2 == 0) sb.append('+');
                else sb.append('*');
            }
            else sb.append(phrase.charAt(i));
        }
        return sb.toString();
    }

    public void testIsVowel(){
        System.out.println(isVowel('A'));
        System.out.println(isVowel('b'));
    }

    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hello World",'*'));
    }

    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }

    public static void main(String[] args) {
        WordPlay wordPlay = new WordPlay();
        wordPlay.testIsVowel();
        wordPlay.testReplaceVowels();
        wordPlay.testEmphasize();
    }
}

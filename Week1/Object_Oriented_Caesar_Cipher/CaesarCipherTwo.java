package Object_Oriented_Caesar_Cipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    CaesarCipherTwo(int key1, int key2){
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(Character.isAlphabetic(ch)) {
                int index = alphabet.indexOf(Character.toLowerCase(ch));

                if(i % 2 == 0) {
                    if(Character.isUpperCase(ch)) {
                        sb.append(Character.toUpperCase(shiftedAlphabet1.charAt(index)));
                    }
                    else{
                        sb.append(shiftedAlphabet1.charAt(index));
                    }
                }
                else {
                    if(Character.isUpperCase(ch)){
                        sb.append(Character.toUpperCase(shiftedAlphabet2.charAt(index)));
                    }
                    else {
                        sb.append(shiftedAlphabet2.charAt(index));
                    }
                }
            }
            else sb.append(ch);
        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipherTwo caesarCipherTwo = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return caesarCipherTwo.encrypt(input);
    }
}

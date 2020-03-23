package Object_Oriented_Caesar_Cipher;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(Character.isAlphabetic(ch)) {
                int index = alphabet.indexOf(Character.toLowerCase(ch));
                if(Character.isUpperCase(ch)) {
                    sb.append(Character.toUpperCase(shiftedAlphabet.charAt(index)));
                }
                else{
                    sb.append(shiftedAlphabet.charAt(index));
                }
            }
            else sb.append(ch);
        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipher caesarCipher = new CaesarCipher(26 - mainKey);
        return caesarCipher.encrypt(input);
    }
}

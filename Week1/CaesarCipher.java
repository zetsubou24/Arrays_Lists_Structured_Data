import edu.duke.FileResource;

public class CaesarCipher {
    public String encrypt(String input, int key){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String cipher = alphabets.substring(key) + alphabets.substring(0,key);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(Character.isAlphabetic(ch)) {
                int index = alphabets.indexOf(Character.toLowerCase(ch));
                if(Character.isUpperCase(ch)) {
                    sb.append(Character.toUpperCase(cipher.charAt(index)));
                }
                else{
                    sb.append(cipher.charAt(index));
                }
            }
            else sb.append(ch);
        }
        return sb.toString();
    }

    public String encryptTwo(String input, int key1, int key2){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String cipher1 = alphabets.substring(key1) + alphabets.substring(0,key1);
        String cipher2 = alphabets.substring(key2) + alphabets.substring(0,key2);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(Character.isAlphabetic(ch)) {
                int index = alphabets.indexOf(Character.toLowerCase(ch));

                if(i % 2 == 0) {
                    if(Character.isUpperCase(ch)) {
                        sb.append(Character.toUpperCase(cipher1.charAt(index)));
                    }
                    else{
                        sb.append(cipher1.charAt(index));
                    }
                }
                else {
                    if(Character.isUpperCase(ch)){
                        sb.append(Character.toUpperCase(cipher2.charAt(index)));
                    }
                    else {
                        sb.append(cipher2.charAt(index));
                    }
                }
            }
            else sb.append(ch);
        }
        return sb.toString();
    }

    public void testEncrypt(){
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
    }

    public void testEncryptTwo(){
        System.out.println(encryptTwo("FIRST LEGION ATTACK EAST FLANK!", 23, 17));
    }

    public void testCaeser(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        caesarCipher.testEncrypt();
        caesarCipher.testCaeser();
        caesarCipher.testEncryptTwo();
    }
}

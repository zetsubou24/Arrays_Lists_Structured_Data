import edu.duke.FileResource;

public class CaesarTester {
    public void testCaesar(){
        FileResource fileResource = new FileResource("TestData/Week4/titus-small.txt");
        String text = fileResource.asString();
        System.out.println(text);

        CaesarCipherNew caesarCipherNew = new CaesarCipherNew(23);
        String ciphered = caesarCipherNew.encrypt(text);
        System.out.println(ciphered);

        String deciphered = caesarCipherNew.decrypt(ciphered);
        System.out.println(deciphered);
    }

    public static void main(String[] args) {
        CaesarTester caesarTester = new CaesarTester();
        caesarTester.testCaesar();
    }
}

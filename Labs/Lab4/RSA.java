import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
 
public class RSA {
    
    private Random random;
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;
    static Scanner scanner = new Scanner(System.in);  
    
    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();
        DataInputStream input = new DataInputStream(System.in);
        String plainText = null;
        String answer = null;
        System.out.println("Please enter a string to encrypt:");
        plainText = input.readLine();
        System.out.println("String in bytes is " + bytes(plainText.getBytes()));
        byte[] encrypted = rsa.encrypt(plainText.getBytes());
        byte[] decrypted = rsa.dencrypt(encrypted);
        
        System.out.println("\nWould you like to dencrypt the String? Y/N");
        answer = scanner.next();
        
        if( answer.equals("y")) {
            System.out.println("Plain text in bytes is " + bytes(decrypted));
            System.out.println("Decrypting bytes to plain text:  " + new String(decrypted));
        }
        
        else if (answer.equals("n")) {
            System.exit(0);
        }
     
    }
    
    public RSA(BigInteger e, BigInteger d, BigInteger n)
    {
        this.e = e;
        this.d = d;
        this.n = n;
    }
    
    public RSA() {
        random = new Random();
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, random);
        
        while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
    
    
    private static String bytes (byte[] enencryptMessage) {
        String change = "";
        
        for (byte b : enencryptMessage) {
            change += Byte.toString(b);
        }
        return change;  
    }
    
    public byte[] encrypt (byte[] plainText) {
        return (new BigInteger(plainText)).modPow(d, n).toByteArray();
    }
    
    public byte[] dencrypt (byte[] cipherText) {
        return (new BigInteger(cipherText)).modPow(e, n).toByteArray();
    }
}

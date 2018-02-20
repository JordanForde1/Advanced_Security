import java.util.Scanner;

public class MillerRabin {
	
	static int num = 0;
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Check if number is prime");
		num = scan.nextInt();
		
		new PrimeNo();
	}
	
	public MillerRabin() {
		isPrime();
	}
	
	public void isPrime() {
		
		if (num == 0 || num == 1) {
			System.out.println("Composite");
			System.exit(0);
		}
		
		if (num == 2) {
			System.out.println("Inconclusive");
			System.exit(0);
		}
		
		if(num % 2 == 0) {
			System.out.println("Composite");
			System.exit(0);
		}
		
		int k = 5;
		int q = 0;
		
		int otherNum = num - 1;
		
		while(true)
		{
			k++;
			q = otherNum / 2^k;
			
			if( q % 2 == 1) {
				break;
			}
		}
		
		int a = 1 + (int)(Math.random() * otherNum);
		
		if((a^q % num) == 1) {
			System.out.println("Inconclusive");
			System.exit(0);
		}
		
		for(int j = 0; j < k - 1; j++) {
			if((a^ (2^j * q)) % num == otherNum) {
				System.out.println("Inconclusive");
				System.exit(0);
			}
			
			else {
				System.out.println("Composite");
				System.exit(0);
			}
		}
		
	}
}

import java.math.BigInteger;

public class AES {
	private static final String[] sbox = { "63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe",
			"d7", "ab", "76", "ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72",
			"c0", "b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15", "04",
			"c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75", "09", "83", "2c",
			"1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84", "53", "d1", "00", "ed", "20",
			"fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf", "d0", "ef", "aa", "fb", "43", "4d", "33",
			"85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8", "51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc",
			"b6", "da", "21", "10", "ff", "f3", "d2", "cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e",
			"3d", "64", "5d", "19", "73", "60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de",
			"5e", "0b", "db", "e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4",
			"79", "e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08", "ba",
			"78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a", "70", "3e", "b5",
			"66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e", "e1", "f8", "98", "11", "69",
			"d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df", "8c", "a1", "89", "0d", "bf", "e6", "42",
			"68", "41", "99", "2d", "0f", "b0", "54", "bb", "16" };

	private static final String[] rcon = {"", "01000000","02000000","04000000","08000000","10000000","20000000","40000000","80000000","1B000000","36000000"};

	public static void main(String args[]) {
		String key =  "0f1571c947d9e8590cb7add6af7f6798";
		String[] w = new String[44];

		for (int i = 0; i < 4; i++) {
			w[i] = key.substring(i * 8, i * 8 + 8);
			System.out.println(w[i]);
		}

		for (int i = 4; i < 44; i++) {
			String temp = w[i - 1];
			BigInteger tempInteger;
			BigInteger wordInteger;
			BigInteger subInteger;

			if(i % 4 == 0) {
				String rotate = rotWord(temp);
				String sub = subWord(rotate);
				subInteger = new BigInteger(sub, 16);
				temp = subInteger.xor(new BigInteger(rcon[i / 4], 16)).toString(16);
			}

			// Set the BigInteger to hexadecimal value of i - 4
			wordInteger = new BigInteger(w[i - 4], 16);
			tempInteger = new BigInteger(temp, 16);
			// Set w[i] to the XOR between word and temp
			w[i] = wordInteger.xor(tempInteger).toString(16);
			System.out.println(w[i]);
		}
	}

	public static String rotWord(String temp) {
		String firstTwo = temp.substring(0, 2);
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < 6; i += 2) {
			stringBuilder.append(temp.substring(i + 2, i + 4));
		}

		return stringBuilder.toString() + firstTwo;
	}
	
	public static String subWord(String temp) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < 8; i += 2) {
			stringBuilder.append(sbox[Integer.parseInt(temp.substring(i, i + 2), 16)]);
		}

		return stringBuilder.toString();
	}
}
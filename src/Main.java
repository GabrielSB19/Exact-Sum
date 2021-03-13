import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public static void main (String [] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String amount;
		do {
			amount = br.readLine();
			int amountInt = Integer.parseInt(amount);
			String [] pBooks = new String[amountInt];
			String pBooksLine = br.readLine();
			pBooks = pBooksLine.split("\\ ");
			//System.out.println(Arrays.toString(pBooks));
			int [] pBooksInt = toInt(pBooks);
			//System.out.println(Arrays.toString(pBooksInt));
			String moneyLine = br.readLine();
			int money = Integer.parseInt(moneyLine);
			
			Arrays.sort(pBooksInt);
			bw.write(bestOption (pBooksInt, money));
			
			amount = br.readLine();
		} while(amount != null);
		
		br.close();
		bw.close();
	}
	
	public static int [] toInt(String[] pBooks) {
		int [] priceBook = new int [pBooks.length];
		for(int i = 0; i<pBooks.length; i++) {
			priceBook[i] = Integer.parseInt(pBooks[i]);
		}
 		return priceBook;
	}
	
	public static String bestOption (int [] pBooksInt, int money) {
		String msg = "";
		ArrayList<Integer> test = new ArrayList<Integer>();
		int [] minPrices = new int[2];
		int most = money;
		int pos = -1;
		int i = 0;
		int j = pBooksInt.length;
		for(int x = 0; x<pBooksInt.length; x++) {
			while (i<=j && pos<0) {
				int m = (i+j)/2;
				if(pBooksInt[m]+pBooksInt[x] == money) {
					test.add(pBooksInt[m]);
					test.add(pBooksInt[x]);
					pos = m;
				} else if (pBooksInt[m]+pBooksInt[x] < money) {
					i = m+1;
				} else {
					j = m-1;
				}
			}
			pos = -1;
			i = 0;
			j = pBooksInt.length;
		}
		
		for(int k = 0; k<test.size(); k = k+2) {
			if(Math.abs(test.get(k)-test.get(k+1)) < most) {
				most = test.get(k)-test.get(k+1);
				minPrices[1] = test.get(k);
				minPrices[0] = test.get(k+1);
			}
		}
		msg = "Peter should buy books whose prices are "+minPrices[0]+" y "+minPrices[1]+"\n";
		return msg;
	}
}


import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class dataRetrieval {

	public static void main(String[] args) throws IOException, ParseException {
		portfolio myPF = new portfolio();
		Scanner in = new Scanner(System.in);
		String stockSym = null;

		System.out.println(
				"Enter stock symbols for stocks in portfolio. Type 'END' when all stocks in your portfolio are added");

		for (int n = 0; n < 2; n++) {
			stockSym = in.nextLine().toUpperCase();
			security nextSec = new security(stockSym);
			myPF.addToPF(nextSec);
		}

		in.close();

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String
		 * test = "2015-10-19"; Calendar testg = Calendar.getInstance();
		 * testg.setTime(sdf.parse(test));
		 * 
		 * for (security security1 : myPF.getPF()) { if
		 * (security1.getSymbol().equals("AAPL")) {
		 * System.out.println(security1.getOpenPriceForDate(testg)); } }
		 */

		for (security sec : myPF.getPF()) {
			System.out.println("Symbol: " + sec.getSymbol() + "\n" + "Volatility: " + sec.getVolatility());
		}

	}

}

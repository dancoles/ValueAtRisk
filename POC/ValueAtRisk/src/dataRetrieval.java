
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class dataRetrieval {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String stockSym = null;
		ArrayList<String> portfolio = new ArrayList<String>();

		System.out.println(
				"Enter stock symbols for stocks in portfolio. Type 'END' when all stocks in your portfolio are added");

		for (int n = 0;n<2;n++) {

			stockSym = in.nextLine();
			portfolio.add(stockSym);
		}

		in.close();
		
		for (int i = 0; i < portfolio.size(); i++) {
			System.out.println(portfolio.get(i));
		}

		System.out.println("Downloading historical stock data...");
		DLData(portfolio);
		System.out.println("Data retrieval complete");

	}

	public static void DLData(ArrayList<String> portfolio) throws IOException {
		for (int i = 0; i < portfolio.size(); i++) {
			String retStr = ("http://ichart.finance.yahoo.com/table.csv?s=" + portfolio.get(i)
					+ "&d=10&e=6&f=2016&g=d&a=5&b=10&c=2015&ignore=.csv");

			URL url = null;
			try {
				url = new URL(retStr);
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(portfolio.get(i)+".csv");
				//FileDescriptor FD = fos.getFD();
				//FileInputStream fis = new FileInputStream(FD);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.flush();
				fos.close();
				
			} catch (MalformedURLException e) {
				System.out.println("No such URL, please check stock symbols");
			}

			
		}
	}
}

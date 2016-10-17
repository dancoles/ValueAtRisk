import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Utilities {

	public static HashMap<Calendar, prices> DLData(String Symbol) throws IOException {
		HashMap<Calendar, prices> temp = new HashMap<Calendar, prices>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		cal.roll(Calendar.MONTH, false);
		int prevMonth =cal.get(Calendar.MONTH);
		System.out.println("Retrieving data..");

		String retStr = ("http://ichart.finance.yahoo.com/table.csv?s=" + Symbol + "&d=" + month + "&e=" + day + "&f="
				+ year + "&g=d&a=" + prevMonth + "&b=" + day + "&c=" + (year) + "&ignore=.csv");

		URL url = null;
		System.out.println("Adding data to local resource..");
		try {
			File dat = new File(Symbol + ".csv");
			url = new URL(retStr);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(dat);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			try {
				// READ FROM FILE
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(dat));
				String line;
				br.readLine();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				while ((line = br.readLine()) != null) {

					// use comma as separator
					String[] data = line.split(",");
					Calendar tempC = Calendar.getInstance();
					tempC.setTime(sdf.parse(data[0]));
					// System.out.println(data[0]);
					temp.put(tempC, new prices((Double.parseDouble(data[1])), (Double.parseDouble(data[6]))));

				}
				// END
			} catch (IOException | ParseException e) {
				System.out.println("Could not load data");
			}

			fos.flush();
			fos.close();

			System.out.println("Data retrieval complete");

		} catch (FileNotFoundException e) {
			System.out.println("No such URL, please check stock symbols");

		}

		return temp;
	}

	public static double calcVolatility(HashMap<Calendar, prices> historicalPrices) {
		double sum = 0;
		for (prices K : historicalPrices.values()) {
			sum = sum + K.getClose();
		}
		double mean = sum / historicalPrices.size();

		double dev = 0;
		double totalDev = 0;
		for (prices K : historicalPrices.values()) {
			dev = K.getClose() - mean;
			totalDev = (dev * dev) + totalDev;
		}

		double square = totalDev / historicalPrices.size();
		double volatility = Math.sqrt(square);

		return volatility;
	}

}


import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class security {
	private String securitySymbol;
	private double volatility;
	private HashMap<Calendar, prices> historicalPrices;

	public security(String securitySymbol) throws IOException {
		this.securitySymbol = securitySymbol;
		this.historicalPrices = new HashMap<Calendar, prices>();
		loadData();
		calcVolatility();	
	}

	public String getSymbol() {
		return this.securitySymbol;
	}

	public double getVolatility() {
		return this.volatility;
	}

	public void calcVolatility() {
		this.volatility = Utilities.calcVolatility(this.historicalPrices);
	}

	public double getClosePriceForDate(Calendar date) {
		return this.historicalPrices.get(date).getClose();
	}

	public double getOpenPriceForDate(Calendar date) {
		return this.historicalPrices.get(date).getOpen();
	}

	protected void loadData() throws IOException {
		this.historicalPrices = Utilities.DLData(this.securitySymbol);

	}

}

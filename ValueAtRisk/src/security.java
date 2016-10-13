
import java.util.Calendar;
import java.util.HashMap;

public class security {
	String securitySymbol;
	float volatility;
	HashMap<Calendar, prices> historicalPrices;

	public security(String securitySymbol) {
		this.securitySymbol = securitySymbol;
		this.historicalPrices = new HashMap<Calendar, prices>();
		this.volatility = 0;
	}

	public String getSymbol() {
		return this.securitySymbol;
	}

	public float getVolatility() {
		return this.volatility;
	}

	public void calcVolatility() {
		this.volatility = 0;
	}
	
	public float getClosePriceForDate(Calendar date) {
		return this.historicalPrices.get(date).getClose();
	}
	
	public float getOpenPriceForDate(Calendar date) {
		return this.historicalPrices.get(date).getOpen();
	}
}
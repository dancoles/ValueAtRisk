import java.util.ArrayList;

public class portfolio {
	private ArrayList<security> PF;
	private double VaR;
	private double volatility;

	public portfolio() {
		PF = new ArrayList<security>();
		VaR = 0;
	}

	public ArrayList<security> getPF() {
		return this.PF;
	}

	public double getDailyVolatility() {
		double PFVolatility = 0;
		double totalSec = 0;
		for (security sec : PF) {
			totalSec = totalSec + sec.getQuantity();
		}
		
		for (security sec : PF) {
			PFVolatility = PFVolatility + (sec.getDailyVolatility() * (sec.getQuantity() / totalSec));
		}

		this.volatility = PFVolatility;
		return this.volatility;
	}

	public double getVar() {
		return this.VaR;
	}

	public boolean addToPF(security mysecurity) {
		return this.PF.add(mysecurity); // returns true if security successfully
										// added
										// // to PF
	}

	public void calcVaR(int confLevel, int period) {
		this.VaR = this.PF.get(1).getDailyVolatility();
	}
}

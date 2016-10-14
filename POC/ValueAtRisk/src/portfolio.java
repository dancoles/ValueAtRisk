import java.util.ArrayList;

public class portfolio {
	ArrayList<security> PF;
	float VaR;

	public portfolio() {
		PF = new ArrayList<security>();
		VaR = 0;
	}

	public ArrayList<security> getPF() {
		return this.PF;
	}

	public float getVar() {
		return this.VaR;
	}

	public boolean addToPF(security mysecurity) {
		return this.PF.add(mysecurity); // returns true if security successfully added
										// // to PF
	}

	public void calcVaR(int confLevel, int period) {
		this.VaR = this.PF.get(1).getVolatility();
	}
}
package problems;

import java.util.ArrayList;

public class P1 extends Problem {

	public P1() {
		super();

		ArrayList<Double> maxValues = new ArrayList<>();
		maxValues.add(2.0);
		maxValues.add(2.0);
		this.setMaxValues(maxValues);

		ArrayList<Double> minValues = new ArrayList<>();
		minValues.add(-2.0);
		minValues.add(-2.0);
		this.setMinValues(minValues);
	}

	@Override
	public double Eval(ArrayList<Double> paramVals) {
		return Math.pow(Math.E, -(Math.pow(paramVals.get(0), 2.0) + Math.pow(paramVals.get(1), 2.0)));
	}

	@Override
	public int getDimensions() {
		return 2;
	}

    @Override
    public ArrayList<Double> getMaximum() {
        ArrayList<Double> maximum = new ArrayList<>();
        maximum.add(0.0);
        maximum.add(0.0);
        
        return maximum;
    }
    
    @Override
    public String toString() {
        return "P1";
    }

}

package problems;

import java.util.ArrayList;

public class RevAckley extends Problem {

	public RevAckley() {
		super();

		ArrayList<Double> maxValues = new ArrayList<>();
		maxValues.add(5.0);
		maxValues.add(5.0);
		this.setMaxValues(maxValues);

		ArrayList<Double> minValues = new ArrayList<>();
		minValues.add(-5.0);
		minValues.add(-5.0);
		this.setMinValues(minValues);
	}

	@Override
	public double Eval(ArrayList<Double> paramVals) {

		double sum1 = 0.0;
		double sum2 = 0.0;

		for (int i = 0; i < getDimensions(); i++) {
			sum1 += Math.pow(paramVals.get(i), 2);
			sum2 += (Math.cos(2 * Math.PI * paramVals.get(i)));
		}

		return -(-20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / (getDimensions()))) + 20 - Math.exp(sum2 / (getDimensions()))
				+ Math.exp(1.0));

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
        return "RevAckley";
    }

}

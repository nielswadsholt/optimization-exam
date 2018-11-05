package ea;

import java.util.ArrayList;
import java.util.Random;
import problems.Problem;

public class Individual implements Comparable<Individual>{
    
    private final Problem problem;
	private final int geneLength;
	private final ArrayList<Double> genes;
	private double fitness = Double.MIN_VALUE;
    
	public Individual(Problem problem) {
        this.problem = problem;
        geneLength = problem.getDimensions();        
		genes = generateRandomGenes();
	}
    
	protected double getSingleGene(int index) {
		return genes.get(index);
	}
    
	protected void setSingleGene(int index, double value) {
		genes.set(index, value);
        cropGene(index);
		fitness = Double.MIN_VALUE;
	}
    
	public double getFitness() {
		if (fitness == Double.MIN_VALUE) {
			fitness = EnhancedEA.getFitness(this, problem);
		}
		return fitness;
	}
    
	@Override
	public String toString() {
		return genes.toString();
	}
    
	public int getDefaultGeneLength() {
		return geneLength;
	}
    
    public ArrayList<Double> getGenes() {
        return genes;
    }
    
    private ArrayList<Double> generateRandomGenes() {
        
        ArrayList<Double> randomGenes = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < geneLength; i++) {
            randomGenes.add(problem.getMinValues().get(i) + random.nextDouble() * 
                (problem.getMaxValues().get(i) - problem.getMinValues().get(i)));
        }
        
        return randomGenes;
    }
    
    public Problem getProblem(){
        return problem;
    }
    
    private void cropGene(int i) {
        double minValue = problem.getMinValues().get(i);
        double maxValue = problem.getMaxValues().get(i);
        
        if (genes.get(i) < minValue) {
            genes.set(i, minValue);
        }
        else if (genes.get(i) > maxValue) {
            genes.set(i, maxValue);
        }
    }
    
    @Override
    public int compareTo(Individual o) {
        if (fitness > o.fitness) {
            return 1;
        }
        else if (fitness < o.fitness) {
            return -1;
        }
        
        return 0;
    }
    
}
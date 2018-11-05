package ea;

import java.util.ArrayList;
import java.util.List;
import problems.Problem;

public class Population {

	private final List<Individual> individuals;
    private final Problem problem;

	public Population(int size, boolean createNew, Problem problem) {
		individuals = new ArrayList<>();
        this.problem = problem;
        
		if (createNew) {
			createNewPopulation(size);
		}
	}

	public Individual getIndividual(int index) {
		return individuals.get(index);
	}
    
    protected void add(int index, Individual individual) {
        individuals.add(index, individual);
    }

	protected Individual getFittest() {
		Individual fittest = individuals.get(0);
		for (int i = 0; i < individuals.size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	private void createNewPopulation(int size) {
		for (int i = 0; i < size; i++) {
			Individual newIndividual = new Individual(problem);
			individuals.add(i, newIndividual);
		}
	}

	protected List<Individual> getIndividuals() {
		return individuals;
	}
    
    public Problem getProblem(){
        return problem;
    }
    
    @Override
    public String toString() {
        return individuals.toString();
    }
}

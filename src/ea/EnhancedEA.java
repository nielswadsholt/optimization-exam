package ea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import problems.Problem;

public class EnhancedEA {

	private final double crossoverRate;
	private final double mutationRate;
    private final int generations;
	private final int elite;
	private final int tournamentSize = 5;
    private static int evaluations;
    
    public EnhancedEA(int generations, double crossoverRate, double  mutationRate, int elite){
        this.generations = generations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.elite = elite;
        evaluations = 0;
    }
    
    public EnhancedEA(int generations, double crossoverRate, double  mutationRate){
        this(generations, crossoverRate, mutationRate, 5);
    }
    
	public Individual runAlgorithm(int populationSize, Problem problem) {
		Population myPop = new Population(populationSize, true, problem);
//        System.out.println("Initial population: " + myPop);
		int generationCount = 1;
        
		for (int i = 0; i < generations; i++) {
            Individual fittest = myPop.getFittest();
			System.out.println(
                "Generation " + generationCount +
                ":\t(" + fittest.getGenes().get(0) + ", " +
                fittest.getGenes().get(1) + ", " +
                fittest.getFitness() + ")");
			myPop = evolvePopulation(myPop);
			generationCount++;
		}
        
        return myPop.getFittest();
	}

	private Population evolvePopulation(Population pop) {
        List<Individual> individuals = pop.getIndividuals();
		Population newPopulation = new Population(individuals.size(), false, pop.getProblem());
        Collections.sort(individuals, Comparator.reverseOrder());
//        System.out.println(individuals);
//        individuals.forEach(indiv -> System.out.print(indiv.getFitness() + " "));
//        System.out.println();

		for (int i = 0; i < elite; i++) {
            Individual eliteIndividual = individuals.get(i);
			newPopulation.add(i, eliteIndividual);
		}

		for (int i = elite; i < individuals.size(); i++) {
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			Individual newIndiv = crossover(indiv1, indiv2);
            mutate(newIndiv);
			newPopulation.add(i, newIndiv);
		}

		return newPopulation;
	}

	private Individual crossover(Individual indiv1, Individual indiv2) {
		Individual newIndiv = new Individual(indiv1.getProblem());
        
		for (int i = 0; i < newIndiv.getDefaultGeneLength(); i++) {
			if (Math.random() <= crossoverRate) {
				newIndiv.setSingleGene(i, (indiv1.getSingleGene(i) + indiv2.getSingleGene(i)) / 2);
			} else if (Math.random() < 0.5) {
				newIndiv.setSingleGene(i, indiv1.getSingleGene(i));
			} else {
				newIndiv.setSingleGene(i, indiv2.getSingleGene(i));
			}
		}
		return newIndiv;
	}

	private void mutate(Individual indiv) {
        Problem problem = indiv.getProblem();
        ArrayList<Double> maxValues = problem.getMaxValues();
        ArrayList<Double> minValues = problem.getMinValues();
        Random r = new Random();
        
		for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
			if (Math.random() <= mutationRate) {
                double range = Math.abs(maxValues.get(i) - minValues.get(i));
                double gene = indiv.getSingleGene(i) + 0.025 * range * r.nextGaussian();
                indiv.setSingleGene(i, gene);
			}
		}
	}

	private Individual tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false, pop.getProblem());
        
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.getIndividuals().size());
			tournament.add(i, pop.getIndividual(randomId));
		}
        
		Individual fittest = tournament.getFittest();
		return fittest;
	}

	protected static double getFitness(Individual individual, Problem problem) {
		ArrayList<Double> genes = individual.getGenes();
        evaluations++;
        
		return problem.Eval(genes);
	}
    
    protected int getEvaluations() {
        return evaluations;
    }

}
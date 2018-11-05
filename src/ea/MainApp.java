package ea;

import problems.Problem;
//import tests.GATests;

public class MainApp {
    
    private static void runExperiment(int iterations, int generations, int populationSize,
        int elite, double  mutationRate, double crossoverRate, Problem problem) {
        
        double avgDist = 0.0;
        
        System.out.println("======================== Enhanced Evolutionary Algorithm =======================");
        System.out.println("Problem: " + problem);
        System.out.println("Generations: " + generations);
        System.out.println("Population size: " + populationSize);
        System.out.println("Elite size: " + elite);
        System.out.println("Mutation rate: " + mutationRate);
        System.out.println("Crossover rate: " + crossoverRate);
        System.out.println("Iterations: " + iterations);
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < iterations; i++) {
            System.out.println("\n---------------------------------- Iteration " + (i + 1) + " ---------------------------------");

            EnhancedEA ga = new EnhancedEA(generations, crossoverRate, mutationRate, elite);
            Individual best = ga.runAlgorithm(populationSize, problem);
            double dist = Util.getDistance(best.getGenes(), problem.getMaximum());
            avgDist += dist;

            System.out.println("\nBest:\t(" + 
                    best.getGenes().get(0) + ", " +
                    best.getGenes().get(1) + ", " +
                    best.getFitness() + ")");
            System.out.println("Distance to the global maximum: " + dist);
    //        System.out.println("Max number of evaluations: " + populationSize * generations);
            System.out.println("Number of evaluations: " + ga.getEvaluations());
            System.out.println();
        }
        
        long endTime = System.nanoTime();
        
        avgDist /= iterations;
        
        System.out.println("The average distance to the global maximum over " + iterations + " iterations was: " + avgDist);
        System.out.println("The average running time meassured over " + iterations + " iterations was: " + (endTime - startTime) / 1000000 + " milliseconds");
    }

	public static void main(String[] args) {

        int iterations = 20;
        int generations = 100;
        int populationSize = 100;
        int elite = 5;
        double  mutationRate = 0.025;
        double crossoverRate = 1.0;
        
//        Problem problem = new problems.P1();
//        Problem problem = new problems.P2();
//        Problem problem = new problems.RevAckley();
//        Problem problem = new problems.RevRosenbrock();
        Problem problem = new problems.RevSphere();

        runExperiment(iterations, generations, populationSize,
            elite, mutationRate, crossoverRate, problem);
        
//        GATests.testGetDistance();
	}
}

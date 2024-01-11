import java.util.*;



public class GeneticTSP{


    private ArrayList<entityTSP> gen = new ArrayList<>(), newGen = new ArrayList<>();
    private int genSize, V, noChanges = 0;
    private long genSum = 0;
    private Random random = new Random();
    private entityTSP best;
    private Graph G;


    public GeneticTSP(int V, Graph G){

        this.V = V;
        genSize = V*V;
        this.G = G;
        for(int i = 0; i<genSize; i++) gen.add(new entityTSP(V, G));
        best = new entityTSP(V, G);

    }




    private entityTSP cross(entityTSP f1, entityTSP f2){

        return new entityTSP(f1, f2, V, G);

    }

    private double fitness(entityTSP i){

        return genSum/i.fitness;

    }

    private void updateBest(){

        entityTSP curr = best;

        for(entityTSP i: gen) if(i.fitness < curr.fitness) curr = i;

        if(best.equals(curr)) noChanges++;

        best = curr;

    }


    private entityTSP findTour(){

        if(noChanges > V*V) return best;

        genSum = 0;
        for(entityTSP i: gen) genSum += i.fitness;
        updateBest();

        newGen.clear(); newGen.add(best);

        for(int i = 1; i<genSize; i++){

            double target1 = random.nextDouble() * genSum, target2 = random.nextDouble() * genSum;
            double cumulativeFit = 0.0;
            entityTSP f1 = null, f2 = null;

            int j = 0;
            while(cumulativeFit < target1 && j < gen.size()) cumulativeFit += fitness(gen.get(j++));
            f1 = gen.get(j-1);

            cumulativeFit = j = 0;
            while(cumulativeFit < target2 && j < gen.size()) cumulativeFit += fitness(gen.get(j++));
            f2 = gen.get(j-1);

            newGen.add(cross(f1, f2));

        }gen = new ArrayList<>(newGen);
        return findTour();

    }


    public void showGeneticTSP(){

        entityTSP bst = findTour();
        System.out.println(bst.fitness);
        for(int i: bst.tour) System.out.print(i+1 + " ");
        System.out.println(bst.tour.get(0) + 1);

    }




}
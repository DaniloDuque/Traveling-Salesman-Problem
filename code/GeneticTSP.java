import java.util.*;



public class GeneticTSP{


    private entityTSP gen[];
    private PriorityQueue<entityTSP> heap = new PriorityQueue<>();
    private int genSize, V, noChanges = 0;
    private double genSum = 0;
    private Random random = new Random();
    private entityTSP best;
    private Graph G;



    public GeneticTSP(int V, Graph G){

        this.V = V;
        genSize = V<<2;
        gen = new entityTSP[genSize];
        this.G = G;
        for(int i = 0; i<genSize; i++){
            gen[i] = new entityTSP(V, G);
            heap.add(gen[i]);
        }best = heap.peek();

    }



    

    private double fitness(entityTSP i){
        return genSum/i.fitness;
    }

    private void updateBest(){

        genSum = 0.0;
        for(entityTSP i: gen) genSum += fitness(i);
        entityTSP curr = gen[0];
        for(entityTSP i: gen) if(i.fitness < curr.fitness) curr = i;
        noChanges = (best.equals(curr))? noChanges + 1: 0;
        best = curr;

    }


    private entityTSP findTour(){

        while(noChanges < V*V){
                        
            for(int i = 0; i<genSize; i++){

                double target1 = random.nextDouble() * genSum, target2 = random.nextDouble() * genSum;
                double cumulativeFit = 0.0;
                entityTSP f1 = null, f2 = null;

                int j = 0;
                while(cumulativeFit < target1 && j < genSize) cumulativeFit += fitness(gen[j++]);
                f1 = gen[Math.max(j-1, 0)];

                cumulativeFit = j = 0;
                while(cumulativeFit < target2 && j < genSize) cumulativeFit += fitness(gen[j++]);
                f2 = gen[Math.max(j-1, 0)];

                heap.add(gen[i]);
                heap.add(new entityTSP(f1, f2, V, G));

            }for(int i = 0; i<genSize; i++) gen[i] = heap.poll();
            updateBest();
            heap.clear();

        }return best;

    }


    public void showGeneticTSP(){

        entityTSP bst = findTour();
        System.out.println("cost: " + bst.fitness);
        for(int i: bst.tour) System.out.print(i+1 + " ");
        System.out.println(bst.tour.get(0) + 1);

    }




}
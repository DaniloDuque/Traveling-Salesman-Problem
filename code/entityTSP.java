import java.util.*;





public class entityTSP{

    public ArrayList<Integer> tour = new ArrayList<>();
    private int size;
    public long fitness = 0;
    private Random random = new Random();


    public entityTSP(int size, Graph G){

        this.size = size;
        for(int i = 0; i<size; i++) tour.add(i);
        Collections.shuffle(tour);
        fitness(G);

    }


    public entityTSP(entityTSP f1, entityTSP f2, int size, Graph G){

        this.size = size;

        int crossIndex = random.nextInt(size);
        int msk = 0;

        for(int i = 0; i<crossIndex; msk = SET(msk, f1.tour.get(i++))) tour.add(f1.tour.get(i));

        for(int i = 0; i<size; i++) if(!TEST(msk, f2.tour.get(i))) tour.add(f2.tour.get(i));

        if(random.nextInt(100) < 5) mutation();

        fitness(G);

    }



    private boolean TEST(int msk, int i){
        return (msk&(1<<i)) != 0;
    }

    private int SET(int msk, int i){
        return msk|(1<<i);
    }



    private void fitness(Graph G){

        for(int i = 0; i<size-1; i++) fitness += G.getCost(tour.get(i), tour.get(i+1));
        fitness += G.getCost(tour.get(size-1), tour.get(0));

    }

    private void mutation(){

        Collections.swap(tour, random.nextInt(size), random.nextInt(size));

    }

    

    @Override
    public boolean equals(Object obj){

        entityTSP curr = (entityTSP) obj;

        for(int i = 0; i<size; i++) if(tour.get(i) != curr.tour.get(i)) return false;
    
        return true;


    }

    public void showTour(){

        for(int i: tour) System.out.println(i + " ");
        System.out.println("fitness: " + fitness);

    }










}
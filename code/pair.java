import java.util.*;


public class pair<t1, t2>{

    public t1 first;
    public t2 second;

    public pair(t1 x, t2 y){
        first = x;
        second = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }


    public boolean equals(pair<Integer, Integer> obj) {

        return obj.first == first && obj.second == second;

    }
    

}

package sample;

import java.util.Map;
import java.util.TreeMap;

public interface Signal {

    int N = 2048;

    double getSignalValue(int n);

    default Map<Integer,Double> getSignal(){
        Map<Integer,Double> result = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            result.put(i,getSignalValue(i));
        }
        return result;
    }

}

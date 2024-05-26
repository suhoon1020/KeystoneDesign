package ItemObserver;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements ISubject{
    List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);

    }

    @Override
    public void notifyObserver(IObserver o) {
        for(IObserver o : observers){
            o.update();
        }
    }
}

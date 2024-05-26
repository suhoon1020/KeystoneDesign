package ItemObserver;

import ItemManager.Item;

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
    public void notifyObserver() {
        for(IObserver o : observers){
            o.update(this);
        }
    }

    void itemInfoChanged(){

    }
}

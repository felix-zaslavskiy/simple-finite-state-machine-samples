package demo;

import com.hexadevlabs.simplefsm.NamedEntity;

public class MyCustomEvent implements NamedEntity {
    private final String name;

    public MyCustomEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
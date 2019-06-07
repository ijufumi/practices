package jp.co.everforth.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Leaf {
    private String name;
    private List<String> children;

    public Leaf(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public boolean addChild(String child) {
        if (this.children.contains(child)) {
            return false;
        }
        this.children.add(child);

        return true;
    }

    public String[] getChildren() {
        return this.children.toArray(new String[0]);
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        return this.hashCode() == obj.hashCode();
    }

    public String toString() {
        return String.format("name:%s,children:%s", name, Arrays.asList(getChildren()));
    }
}

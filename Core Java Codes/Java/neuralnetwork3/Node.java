/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Sinthu
 */
public abstract class Node  implements Serializable{
protected String name;
protected double input;
protected double output;
protected int Id;
protected Vector<Node> child;
protected Vector<Node> parent;

    public Node(String name, int id) {
        this.name = name;
        this.Id = id;
        child = new Vector<Node>();
        parent = new Vector<Node>();
    }

    public Node(int id){
        this.Id = id;
        child = new Vector<Node>();
        parent = new Vector<Node>();
    }

public abstract double process(double input);

protected int getId(){
    return Id;
}

protected double getOutput(){
    return output;
}

protected double getInput(){
    return input;
}

protected void addChild(Node node){
    this.child.add(node);
}

protected void addParent(Node node){
    this.parent.add(node);
}

    public Vector<Node> getChild() {
        return child;
    }

    public Vector<Node> getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }


}

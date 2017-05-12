/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Sinthu
 */
public class Connection  implements Serializable{
private volatile double[] weight;
private volatile Node[] nodes;
private volatile double[] newWeight;

//public Connection(Node parent, Node child){
//    nodes = new Node[2];
//    weight = new double[1];
//   // newWeight = new double[1];
//    nodes[0] = parent;
//    nodes[1] = child;
//    this.generateRandom(1);
//   // weight[0] = 1.0;
//    child.addParent(parent);
//     parent.addChild(child);
//
//}
//public Connection(Node parent1,Node parent2, Node child){
//    nodes = new Node[3];
//     weight = new double[2];
//   //  newWeight = new double[2];
//    nodes[0] = parent1;
//    nodes[1] = parent2;
//    nodes[2] = child;
//    this.generateRandom(2);
//   // weight[0] = 1.0;
//    //weight[1] = 1.0;
//    child.addParent(parent1);
//    child.addParent(parent2);
//     parent1.addChild(child);
//     parent2.addChild(child);
//}
//public Connection(Node parent1,Node parent2,Node parent3, Node child){
//    nodes = new Node[4];
//     weight = new double[3];
//   //  newWeight = new double[3];
//    nodes[0] = parent1;
//    nodes[1] = parent2;
//    nodes[2] = parent3;
//    nodes[3] = child;
//    child.addParent(parent1);
//    child.addParent(parent2);
//     child.addParent(parent3);
//     parent1.addChild(child);
//     parent2.addChild(child);
//     parent3.addChild(child);
//     this.generateRandom(3);
//     //weight[0] = 1.0;
//   // weight[1] = 1.0;
//   // weight[2] = 1.0;
//
//
//}
//public Connection(Node parent1,Node parent2,Node parent3,Node parent4, Node child){
//    nodes = new Node[5];
//    weight = new double[4];
//   // newWeight = new double[4];
//    nodes[0] = parent1;
//    nodes[1] = parent2;
//    nodes[2] = parent3;
//    nodes[3] = parent4;
//    nodes[4] = child;
//    this.generateRandom(4);
//   /* weight[0] = 1.0;
//    weight[1] = 1.0;
//    weight[2] = 1.0;
//    weight[3] = 1.0;*/
//    child.addParent(parent1);
//    child.addParent(parent2);
//     child.addParent(parent3);
//     child.addParent(parent4);
//     parent1.addChild(child);
//     parent2.addChild(child);
//     parent3.addChild(child);
//     parent4.addChild(child);
//}

public Connection(Vector<Node> parents, Node child){
     nodes = new Node[parents.size()+1];
    weight = new double[parents.size()];
    newWeight = new double[parents.size()];
    for(int i=0; i<parents.size(); i++){
        nodes[i] = parents.get(i);
        child.addParent(parents.get(i));
        parents.get(i).addChild(child);
    }
    nodes[parents.size()] = child;
    this.generateRandom(weight.length);
}

public Node getChildNode(){
    return nodes[nodes.length-1];
}

public int NoOfParents(){
    return nodes.length-1;
}

public void process(){
    double temp = 0;
    double sum = 0.0;
    for(int i=0; i< this.NoOfParents(); i++){
        temp = nodes[i].output * weight[i];
        sum = sum + temp;
    }
    this.getChildNode().process(sum);
}

public void updateWeight(double surplus, int parentId){
    for(int i=0; i< nodes.length-1; i++){
        if(nodes[i].getId() == parentId){
            newWeight[i] = weight[i] + surplus;
           // System.out.println("New weight calculated.");
            break;
        }
      //  System.out.println("New weight: ");
        //this.printArray(newWeight);
        //System.out.println("last weight:");
        //this.printArray(weight);
    }
}

private void printArray(double[] a){
    for(int i=0; i<a.length; i++){
        System.out.println(a[i]);
    }
}
    public Node[] getNodes() {
        return nodes;
    }

    public double[] getWeight() {
        return weight;
    }

    public double getWeightForParent(Node parent){
        double w = -1;
        for(int i=0; i<nodes.length-1; i++){
            if(nodes[i].getId() == parent.getId()){
                w = weight[i];
                break;
            }
        }
        return w;
    }


    public void loadNewWeights(){
        for(int i=0; i<this.newWeight.length; i++){
         //    System.out.println("New weight:" + newWeight[i]);
           //   System.out.println("current weight:" + weight[i]);
            weight[i] = newWeight[i];
            //System.out.println("New weight updated with current weight");
        }

   }
    
    public void generateRandom(int number){
         Random randomGenerator = new Random();
         double randomDouble=0;
for (int idx = 1; idx <= number; idx++){
       randomDouble= randomGenerator.nextDouble();
weight[idx-1]=randomDouble;
    }


    }
}

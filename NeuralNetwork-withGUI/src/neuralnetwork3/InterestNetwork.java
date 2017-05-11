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
public class InterestNetwork implements Serializable{
private String name;
private Vector<InputNode> inputLayer;
private Vector<OutputNode> outputLayer;
private Vector<HiddenNode> hiddenLayer;
private Vector<Connection> input_hid;
private Vector<Connection> hid_output;
private double[] threshold;


public InterestNetwork(String nameOfNet, String[] outputNames, int NoOfHiddenNodes){
    this.name = nameOfNet;
    this.inputLayer = new Vector<InputNode>();
  this.outputLayer = new Vector<OutputNode>();
  this.hiddenLayer = new Vector<HiddenNode>();
    this.input_hid = new Vector<Connection>();
    this.hid_output = new Vector<Connection>();
    this.createInputLayer();
    this.createHiddenLayer(NoOfHiddenNodes);
    this.createOutputLayer(outputNames);
    this.createHiddenConnection();
    this.createOutputConnection();

}

    public InterestNetwork() {
        
    }


private void createInputLayer(){
    InputNode age = new InputNode("Age",1);
    InputNode nationality = new InputNode("Nationality",2);
    InputNode gender = new InputNode("Gender",3);
    InputNode profession = new InputNode("Profession",4);
    BiasNode bias = new BiasNode("Bias", 5);
    this.inputLayer.add(age);
    this.inputLayer.add(nationality);
    this.inputLayer.add(gender);
    this.inputLayer.add(profession);
    this.inputLayer.add(bias);
}

private void createOutputLayer(String[] outputNames){
    for(int i=0; i<outputNames.length; i++){
        this.outputLayer.add(new OutputNode(outputNames[i], i+1));
    }
}

private void createHiddenLayer(int NoOfHiddenNodes){
    for(int i=0; i<NoOfHiddenNodes; i++){
        this.hiddenLayer.add(new HiddenNode(i+1));
    }
}
private void createHiddenConnection(){
    Vector<Node> temp = new Vector<Node>();
    for(int i=0; i<this.inputLayer.size(); i++){
        temp.add(inputLayer.get(i));
    }

    for(int i=0; i<this.hiddenLayer.size(); i++){
        this.input_hid.add(new Connection(temp, hiddenLayer.get(i)));
    }
}

private void createOutputConnection(){
    Vector<Node> temp = new Vector<Node>();
    for(int i=0; i<this.hiddenLayer.size(); i++){
        temp.add(this.hiddenLayer.get(i));
    }
    for(int i=0; i<this.outputLayer.size(); i++){

        this.hid_output.add(new Connection(temp, this.outputLayer.get(i)));
    }
}

public Node getNodeInputLayer(int id){
    for(int i=0; i<this.inputLayer.size(); i++){
        if(this.inputLayer.get(i).getId() == id){
            return this.inputLayer.get(i);
        }
    }
    return null;
}

public Node getNodeHiddenLayer(int id){
    for(int i=0; i<this.hiddenLayer.size(); i++){
        if(this.hiddenLayer.get(i).getId() == id){
            return this.hiddenLayer.get(i);
        }
    }
    return null;
}



public OutputNode getNodeOutputLayer(int id){
    for(int i=0; i<this.outputLayer.size(); i++){
        if(this.outputLayer.get(i).getId() == id){
            return this.outputLayer.get(i);
        }
    }
    return null;
}

public Connection getConnectionOfOuputLayer(int childNodeId){
    for(int i=0; i<this.hid_output.size(); i++){
        if(this.hid_output.get(i).getChildNode().getId() == childNodeId){
            return hid_output.get(i);
        }
    }
    return null;
}

public Connection getConnectionOfHiddenLayer(int childNodeId){
    for(int i=0; i<this.input_hid.size(); i++){
        if(this.input_hid.get(i).getChildNode().getId() == childNodeId){
            return input_hid.get(i);
        }
    }
    return null;
}



//public void changeOutputNodeName(String oldName, String newName){
//    for(int i=0; i<this.outputLayer.size(); i++){
//        if(this.outputLayer.get(i).getName().equals(oldName)){
//            this.outputLayer.get(i).name = newName;
//            break;
//        }
//    }
//}

public void process(double age, double nat, double gender, double prof){
    this.getNodeInputLayer(1).process(age);
    this.getNodeInputLayer(2).process(nat);
     this.getNodeInputLayer(3).process(gender);
      this.getNodeInputLayer(4).process(prof);
    //  this.getNodeInputLayer(5).process(1);

    for(int i=0; i<this.input_hid.size(); i++){
        this.input_hid.get(i).process();
        //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
      }

      for(int i=0; i<this.hid_output.size(); i++){
        this.hid_output.get(i).process();
        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      }

}

public double[] getOutput(){
    double[] out = new double[outputLayer.size()];
    for(int i=0; i<outputLayer.size(); i++){
        OutputNode node = outputLayer.get(i);
        out[node.Id-1] = node.getOutput();
    }
    return out;
}

    public Vector<Connection> getHid_output() {
        return hid_output;
    }

    public Vector<HiddenNode> getHiddenLayer() {
        return hiddenLayer;
    }

    public Vector<Connection> getInput_hid() {
        return input_hid;
    }

    public double[] getThreshold() {
        return threshold;
    }


    public Vector<InputNode> getInputLayer() {
        return inputLayer;
    }


    public Vector<OutputNode> getOutputLayer() {
        return outputLayer;
    }

    public void setThreshold(double[] threshold){
        this.threshold = threshold;
    }
    public Vector<String> getInterest(){
        Vector<String> interest = new Vector<String>();
        int j=0;
        OutputNode interestNode = null;
        for(int i=0; i<outputLayer.size(); i++){
        OutputNode node = outputLayer.get(i);
        if((node.getOutput()> this.threshold[j]) & (node.getOutput()<this.threshold[j+1])){
            if(interestNode != null){
//               if(interestNode.getOutput() <= node.getOutput()){
//                   interest.remove(0);
//                   interest.add(node.getName());
//                   interestNode = node;
//               }
//            }
            if(interestNode.getName().equals(node.getName())){
                    interest.remove(0);
                   interest.add(node.getName());
                   interestNode = node;
                }
            }
            else{
                interest.add(node.getName());
                interestNode = node;
            }

        }
        j = j+2;
    }

        return interest;
    }



    public String getName() {
        return name;
    }

    public void setHid_output(Vector<Connection> hid_output) {
        this.hid_output = hid_output;
    }

    public void setHiddenLayer(Vector<HiddenNode> hiddenLayer) {
        this.hiddenLayer = hiddenLayer;
    }

    public void setInputLayer(Vector<InputNode> inputLayer) {
        this.inputLayer = inputLayer;
    }

    public void setInput_hid(Vector<Connection> input_hid) {
        this.input_hid = input_hid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOutputLayer(Vector<OutputNode> outputLayer) {
        this.outputLayer = outputLayer;
    }


}

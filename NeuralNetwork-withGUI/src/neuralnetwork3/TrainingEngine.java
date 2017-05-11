/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class TrainingEngine {
private InterestNetwork network;
private Vector<TrainingData> data;
private boolean success;
private double[] error;
private double learningrate_op;
//private double learningrate_hiddden;
//private double[] g;
private double err;

public TrainingEngine(InterestNetwork network){
    this.network = network;
    this.data = new Vector<TrainingData>();
    error = new double[network.getOutputLayer().size()];
    //g = new double[network.getHiddenLayer2().size()];
    success=false;
    this.learningrate_op = 0.25;
    //this.learningrate_hiddden = 0.15;
}


public void addTrainingData(TrainingData tdata){
    this.data.add(tdata);
}

//public void train(){
//    for(int i=0; i<data.size(); i++){
//        double[] inputData = data.get(i).getInput();
//        network.process(inputData[0], inputData[1], inputData[2], inputData[3]);
//        while(getMSE(data.get(i).getOutput())>=0 & getMSE(data.get(i).getOutput())>0.000001){
//            //do back propogation
//            System.out.println(getMSE(data.get(i).getOutput()));
//        this.backPropagation(data.get(i).getOutput());
//        network.process(inputData[0], inputData[1], inputData[2], inputData[3]);
//       System.out.println("training....");
//            if(getMSE(data.get(i).getOutput())<0){
//                success=false;
//                break;
//            }
//            else{
//                success= true;
//            }
//
//        }
//    }
//}

public void train(){
    boolean[] suc = new boolean[data.size()];
   for(int x=0; x<suc.length; x++){
       suc[x] = false;
   }
    while(!this.isTrained(suc)){
   System.out.println("****************************************************************************************************************");
    for(int i=0; i<data.size(); i++){
        double[] inputData = data.get(i).getInput();
        network.process(inputData[0], inputData[1], inputData[2], inputData[3]);
       // while(getMSE(data.get(i).getOutput())>=0 & getMSE(data.get(i).getOutput())> 0.01){
            //do back propogation
        if(getMSE(data.get(i).getOutput()) < 0.05){
            suc[i] = true;
        }
        else{
            suc[i] = false;
            System.out.println(getMSE(data.get(i).getOutput()));
        }
           // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            
           //  this.updateOutputLayer(data.get(i).getOutput());
        this.backPropagation(data.get(i).getOutput());
      //  network.process(inputData[0], inputData[1], inputData[2], inputData[3]);

}
        //}
    }
}


private boolean isTrained(boolean[] suc){
    for(int i=0; i<suc.length; i++){
        if(!suc[i]){
            return false;
        }
    }
    return true;
}
private double getMSE(double[] outputData){
    err= 0.0;
    double temp = 0.0;
   
    if(outputData.length == network.getOutput().length){
        for(int i=0; i<outputData.length; i++){
            temp=  (outputData[i] - network.getOutput()[i]) * (outputData[i] - network.getOutput()[i]);
            err= err + temp;
        }
        err = err/outputData.length;
        return err;
    }
    else{
        return -1.0;
    }
}

private void backPropagation(double[] desiredOP){
//   System.out.println("back propoagation...");
   this.updateOutputLayer(desiredOP);
   //this.updateHiddenLayer2();
   this.updateHiddenLayer();
   this.loadNewWeights();
   //hidden back prop....
}

private void updateOutputLayer(double[] desOP){
  //  System.out.println(Thread.activeCount());
    for(int i=0;i<error.length; i++){
        error[i] = network.getOutput()[i] * (1-network.getOutput()[i]) * (desOP[i] - network.getOutput()[i]);

        double delta_theta = this.learningrate_op * error[i];
        Connection con = network.getConnectionOfOuputLayer(i+1);
        for(int j=0; j<con.NoOfParents(); j++){
            // double delta_weight = delta_theta * network.getNodeOutputLayer(i).getInput();
           double delta_weight = delta_theta * con.getNodes()[j].getOutput();
           con.updateWeight(delta_weight, con.getNodes()[j].getId());
           //System.out.println("ffff");
        }
    }
}

//private void updateHiddenLayer2(){
//    for(int i=0; i<network.getHiddenLayer2().size(); i++){
//        HiddenNode par = network.getHiddenLayer2().get(i);
//        Vector<Node> childs = par.getChild();
//        double temp_g = 0.0;
//        for(int j=0; j<childs.size(); j++){
//            Connection con = network.getConnectionOfOuputLayer(childs.get(j).getId());
//            temp_g = temp_g + (con.getWeightForParent(par) * error[childs.get(j).getId()-1]);
//        }
//        g[par.getId()-1] = temp_g;
//        double e = par.getOutput() * (1 - par.getOutput()) * temp_g;
//        double delta_theta = this.learningrate_op * e;
//        Connection con = network.getConnectionOfHiddenLayer2(par.getId());
//        double delta_weight = delta_theta * con.getNodes()[0].getOutput();
//        con.updateWeight(delta_weight, con.getNodes()[0].getId());
//    }
//}
//
private void updateHiddenLayer(){
     for(int i=0; i<network.getHiddenLayer().size(); i++){

         HiddenNode par = network.getHiddenLayer().get(i);
        Vector<Node> childs = par.getChild();
        double temp_g = 0.0;
        for(int j=0; j<childs.size(); j++){
            Connection con = network.getConnectionOfOuputLayer(childs.get(j).getId());
            temp_g = temp_g + (con.getWeightForParent(par) * error[childs.get(j).getId()-1]);
        }
        double e = par.getOutput() * (1 - par.getOutput()) * temp_g;
        double delta_theta = this.learningrate_op * e;
        Connection con = network.getConnectionOfHiddenLayer(par.getId());
         for(int j=0; j<con.NoOfParents(); j++){
            // double delta_weight = delta_theta * network.getNodeOutputLayer(i).getInput();
           double delta_weight = delta_theta * con.getNodes()[j].getOutput();
           con.updateWeight(delta_weight, con.getNodes()[j].getId());
        }
    }
}

private void loadNewWeights(){
    for(int i=0; i<network.getInput_hid().size(); i++){
        Connection con  = network.getInput_hid().get(i);
        con.loadNewWeights();
    }

   
    for(int i=0; i<network.getHid_output().size(); i++){
        Connection con  = network.getHid_output().get(i);
        con.loadNewWeights();
    }

}



}

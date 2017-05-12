/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ClusterBankForNeural;

import Group.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Vector;
import neuralnetwork3.InterestNetwork;
import neuralnetwork3.OutputNode;
import neuralnetwork3.TrainingData;

/**
 *
 * @author Sinthu
 */
public class Cluster implements Serializable{
private String name;
private double[] age;
private double[] nationality;
private double[] gender;
private double[] prof;
private TrainingData data;
private InterestNetwork net;
private Vector<String> namesOfOuputsOfRetrainPas;
private boolean isNeededRetrain;
private String currentOp;

    public Cluster(double[] age, double[] nationality, double[] gender, double[] prof, InterestNetwork net) {
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.prof = prof;
        this.name = age[0]+"_"+age[1]+"_"+nationality[0]+"_"+nationality[1]+"_"+gender[0]+"_"+gender[1]+"_"+prof[0]+"_"+prof[1];
        data = new TrainingData(net.getOutputLayer().size(), this);
        data.setInput(1,  this.roundTwoDecimals((age[0]+age[1])/2));
        data.setInput(2, this.roundTwoDecimals((nationality[0]+nationality[1]))/2);
        data.setInput(3,  this.roundTwoDecimals((gender[0]+gender[1])/2));
        data.setInput(4,  this.roundTwoDecimals((prof[0]+prof[1])/2));
        this.net = net;
        this.isNeededRetrain  =false;
        this.currentOp = "";
    }

   double roundTwoDecimals(double d) {
        	DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
}
    public double[] getAge() {
        return age;
    }

    public double[] getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public double[] getNationality() {
        return nationality;
    }

    public double[] getProf() {
        return prof;
    }

    public TrainingData getData() {
        return data;
    }

   public void setOutput(String nameOfOutput){
       Vector<OutputNode> allOp = this.net.getOutputLayer();
       int index =1;
//       for(int i=0; i<allOp.size(); i++){
//           if(allOp.get(i).getName().equalsIgnoreCase(nameOfOutput)){
//            index = i+1;
//            break;
//           }
//       }
       this.currentOp = nameOfOutput;
       this.data.setOutput(index, 0.9);
   }

   public void setOutput(String nameOfOutput, boolean isChanged){
       Vector<OutputNode> allOp = this.net.getOutputLayer();
       int index =0;
       for(int i=0; i<allOp.size(); i++){
           if(allOp.get(i).getName().equalsIgnoreCase(nameOfOutput)){
            index = i+1;
            break;
           }
       }
       this.currentOp = nameOfOutput;
       this.data.setOutput(index, 0.9, isChanged);
   }

   public void printTraingData(){
       System.out.println("age: "+ this.getData().getInput()[0]);
        System.out.println("nationality: "+ this.getData().getInput()[1]);
         System.out.println("gender: "+ this.getData().getInput()[2]);
          System.out.println("prof: "+ this.getData().getInput()[3]);
          double[] op = this.getData().getOutput();
          int id = -1;
          for(int i=0; i<op.length; i++){
              if(op[i]==0.9){
                  id = i;
                  break;
              }
          }
          Vector<OutputNode> allOp = this.net.getOutputLayer();
         String opname =  allOp.get(id).getName();
   //  if(!opname.equalsIgnoreCase(currentOp)){
       System.out.println("Output: "+opname);
       System.out.println("Current Output: "+currentOp);
    // }
    }


   public void clearRetrainData(){
       this.namesOfOuputsOfRetrainPas = new Vector<String>();
       this.data.setIsChanged(false);
   }


   public void addSubInterestPas(String nameOfSubInt){
       this.namesOfOuputsOfRetrainPas.add(nameOfSubInt);
   }

   public void removeConflict(){
    if(this.namesOfOuputsOfRetrainPas.size()>0){
      int[] count = new int[this.net.getOutputLayer().size()];
      for(int i=0; i<count.length; i++){
          count[i] = this.getCountofSubInt(this.net.getOutputLayer().get(i).getName());
      }
      String[] tempSubInt = new String[count.length];
      for(int i=0; i<tempSubInt.length; i++){
          tempSubInt[i] = this.net.getOutputLayer().get(i).getName();
      }
  int t = 0;
  String temp;
  for( int i = 0; i < count.length; i++){
  for( int j = 1; j < (count.length-i); j++){
  if(count[j-1] > count[j]){
  t = count[j-1];
  temp = tempSubInt[j-1];
  count[j-1]=count[j];
  tempSubInt[j-1] = tempSubInt[j];
  count[j]=t;
  tempSubInt[j] = temp;
  }
  }
  }

  String selectedSubInt = tempSubInt[tempSubInt.length-1];
  if(this.currentOp.equalsIgnoreCase(selectedSubInt)){
      this.isNeededRetrain = false;
  }
  else{
      this.setOutput(selectedSubInt, true);
      this.isNeededRetrain = true;
  }
  
    }
    else{
        if(this.net.getName().equalsIgnoreCase("music") | this.net.getName().equalsIgnoreCase("films") | this.net.getName().equalsIgnoreCase("foods")){
               neuralnetwork3.RetrainChecker rc = new neuralnetwork3.RetrainChecker();
               boolean notoriginal = rc.isReatrained(this.net.getName());
               if(!notoriginal){
                   this.setOutput("no "+net.getName());
                   this.isNeededRetrain = true;
               }
               else{
                    this.isNeededRetrain = false;
               }
        }
        else this.isNeededRetrain = false;
    }
   }

   public boolean isNeededRetrain(){
   return this.isNeededRetrain;
   }

   private int getCountofSubInt(String nameOFsubInt){
       int count = 0;
       for(int i=0; i<this.namesOfOuputsOfRetrainPas.size(); i++){
           if(this.namesOfOuputsOfRetrainPas.get(i).equalsIgnoreCase(nameOFsubInt)){
               count++;
           }
       }
       return count;
   }

    public String getCurrentOp() {
        return currentOp;
    }


}

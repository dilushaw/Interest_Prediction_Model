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
public class GetInterest {
   

  private int nElems=0;
   private double[] a = new double[10];
    InterestNetwork net=null;

    GetInterest(InterestNetwork network){
     this.net=network;
    }

public String getInterestPets(){
      
       String interest="";
       this.createNode();
 //System.out.println(a[0]);
   // System.out.println(a[1]);
       if(a[0]>a[1]) {
            interest=(net.getOutputLayer().get(0).getName() );
       }
// else
//      interest.add(net.getOutputLayer().get(1).getName() );
//
       

return interest;
    }

public String getInterestFilms(){
    String interest ="";
       this.createNode();
        if(a[1]>a[0]) {
            interest=(net.getOutputLayer().get(1).getName() );
       }
 else{
    interest=(net.getOutputLayer().get(0).getName() );
 }
       return interest;
}
public String getInterestNews(){
      String interest ="";
       this.createNode();

       if(a[1]>a[0]) {
            interest=(net.getOutputLayer().get(1).getName() );
       }
// else
//      interest.add(net.getOutputLayer().get(1).getName() );
//


return interest;
    }
public String getInterestScience(){
    String interest = "";
   this.createNode();
   System.out.println(a[0]);
    System.out.println(a[1]);
     System.out.println(a[2]);
    if(a[0]>a[1]) {
            interest=(net.getOutputLayer().get(0).getName() );
       }
 else{
     if(a[1]<0.81){
      interest=(net.getOutputLayer().get(1).getName() );
     }
// else
//     interest.add("no science");
}
 return interest;
}

public String getInterestFoods_Fashions_Music_Science(){

String interest="";
       this.createNode();
       this.find_HighestNodeValue();
        double highest= a[net.getOutputLayer().size()-1];
        OutputNode findHighestnode = this.findHighestnode(highest);
        if(!(findHighestnode.getName().equals("no foods")||findHighestnode.getName().equals("no science"))){
           interest=(findHighestnode.getName() );
        }


return interest;
}

public String getInterestPets_News_Science_Films(){
//System.out.println(net.getName());


 if(net.getName().equals("news"))
   return  this.getInterestNews();
//else if(net.getName().equals("science"))
//return this.getInterestScience();
    else if(net.getName().equals("pets"))
    {
   // System.out.println("pets ");
return this.getInterestPets();}
else
 return  this.getInterestFilms();
}



 

private void createNode(){
    for(int i=0;i<net.getOutputLayer().size();i++){
     this.insert(net.getOutputLayer().get(i).getOutput());
    }
//System.out.println(a[0]);
//        System.out.println(a[1]);
}



private OutputNode findHighestnode(double highest){
    OutputNode out=null;
    for(int i=0;i<net.getOutputLayer().size();i++){
     if( highest==(net.getOutputLayer().get(i).getOutput())  ){
       out= net.getOutputLayer().get(i);
     }
    }
     return out;
}



  public void find_HighestNodeValue() {
    int out, in;

    for (out = nElems - 1; out > 1; out--)
      // outer loop (backward)
      for (in = 0; in < out; in++)
        // inner loop (forward)
        if (a[in] > a[in + 1]) 
          swap(in, in + 1); // swap them

//    System.out.println(a[0]);
//        System.out.println(a[1]);
  }

  private void swap(int one, int two) {
    double temp = a[one];
    a[one] = a[two];
    a[two] = temp;
  }
  public void insert(double value) {
    a[nElems] = value;
    nElems++;
  }
  

}

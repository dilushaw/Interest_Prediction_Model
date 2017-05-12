/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilusha
 */
public class InterestBank implements Serializable{
private Vector<InterestNetwork> networks1;
private Vector<InterestNetwork> networks2;

public InterestBank(){
    this.networks1 = new Vector<InterestNetwork>(); //sinthu
    this.networks2 = new Vector<InterestNetwork>(); //dilu

    this.networks1.add(this.retrieveNetwork("Books"));
    this.networks1.add(this.retrieveNetwork("Finance"));
    this.networks1.add(this.retrieveNetwork("Games"));
    this.networks1.add(this.retrieveNetwork("Hotels"));
    this.networks1.add(this.retrieveNetwork("Sports"));
    this.networks1.add(this.retrieveNetwork("Toys"));
    this.networks1.add(this.retrieveNetwork("Technology"));


    this.networks2.add(this.retrieveNetwork("fashions"));
    this.networks2.add(this.retrieveNetwork("foods"));
    this.networks2.add(this.retrieveNetwork("music"));
    this.networks2.add(this.retrieveNetwork("science"));
    this.networks2.add(this.retrieveNetwork("news"));
    
    this.networks2.add(this.retrieveNetwork("pets"));
    this.networks2.add(this.retrieveNetwork("films"));
}

public Vector<Vector<String>>  getInterest(double age, double nat, double gen, double prof){
    Vector<Vector<String>> allInterest = new Vector<Vector<String>>();

    for(int i=0; i<networks1.size(); i++){
        Vector<String> eachInterest=new Vector<String>();

        InterestNetwork net = networks1.get(i);
        eachInterest.add(net.getName());
        net.process(age, nat, gen, prof);
        Vector<String> interst = net.getInterest();
        if(interst.size() == 1) {
            String[] s = interst.get(0).split("&");
            for(int j=0; j<s.length; j++){
                 eachInterest.add(s[j].trim());
            }
        }
        allInterest.add(eachInterest);
    }


    for(int i=0;i<networks2.size()-3;i++){
         Vector<String> eachInterest=new Vector<String>();
        InterestNetwork net = networks2.get(i);
        eachInterest.add(net.getName());
        net.process(age, nat, gen, prof);
       GetInterest getI =new GetInterest(net) ;

       String interst = getI.getInterestFoods_Fashions_Music_Science();
if(!(interst=="")){
   String[] s = interst.split("&");
            for(int j=0; j<s.length; j++){
                 eachInterest.add(s[j].trim());
}

    }
           allInterest.add(eachInterest);
    }
    for(int i=4;i<networks2.size();i++){
         Vector<String> eachInterest=new Vector<String>();
       InterestNetwork net = networks2.get(i);
        eachInterest.add(net.getName());
       
        net.process(age, nat, gen, prof);
       GetInterest getI =new GetInterest(net) ;

       String interst = getI.getInterestPets_News_Science_Films();
if(!(interst=="")){
   String[] s = interst.split("&");
            for(int j=0; j<s.length; j++){
                 eachInterest.add(s[j].trim());
}
 
    }
            allInterest.add(eachInterest);
    }
//int strtNo = allInterest.size();
//Vector<String> temp = new Vector<String>();
//
//    GetInterest getI =new GetInterest(networks2.get(0));
//    networks2.get(0).process(age, nat, gen, prof);
//    temp = getI.getInterestFoods_Science_Films();
//    if(temp.size()==1)allInterest.add(temp.get(0));
//
//     getI =new GetInterest(networks2.get(1));
//    networks2.get(1).process(age, nat, gen, prof);
//    temp = getI.getInterestFashions();
//   allInterest.add(temp.get(0));
//
//
//    getI =new GetInterest(networks2.get(2));
//    networks2.get(2).process(age, nat, gen, prof);
//    temp = getI.getInterestFoods_Science_Films();
//    if(temp.size()==1)allInterest.add(temp.get(0));
//
//    getI =new GetInterest(networks2.get(3));
//    networks2.get(3).process(age, nat, gen, prof);
//    temp=getI.getInterestMusic();
//    if(temp.size()==1)allInterest.add(temp.get(0));
//
//
//    getI =new GetInterest(networks2.get(4));
//    networks2.get(4).process(age, nat, gen, prof);
//    temp = getI.getInterestNews();
//    if(temp.size()==1)allInterest.add(temp.get(0));
//
//    getI =new GetInterest(networks2.get(5));
//    networks2.get(5).process(age, nat, gen, prof);
//    temp = getI.getInterestScience();
//    if(temp.size()==1)allInterest.add(temp.get(0));
//
//    getI =new GetInterest(networks2.get(6));
//    networks2.get(6).process(age, nat, gen, prof);
//    temp = getI.getInterestPets();
//    if(temp.size()==1)allInterest.add(temp.get(0));

//    for(int i=strtNo; i<allInterest.size(); i++){
//         String[] s = allInterest.get(i).split("&");
//            for(int j=0; j<s.length; j++){
//                 allInterest.remove(i);
//                 allInterest.add(s[j]);
//            }
//    }
    
return allInterest;
}


//private void trainToyNetwork(){
//    String[] toys = {"kids' Toys", "Elders' Toys"};
//    Network net = new Network("Toys", toys, 2);
//    networks.add(net);
//    TrainingEngine engine = new TrainingEngine(net);
//    TrainingData data = new TrainingData(2);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//         data.setInput(1, 0.10);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        double[] threshold = {0.5, 1, 0.91, 0.995};
//        net.setThreshold(threshold);
//        engine.train();
//
//       net.process(0.7, 0, 0.3, 0);
//    System.out.println(net.getInterest());
//        net.process(0.7, 0, 0.6, 0);
//        System.out.println(net.getInterest());
//        net.process(0.4, 0.12, 0.6, 0.24);
//        System.out.println(net.getInterest());
//        net.process(0.1, 0.12, 0.3, 0.06);
//        System.out.println(net.getInterest());
//        net.process(0.1, 0.6, 0.6, 0.06);
//        System.out.println(net.getInterest());
//}


private void trainToyNetwork(){
    String[] toys = {"kids' Toys", "Elders' Toys"};
    InterestNetwork net = new InterestNetwork("Toys", toys, 5);

    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);
    TrainingData data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.3);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);


          data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.3);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.3);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.4);
        engine.addTrainingData(data);


//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.4);
//        engine.addTrainingData(data);
//
//
//
//           data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//
//          data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(2);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//

                 data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


                   data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.35);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.45);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

    double[] threshold = {0.5, 1, 0.8, 0.94}; //for toys
    net.setThreshold(threshold);

        engine.train();

}



//For this for kids, profession should be student => please try 0.12 for the profession otherwise it'll
//result in wrong reslt..
//ie, net.process(0.1, 0.12, 0.3, 0.12); will work
    // net.process(0.1, 0.12, 0.3, 0); WON'T WORK
//This interests are depend on the profession also, as far as they are student and less age, the games will be one of the interest.
//net.process(0.2,0.6, 0.6, 0.6) => is a politician therefore reagardless of the age the games won't be an interest
//private void trainGamesNetwork(){
//    String[] games = {"Girl kids' Games","Boy kids' games", "Advanced Games"};
//     //Advanced Games can have info about the board games, card games, chess..
//    Network net = new Network("Games", games, 4);
//    networks.add(net);
//    TrainingEngine engine = new TrainingEngine(net);
//
//    TrainingData data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        ////////
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//       data.setInput(1, 0.20);
//        data.setInput(2, 0);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        ////////
//        data = new TrainingData(3);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//        ///added
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//       //end
//
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//        ///more added
//         data = new TrainingData(3);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//        ///added
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//       //end
//
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.40);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//        //end
//
//        //more added for age 0.6..
//         data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.20);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//        ///added
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.60);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.42);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//       //end
//
//
//        data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//            data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(3);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.6);
//        data.setOutput(3, 0.4);
//        engine.addTrainingData(data);
//        //end
//        double[] threshold = {0.5, 0.999, 0.51, 1, 0.44, 0.97};
//        net.setThreshold(threshold);
//
//        engine.train();
//
////sample output..
//  net.process(0.25, 0.6, 0.3, 0.6);
//  System.out.println(net.getInterest());
//}

private void trainGamesNetwork(){
    String[] games = {"Girl kids' Games","Boy kids' games", "Advanced Games"};
     //Advanced Games can have info about the board games, card games, chess..
    InterestNetwork net = new InterestNetwork("Games", games, 8);   //run1 => 6, run2 => 5, run3=> 8
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

    //Kids games..
    TrainingData data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        ////////
        data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


       //

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        ////////
        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        // end kid's games


        //Advanced games
         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


            data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


          data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


            data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);



          data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


            data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


          data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


            data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.4);
        engine.addTrainingData(data);


        //end
       double[] threshold = {0.75, 0.999999999999968, 0.5, 1, 0.65, 1}; //for games
    net.setThreshold(threshold);

        engine.train();

//sample output..
//  net.process(0.25, 0.6, 0.3, 0.6);
//  System.out.println(net.getInterest());
}

public void trainfilmsNetwork(InterestNetwork net){

    TrainingEngine engine = new TrainingEngine(net);
    TrainingData data = new TrainingData(2);

        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.30);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.60);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


//2

       

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);







        

        


        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



//3data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);








//4

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



//5
        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.60);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);
        

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);
 engine.train();

    }
public void trainScienceNetwork(InterestNetwork net){

    TrainingEngine engine = new TrainingEngine(net);
    TrainingData data = new TrainingData(3);

        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.30);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.60);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


//2



        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);







        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

//
        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

       data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);








        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);



//3
        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

 data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

 data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);








//4

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);
//
        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);



data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);



//5
        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.60);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.20);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

//
         data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);






        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.30);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.30);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.40);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.30);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.54);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);
 engine.train();

    }
private void trainpetsNetwork(InterestNetwork net){
//pets
    TrainingEngine engine = new TrainingEngine(net);
    TrainingData data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.20);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.60);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        //no

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);





        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.40);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.60);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.36);
        data.setInput(3, 0.30);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.60);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(2);
        data.setInput(1, 0.50);
        data.setInput(2, 0.6);
        data.setInput(3, 0.30);
        data.setInput(4, 0.36);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        engine.train();

    }
private void trainBooksNetwork(){
String[] books = {"Children's book","Elders' story books", "Cookery Books & Elders' Story Books", "Scientific books & Elders' Story Books", "Business Magazines", "Elders' story books"};//"Politics books"
     //Advanced Games can have info about the board games, card games, chess..
    InterestNetwork net = new InterestNetwork("Books", books, 16); //run#2 => 15, run=> 13

 // Network net = this.retrieveNetwork("Books");
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

   TrainingData data = new TrainingData(6);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        ////////
        data = new TrainingData(6);
        data.setInput(1, 0.10);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.10);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(6);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        ////////
        data = new TrainingData(6);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);



         //Scientific
        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


       
                data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);



        //Elders' book
         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
         data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

           data = new TrainingData(6);
         data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

 
         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


                data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
         data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
         data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        //
         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
         data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        //cookery
         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        //Business magazine
        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


            data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);



           data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);



         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

//        //More elders'  books

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


             data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        
           data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(6);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(6, 0.9);
        engine.addTrainingData(data);


        //end

        double[] threshold = {0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1};
        net.setThreshold(threshold);

        engine.train();

}

/*
 * This network wroks perfectly but a small issue is there won't be any books for the lawyer - 0.48, politician - 0.54, artist -0.6.
 * by setting the upper threshold value for 1 results in 'business magazines' the above mentioned ppl, which is irrelavant
 * therefore i changed the threshold and assuemed those ppl are not interested in any story books.
 * If ever like to apply some books to them, we can do as 'if there is no childer book and interest id empty => books = elders' story book';
 * */
//private void trainBooksNetwork(){
//String[] books = {"Children's book","Elders' story books", "Cookery Books & Elders' Story Books ", "Scientific books & Elders' Story Books", "Business Magazines"};//"Politics books"
//     //Advanced Games can have info about the board games, card games, chess..
//    Network net = new Network("Books", books, 5);
//    networks.add(net);
//    TrainingEngine engine = new TrainingEngine(net);
//
//   TrainingData data = new TrainingData(5);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        ////////
//        data = new TrainingData(5);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.10);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//
//         //Scientific
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.24);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.3);
//        data.setOutput(4, 0.9);
//        engine.addTrainingData(data);
//
//
//
//        //Elders' book
//         data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//         data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(5);
//         data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//         //
//         data = new TrainingData(6);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(6);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(6);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(6);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(6);
//         data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(2, 0.9);
//        engine.addTrainingData(data);
//
//
//        //cookery
//         data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.3);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.06);
//        data.setOutput(3, 0.9);
//        engine.addTrainingData(data);
//
//
//        //Business magazine
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.36);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.3);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(5);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.48);
//        data.setOutput(5, 0.9);
//        engine.addTrainingData(data);
//
////        //Political books
////
////         data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////         data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.2);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////
////         data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.42);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////         data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.3);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.12);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.3);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
////
////        data = new TrainingData(6);
////        data.setInput(1, 0.6);
////        data.setInput(2, 0.6);
////        data.setInput(3, 0.6);
////        data.setInput(4, 0.6);
////        data.setOutput(6, 0.9);
////        engine.addTrainingData(data);
//
//        double[] threshold = {0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1};
//        net.setThreshold(threshold);
//
//        engine.train();
//
//}

/*
 * Since for business ppl prof - 0.36 only the share market interest is there, and the rest of the interest should fall
 * on the banks and insurance => mainly middle age and elder ppl
 * */

private void trainFinancialNetwrork(){
    String[] finance = {"Share Market", "Banks & Insurance", "Bank & Insurance"};
    InterestNetwork net = new InterestNetwork("Finance", finance, 9);
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

     TrainingData data = new TrainingData(3);
     //Middle age ppl
       data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);
///copied..

         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);
//1
         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

//
         data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.4);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        //
        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.25);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


    //end


    //For share market..
    data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

// added start..
  data = new TrainingData(3);
        data.setInput(1, 0.35);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


 //added

         data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(3);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);
        //end

        double[] threshold = {0.5, 0.98, 0.5, 0.999991, 0.5, 1};
        net.setThreshold(threshold);

        engine.train();
        //end


}

public void trainSportsNetwork(){
    String[] sports = {"Western sports", "African Sports", "Chines Sports", "Asian Spots", "Middle East"};
    InterestNetwork net = new InterestNetwork("Sports", sports, 5);
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

     TrainingData data = new TrainingData(3);

     //Western sports
        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

      //end

        //African sports
         data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.24);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.24);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

      //end

        //Chinese sports
         data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);
        //end

        //Asian Sports
          data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.48);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.48);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        //end

        //MiddleEast Sports
          data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(5);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(5, 0.9);
        engine.addTrainingData(data);

        //end

        double[] threshold = {0.5, 1,0.5, 1,0.5, 1,0.5, 1,0.5, 1,0.5, 1};
        net.setThreshold(threshold);

        engine.train();
        
}

public void trainHotelsNetwork(){
     String[] hotels = {"Hotels"};
    InterestNetwork net = new InterestNetwork("Hotels", hotels, 3);
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

     TrainingData data = new TrainingData(1);

    
        data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);


           data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);


          data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

                
         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.25);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


                 data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.3);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(1);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        double [] threshold = {0.522, 1};
         net.setThreshold(threshold);
     

        engine.train();
}

public void trainTechnologyNetwork(){
String[] tech = {"New Trends & Mobile Phones", "House hold Electonics & Cookery", "Laptops & PC's & New Trends", "House Hold Electronics"};
  InterestNetwork net = new InterestNetwork("Technology", tech, 16);

//    Network net = this.retrieveNetwork("Books");
    networks1.add(net);
    TrainingEngine engine = new TrainingEngine(net);

   TrainingData data;
         //New Trends and mobile phones

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.1);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.4);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.15);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        
        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


                

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        
        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);
        

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         
          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);




        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.12);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.18);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);
        
         
       
//        data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(4);
//         data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//        data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//           data = new TrainingData(4);
//         data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//
//         data = new TrainingData(4);
//        data.setInput(1, 0.2);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//
//                data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//         data = new TrainingData(4);
//         data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//         data.setInput(1, 0.4);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//
//        //
//         data = new TrainingData(4);
//        data.setInput(1, 0.4);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.3);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.12);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//        data.setInput(1, 0.6);
//        data.setInput(2, 0.6);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.18);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//
//          data = new TrainingData(4);
//         data.setInput(1, 0.6);
//        data.setInput(2, 0.12);
//        data.setInput(3, 0.6);
//        data.setInput(4, 0.06);
//        data.setOutput(1, 0.9);
//        engine.addTrainingData(data);
//


        //cookery
         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

          data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);



        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.06);
        data.setOutput(2, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.06);
        data.setOutput(1, 0.9);
        engine.addTrainingData(data);


         //Laptops..
        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        
         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);  


                data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        //Continue..
        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


                data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);
                //continue end
        
         
         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.24);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.36);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


                data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.36);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.42);
        data.setOutput(3, 0.9);
        engine.addTrainingData(data);

      //House hold electronics

                 data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.2);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


             data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.3);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.4);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


           data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.5);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.48);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.3);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);


        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.12);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.3);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

        data = new TrainingData(4);
        data.setInput(1, 0.6);
        data.setInput(2, 0.6);
        data.setInput(3, 0.6);
        data.setInput(4, 0.6);
        data.setOutput(4, 0.9);
        engine.addTrainingData(data);

         double[] th = {0.5, 1,0.5, 1,0.5, 1,0.5, 1};
         net.setThreshold(th);

        engine.train();

}

public  Vector<Vector<String>> showInterest(Passenger pas){
    Vector<Vector<String>>  inter = this.getInterest(pas.getAge(), pas.getNationality(), pas.getGender(),pas.getProfession());


   Vector<InterestHandler> interHandlerBank=  new  Vector<InterestHandler>();
 for(int i=0; i<inter.size(); i++){
           // try {
                InterestHandler interHandler = new InterestHandler(pas);
                Vector<String> interest = inter.get(i);
                interHandler.setInterest_name(interest.get(0));
                System.out.println(interest.get(0));
                Vector<String> allinterests = new Vector<String>();
                for (int j = 1; j < interest.size(); j++) {
                    allinterests.add(interest.get(j));
                    System.out.println(interest.get(j));
                }
                interHandler.setInterest_output(allinterests);
                interHandlerBank.add(interHandler);
               // interHandler.checkInterest();
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(InterestBank.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(InterestBank.class.getName()).log(Level.SEVERE, null, ex);
//            }
// }

}

return inter;
    }

public Vector<String> getSubInterests(Passenger pas, String mainInterest){
    Vector<String> sub = new Vector<String>();
     Vector<Vector<String>>  inter = this.getInterest(pas.getAge(), pas.getNationality(), pas.getGender(),pas.getProfession());
     for(int i=0; i<inter.size(); i++){
         Vector<String> temp = inter.get(i);
         String intName = temp.firstElement();
         if(intName.equalsIgnoreCase(mainInterest)){
             for(int j=1; j<temp.size(); j++){
                 sub.add(temp.get(j));
             }
         }
     }
     return sub;
}

//public boolean isSubInterest(String subInt){
//    for(int i=0; i<networks1.size(); i++){
//        if(networks1.get(i).getName().equalsIgnoreCase(mainInts)){
//            return true;
//    }
//    }
//    for(int i=0; i<networks2.size(); i++){
//        if(networks2.get(i).getName().equalsIgnoreCase(mainInts)){
//            return true;
//    }
//    }
//     return false;
//}


public static void main(String[] args){
    InterestBank b = new InterestBank();
   InterestNetwork net = b.runTestCases("Toys");
    //InterestNetwork net = b.retrieveNetwork("Toys");
      // Network net = new InterestBank().retrieveNetwork("Games");
 net.process(0.16, 0.35, 0.6, 0.12);
 //net.getNodeOutputLayer(3).name = "Banks & Insurance";
// net.getNodeOutputLayer(4).name = "Science books & Elders' books";
// net.getNodeOutputLayer(5).name = "Business books & Elders' books";
// double[] threshold = {0.5, 0.98, 0.5, 0.999991, 0.5, 1};//0.999999
 double[] threshold = {0.82, 1, 0.8, 0.938};//0.92
 net.setThreshold(threshold);
 double[] out = net.getOutput();
 for(int i=0; i<out.length; i++){
     System.out.println(net.getNodeOutputLayer(i+1).getName() +" : "+out[i]);
 }
 System.out.println(net.getInterest());
}

/*public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException{
   /* Neural_GUI ngui=new Neural_GUI();
    PointHandler point=new PointHandler();
    if(ngui.action(null, point))
     //   ngui.
    point.setAge_Str(ngui.getAge());
    point.setNationality_Str(ngui.getNationality());
    point.setGender_Str(ngui.getGender());
    point.setProfession_Str(ngui.getProfession());
    Passenger pas=new Passenger(point.getNationality_Doub(),point.getAge_Doub(),point.getGender_Doub(),point.getProfession_Doub());
    pas.setNationalityString("Sri lankan");//for testing

    */













    // Network net = new InterestBank().retrieveNetwork("Games");
// net.process(0.3, 0.12, 0.3, 0.3);
// //net.getNodeOutputLayer(3).name = "Banks & Insurance";
//// net.getNodeOutputLayer(4).name = "Science books & Elders' books";
//// net.getNodeOutputLayer(5).name = "Business books & Elders' books";
//// double[] threshold = {0.5, 0.98, 0.5, 0.999991, 0.5, 1};//0.999999
//   double[] threshold = {0.8, 0.98, 0.51, 1, 0.5, 0.944};//0.92
// net.setThreshold(threshold);
// double[] out = net.getOutput();
// for(int i=0; i<out.length; i++){
//     System.out.println(net.getNodeOutputLayer(i+1).getName() +" : "+out[i]);
// }
// System.out.println(net.getInterest());
//


//InterestBank b = new InterestBank();
//Network net = b.runTestCases("Finance");
////double[] threshold = net.getThreshold();
////System.out.println("###########################################");
////for(int i=0; i<threshold.length; i++){
////    System.out.println(threshold[i]);
////}
//b.saveNetwork(net);
//String[] pets = {"pets", "no pets"};
//    InterestNetwork net = new InterestNetwork("pets", pets,3);

//    String[] films = {"Cartoon films & English movies for childrens", "English films","English films&Chineese films","English films&Asian films","English films&Middle eastern films"};
//    InterestNetwork net = new InterestNetwork("films", films,4);
//InterestBank b = new InterestBank();
//b.trainfilmsNetwork(net);
//b.saveNetwork(net);
//  String[] tech = {"New Trends & Mobile Phones", "House hold Electonics & Cookery", "Laptops & PC's & New Trends", "House Hold Electronics"};
//InterestNetwork net = b.retrieveNetwork("fashions");
//b.printOutputValues(net);
//////
//net.getOutputLayer().get(0).name = "Latest musics";
//net.getOutputLayer().get(1).name = "Latest music & old musics";
//net.getOutputLayer().get(2).name = "Latest musics";
//net.getOutputLayer().get(3).name = "Latest music & old musics";
////net.getOutputLayer().get(4).name = "Films";
//b.printOutputValues(net);
//b.saveNetwork(net);

    //  InterestBank b = new InterestBank();

//     Vector<Vector<String>>  inter = b.getInterest(pas.getAge(), pas.getNationality(), pas.getGender(),pas.getProfession());
    

 /*  Vector<InterestHandler> interHandlerBank=  new  Vector<InterestHandler>();
 for(int i=0; i<inter.size(); i++){
     InterestHandler interHandler=new InterestHandler(pas);
     Vector<String> interest=inter.get(i);
     interHandler.setInterest_name(interest.get(0));
      System.out.println(interest.get(0));
     Vector<String> allinterests= new Vector<String> ();
     for(int j=1; j<interest.size(); j++){
allinterests.add(interest.get(j));
     System.out.println(interest.get(j));
     }
     interHandler.setInterest_output(allinterests);
     interHandlerBank.add(interHandler);
    // interHandler.checkInterest();
 }
    
  
//   Network net = b.retrieveNetwork("Toys");
//   InterestNetwork iNet = new InterestNetwork();
//   iNet.setName(net.getName());
//   iNet.setInput_hid(net.getInput_hid());
//   iNet.setInputLayer(net.getInputLayer());
//   iNet.setHiddenLayer(net.getHiddenLayer());
//   iNet.setHid_output(net.getHid_output());
//   iNet.setOutputLayer(net.getOutputLayer());
//   iNet.setThreshold(net.getThreshold());
//   b.saveNetwork(iNet);


 //InterestBank b = new InterestBank().retrieveInterestBank();
// InterestBank b = new InterestBank();
//// Network net = b.getNetwork("Games");
//  b.trainTechnologyNetwork();
//   // System.out.println(b.networks.get(0).getInterest());
//   InputStreamReader isr = new InputStreamReader(System.in);
//      BufferedReader br = new BufferedReader(isr);
//      String s = "";
//        try {
//            s = br.readLine();
//            } catch (IOException ex) {
//            System.out.println("exception thrown in reading from console");
//        }
//      if(s.equals("1")){
//           Network net = b.getNetwork("Technology");
//           b.saveNetwork(net);
//      }
}*/

    public Vector<InterestNetwork> getNetworks() {
        return networks1;
    }

public InterestNetwork getNetwork(String nameOfNetwork){
    for(int i=0; i<networks1.size(); i++){
        if(networks1.get(i).getName().equals(nameOfNetwork)){
            return networks1.get(i);
        }
    }
    return null;
}


public void saveNetwork(InterestNetwork net){

  try
      {
         FileOutputStream fileOut =
         new FileOutputStream(net.getName()+".ser");
         ObjectOutputStream out =
                            new ObjectOutputStream(fileOut);
         out.writeObject(net);
         out.close();
          fileOut.close();
          System.out.println(net.getName() +" Network saved...");
      }catch(IOException i)
      {
          i.printStackTrace();
          System.out.println(net.getName() +" Network notsaved...");
      }

}

public InterestNetwork retrieveNetwork(String networkName){
InterestNetwork b = null;
 try
         {
            FileInputStream fileIn =
                          new FileInputStream(networkName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
             b= (InterestNetwork) in.readObject();
            in.close();
            fileIn.close();
            return b;

        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("InterestBank  class not found");
            c.printStackTrace();
            return null;
        }

}

public InterestNetwork runTestCases(String netName){
    InterestNetwork net = this.retrieveNetwork(netName);
    //double[] age = { 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5, 0.55, 0.6};
    double[] age = { 0.15, 0.16, 0.17, 0.18, 0.19, 0.20};
    double[] nat = {0.36};
    double[] gender = {0.3, 0.6};
    double[] prof = {0.06, 0.12, 0.18, 0.24, 0.3, 0.36, 0.42, 0.48, 0.54, 0.6};

//    double [] threshold = {0.5, 0.999, 0.54, 1, 0.5, 1}; //for games
//    net.setThreshold(threshold);

//     double[] threshold = {0.5, 0.98, 0.5, 0.999991, 0.5, 1};   //for finance
//        net.setThreshold(threshold);

//    double[] threshold = {0.5, 1, 0.8, 0.94}; //for toys
//    net.setThreshold(threshold);

//double[] threshold = {0.75, 0.999999999999968, 0.5, 1, 0.65, 1}; //for games
//    net.setThreshold(threshold);

//    double[] threshold= {0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1,0.49, 1, 0.5, 1};   //for books
//    net.setThreshold(threshold);

//    double[] threshold = {0.5, 1, 0.5, 1, 0.39, 1, 0.5, 1};      //for tech
//    net.setThreshold(threshold);

//    double[] threshold = {0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1};      //for tech
//    net.setThreshold(threshold);

//    double[] threshold = {0.5, 0.94, 0.8, 0.94};
//    net.setThreshold(threshold);
    double[] threshold = {0.82, 1, 0.8, 0.938};//0.92
 net.setThreshold(threshold);
 
    for(int a=0; a<age.length; a++){
        for(int n=0; n<nat.length; n++){
            for(int g=0; g<gender.length; g++){
                for(int p=0; p<prof.length; p++){
                    System.out.println("******************************************************************************");
                    if(age[a]<=0.15){
                        if(prof[p] == 0.12){
                             net.process(age[a], nat[n], gender[g], prof[p]);
                             System.out.println("Age: "+age[a] + ", Nat: "+ nat[n]+ ", Gender: "+gender[g]+", prof: "+ prof[p]);
                             System.out.println(net.getInterest());
                            this.printOutputValues(net);
                        }
                    }
                    else{
                        net.process(age[a], nat[n], gender[g], prof[p]);
                        System.out.println("Age: "+age[a] + ", Nat: "+ nat[n]+ ", Gender: "+gender[g]+", prof: "+ prof[p]);
                        System.out.println(net.getInterest());
                       this.printOutputValues(net);
                    }

                }
            }
        }
    }
 this.saveNetwork(net);
return net;
}

public void printOutputValues(InterestNetwork net){
 double[] out = net.getOutput();
 for(int i=0; i<out.length; i++){
     System.out.println(net.getNodeOutputLayer(i+1).getName() +" : "+out[i]);
 }
}

}

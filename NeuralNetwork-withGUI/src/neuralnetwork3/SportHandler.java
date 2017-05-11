/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilusha
 */
public class SportHandler {
  private  String nationality;
 private WebSearch web;
 private Sports_CountryBank bank;
 
    public SportHandler (String nationality) throws UnsupportedEncodingException, MalformedURLException{

        System.out.println("constructor");
        Vector<Sport> sports;
        this.nationality=nationality;
        bank = new Sports_CountryBank();
        bank.createBank();
//       web=new WebSearch("sports");
//
//        try {
//         //   System.out.println("tryyyyyyyyyyyyyy");
//       GoogleResults gr=  (web.getURL("most popular sport in"+" sri lanka"))  ;
//    //     System.out.println("grrrrrrrrrr"+gr.getResponseData().getResults().get(0));
//         web.searchResults(gr);
//
//         //  web.getURL("most popular sport in"+nationality);
//         sports=   this.findSport( web.getURLforSports()) ;
//
//
//          Set<String> sortedSports=this.sort(sports);
//    Vector<Sport> finalSports=new Vector<Sport>()    ;
//  Iterator it =  sortedSports.iterator();
//while (it.hasNext()) {
//Vector<Object> SportName_url=new Vector<Object>() ;
//    Object element = it.next();
//SportName_url.add(element);
//    System.out.println(element+"oooooooooooooo");
//
//  gr=(web.getURL("hot "+element+" news "))  ;
//    for(int k=0;k<gr.getResponseData().getResults().size();k++){
//               SportName_url.add(gr.getResponseData().getResults().get(k));
//
//                    }
//
//}
//
//
//
////System.out.println(web.getURLforSports().size());
////         for(int i=0;i< sports.size();i++) {
////System.out.println(sports.get(i).getName());
////          web.searchResults_Sports(web.getURL("hot "+sports.get(i).getName()+"news "))  ;
////         }
//
//
//        } catch (IOException ex) {
//            Logger.getLogger(SportHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }



    }

public Vector<Vector<Object>>  handleURL(){

Vector<Vector<Object>> allSportName_url=new Vector<Vector<Object>>() ;
    Vector<Sport> sports;

       web=new WebSearch("sports");

        try {
         //   System.out.println("tryyyyyyyyyyyyyy");
       GoogleResults gr;
      // GoogleResults gr=  (web.getURL("most popular sport in"+this.nationality))  ;
    //     System.out.println("grrrrrrrrrr"+gr.getResponseData().getResults().get(0));
        // web.searchResults(gr);

         //  web.getURL("most popular sport in"+nationality);
       //  sports=   this.findSport( web.getURLforSports()) ;


         // Set<String> sortedSports=this.sort(sports);

       Vector<String> sortedSports = bank.getSports(nationality);
    Vector<Sport> finalSports=new Vector<Sport>()    ;
  //Iterator it =  sortedSports.iterator();

for (int i=0; i<sortedSports.size(); i++) {
Vector<Object> SportName_url=new Vector<Object>() ;
    Object element = sortedSports.get(i);
SportName_url.add(element);
    System.out.println(element+"oooooooooooooo");

  gr=(web.getURL("best sites for "+element+" news"))  ;
  if(!(gr==null)){
      if(!(gr.getResponseData()==null)){
    for(int k=0;k<gr.getResponseData().getResults().size();k++){
               SportName_url.add(gr.getResponseData().getResults().get(k).getUrl());

                    }}}
allSportName_url.add(SportName_url);
}



//System.out.println(web.getURLforSports().size());
//         for(int i=0;i< sports.size();i++) {
//System.out.println(sports.get(i).getName());
//          web.searchResults_Sports(web.getURL("hot "+sports.get(i).getName()+"news "))  ;
//         }


        } catch (IOException ex) {
            //Logger.getLogger(SportHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exceprion..");
            allSportName_url = null;
        }



return allSportName_url;

}


private Vector<Sport> findSport(Vector<String> url){
    
Vector<Sport> sports=new Vector<Sport>();

for(int k=0;k<url.size();k++){

String text=web.getText(url.get(k));

String data[]=(text.split("\\."));
    for(int i=0;i<data.length;i++) {


        if(data[i].contains("most popular")){

  //  System.out.println("dot..................");
       //     String dot=data[i];
           // String data1[]=(dot.split("\\,"));
        //    for(int j=0;j<data1.length;j++) {
             // System.out.println(data1[j]);
         //       if(data[i].contains("most popular")||data[i].contains("most famous")){

                 String comma=data[i];
         if(comma.contains("Football")||comma.contains("football")){
              Sport football=new Sport("Football");
              sports.add(football);

           }
                 if(comma.contains("soccer")||comma.contains("Soccer")){
              Sport soccer=new Sport("Soccer");
              sports.add(soccer);

           }
            else if(comma.contains("Volleyball")||comma.contains("volleyball")){
    Sport volleyball=new Sport("Volleyball");
  sports.add(volleyball);
 }
 else if(comma.contains("Cricket")||comma.contains("cricket")){
       Sport cricket=new Sport("Cricket");
     sports.add(cricket);
 }
                 else if(comma.contains("Athletics")||comma.contains("athletics")){
     Sport athletics=new Sport("Athletics");
   sports.add(athletics);
 }
else if(comma.contains("Golf")||comma.contains("golf")){
     Sport Golf=new Sport("Golf");
      sports.add(Golf);
 }
          else if(comma.contains("Rugby")||comma.contains("rugby")){
      Sport Rugby=new Sport("Rugby");
      sports.add(Rugby);
 }
                 else if(comma.contains("Basketball")||comma.contains("basketball")){
     Sport basketball=new Sport("Basketball");
     sports.add(basketball);
 }
                 else if(comma.contains("Baseball")||comma.contains("baseball")){
   Sport  baseball=new Sport("Baseball");
     sports.add( baseball);
 }

           //     }


    //    }
        }
    }

     }
return sports;
 }

private Vector<String> findSport_2(Vector<String> url){
Vector<String> sports=new Vector<String>();
for(int k=0;k<url.size();k++){
//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
String text=web.getText(url.get(k));
//System.out.println(text);
String data[]=(text.split("\\."));
    for(int i=0;i<data.length;i++) {
     //   System.out.println(data[i]+"jjjjjjjjjjjjjjjjjjjjjjjj");

        if(data[i].contains("most popular sport")){

       //     System.out.println("has popular"+"oooooooooooooooooooooo");
            String dot=data[i];
           // String data1[]=(dot.split("\\,"));
          //  for(int j=0;j<data1.length;j++) {
             //   if(data[i].contains("most popular sport")){
          //       System.out.println("****************************************");
                 String comma=data[i];
                //  System.out.println(comma);
          String data2[]=(comma.split(" "));
           //   System.out.println(data2.length);
for(int l=0;l<data2.length;l++) {

  //   System.out.println(data2[l]);
 //        System.out.println(l);

    if(data2[l].equalsIgnoreCase("sport")||data2[l].equalsIgnoreCase("sports")){
        if(!(l==data2.length-1)){
            if(data2[l+1].equalsIgnoreCase("is")||data2[l+1].equalsIgnoreCase("are")){
               if(!(data2[l+2].contains("<a"))) {
            sports.add(data2[l+2]);
                }
            }
        }
    }
 else if(data2[l].equalsIgnoreCase("is")&&(l<data2.length-2)&&(data2[l+1].equalsIgnoreCase("the"))&&data2[l+2].equalsIgnoreCase("most")){
      if(!(l==0)){

          if(!(data2[l-1].contains("What")||data2[l-1].contains("what")||data2[l-1].contains("It")||data2[l-1].contains("it")||data2[l-1].contains("That")||data2[l-1].contains("that")||data2[l-1].contains("sport")||data2[l-1].contains("Sport"))){
            sports.add(data2[l-1]);
          }


 }
}
                }


       // }
       // }
    }

     }
// for(int a=0;a<sports.size();a++){
//System.out.println(sports.get(a));
// }
for(int a=0;a<sports.size();a++){
System.out.println(sports.get(a)+k+"///////////////////////////////////////////////////");
    }
}
for(int b=0;b<sports.size();b++){
System.out.println(sports.get(b));
    }
return sports;
    }


public Set<String> sort(Vector<Sport> sports) {
   Set<String> sortedSports = new HashSet<String>() ;

    System.out.println(sports.size());
    for(int i=0;i<sports.size();i++){
        if((sports.get(i).getCheck()==false)){
            for(int j=i+1;j<sports.size();j++){
            if(sports.get(i).getName().equals(sports.get(j).getName())){
             sports.get(j).setCheck(true);
             sports.get(i).setCounter(sports.get(i).getCounter()+1);
            }
            }
        }
    }
  int highestCounter=0;
  int secondCounter=0;
  int[] sortedsports = new int[sports.size()];
for(int i=0;i<sports.size();i++){//sorting the counter
sortedsports[i]=sports.get(i).getCounter();
    }

    Arrays.sort(sortedsports);
    if(sports.size()>0){
 highestCounter= sortedsports[sports.size()-1];
    }
 if(sports.size()>1){
    secondCounter=sortedsports[sports.size()-2];
    }
System.out.println(highestCounter);


  for(int i=0;i<sports.size();i++){
    int temp=sports.get(i).getCounter();
    if(temp==highestCounter){
     sortedSports.add(sports.get(i).getName());
    }

  }
for(int i=0;i<sports.size();i++){
    int temp=sports.get(i).getCounter();

    if(temp==secondCounter){
    sortedSports.add(sports.get(i).getName());
    }
  }



return sortedSports;

  }

}

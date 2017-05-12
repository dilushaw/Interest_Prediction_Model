/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class NationalityMatcher {

    /**
     * @param args the command line arguments
     */
private Vector<String> nationality_country;
    private Vector<String> nationality_countryUSA;//with usa
private Vector<Vector<String>> allNations;
private Vector<Vector<String>> allNationsUSA;

private int[]youtube;
private int[]wiki;
private String interest;
private String keyword;

public NationalityMatcher(String interest,String keyword){
    this.allNations=new Vector<Vector<String>>();
    
    this.allNationsUSA=new Vector<Vector<String>>();
    this.interest=interest;
    this.keyword=keyword;
    this.processFile();
    this.processFile_usa();
}


public String handler() throws UnsupportedEncodingException, MalformedURLException, IOException{

    String nat="";
if(this.interest.equalsIgnoreCase("Food")){
   Food food=new Food(new ProcessorNat(this.nationality_country,this.nationality_countryUSA,this.allNations,this.allNationsUSA),this.keyword);
food.handler();
nat=food.print();
}
 else if (this.interest.equalsIgnoreCase("Films")){
Film film=new Film(new ProcessorNat(this.nationality_country,this.nationality_countryUSA,this.allNations,this.allNationsUSA),this.keyword);
film.handler();
nat=film.print();
}
else if (this.interest.equalsIgnoreCase("Music")){
Music music=new Music(new ProcessorNat(this.nationality_country,this.nationality_countryUSA,this.allNations,this.allNationsUSA),this.keyword);
music.handler();
nat=music.print();

}
else if (this.interest.equalsIgnoreCase("Fashion")){
Fashion fashion=new Fashion(new ProcessorNat(this.nationality_country,this.nationality_countryUSA,this.allNations,this.allNationsUSA),this.keyword);
fashion.handler();
nat=fashion.print();


}
return nat;
}



   private void processFile(){

    try{
  FileInputStream fstream = new FileInputStream("countryAndNationality.txt");
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  while ((strLine = br.readLine()) != null)   {
      this.nationality_country=new Vector<String>();
    String[] words = strLine.split("#");
    String ctry = words[words.length-1].trim();
    String allNats = words[0];

    String[] nats = allNats.split("&");
 this.nationality_country.add(ctry);
   for(int i=0; i<nats.length; i++){
       this.nationality_country.add(nats[i].trim());

   }
 this.allNations.add(this.nationality_country);
  }
  in.close();
    }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
  }
private void processFile_usa(){

    try{
  FileInputStream fstream = new FileInputStream("countryAndNationality_withusa.txt");
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  while ((strLine = br.readLine()) != null)   {
      this.nationality_countryUSA=new Vector<String>();
    String[] words = strLine.split("#");
    String ctry = words[words.length-1].trim();
    String allNats = words[0];

    String[] nats = allNats.split("&");
 this.nationality_countryUSA.add(ctry);
   for(int i=0; i<nats.length; i++){
       this.nationality_countryUSA.add(nats[i].trim());

   }
 this.allNationsUSA.add(this.nationality_countryUSA);
  }
  in.close();
    }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
  }




}

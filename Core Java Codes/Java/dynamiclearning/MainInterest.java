/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamiclearning;

import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class MainInterest {
private String name;
private Vector<String> keywords;
private Vector<SubInterest> subInts;
private Vector<String> url;
transient private boolean isWiki;
//transient private  wikiMatches;
transient private int matches;
private double sum;
private boolean ischosen;
private double percentage_match; //this is for the whole percentage match with regard to the keyword list we defined
private double percentage_newInterest;
private Vector<String> keys_newInterest;
private Vector<Boolean> matches_newInterest; //keys and matches shoulbe the same size, and the keyword is mtached or not will bethere in the same index of the matches
private int[] subInterestMaxKeyword;
private int[] subInterestMaxURL;
private int[] subInterestMaxTitle;
private int[] totalMax;
private boolean finalSelection;

public MainInterest(String name){
    this.name = name;
    this.keywords = new Vector<String>();
    this.subInts = new Vector<SubInterest>();
    this.isWiki = false;
    this.matches = 0;
     this.percentage_match = 0.0;
     this.percentage_newInterest = 0.0;
     this.matches_newInterest = new Vector<Boolean>();
     this.keys_newInterest  = new Vector<String>();
     this.subInterestMaxKeyword = new int[this.subInts.size()];
     this.subInterestMaxURL = new int[this.subInts.size()];
     this.subInterestMaxTitle = new int[this.subInts.size()];
     this.totalMax = new int[this.subInts.size()];
}
public MainInterest(){
    
}

    public boolean isIschosen() {
        return ischosen;
    }

    public void setIschosen(boolean ischosen) {
        this.ischosen = ischosen;
    }





public void setsum(double sum){
   this.sum=sum;
}

public double getsum(){
  return sum;
}
public void addKeyword(String word){
    boolean found = false;
    for(int i=0; i<this.keywords.size(); i++){
       if(this.keywords.get(i).equalsIgnoreCase(word)){
           found = true;
           break;
       }
    }
    if(!found) this.keywords.add(word);
}

public void addSubInterest(SubInterest sub){
    this.subInts.add(sub);
}

    public Vector<String> getKeywords() {
        return keywords;
    }

    public String getName() {
        return name;
    }

    public Vector<SubInterest> getSubInts() {
        return subInts;
    }

 public void addMatches(int val){
        this.matches = this.matches+val;
       // System.out.println(this.matches);
    }

    public double getMatches(){
       Double double_match = new Double(this.matches);
       Double double_key = new Double(this.keywords.size());
       System.out.println(name+" : keyword : "+this.keywords.size()+", matches: "+this.matches);
        return ((double_match/double_key)*100);
    }

    public boolean isIsWiki() {
        return isWiki;
    }

    public void setIsWiki(boolean isWiki) {
        this.isWiki = isWiki;
    }

     public double getPercentage_match() {
        return percentage_match;
    }

    public void setPercentage_match(double percentage_match) {
        this.percentage_match = percentage_match;
    }

     public void addNewInterestKeyword(Vector<String> key){
     for(int i=0; i<key.size(); i++){
         this.keys_newInterest.add(key.get(i));
     }
     this.matches_newInterest.setSize(this.keys_newInterest.size());
    }

    public Vector<String> getKeys_newInterest() {
        return keys_newInterest;
    }

    public Vector<Boolean> getMatches_newInterest() {
        return matches_newInterest;
    }

    public double getPercentage_newInterest() {
        return percentage_newInterest;
    }


     public void setMatchFound(int index){
        this.matches_newInterest.set(index, Boolean.TRUE);
    }


     public void findPercentageMatchNewInterest(){
         int count = 0;
        for(int i=0; i<this.matches_newInterest.size(); i++){
            if(this.matches_newInterest.get(i) != null){
                if(this.matches_newInterest.get(i)){
                    count++;
                }
            }
        }
        Double double_count = new Double(count);
        Double double_keys = new Double(this.keys_newInterest.size());
        this.percentage_newInterest = (double_count/double_keys)*100;
     }


     public void clearNewInterestData(){
         this.percentage_newInterest = 0.0;
         this.matches_newInterest = new Vector<Boolean>();
         this.keys_newInterest = new Vector<String>();
     }

    public int[] getSubInterestMaxKeyword() {
        return subInterestMaxKeyword;
    }

    public int[] getSubInterestMaxTitle() {
        return subInterestMaxTitle;
    }

    public int[] getSubInterestMaxURL() {
        return subInterestMaxURL;
    }

    public int[] getTotalMax() {
        return totalMax;
    }

    public boolean isFinalSelection() {
        return finalSelection;
    }

    public void setFinalSelection(boolean finalSelection) {
        this.finalSelection = finalSelection;
    }

   public void clearMaxArrays(){
     this.subInterestMaxKeyword = new int[this.subInts.size()];
     this.subInterestMaxURL = new int[this.subInts.size()];
     this.subInterestMaxTitle = new int[this.subInts.size()];
     this.totalMax = new int[this.subInts.size()];
   }
}

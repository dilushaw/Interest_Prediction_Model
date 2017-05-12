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
public class SubInterest {
private String name;
private MainInterest mainInt;
private Vector<String> keywords;
transient private boolean isWiki;
transient private int matches;
private boolean ischosen;
private boolean isHasNextLayer;
private Vector<SubInterest> childSubInterests;
private SubInterest mainSubInterest;
private boolean finalSelection;

public SubInterest(String name, MainInterest mainInt){
    this.name = name;
    this.mainInt = mainInt;
    this.keywords = new Vector<String>();
    this.mainInt.addSubInterest(this);
    this.isHasNextLayer = false;
    this.isWiki = false;
    this.matches = 0;
    this.mainSubInterest = null;
   
}

public SubInterest(String name, SubInterest mainSub){
    this.mainSubInterest = mainSub;
    this.mainInt = mainSub.getMainInt();
    this.name = name;
    this.keywords = new Vector<String>();
    this.mainSubInterest.addSubInterest(this);
    this.isWiki = false;
    this.matches = 0;
}

    public boolean isIschosen() {
        return ischosen;
    }

    private void addSubInterest(SubInterest subInt){
        this.isHasNextLayer = true;
        if(this.childSubInterests == null){
            this.childSubInterests = new Vector<SubInterest>();
        }
        this.childSubInterests.add(subInt);
    }
    
    public void setIschosen(boolean ischosen) {
        this.ischosen = ischosen;
    }

public boolean isHasNextLayer(){
    return this.isHasNextLayer;
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

public Vector<SubInterest> getAllChildSubInterests(){
    return this.childSubInterests;
}
    public Vector<String> getKeywords() {
        return keywords;
    }

    public MainInterest getMainInt() {
        return mainInt;
    }

    public String getName() {
        return name;
    }

    public void addMatches(int val){
        this.matches = this.matches+val;
    }

    public double getMatches(){
       Double double_match = new Double(this.matches);
       Double double_key = new Double(this.keywords.size());
       System.out.println(name+" "+this.keywords.size()+" "+this.matches);
        return ((double_match/double_key)*100);
    }

    public boolean isIsWiki() {
        return isWiki;
    }

    public void setIsWiki(boolean isWiki) {
        this.isWiki = isWiki;
    }

    public boolean isParentSubInterest(){
        if(this.mainSubInterest != null){
            return true;
        }
        else{
            return false;
        }
    }

    public SubInterest getParentSubInterest(){
        return this.mainSubInterest;
    }

    public boolean isFinalSelection() {
        return finalSelection;
    }

    public void setFinalSelection(boolean finalSelection) {
        this.finalSelection = finalSelection;
    }

   

    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */

/*This class is for each sub group of the main attribute.
 * ie, the main attrubutes are are, gender, nationality, professin.
 * for this some of the clusteres are taken together and formaed a sub group.
 * for age 0 - 15, 15- 25, 25-40, 40-55, 55> are the sub groups of the main attribute, age.
 * 
 * */

public class SubGroup implements Serializable{
private Attribute mainAttribute;
private double minValue;
private double maxValue;
private Vector<Cluster> clusters;
private Vector<Passenger> pas;
private String name;

public SubGroup(Attribute atbte, double min, double max){
    this.mainAttribute = atbte;
    this.minValue = min;
    this.maxValue = max;
    this.clusters = new Vector<Cluster>();
    name=mainAttribute.getName()+"_"+min+"_"+max;
    this.pas = new Vector<Passenger>();
    atbte.addSubGroup(this);
}

    public Attribute getMainAttribute() {
        return mainAttribute;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void addCluster(Cluster c){
        boolean found = false;
        for(int i=0; i<this.clusters.size(); i++){
            if(this.clusters.get(i).getName().equals(c.getName())){
                found = true;
                break;
            }
        }
        if(!found)this.clusters.add(c);
    }

    public Vector<Cluster> getAllClusters(){
        return this.clusters;
    }

    public void setMainAttribute(Attribute mainAttribute) {
        this.mainAttribute = mainAttribute;
        this.name = mainAttribute.getName()+ "_"+this.minValue+"_"+this.maxValue;
    }

    public String getName() {
        return name;
    }

    public void addPassenger(Passenger pass){
        boolean found = false;
        for(int i=0; i<this.pas.size(); i++){
            if(this.pas.get(i).getPassengerID().equals(pass.getPassengerID())){
                found = true;
                break;
            }
        }
        if(!found) this.pas.add(pass);
    }

    public int getNoOfPassengers(){
        return this.pas.size();
    }

    public Vector<Passenger> getPas() {
        return pas;
    }

    public boolean isPassengerFound(Passenger pas){
        boolean found = false;
        for(int i=0; i<this.pas.size(); i++){
            if(this.pas.get(i).getPassengerID().equals(pas.getPassengerID())){
                found = true;
                break;
            }
        }
        return found;
    }

    public void print(){
        System.out.println(this.name);
    }

    public boolean isClusterExist(Cluster c){
        for(int i=0; i<this.clusters.size(); i++){
        if(this.clusters.get(i).getName().equals(c.getName())){
            return true;
        }
        }
        return false;
    }
}

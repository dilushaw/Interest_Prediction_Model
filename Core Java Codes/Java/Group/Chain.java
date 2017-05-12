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
public class Chain implements Serializable{
private String chainId;
private Vector<SubGroup> groups;
private int noOfPassengers;
private boolean valid;
private Vector<Passenger> commonPas;

public Chain(String chainId){
    this.chainId = chainId;
    groups = new Vector<SubGroup>();
    this.noOfPassengers = 0;
    this.commonPas = new Vector<Passenger>();
}

public Chain(){
  //  this.chainId = chainId;
    groups = new Vector<SubGroup>();
    this.noOfPassengers = 0;
    this.commonPas = new Vector<Passenger>();
}

public void addGroup(SubGroup grp){
    boolean found = false;
    for(int i=0; i<this.groups.size(); i++){
        if(this.groups.get(i).getName().equals(grp.getName())){
            found = true;
            break;
        }
    }
    if(!found) {
        if(this.commonPas.size()!=0){
        Vector<Passenger> copyPas = new Vector<Passenger>();
            for(int i=0; i<this.commonPas.size();i++){
               if(grp.isPassengerFound(this.commonPas.get(i))){
                  copyPas.add(this.commonPas.get(i));

               }
            }
        this.commonPas = copyPas;
        }
        else{
            for(int i=0; i<grp.getNoOfPassengers(); i++){
                this.commonPas.add(grp.getPas().get(i));
            }
        }
        this.groups.add(grp);
    }
}

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public SubGroup getSubGroup(int index){
        if(index<this.groups.size()){
             return this.groups.get(index);
        }
       return null;
    }

    public boolean isGroupIn(SubGroup grp){
        for(int i=0; i<this.groups.size(); i++){
            if(this.groups.get(i).getName().equals(grp.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    //@Override
    public void print(){
        for(int i=0; i<this.groups.size(); i++){
            this.groups.get(i).print();
        }
    }

    public int getNoOfSubGroups(){
        return this.groups.size();
    }

    public boolean isPassengerFound(Passenger pas){
        boolean found = false;
        for(int i=0; i<this.commonPas.size(); i++){
            if(this.commonPas.get(i).getPassengerID().equals(pas.getPassengerID())){
                found = true;
                break;
            }
        }
        return found;
    }

    public SubGroup getSubGroup(Attribute at){
        for(int i=0; i<this.groups.size(); i++){
            if(this.groups.get(i).getMainAttribute().getName().equals(at.getName())){
                return groups.get(i);
            }
        }
        return null;
    }

    public boolean isClusterInAllSubGrp(Cluster c){
        boolean res= true;
        for(int i=0; i<this.groups.size(); i++){
            res = res & this.groups.get(i).isClusterExist(c);
            if(!res) return false;
        }
        return true;
    }

   
    
    
}

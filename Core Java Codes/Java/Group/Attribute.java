/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Sinthu
 */
public class Attribute implements Serializable{
private String name;
private Vector<SubGroup> subGrps;
private int maxOccurance;
private double metric;
private Vector<SubGroup> maxSubGroups;
private int metricFactor;
private int priority;
private boolean isDominating;

//metric factor is a commonfactor/noofelelments; age-5, nat-30. gen-2. prof-10 => common 30. =>metrics age=6,nat=1, gen=15, prof=3;
public Attribute(String name, int metricFactor, int priority){
    this.name = name;
    this.maxOccurance = 0;
    this.metric = 0.0;
    subGrps = new Vector<SubGroup>();
    this.maxSubGroups = new Vector<SubGroup>();
    this.metricFactor = metricFactor;
    this.priority = priority;
    this.isDominating = true;
}

public void addSubGroup(SubGroup grp){
    grp.setMainAttribute(this);
    this.subGrps.add(grp);
}

    public String getName() {
        return name;
    }

    public Vector<SubGroup> getSubGrps() {
        return subGrps;
    }

    public int getMaxOccurance() {
        return maxOccurance;
    }

    public void setMaxOccurance(int maxOccurance) {
        this.maxOccurance = maxOccurance;
    }

    public double getMetric() {
        return metric;
    }

    public void setMetric() {
        this.metric = this.maxOccurance/this.metricFactor;
    }

    public void addMaxSubGroup(SubGroup grp){
        boolean found = false;
        for(int i=0; i<this.maxSubGroups.size(); i++){
            if(this.maxSubGroups.get(i).getName().equals(grp.getName())){
                found = true;
                break;
            }
        }
        if(!found) this.maxSubGroups.add(grp);
    }

    public void resetMaxOccurance(){
        this.maxSubGroups.removeAllElements();
        this.maxOccurance = 0;
        this.metric = 0.0;
    }

    public int getPriority() {
        return priority;
    }

    public Vector<SubGroup> getMaxSubGroups() {
        return maxSubGroups;
    }

public int getNoOfSubGroups(){
    return this.subGrps.size();
}

public boolean isThisSubGroupMaxSubGroup(SubGroup grp){
    for(int i=0; i<this.maxSubGroups.size(); i++){
        if(this.maxSubGroups.get(i).getName().equals(grp.getName())){
            return true;
        }
    }
    return false;
}

public boolean isAllMaxSubGroupsFound(Vector<Chain> c){
    Vector<boolean[]> found = new Vector<boolean[]>();
    for(int i=0; i<c.size(); i++){
        Chain ch = c.get(i);
        boolean[] b = new boolean[this.maxSubGroups.size()];
        for(int j=0; j<this.maxSubGroups.size(); j++){
            if(ch.isGroupIn(this.maxSubGroups.get(j))){
                b[j] = true;
            }
            else{
                b[j] = false;
            }
        }
        found.add(b);
    }
    boolean suc = false;
    for(int i=0; i<this.maxSubGroups.size(); i++){
        suc = false;
        for(int j=0; j<found.size(); j++){
            if(found.get(j)[i]){
               suc=true;
               j=found.size();
            }
        }
        if(!suc) break;
    }
    return suc;
}

public Vector<SubGroup> getUncatchedMaxSubGroup(Vector<Chain> c){
    Vector<boolean[]> found = new Vector<boolean[]>();
    Vector<SubGroup> subGrp = new Vector<SubGroup>();
    for(int i=0; i<c.size(); i++){
        Chain ch = c.get(i);
        boolean[] b = new boolean[this.maxSubGroups.size()];
        for(int j=0; j<this.maxSubGroups.size(); j++){
            if(ch.isGroupIn(this.maxSubGroups.get(j))){
                b[j] = true;
            }
            else{
                b[j] = false;
            }
        }
        found.add(b);
    }
    boolean suc = false;
    for(int i=0; i<this.maxSubGroups.size(); i++){
        suc = false;
        for(int j=0; j<found.size(); j++){
            if(found.get(j)[i]){
               suc=true;
               j=found.size();
            }
        }
        if(!suc){
            subGrp.add(this.maxSubGroups.get(i));
        }
    }
    return subGrp;
}

public boolean isDominatingAttribute(){
    int noOfPas = 0;
    for(int i=0; i<this.subGrps.size(); i++){
        noOfPas = noOfPas + this.subGrps.get(i).getNoOfPassengers();
    }
    double val = noOfPas/this.subGrps.size();
    val = val/this.metricFactor;
    if(this.metric == val){
        this.isDominating = false;
        return false;
    }
    else{
        this.isDominating = true;
        return true;
    }
}
}

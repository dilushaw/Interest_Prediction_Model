/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Group;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Classifier implements Serializable{
private Object[] clusterBank;
private Attribute age;
private Attribute nationality;
private Attribute gender;
private Attribute prof;
private Vector<Chain> chains;

public void createClusters(){
   this.initializeAll();

     double[] ageVal = { 0.10, 0.15, 0.20, 0.25, 0.30, 0.35, 0.40, 0.45, 0.50, 0.55, 0.60, 0.65};
    double[] natVal = new double[30];
    for(int i=0; i<30; i++){
        natVal[i] = this.roundTwoDecimals(0.02*(i+1));
    }
    double[] genderVal = {0.30, 0.60} ;
    double[] profVal = {0.06, 0.12, 0.18, 0.24, 0.30, 0.36, 0.42, 0.48, 0.54, 0.60};



    Cluster cluster;
     for(int a=0; a<ageVal.length; a++){
        for(int n=0; n<natVal.length; n++){
            for(int g=0; g<genderVal.length; g++){
                for(int p=0; p<profVal.length; p++){
                    double[] tempAge = new double[2];
                    double[] tempNat = new double[2];
                    double[] tempGen = new double[2];
                    double[] tempProf = new double[2];
                    
                   if(ageVal[a]<=0.15){
                        if(profVal[p] == 0.12){
                             if(ageVal[a]==0.1){
                                 tempAge[0] = this.roundTwoDecimals(0.00);
                             }
                             else{
                                 tempAge[0] = this.roundTwoDecimals(ageVal[a-1]);
                             }
                             tempAge[1] = this.roundTwoDecimals(ageVal[a]);
                             tempNat[0] = this.roundTwoDecimals(natVal[n]-0.01);
                             tempNat[1] = this.roundTwoDecimals(natVal[n]+0.01);
                             tempGen[0] = this.roundTwoDecimals(genderVal[g]);
                             tempGen[1] = this.roundTwoDecimals(genderVal[g]);
                             tempProf[0] = this.roundTwoDecimals(profVal[p]-0.03);
                             tempProf[1] = this.roundTwoDecimals(profVal[p]+0.03);
                             cluster = new Cluster(tempAge, tempNat, tempGen, tempProf);
                            this.setClusterToBank(cluster);
                            this.addToSubGroup(cluster);
                        }
                    }
                    else{
                        if(ageVal[a]==0.65){
                                 tempAge[0] = this.roundTwoDecimals(0.60);
                                 tempAge[1] = this.roundTwoDecimals(1.00);
                             }
                        else{
                             tempAge[0] = this.roundTwoDecimals(ageVal[a-1]);
                           tempAge[1] = this.roundTwoDecimals(ageVal[a]);
                             tempNat[0] = this.roundTwoDecimals(natVal[n]-0.01);
                             tempNat[1] = this.roundTwoDecimals(natVal[n]+0.01);
                             tempGen[0] = this.roundTwoDecimals(genderVal[g]);
                             tempGen[1] = this.roundTwoDecimals(genderVal[g]);
                             tempProf[0] = this.roundTwoDecimals(profVal[p]-0.03);
                             tempProf[1] = this.roundTwoDecimals(profVal[p]+0.03);
                             cluster = new Cluster(tempAge, tempNat, tempGen, tempProf);
                             this.setClusterToBank(cluster);
                             this.addToSubGroup(cluster);
                        }
                    }

                }
            }
        }
    }
}

double roundTwoDecimals(double d) {
        	DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
}

private void initializeAll(){
    this.initializeClusterBank();
    this.initializeArribute();
}

private void initializeClusterBank(){
    int ageNo = 12;
    int natNo = 30;
    int genderNo = 2;
    int profNo = 10;

    clusterBank = new Object[ageNo];

     for(int a=0; a<ageNo; a++){
         Object[] natArr = new Object[natNo];
         clusterBank[a] = natArr;
        for(int n=0; n<natNo; n++){
            Object[] genArr = new Object[genderNo];
            natArr[n] = genArr;
            for(int g=0; g<genderNo; g++){
                Object[] profArr = new Object[profNo];
                genArr[g] = profArr;
            }
        }
    }
}
private void initializeArribute(){
    age = new Attribute("Age", 6, 2);
    nationality = new Attribute("Nationality", 1, 4);
    gender = new Attribute("Gender", 15, 1);
    prof = new Attribute("Profession", 3, 3);

    double[] natVal = new double[30];
    for(int i=0; i<30; i++){
        natVal[i] = this.roundTwoDecimals(0.02*(i+1));
    }
    double[] genderVal = {0.30, 0.60} ;
    double[] profVal = {0.06, 0.12, 0.18, 0.24, 0.30, 0.36, 0.42, 0.48, 0.54, 0.60};


    SubGroup sub = new SubGroup(age, 0, 0.15);
    sub = new SubGroup(age, 0.15, 0.25);
    sub = new SubGroup(age, 0.25, 0.40);
    sub = new SubGroup(age, 0.40, 0.55);
    sub = new SubGroup(age, 0.55, 1);

    for(int i=0; i<natVal.length; i++){
        sub = new SubGroup(nationality, this.roundTwoDecimals(natVal[i]-0.01), this.roundTwoDecimals(natVal[i]+0.01));
    }

    for(int i=0; i<genderVal.length; i++){
        sub = new SubGroup(gender, this.roundTwoDecimals(genderVal[i]), this.roundTwoDecimals(genderVal[i]));
    }

    for(int i=0; i<profVal.length; i++){
        sub = new SubGroup(prof, this.roundTwoDecimals(profVal[i]-0.03), this.roundTwoDecimals(profVal[i]+0.03));
    }
}

private void addToSubGroup(Cluster c){
   double maxAge = c.getAge()[1];
   Vector<SubGroup> subGrp = age.getSubGrps();
   if(maxAge>0.55) {
       subGrp.get(4).addCluster(c);
        c.addSubGroup(subGrp.get(4));
   }
   else if (maxAge > 0.40) {
       subGrp.get(3).addCluster(c);
        c.addSubGroup(subGrp.get(3));
   }
    else if (maxAge > 0.25) {
        subGrp.get(2).addCluster(c);
         c.addSubGroup(subGrp.get(2));
    }
    else if (maxAge > 0.15) {
        subGrp.get(1).addCluster(c);
         c.addSubGroup(subGrp.get(1));
    }
    else {
       subGrp.get(0).addCluster(c);
        c.addSubGroup(subGrp.get(0));
    }

   int pos = (int) (Math.round((c.getNationality()[0] + c.getNationality()[1]) / 0.04)-1);
   subGrp = nationality.getSubGrps();
   subGrp.get(pos).addCluster(c);
   c.addSubGroup(subGrp.get(pos));
   
    pos =(int) (Math.round((c.getGender()[0] + c.getGender()[1]) / 0.6)-1);
   subGrp = gender.getSubGrps();
   subGrp.get(pos).addCluster(c);
   c.addSubGroup(subGrp.get(pos));
   
    pos = (int) (Math.round((c.getProf()[0] + c.getProf()[1]) / 0.12)-1);
   subGrp = prof.getSubGrps();
   subGrp.get(pos).addCluster(c);
   c.addSubGroup(subGrp.get(pos));
}


private void setClusterToBank(Cluster c){
    int[] pos = new int[4];
    if(c.getAge()[1]!=1){
        pos[0]=(int)Math.round(c.getAge()[1] / 0.05) - 2;
    }
    else{
        pos[0] = 11;
    }
//    pos[1] = (int) (((c.getNationality()[0] + c.getNationality()[1]) / 0.24) -1);
//    pos[2] = (int) (((c.getGender()[0] + c.getGender()[1]) / 0.60)-1);
//    pos[3] = (int) (((c.getProf()[0] + c.getProf()[1]) / 0.12)-1);

    pos[1] = (int) (Math.round((c.getNationality()[0] + c.getNationality()[1]) / 0.04) -1);
    pos[2] = (int) (Math.round((c.getGender()[0] + c.getGender()[1]) / 0.60)-1);
    pos[3] = (int) (Math.round((c.getProf()[0] + c.getProf()[1]) / 0.12)-1);

    Object[] ageEle = (Object[])clusterBank[pos[0]];
    Object[] natEle = (Object[])ageEle[pos[1]];
    Object[] genEle = (Object[])natEle[pos[2]];
    genEle[pos[3]] = c;
}

public boolean clusterData(Vector<Passenger> passengers){
   boolean suc = true;
    try{
       for(int i=0; i<passengers.size(); i++){
        Passenger pas =  passengers.get(i);
        double tempAge = pas.getAge();
       Vector<SubGroup> subGrp = age.getSubGrps();

   if(tempAge >= 0.55) {
       subGrp.get(4).addPassenger(pas);
   }
   else if (tempAge >= 0.40) {
      subGrp.get(3).addPassenger(pas);
   }
    else if (tempAge >= 0.25) {
        subGrp.get(2).addPassenger(pas);
    }
    else if (tempAge >= 0.15) {
       subGrp.get(1).addPassenger(pas);
    }
    else {
       subGrp.get(0).addPassenger(pas);
    }

       int pos = (int) (Math.round(pas.getNationality() / 0.02) - 1);
       subGrp = nationality.getSubGrps();
       subGrp.get(pos).addPassenger(pas);

       pos = (int) (Math.round(pas.getGender() / 0.3) - 1);
       subGrp = gender.getSubGrps();
       subGrp.get(pos).addPassenger(pas);

       pos = (int) (Math.round(pas.getProf() / 0.06) - 1);
       subGrp = prof.getSubGrps();
       subGrp.get(pos).addPassenger(pas);
    }
   }catch(Exception ex){
       suc= false;
   }
   return suc;
}

public boolean findGrouping(Vector<Passenger> pas){
    boolean suc= false;
    suc = this.clusterData(pas);
    if(suc) suc=this.findMaxOccuranceAttribute();
    if(suc) suc = this.FormChains(this.orderAttributes());
    if(suc) suc = this.findFinalChains();
    return suc;
}

public void ClassifyAndSetInterest(Vector<Passenger> pas, String interest,Vector<String> urls ){
    if(this.findGrouping(pas)){
      for(int i=0; i<this.chains.size(); i++){
          Chain c = this.chains.get(i);
          
          //for(int j=0; j<c.getNoOfSubGroups(); j++){
              this.AddInterestToSubGroup(c, interest, urls);
          //}
      }
//      Vector<Attribute> v = this.getNotDominatingAttributes();
//      if(v.size()!=0){
//          for(int i=0; i<v.size(); i++){
//              for(int j=0; j<v.get(i).getNoOfSubGroups(); j++){
//                  this.AddInterestToSubGroup(v.get(i).getSubGrps().get(j), interest);
//              }
//          }
//      }
      this.printChains();
    }
}

public Vector<String> getInterest(Passenger pas){
    int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(pas.getAge()/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(pas.getNationality()/0.02) - 1;
     pos[2] = (int)Math.round(pas.getGender()/0.3) - 1;
     pos[3] = (int)Math.round(pas.getProf()/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     return c.getInterests();
}



private void AddInterestToSubGroup(Chain c, String interest, Vector<String> url){
//    Vector<Cluster> cls = grp.getAllClusters();
//    for(int i=0; i<cls.size(); i++){
//        cls.get(i).addInterest(interest);
//    }
    SubGroup first = null;
   if(prof.isDominatingAttribute()) first=c.getSubGroup(prof);
   else if(age.isDominatingAttribute()) first=c.getSubGroup(age);
   else if(nationality.isDominatingAttribute()) first=c.getSubGroup(nationality);
   else if(gender.isDominatingAttribute()) first=c.getSubGroup(gender);

    if(first != null){
        Vector<Cluster> cls = first.getAllClusters();
        for(int i=0; i<cls.size(); i++){
            if(c.isClusterInAllSubGrp(cls.get(i))){
                cls.get(i).addInterest(interest, url);
            }
        }
    }
}


private Vector<Attribute> getNotDominatingAttributes(){
    Vector<Attribute> v = new Vector<Attribute>();
    if(!age.isDominatingAttribute()) v.add(age);
    if(!nationality.isDominatingAttribute()) v.add(this.nationality);
    if(!gender.isDominatingAttribute()) v.add(gender);
    if(!prof.isDominatingAttribute()) v.add(prof);
    return v;
}

private boolean findMaxOccuranceAttribute(){
     boolean suc = true;
    Attribute[] allAtt = {age, nationality, gender, prof};
    boolean foundOneLessFour = false;
    for(int a=0; a<allAtt.length; a++){
    Attribute cur = allAtt[a];
    Vector<SubGroup> subGrp = cur.getSubGrps();
    cur.resetMaxOccurance();
    for(int i=0; i<subGrp.size(); i++){
        if(subGrp.get(i).getNoOfPassengers() != 0){
        if(cur.getMaxOccurance() == 0){
            cur.addMaxSubGroup(subGrp.get(i));
            cur.setMaxOccurance(subGrp.get(i).getNoOfPassengers());
        }
        else{

            if(cur.getMaxOccurance()< subGrp.get(i).getNoOfPassengers()){
                cur.resetMaxOccurance();
                cur.addMaxSubGroup(subGrp.get(i));
                cur.setMaxOccurance(subGrp.get(i).getNoOfPassengers());
            }
            else{
                if(cur.getMaxOccurance()== subGrp.get(i).getNoOfPassengers()){
                cur.addMaxSubGroup(subGrp.get(i));
            }

            }
        }
        }
    }
//    if(cur.getMaxOccurance()<4 & cur.getMaxOccurance()>1){
//        if(foundOneLessFour){
//            suc = false;
//            break;
//        }
//        else{
//            foundOneLessFour = true;
//        }
//    }
//    else{
//        suc = false;
//        break;
//    }

        if(cur.getMaxOccurance()<4){
        if(cur.getMaxOccurance()>1){
        if(foundOneLessFour){
            suc = false;
            break;
        }
        else{
            foundOneLessFour = true;
        }
        }
        else{
            suc = false;
            break;
        }
    }

    cur.setMetric();
    }
    return suc;
}

//This vector will hold the maximum occurance attribute and the related subgroup/sub groups in ascending order.
//Each attribute data will be seperated by null data.
//If any unsuccessfull condition achieved the object[] will be null;
private Vector<Attribute> orderAttributes(){
  Attribute[] allAtt = {age, nationality, gender, prof};
  Vector<Attribute> order = new Vector();
  for(int i=0; i<allAtt.length; i++){
      if(allAtt[i].isDominatingAttribute()){
          if(order.size()==0){
              order.add(allAtt[i]);
          }
          else{
          for(int j=0; j<order.size(); j++){
              if(allAtt[i].getMetric()>order.get(j).getMetric()){
                order.insertElementAt(allAtt[i], j);
                break;
              }
              else if(allAtt[i].getMetric() == order.get(j).getMetric()){
                  if(allAtt[i].getPriority()> order.get(j).getPriority()){
                      order.insertElementAt(allAtt[i], j);
                      break;
                  }
                  else if(j+1<order.size()) {
                      order.insertElementAt(allAtt[i], j+1);
                      break;
                  }
                  else {
                      order.add(allAtt[i]);
                      break;
                  }
              }
              if(j==order.size()-1){
                  order.add(allAtt[i]);
                  break;
              }

          }
          }
      }
  }
  return order;
}

//private boolean isWholeNumber(double val){
//    if(val == (double)Math.round(val)){
//            return true;
//        }
//    else return false;
//}

private boolean FormChains(Vector<Attribute> orderAtt){
    chains = new Vector<Chain>();
    int noAtt = orderAtt.size();

    for(int i=0; i<orderAtt.get(0).getMaxSubGroups().size(); i++){
        Chain c = new Chain();
        c.addGroup(orderAtt.get(0).getMaxSubGroups().get(i));
        c.setNoOfPassengers(orderAtt.get(0).getMaxSubGroups().get(i).getNoOfPassengers());
        chains.add(c);
    }

    for(int iter=1; iter<noAtt; iter++){
     Attribute at = orderAtt.get(iter);
    this.matchNextAttribute(at, iter);
    Vector<Chain> oldChain = chains;
    Vector<SubGroup> subGrp = at.getUncatchedMaxSubGroup(chains);
    int count = 0;
    if(subGrp.size()>0){
        chains = new Vector<Chain>();
        for(int i=0; i<subGrp.size(); i++){
        Chain c = new Chain();
        c.addGroup(subGrp.get(i));
        c.setNoOfPassengers(subGrp.get(i).getNoOfPassengers());
        chains.add(c);
    }
        count++;
        for(int j=iter-1; j>=0; j--){
        Attribute att = orderAtt.get(j);
        this.matchNextAttribute(att, count);
        count++;
        }
        this.updateChains(oldChain);
    }
    this.removeInvalidChains();
    }

    if(chains.size()>0){
        return true;
    }
    else return false;
}

private void matchNextAttribute(Attribute at, int indexOfNextAtt){
    //  Attribute at = orderAtt.get(iter);
    Vector<Chain> ch = this.copyChains();
    //ch = chains;
    for(int i=0; i<chains.size(); i++){
        Chain c = ch.get(i);
        SubGroup firstGrp = c.getSubGroup(indexOfNextAtt-1);
        int[] noOfMatches = new int[at.getNoOfSubGroups()];
        for(int j=0; j<at.getNoOfSubGroups(); j++){
            int count =0;
            SubGroup secondGrp = at.getSubGrps().get(j);
            for(int k=0; k<secondGrp.getNoOfPassengers(); k++){
                Passenger curPas = secondGrp.getPas().get(k);
               if(c.isPassengerFound(curPas)){
                   // if(firstGrp.isPassengerFound(curPas)){
                    count++;
                }
            }
            noOfMatches[j] = count;
        }
        Vector<Integer> indexNo = new Vector<Integer>();
        for(int j=0; j<noOfMatches.length; j++){
            if(noOfMatches[j]>1){
            if(indexNo.size() == 0){
                indexNo.add(j);
            }
            else if(noOfMatches[indexNo.lastElement()]<noOfMatches[j]){
                indexNo.removeAllElements();
                indexNo.add(j);
            }
            else if(noOfMatches[indexNo.lastElement()] == noOfMatches[j]){
                indexNo.add(j);
            }
            }
        }
        if(indexNo.size()>0){

        for(int j=0; j<indexNo.size(); j++){
            //if(!isMaxGrp) isMaxGrp = at.isThisSubGroupMaxSubGroup(at.getSubGrps().get(indexNo.get(j)));
            if(j==0){
            c.addGroup(at.getSubGrps().get(indexNo.get(j)));
            c.setNoOfPassengers(noOfMatches[indexNo.get(j)]);
            c.setValid(true);
            }
            else{
                Chain newC = new Chain();
                for(int k=0; k<c.getNoOfSubGroups()-1; k++){
                    newC.addGroup(c.getSubGroup(k));
                }
                newC.addGroup(at.getSubGrps().get(indexNo.get(j)));
                newC.setNoOfPassengers(noOfMatches[indexNo.get(j)]);
                newC.setValid(true);
                ch.add(newC);
            }
        }
        }else{
            c.setValid(false);
        }

    }
    chains = ch;

}

private Vector<Chain> copyChains(){
   Vector<Chain> newCh = new Vector<Chain>();
   for(int i=0; i<chains.size(); i++){
       newCh.add(chains.get(i));
   }
   return newCh;
}
private void updateChains(Vector<Chain> oldChain){
    for(int i=0; i<chains.size(); i++){
       oldChain.add(chains.get(i));
    }
    chains = oldChain;
}

public Vector<Chain> getChains(){
    return this.chains;
}

public void printChains(){
    for(int i=0; i<this.chains.size(); i++){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        this.chains.get(i).print();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
private void removeInvalidChains(){
    Vector<Chain> ch = new Vector<Chain>();
    for(int i=0; i<chains.size(); i++){
        if(chains.get(i).isValid()){
            ch.add(chains.get(i));
        }
    }
    chains = ch;
}





//this mehtod is called for the existing interest...
public Vector<Vector> getFilteredInterest(Passenger pas){
       int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(pas.getAge()/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(pas.getNationality()/0.02) - 1;
     pos[2] = (int)Math.round(pas.getGender()/0.3) - 1;
     pos[3] = (int)Math.round(pas.getProf()/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     return c.getFilteredUrls();
}





private boolean findFinalChains(){
    Vector<Chain> finalCh = new Vector<Chain>();
    for(int i=0; i<chains.size(); i++){
        Chain ch = chains.get(i);
        if(finalCh.size()==0){
            finalCh.add(ch);
        }
        else if(finalCh.lastElement().getNoOfPassengers()<ch.getNoOfPassengers()){
            finalCh.removeAllElements();
            finalCh.add(ch);
        }else if(finalCh.lastElement().getNoOfPassengers() == ch.getNoOfPassengers()){
            finalCh.add(ch);
        }
    }
    chains = finalCh;
    if(finalCh.size()>0){
        return true;
    }
    else{
        return false;
    }

}

//method to call for save the pas into the cluster
public void setPassengerAndInterest(Passenger pas, String ints, String int_nat, Vector<String> urls ){
        int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(pas.getAge()/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(pas.getNationality()/0.02) - 1;
     pos[2] = (int)Math.round(pas.getGender()/0.3) - 1;
     pos[3] = (int)Math.round(pas.getProf()/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     c.addPassengerDetails(new PassengerDetails(pas, ints, int_nat,urls));
    }

public Vector<Interest> getInterestWithURL(Passenger pas){
           int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(pas.getAge()/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(pas.getNationality()/0.02) - 1;
     pos[2] = (int)Math.round(pas.getGender()/0.3) - 1;
     pos[3] = (int)Math.round(pas.getProf()/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     return c.getInterest_url();
}



//private boolean FormChains(Vector<Attribute> orderAtt){
//    chains = new Vector<Chain>();
//    int noAtt = orderAtt.size();
//
//    for(int i=0; i<orderAtt.get(0).getMaxSubGroups().size(); i++){
//        Chain c = new Chain();
//        c.addGroup(orderAtt.get(0).getMaxSubGroups().get(i));
//        c.setNoOfPassengers(orderAtt.get(0).getMaxSubGroups().get(i).getNoOfPassengers());
//        chains.add(c);
//    }
//
//    for(int iter=1; iter<noAtt; iter++){
//    Attribute at = orderAtt.get(iter);
//    for(int i=0; i<chains.size(); i++){
//        Chain c = chains.get(i);
//        SubGroup firstGrp = c.getSubGroup(iter-1);
//        int[] noOfMatches = new int[at.getNoOfSubGroups()];
//        for(int j=0; j<at.getNoOfSubGroups(); j++){
//            int count =0;
//            SubGroup secondGrp = at.getSubGrps().get(j);
//            for(int k=0; k<secondGrp.getNoOfPassengers(); k++){
//                Passenger curPas = secondGrp.getPas().get(k);
//                if(firstGrp.isPassengerFound(curPas)){
//                    count++;
//                }
//            }
//            noOfMatches[j] = count;
//        }
//        Vector<Integer> indexNo = new Vector<Integer>();
//        for(int j=0; j<noOfMatches.length; j++){
//            if(noOfMatches[j]>1){
//            if(indexNo.size() == 0){
//                indexNo.add(j);
//            }
//            else if(indexNo.lastElement()<noOfMatches[j]){
//                indexNo.removeAllElements();
//                indexNo.add(j);
//            }
//            else if(indexNo.lastElement() == noOfMatches[j]){
//                indexNo.add(j);
//            }
//            }
//        }
//        for(int j=0; j<indexNo.size(); j++){
//            //if(!isMaxGrp) isMaxGrp = at.isThisSubGroupMaxSubGroup(at.getSubGrps().get(indexNo.get(j)));
//            c.addGroup(at.getSubGrps().get(indexNo.get(j)));
//            c.setNoOfPassengers(noOfMatches[indexNo.get(j)]);
//        }
//    }
//    if(!at.isAllMaxSubGroupsFound(chains)){
//        Vector<SubGroup> grps = at.getUncatchedMaxSubGroup(chains);
//        for(int )
//    }
//    }
//}
}

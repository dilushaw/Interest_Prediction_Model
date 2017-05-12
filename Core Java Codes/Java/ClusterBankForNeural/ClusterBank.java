/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ClusterBankForNeural;

import java.io.Serializable;
import java.text.DecimalFormat;
import neuralnetwork3.InterestNetwork;

/**
 *
 * @author Dilusha
 */
public class ClusterBank implements Serializable{
private Object[] clusterBank;
private InterestNetwork net;
private int outputSize;

public ClusterBank(InterestNetwork net){
    this.net = net;
    this.outputSize = this.net.getOutputLayer().size();
    this.createClusters();
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

    public void createClusters(){
   this.initializeClusterBank();

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
                             cluster = new Cluster(tempAge, tempNat, tempGen, tempProf, this.net);
                            this.setClusterToBank(cluster);
                          //  this.addToSubGroup(cluster);
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
                             cluster = new Cluster(tempAge, tempNat, tempGen, tempProf,this.net);
                             this.setClusterToBank(cluster);
                            // this.addToSubGroup(cluster);
                        }
                    }

                }
            }
        }
    }
}

public void setPassengerDetailsAndInsertSubInterest(double age1, double nat1, double gen1, double prof1, String subInt){
        int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(age1/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(nat1/0.02) - 1;
     pos[2] = (int)Math.round(gen1/0.3) - 1;
     pos[3] = (int)Math.round(prof1/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     c.addSubInterestPas(subInt);
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

    double roundTwoDecimals(double d) {
        	DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
}

    public Object[] getClusterBank() {
        return clusterBank;
    }

      public String getInterest(double age1, double nat1, double gen1, double prof1){
     int[] pos = new int[4];
     pos[0] = ((int)this.roundTwoDecimals(age1/0.05))-1;
//     System.out.println(pas.getAge()/0.05);
//     System.out.println(((int)pas.getAge()/0.05));
     pos[1] = (int)Math.round(nat1/0.02) - 1;
     pos[2] = (int)Math.round(gen1/0.3) - 1;
     pos[3] = (int)Math.round(prof1/0.06) - 1;

     Object[] nat = (Object[])this.clusterBank[pos[0]];
     Object[] gen = (Object[])nat[pos[1]];
     Object[] pro = (Object[]) gen[pos[2]];
     Cluster c = (Cluster)pro[pos[3]];
     return c.getCurrentOp();
    }


}

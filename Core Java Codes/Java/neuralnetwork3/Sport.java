/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

/**
 *
 * @author Dilusha
 */
public class Sport {
private boolean check=false;
private String  name ;
private int counter=0;

public Sport(String name){
   this.setName(name);
}
public String getName(){
    return name;
}
public void setName(String name){
    this.name=name;
}

public boolean getCheck(){
    return check;
}
public void setCheck(boolean check){
    this.check=check;
}
public int getCounter(){
    return counter;
}
public void setCounter(int counter){
    this.counter=counter;
}

}

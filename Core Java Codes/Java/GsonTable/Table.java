/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GsonTable;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Dilusha
 */
public class Table implements Serializable{
    private Vector<Tuple> allTuples;
    
    public Table(){
        this.allTuples = new Vector<Tuple>();
    }
    
    public void addTuple(String mainInt, String subInt, String nationality, Vector<String> urls, Vector<String> title, boolean replace){
        String name = mainInt+"_"+subInt;
        int index = -1;
        for(int i=0; i<this.allTuples.size(); i++){
            if(this.allTuples.get(i).getName().equalsIgnoreCase(name)){
                index = i;
                break;
            }
        }
        if(index != -1){
            if(replace){
                this.allTuples.remove(index);
                Tuple t = new Tuple(mainInt, subInt, nationality, urls, title);
                this.allTuples.add(t);
            }
        }
        else{
            Tuple t = new Tuple(mainInt, subInt,nationality,  urls, title);
                this.allTuples.add(t);
        }
    }

    public Vector<Tuple> getAllTuples() {
        return allTuples;
    }
    
    
    
}

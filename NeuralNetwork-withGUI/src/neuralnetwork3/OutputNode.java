/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.io.Serializable;

/**
 *
 * @author Dilusha
 */
public class OutputNode extends Node  implements Serializable{

    public OutputNode(String name, int id){
        super(name, id);
    }

    public double process(double input) {
   // System.out.println("###############################################################");
    //System.out.println("last interation o/p:" + output);
    this.input = input;
    double exp = Math.exp(-1* input);
    output = 1/(1 + exp);
    //System.out.println("New iteration o/p:" + output);
    return output;
    }

    public boolean isSelected(){
        if(output>= 0.5){
            return true;
        }
        else{
            return false;
        }
    }
}

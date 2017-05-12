/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

/**
 *
 * @author Sinthu
 */
public class HiddenNode extends Node{

     public HiddenNode(int id){
        super(id);
    }


    public double process(double input) {
    this.input = input;
    double exp = Math.exp(-1* input);
    output = 1/(1 + exp);

    return output;
    }
    
}

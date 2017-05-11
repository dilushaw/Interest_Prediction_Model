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
public class InputNode extends Node implements Serializable{

    public InputNode(String name, int id){
        super(name, id);
    }

    public double process(double input) {
    output = input;
    this.input = input;
    return output;
    }




}

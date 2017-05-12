/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

/**
 *
 * @author Dilusha
 */
public class BiasNode extends InputNode{

    public BiasNode(String name, int id){
        super(name, id);
    }

    @Override
    public double process(double input) {
    output = 1;
    this.input = input;
    return output;
    }
}

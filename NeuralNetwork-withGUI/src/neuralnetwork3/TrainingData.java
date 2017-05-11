/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork3;

import java.util.Vector;

/**
 *
 * @author Sinthu
 */
public class TrainingData {
private double[] input;
private double[] output;

public TrainingData(int outputSize){
    input = new double[4];
    output = new double[outputSize];
    for(int i=0; i<outputSize; i++){
        output[i] = 0.1;
    }
}

public void setInput(int inputId, double val){
    this.input[inputId-1] = val;
}

public void setOutput(int outputId, double val){
    this.output[outputId-1] = val;
}

    public double[] getInput() {
        return input;
    }

    public double[] getOutput() {
        return output;
    }


}

package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
public class DecisionLeaf implements ITreeNode {
    private String attribute;
    private String finalValue;

    /**
     * A class representing an outer node in the decision tree, a final decision, a leaf
     */
    public DecisionLeaf(String attribute, String finalValue){
        this.attribute = attribute;
        this.finalValue = finalValue;
    }

    /**
     * Implemented method from ITreeNode, gets decision from the DecisionLeaf
     * @param forDatum the datum to lookup a decision for
     * @return - the final value of this decision
     */
    @Override
    public String getDecision(Row forDatum) {
        forDatum.setAttributeValue(this.attribute, this.finalValue);
        return forDatum.getAttributeValue(this.attribute);
    }
}

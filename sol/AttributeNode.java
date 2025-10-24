package sol;

import java.util.ArrayList;
import java.util.List;
import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
public class AttributeNode implements ITreeNode {
    private String attribute;
    private String defaultFinal;
    private List<ValueEdge> outgoingEdges;

    /**
     * Constructor for an AttributeNode
     * @param attribute - the attribute of the node
     */
    public AttributeNode(String attribute) {
        this.attribute = attribute;
        this.outgoingEdges = new ArrayList<>();
    }

    /**
     * Implemented method from ITreeNode, gets decision from the AttributeNode
     * @param forDatum the datum to lookup a decision for
     * @return - either an outgoingEdge into another AttributeNode or a decision
     */
    @Override
    public String getDecision(Row forDatum) {
        if (forDatum.getAttributes().contains(this.attribute)) {
            if (this.getEdge(forDatum.getAttributeValue(this.attribute)) != null) {
                return this.getEdge(forDatum.getAttributeValue(this.attribute)).getChild().getDecision(forDatum);
            }
            else{
                return this.defaultFinal;
            }
        }
        else{
            return "Row does not include attribute: " + this.attribute ;
        }
    }

    /**
     * Adds the outgoingEdges
     * @param outgoingEdge - a ValueEdge of an AttributeNode that laeds to another node or decision
     */
    public void addOutgoingEdge(ValueEdge outgoingEdge) {
        this.outgoingEdges.add(outgoingEdge);
    }

    /**
     * Gets the ValueEdges of an AttributeNode
     * @param edge - outgoingEdge of an AttributeNode
     * @return - the ValueEdge that matches the String
     */
    public ValueEdge getEdge(String edge){
        List<ValueEdge> singleEdge = this.outgoingEdges.stream().filter(e -> e.getValue().equals(edge)).toList();//makes list of only one of the outgoing edges
        if(!singleEdge.isEmpty()) {
            return singleEdge.get(0);//gets first element of the list (there will always only be one since TreeGenerator does not make duplicate edges)
        }
        else{
           return null;
        }
    }

    /**
     * Sets the decision value of a node
     * @param finalValue - final decision
     */
    public void setFinalValue(String finalValue){
        this.defaultFinal = finalValue;

    }
}

package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    private String value;
    private ITreeNode child;

    /**
     * Assigns values of an attribute to an edge
     * @param value - results of an attribute
     */
    public ValueEdge(String value) {
        this.value = value;
    }

    /**
     * Setter method to assign a node for ValueEdge to connect to
     * @param child - the node
     */
    public void setChild(ITreeNode child) {
        this.child = child;
    }

    /**
     * Getter method to get the node that ValueEdge connects to
     * @return the node
     */
    public ITreeNode getChild(){
        return this.child;
    }

    /**
     * Gets the value that is assigned to the outgoingEdge
     * @return the value
     */
    public String getValue(){
        return this.value;
    }
}

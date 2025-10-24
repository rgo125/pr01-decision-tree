package sol;

import src.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {
    private String targetAttribute;
    private ITreeNode root;

    /**
     * Implements from ITreeGenerator
     * @param trainingData - the dataset to train on
     * @param targetAttribute - the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        this.targetAttribute = targetAttribute;
        this.root = this.createNode(trainingData);
    }

    /**
     * Helper method to create new nodes
     * @param trainingData - the dataset to train on
     * @return an attribute node
     */
    private ITreeNode createNode(Dataset trainingData){
        AttributeNode attNode = null; // Starts as null

        String attribute = trainingData.getAttributeToSplitOn();
        List<AttributeValue> attValueList = new ArrayList<>();

        trainingData.getDataObjects().forEach(row -> {
            String attValue = row.getAttributeValue(attribute); // Gets attribute value from attribute
            String targetAttValue = row.getAttributeValue(this.targetAttribute); // Get targetAttribute decision from targetAttribute
            attValueList.add(new AttributeValue(attValue, targetAttValue)); // Stores in a list of the attValue of each row as well as their values of targAtt
        });

        List<AttributeValue> list = attValueList.stream().distinct().toList(); // Removes duplicates
        List<String> keyList = list.stream().map(AttributeValue::getKey).distinct().toList(); // Gets the key(attValue) of each AttributeValue, so left with one of each edge

        for (String key : keyList){
            List<String> keyValues = list.stream().filter(e -> e.getKey().equals(key)).map(AttributeValue::getValue).distinct().toList();

            ValueEdge valueEdge = new ValueEdge(key);

            if (keyValues.size() > 1){ // If there are more than one result for an attribute result, generate a valueEdge that leads to an AttributeNode
                if (attNode == null) {
                    attNode = new AttributeNode(attribute);
                    attNode.setFinalValue(trainingData.mostCommonValue(this.targetAttribute));
                }
                Dataset updatedDataset = this.updateDataset(trainingData, attribute, key); // Remove this attribute form the data to continue
                valueEdge.setChild(this.createNode(updatedDataset));
            }
            else { // If there is only one result for an attribute result, generate a valueEdge that leads to a DecisionLeaf
                DecisionLeaf decisionLeaf = new DecisionLeaf(this.targetAttribute, keyValues.get(0));

                System.out.println("Attribute: " + attribute + " edge value: " + key + " decision: " + keyValues.get(0));
                if(attNode == null) {
                    attNode = new AttributeNode(attribute);
                    attNode.setFinalValue(trainingData.mostCommonValue(this.targetAttribute));
                }
                valueEdge.setChild(decisionLeaf);
            }
            attNode.addOutgoingEdge(valueEdge);
        }
        return attNode;
    }

    /**
     * Helper method to update the dataset for each node it goes through
     * @param trainingData - the dataset to train on
     * @param attribute - the attribute of the node
     * @param attributeValue - the result or value of that attribute
     * @return a new dataset with current attribute and its values removed
     */
    private Dataset updateDataset(IDataset trainingData, String attribute, String attributeValue){
        List<String> attributeList = new ArrayList<>();
        for(String attr : trainingData.getAttributeList()){
            attributeList.add(attr); //add attributes from dataset to this new list so that original is unaffected
        }
        attributeList.remove(attribute); // Removes the "current" attribute from the list

        List<Row> dataObjects = new ArrayList<>();
            trainingData.getDataObjects().forEach(row -> {
                if (row.getAttributeValue(attribute).equals(attributeValue)) {
                    dataObjects.add(row);
                }
            });
        return new Dataset(attributeList, dataObjects, trainingData.getSelectionType());
    }

    /**
     * Implemented from ITreeGenerator
     * @param datum the datum to look up a decision for
     * @return the decision of the row
     */
    public String getDecision(Row datum){
        return this.root.getDecision(datum);
    }
}
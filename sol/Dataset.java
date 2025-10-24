package sol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.AttributeSelection;
import src.IDataset;
import src.Row;

/**
 * A class representing a training dataset for the decision tree
 */
public class Dataset implements IDataset {
    private List<String> attributeList;
    private List<Row> dataObjects;
    private AttributeSelection selectionType;

    /**
     * Constructor for a Dataset object
     * @param attributeList - a list of attributes
     * @param dataObjects -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes.
     */
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {
        this.attributeList = attributeList;
        this.dataObjects = dataObjects;
        this.selectionType = attributeSelection;
    }

    /**
     * Implemented from IDataset
     * @return - a list of attributes
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    /**
     * Implemented from IDataset
     * @return - a list of rows
     */
    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    /**
     * Implemented from IDataset
     * @return - an enum for which way to select attributes.
     */
    @Override
    public AttributeSelection getSelectionType() {
        return this.selectionType;
    }

    /**
     * Getter method that returns the size of dataObjects (amount of rows in List<Row>)
     * @return - size of list
     */
    @Override
    public int size() {
        if (this.dataObjects != null) {
            return this.dataObjects.size();
        }
        else {
            return 0;
        }
    }

    /**
     * Looks at attributeList and chooses an attribute to be the root node
     * @return - an attribute
     */
    public String getAttributeToSplitOn() {
        switch (this.selectionType) {
            case ASCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
            }
            case RANDOM -> {
                // Math.random is a number between 0 and 1, multiplied with the list size gives a random number within the list
                int random = (int)(Math.random() * this.attributeList.size());
                return this.attributeList.stream().sorted().toList().get(random);
            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }

    /**
     * Helper method calculates the most common targetAttribute value of a set attribute dataset
     * @param attribute - the specific attribute of the dataset
     * @return - the most common targetAttribute value
     */
    public String mostCommonValue(String attribute){
        List<String> allOutcomes = new ArrayList<>();
        for(Row data : this.dataObjects){
            allOutcomes.add(data.getAttributeValue(attribute));
        }
        String mostCommon = allOutcomes.get(0);
        int outerCount = 0;
        for (int i = 0; i < allOutcomes.size(); i++) {
            int innerCount = 0;
            for (String allOutcome : allOutcomes) {
                if (allOutcomes.get(i).equals(allOutcome)) {
                    innerCount++;
                }
                if (outerCount < innerCount) {
                    mostCommon = allOutcomes.get(i);
                    outerCount = innerCount;
                }
            }
        }
       return mostCommon;
    }
}

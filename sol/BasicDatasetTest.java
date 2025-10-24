package sol;

import org.junit.Assert;
import org.junit.Test;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

/**
 * A class to test basic decision tree functionality on a basic training dataset
 */
public class BasicDatasetTest {
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)

    String trainingPath = "data/risd-majors.csv";
    String targetAttribute = "major";
    TreeGenerator testGenerator;

    /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        this.testGenerator = new TreeGenerator();
        this.testGenerator.generateTree(training, this.targetAttribute);
    }

    /**
     * Tests the expected classification of the "tangerine" row is a fruit
     */
    @Test
    public void testClassification() {
        Row student1 = new Row("fineArt");
        student1.setAttributeValue("canDraw", "false");
        student1.setAttributeValue("isConceptual", "false");
        student1.setAttributeValue("caresAboutMoney", "true");

        Assert.assertEquals("design", this.testGenerator.getDecision(student1));
    }
}

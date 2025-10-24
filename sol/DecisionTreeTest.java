package sol;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.AttributeSelection;
import src.DecisionTreeTester;
import src.Row;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
    private static final String DATA_BASE = "data/";
    private static final String IS_VILLAIN = "isVillain";
    private static final String VILLAINS_BASE = DATA_BASE + "villains/";
    private static final String VILLAINS_TRAINING = VILLAINS_BASE + "training.csv";
    private static final String VILLAINS_TESTING = VILLAINS_BASE + "testing.csv";
    private static final String IS_POISONOUS = "isPoisonous";
    private static final String MUSHROOMS_BASE = DATA_BASE + "mushrooms/";
    private static final String MUSHROOMS_TRAINING = MUSHROOMS_BASE + "training.csv";
    private static final String MUSHROOMS_TESTING = MUSHROOMS_BASE + "testing.csv";
    List<String> majorAttributes;
    List<String> cafeteriaAttributes;
    List<Row> majorDataObjects;
    List<Row> cafeteriaDataObjects;
    Dataset majorTestData;
    Dataset cafeteriaTestData;

    /**
     * Sets up data to test risd-majors.csv dataset
     */
    @Before
    public void majorsSetup() {
        this.majorAttributes = new ArrayList<>();
        this.majorAttributes.add("Can draw");
        this.majorAttributes.add("Conceptual");
        this.majorAttributes.add("Cares about money");
        this.majorAttributes.add("Major");

        Row studentA = new Row("studentA");
        studentA.setAttributeValue("Can draw", "true");
        studentA.setAttributeValue("Conceptual", "false");
        studentA.setAttributeValue("Cares about money", "false");
        studentA.setAttributeValue("Major", "Design");

        Row studentB = new Row("student B");
        studentB.setAttributeValue("Can draw", "true");
        studentB.setAttributeValue("Conceptual", "true");
        studentB.setAttributeValue("Cares about money", "false");
        studentB.setAttributeValue("Major", "Fine art");

        Row studentC = new Row("studentC");
        studentC.setAttributeValue("Can draw", "true");
        studentC.setAttributeValue("Conceptual", "true");
        studentC.setAttributeValue("Cares about money", "true");
        studentC.setAttributeValue("Major", "Design");

        Row studentD = new Row("studentD");
        studentD.setAttributeValue("Can draw", "false");
        studentD.setAttributeValue("Conceptual", "true");
        studentD.setAttributeValue("Cares about money", "true");
        studentD.setAttributeValue("Major", "Fine art");

        Row studentE = new Row("studentE");
        studentE.setAttributeValue("Can draw", "true");
        studentE.setAttributeValue("Conceptual", "false");
        studentE.setAttributeValue("Cares about money", "true");
        studentE.setAttributeValue("Major", "Design");

        this.majorDataObjects = new ArrayList<>();
        this.majorDataObjects.add(studentA);
        this.majorDataObjects.add(studentB);
        this.majorDataObjects.add(studentC);
        this.majorDataObjects.add(studentD);
        this.majorDataObjects.add(studentE);

        this.majorTestData = new Dataset(this.majorAttributes, this.majorDataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
    }

    /**
     * Sets up data to test risd-cafeterias.csv dataset
     */
    @Before
    public void cafeteriasSetup() {
        this.cafeteriaAttributes = new ArrayList<>();
        this.cafeteriaAttributes.add("year");
        this.cafeteriaAttributes.add("dorm");
        this.cafeteriaAttributes.add("isSocial");
        this.cafeteriaAttributes.add("cafeteria");

        Row student1 = new Row("student1");
        student1.setAttributeValue("year", "freshman");
        student1.setAttributeValue("dorm", "theQuad");
        student1.setAttributeValue("isSocial", "false");
        student1.setAttributeValue("cafeteria", "met");

        Row student2 = new Row("student 2");
        student2.setAttributeValue("year", "freshman");
        student2.setAttributeValue("dorm", "theQuad");
        student2.setAttributeValue("isSocial", "true");
        student2.setAttributeValue("cafeteria", "met");

        Row student3 = new Row("student 3");
        student3.setAttributeValue("year", "nonFreshman");
        student3.setAttributeValue("dorm", "fifteenWest");
        student3.setAttributeValue("isSocial", "false");
        student3.setAttributeValue("cafeteria", "portfolio");

        Row student4 = new Row("student 4");
        student4.setAttributeValue("year", "nonFreshman");
        student4.setAttributeValue("dorm", "theQuad");
        student4.setAttributeValue("isSocial", "true");
        student4.setAttributeValue("cafeteria", "met");

        Row student5 = new Row("student 4");
        student5.setAttributeValue("year", "nonFreshman");
        student5.setAttributeValue("dorm", "fifteenWest");
        student5.setAttributeValue("isSocial", "true");
        student5.setAttributeValue("cafeteria", "met");

        Row student6 = new Row("student 5");
        student6.setAttributeValue("year", "gradStudent");
        student6.setAttributeValue("dorm", "fifteenWest");
        student6.setAttributeValue("isSocial", "true");
        student6.setAttributeValue("cafeteria", "portfolio");

        this.cafeteriaDataObjects = new ArrayList<>();
        this.cafeteriaDataObjects.add(student1);
        this.cafeteriaDataObjects.add(student2);
        this.cafeteriaDataObjects.add(student3);
        this.cafeteriaDataObjects.add(student4);
        this.cafeteriaDataObjects.add(student5);
        this.cafeteriaDataObjects.add(student6);

        this.cafeteriaTestData = new Dataset(this.cafeteriaAttributes, this.cafeteriaDataObjects, AttributeSelection.DESCENDING_ALPHABETICAL);
    }

    /**
     * Tests getAttributeList() in Dataset class
     */
    @Test
    public void testGetAttributeList(){
        Assert.assertEquals(this.majorTestData.getAttributeList(), this.majorAttributes);
        Assert.assertEquals(this.cafeteriaTestData.getAttributeList(), this.cafeteriaAttributes);
    }

    /**
     * Tests getDecision() in TreeGenerator class
     */
    @Test
    public void testGetDecision(){
        TreeGenerator majorTree = new TreeGenerator();
        TreeGenerator cafeteriaTree = new TreeGenerator();

        majorTree.generateTree(this.majorTestData, "Major"); //can set the target attribute in the generateTree method in TreeGenerator
        cafeteriaTree.generateTree(this.cafeteriaTestData, "cafeteria"); //can set the target attribute in the generateTree method in TreeGenerator

        Assert.assertEquals(majorTree.getDecision(this.majorTestData.getDataObjects().get(0)),"Design");
        Assert.assertEquals(cafeteriaTree.getDecision(this.cafeteriaTestData.getDataObjects().get(0)),"met");

        // For testing majors

        Row studentNew = new Row("New student");
        studentNew.setAttributeValue("Can draw", "false");
        studentNew.setAttributeValue("Conceptual", "false");
        studentNew.setAttributeValue("Cares about money", "false");

        Assert.assertEquals(majorTree.getDecision(studentNew), "Fine art");

        Row studentNewNew = new Row("New new student");
        studentNewNew.setAttributeValue("Can draw", "true");
        studentNewNew.setAttributeValue("Conceptual", "true");
        studentNewNew.setAttributeValue("Cares about money", "true");

        Assert.assertEquals(majorTree.getDecision(studentNewNew), "Design");

        Row studentNewNewNew = new Row("Brand new student");
        studentNewNewNew.setAttributeValue("Can draw", "maybe");
        studentNewNewNew.setAttributeValue("Conceptual", "I guess");
        studentNewNewNew.setAttributeValue("Cares about money", "sure");

        Assert.assertEquals(majorTree.getDecision(studentNewNewNew), "Design");

        Row studentEdgeCase = new Row("Edge case student");
        studentEdgeCase.setAttributeValue("Can draw", "true");
        studentEdgeCase.setAttributeValue("Conceptual", "I guess");
        studentEdgeCase.setAttributeValue("Cares about money", "false");

        Assert.assertEquals(majorTree.getDecision(studentEdgeCase), "Design");

        Row newStudentEdgeCase = new Row("New edge case student");
        newStudentEdgeCase.setAttributeValue("Can draw", "true");
        newStudentEdgeCase.setAttributeValue("Conceptual", "I guess");
        newStudentEdgeCase.setAttributeValue("Cares about money", "true");

        Assert.assertEquals(majorTree.getDecision(newStudentEdgeCase), "Design");

        Row newNewStudentEdgeCase = new Row("New new edge case student");
        newNewStudentEdgeCase.setAttributeValue("Can draw", "false");
        newNewStudentEdgeCase.setAttributeValue("Conceptual", "I guess");
        newNewStudentEdgeCase.setAttributeValue("Cares about money", "maybe");

        Assert.assertEquals(majorTree.getDecision(newNewStudentEdgeCase), "Fine art");

        // For testing cafeterias

        Row newStudent1 = new Row("new student1");
        newStudent1.setAttributeValue("year", "freshman");
        newStudent1.setAttributeValue("dorm", "fifteenWest");
        newStudent1.setAttributeValue("isSocial", "false");

        Assert.assertEquals(cafeteriaTree.getDecision(newStudent1), "met");

        Row newStudent2 = new Row("new student2");
        newStudent2.setAttributeValue("year", "nonFreshman");
        newStudent2.setAttributeValue("dorm", "theQuad");
        newStudent2.setAttributeValue("isSocial", "kinda");

        Assert.assertEquals(cafeteriaTree.getDecision(newStudent2), "met");

        Row newStudent3 = new Row("new student3");
        newStudent3.setAttributeValue("year", "gradStudent");
        newStudent3.setAttributeValue("dorm", "offCampus");
        newStudent3.setAttributeValue("isSocial", "false");

        Assert.assertEquals(cafeteriaTree.getDecision(newStudent3), "portfolio");
    }

    /**
     * Testing mostCommonValue() in Dataset class
     */
    @Test
    public void testMostCommon(){
        Assert.assertEquals("Design", this.majorTestData.mostCommonValue("Major"));
        Assert.assertEquals("true", this.majorTestData.mostCommonValue("Can draw"));

        Assert.assertEquals("met", this.cafeteriaTestData.mostCommonValue("cafeteria"));
    }

    /**
     * Testing mushrooms dataset
     */
    @Test
    public void testMushrooms(){
        DecisionTreeTester<TreeGenerator, Dataset> tester;
        try {
            tester = new DecisionTreeTester<>(TreeGenerator.class, Dataset.class);
            Dataset trainingData = DecisionTreeTester.makeDataset(MUSHROOMS_TRAINING, Dataset.class);
            double accuracy =
                    tester.getDecisionTreeAccuracy(trainingData, trainingData, IS_POISONOUS);
            System.out.println("Accuracy on training data: " + accuracy);
            Assert.assertTrue(accuracy > 0.95);


            int numIters = 100;
            Dataset testingData = DecisionTreeTester.makeDataset(MUSHROOMS_TESTING, Dataset.class);
            accuracy = tester.getAverageDecisionTreeAccuracy(trainingData, testingData, IS_POISONOUS, numIters);
            System.out.println("Accuracy on testing data: " + accuracy);
            Assert.assertTrue(accuracy > 0.7);


        } catch (InstantiationException | InvocationTargetException
                 | NoSuchMethodException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Testing villans dataset
     */
    @Test
    public void testVillains(){
        DecisionTreeTester<TreeGenerator, Dataset> tester;
        try {
            tester = new DecisionTreeTester<>(TreeGenerator.class, Dataset.class);
            Dataset trainingData = DecisionTreeTester.makeDataset(VILLAINS_TRAINING, Dataset.class);
            double accuracy =
                    tester.getDecisionTreeAccuracy(trainingData, trainingData, IS_VILLAIN);
            System.out.println("Accuracy on training data: " + accuracy);
            Assert.assertTrue(accuracy > 0.95);


            int numIters = 100;
            Dataset testingData = DecisionTreeTester.makeDataset(VILLAINS_TESTING, Dataset.class);
            accuracy = tester.getAverageDecisionTreeAccuracy(trainingData, testingData, IS_VILLAIN, numIters);
            System.out.println("Accuracy on testing data: " + accuracy);
            Assert.assertTrue(accuracy > 0.7);


        } catch (InstantiationException | InvocationTargetException
                 | NoSuchMethodException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}


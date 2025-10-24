# Repository Overview

This repository contains the following Java files:

---

### **AttributeNode**
A class that represents a node in a decision tree.  
Implements the `ITreeNode` interface.

---

### **BasicDatasetTest**
A class containing a few simple tests for your decision tree implementation.

---

### **Dataset**
A class that represents a training dataset.  
This can be thought of as a table where:
- Each **column** represents an attribute.
- Each **row** represents an item in the data (rows are represented by the `Row` class).  

Implements the `IDataset` interface.

---

### **DecisionLeaf**
A class that represents a leaf in a decision tree.  
Implements the `ITreeNode` interface.

---

### **DecisionTreeTest**
This is where you will write your tests!

---

### **TreeGenerator**
A class responsible for creating the decision tree given a dataset.  
Implements the `ITreeGenerator` interface.

---

### **ValueEdge**
A class that represents an edge in the decision tree.

---

### **DecisionTreeCSVParser**
A class that provides a static method which takes in a CSV file of data and outputs a list of rows.  
**Note:** You should **not** edit this class.

---

### **DecisionTreeTester**
A class that provides methods for testing the accuracy of your decision tree on testing data.  
Some lines are marked for you to modify (to change which dataset this runs on).  
Otherwise, you should **not** edit anything else in this class.

---

### **IDataset**
An interface containing methods for retrieving information about a dataset.  
**Do not edit** this interface.

---

### **ITreeGenerator**
An interface containing methods for generating a decision tree and getting a decision for a `Row`.  
**Do not edit** this interface.

---

### **ITreeNode**
An interface containing a method to get a decision given a `Row`.  
**Do not edit** this interface.

---

### **Row**
A class that represents a row in a dataset.  
**Do not edit** this class.

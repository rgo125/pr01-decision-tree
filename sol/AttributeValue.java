package sol;

/**
 * A class connecting the result of an attribute to the value of the targetAttribute
 */
public class AttributeValue {
    private String key;
    private String value;

    /**
     * Constructor of AttributeValue
     * @param key - the value of an attribute
     * @param value - the value of a targetAttribute
     */
    public AttributeValue(String key, String value) {
        this.key = key;
        this.value = value;

    }

    /**
     * Getter method
     * @return the value of an attribute
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Getter method
     * @return the value of a targetattribute
     */
    public String getValue() {
        return this.value;
    }
}

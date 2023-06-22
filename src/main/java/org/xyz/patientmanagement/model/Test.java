package org.xyz.patientmanagement.model;

/**
 * @author inanmashrur
 * @since 6/8/2023
 */
public class Test {

    private String testName;

    private String additionalInfo;

    public Test() {
    }

    public Test(String testName, String additionalInfo) {
        this.testName = testName;
        this.additionalInfo = additionalInfo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}

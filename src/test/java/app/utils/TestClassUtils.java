package app.utils;

public class TestClassUtils {
    private String variety = "testVariety";
    public double size = 0;

    public String getVariety() {
        return this.variety;
    }

    public void setVariety(String newVariety) {
        this.variety = newVariety;
    }

    public double varietyToDouble(Object other) {
        TestClassUtils otherClass = (TestClassUtils) other;
        return this.variety.equals(otherClass.getVariety()) ? 1 : 0;
    }
}

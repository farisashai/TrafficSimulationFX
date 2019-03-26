package TRAFFICSIM;
class Staircase {
    private int bottomX, bottomY, topX, topY;
    private String building;
    private int studentCounter = 0;
    public Staircase(int bottomX, int bottomY, int topX, int topY, String building) {
        this.bottomX = bottomX;
        this.bottomY = bottomY;
        this.topX = topX;
        this.topY = topY;
        this.building = building;
    }
    public int getBottomX() {
        return bottomX;
    }
    public int getBottomY() {
        return bottomY;
    }
    public int getTopX() {
        return topX;
    }
    public int getTopY() {
        return topY;
    }
    public String getBuilding() {
        return building;
    }
    public void resetCounter() {
        studentCounter = 0;
    }
    public void increment() {
        studentCounter++;
    }
    public int getStudentCounter() {
        return studentCounter;
    }
}
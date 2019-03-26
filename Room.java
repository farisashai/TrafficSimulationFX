package TRAFFICSIM;


class Room {


    private String roomName;
    private int mapX, mapY, trueX, trueY;
    private double radius;

    /**
     * DOWNSTAIRS ROOM
     * @param roomName
     * @param mapX
     * @param mapY
     * @param radius
     */
    public Room(String roomName, int mapX, int mapY, double radius) {
        this.roomName = roomName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.trueX = mapX;
        this.trueY = mapY;
        this.radius = radius;
    }
    /**
     * DOWNSTAIRS CLASSROOM
     * @param roomName
     * @param mapX
     * @param mapY
     */
    public Room(String roomName, int mapX, int mapY) {
        this.roomName = roomName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.trueX = mapX;
        this.trueY = mapY;
        this.radius = Data.classRadius;
    }
    /**
     * UPSTAIRS CLASSROOM
     * @param roomName
     * @param mapX
     * @param mapY
     * @param trueX
     * @param trueY
     */
    public Room(String roomName, int mapX, int mapY, int trueX, int trueY) {
        this.roomName = roomName;
        this.mapX = mapX;
        this.mapY = mapY;
        this.trueX = trueX;
        this.trueY = trueY;
        this.radius = Data.classRadius;
    }

    public static int[] randSpotInside(int x, int y, double rad) {
        int angle = (int)(Math.random() * 360);
        double radius = Math.random() * rad;

        return new int[] {
                x+(int)(radius*Math.cos(angle)),
                y+(int)(radius*Math.sin(angle))
        };
    }


    public String getRoomName() {
        return roomName;
    }
    public int getMapX() {
        return mapX;
    }
    public int getMapY() {
        return mapY;
    }
    public int getTrueX() {
        return trueX;
    }
    public int getTrueY() {
        return trueY;
    }


    boolean isClass() {
        return roomName.matches("[A-Z]\\d{2,3}");
    }
    public boolean isUpstairs() {
        return mapX != trueX;
    }
    public String getBuilding() {
        return isClass() ? "" + roomName.charAt(0) : "";
    }
    public int[] randSpotInside() {
        int angle = (int)(Math.random() * 360);
        double radius = Math.random() * this.radius;

        return new int[] {
                mapX+(int)(radius*Math.cos(angle)),
                mapY+(int)(radius*Math.sin(angle))
        };
    }
}
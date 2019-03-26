package TRAFFICSIM;
import java.util.LinkedList;
import java.util.List;
class Database {
    private static List<Room> roomList;
    private static List<Staircase> staircaseList;
    static {
        roomList = new LinkedList<>();
        staircaseList = new LinkedList<>();
        for (String[] room: Data.ROOMS) {
            switch (room.length) {
                case 3: roomList.add(new Room(room[0],Integer.parseInt(room[1]),Integer.parseInt(room[2]))); break;
                case 4: roomList.add(new Room(room[0],Integer.parseInt(room[1]),Integer.parseInt(room[2]),Double.parseDouble(room[3]))); break;
                case 5: roomList.add(new Room(room[0],Integer.parseInt(room[1]),Integer.parseInt(room[2]),Integer.parseInt(room[3]),Integer.parseInt(room[4]))); break;
            }
        }
        for (String[] stairs: Data.staircases) {
            staircaseList.add(new Staircase(Integer.parseInt(stairs[0]),Integer.parseInt(stairs[1]),Integer.parseInt(stairs[2]),Integer.parseInt(stairs[3]),stairs[4]));
        }
    }
    public static Staircase getNearestStaircase(Room room) {
        Staircase closest = staircaseList.get(0);
        for (Staircase s: staircaseList)
            if (s.getBuilding().equals(room.getBuilding()))
                if (distBtwn(s.getBottomX(),s.getBottomY(),room.getTrueX(),room.getTrueY()) < distBtwn(closest.getBottomX(),closest.getBottomY(),room.getTrueX(),room.getTrueY()))
                    closest = s;
                closest.increment();
        return closest;
    }
    public static double distBtwn(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
    public static Room[] generateSchedule() {
        Room[] schedule = new Room[7];
        schedule[0] = Database.getRandClass(Data.percentZero/100.);
        schedule[1] = Database.getRandClass();
        schedule[2] = Database.getRandClass();
        schedule[3] = Database.getRandClass();
        schedule[4] = Database.getRandClass();
        schedule[5] = Database.getRandClass(Data.percentFifth/100.);
        schedule[6] = Database.getRandClass(schedule[5] == null ? 0 : Data.percentSixth/100.);
        return schedule;
    }
    public static Room getRandRoom() {
        return roomList.get((int)(Math.random()*roomList.size()));
    }
    public static Room getRandClass() {
        Room r;
        while (!(r = getRandRoom()).isClass())
            r = getRandRoom();
        return r;
    }
    public static Room getRoom(String name) {
        for (Room r: roomList)
            if (r.getRoomName().equals(name))
                return r;

            return null;
    }
    public static Room getRandRoom(String name) {
        List<Room> list = new LinkedList<>();
        for (Room r: roomList)
            if (r.getRoomName().equals(name))
                list.add(r);
        return list.get((int)(Math.random()*list.size()));
    }
    public static Room getRandClass(double probabilityPresent) {
        if (Math.random() > probabilityPresent)
            return null;
        return getRandClass();
    }
    public static List<Staircase> getStaircaseList() {
        return staircaseList;
    }
    public static void resetStairCounters() {
        for (Staircase s: staircaseList)
            s.resetCounter();
    }
}
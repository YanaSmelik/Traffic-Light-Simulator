package traffic.program;

import traffic.error.TrafficErrorHandler;

import java.util.List;
import java.util.Scanner;

public class ProgramManagement {
    private int roads;
    private int intervals;
    private final Scanner scanner = new Scanner(System.in);
    private RoadQueue roadsQueue;

    public void setRoads(int roads) {
        if (roads < 1) {
            throw new IllegalArgumentException();
        } else {
            this.roads = roads;
        }
    }

    public void setIntervals(int intervals) {
        if (intervals < 1) {
            throw new IllegalArgumentException();
        } else {
            this.intervals = intervals;
        }
    }

    public void getInputValues() {
        boolean invalidInput = true;
        System.out.print("Input the number of roads: ");
        do {
            try {
                setRoads(scanner.nextInt());
                roadsQueue = new RoadQueue(getRoads());
                invalidInput = false;
            } catch (Exception e){
                TrafficErrorHandler.printInputErrorMessage();
            } finally {
                scanner.nextLine();
            }
        } while (invalidInput);

        invalidInput = true;

        System.out.print("Input the interval: ");
        do {
            try {
                setIntervals(scanner.nextInt());
                invalidInput = false;
            } catch (Exception e){
                TrafficErrorHandler.printInputErrorMessage();
            } finally {
                scanner.nextLine();
            }
        } while (invalidInput);
    }

    public void addRoad() {
        System.out.print("Input road name: ");
        Scanner scanner = new Scanner(System.in);
        String roadName = scanner.nextLine();
        Road road = new Road(roadName);

        if (roadsQueue.enqueue(road)) {
            System.out.println(road.getName() + " Added!");
        }
    }

    public void deleteRoad() {
        Road deletedRoad = roadsQueue.dequeue();
        if(deletedRoad != null) {
            System.out.println(deletedRoad.getName() + " delete");
        }
    }

    public List<Road> getRoadsList() {
       return roadsQueue.printElements();
    }

    public int getRoads() {
        return roads;
    }

    public int getIntervals() {
        return intervals;
    }
}

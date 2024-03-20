package traffic.program;

import java.util.ArrayList;
import java.util.List;

public class RoadState extends Thread {
    private int interval;
    private List<Road> roads;
    private int openRoadIndex;

    public RoadState() {
        roads = new ArrayList<>();
    }

//    @Override
//    public void run()  {
//
//    }

    public void manageRoadActivity() {
        int remainingTime;
        setRoadStatuses();
        for (Road road : roads) {
            if(road.getRemainingTime() == -1) {
                road.setRemainingTime(interval);
                if (roads.size() != 1) {
                    setRoadsRemainingTime();
                }
                break;
            } else {
                remainingTime = road.getRemainingTime();
                if (remainingTime < 0) {
                    road.setOpen(false);
                } else {
                    remainingTime--;
                    road.setRemainingTime(remainingTime);
                }
            }
        }
        if(roads.size() > 0) {
            changeOpenRoad();
        }

    }

    private Road getOpenRoad() {
        return roads.get(openRoadIndex);
    }

    private void changeOpenRoad() {
        Road currentOpenRoad = getOpenRoad();
        if(currentOpenRoad.getRemainingTime() == 0) {
            if(roads.size() > 1) {
                openRoadIndex++;
            }
            if (openRoadIndex >= roads.size()) {
                openRoadIndex = 0;
            }
            Road newOpenRoad = roads.get(openRoadIndex);
            newOpenRoad.setOpen(true);
            newOpenRoad.setRemainingTime(interval);

            if (currentOpenRoad != newOpenRoad) {
                currentOpenRoad.setOpen(false);
                currentOpenRoad.setRemainingTime(newOpenRoad.getRemainingTime());
            }

           setRoadsRemainingTime();
        }
    }

    private void setRoadsRemainingTime(){
        Road openRoad = roads.get(openRoadIndex);
        int previousRoadRemainingTime = openRoad.getRemainingTime();

        for (int i = openRoadIndex + 1; i < roads.size(); i++) {
            if(i == openRoadIndex + 1) {
                roads.get(i).setRemainingTime(previousRoadRemainingTime);
            } else {
                roads.get(i).setRemainingTime(previousRoadRemainingTime);
            }
            previousRoadRemainingTime = roads.get(i).getRemainingTime() + interval;
        }

        for (int i = 0; i < openRoadIndex; i++) {
            roads.get(i).setRemainingTime(previousRoadRemainingTime);
            previousRoadRemainingTime = roads.get(i).getRemainingTime() + interval;
        }
    }

    private void setRoadStatuses() {
        int numOfOpen = 0;
        for (int i = 0; i < roads.size(); i++) {
            if (roads.get(i).isOpen() && numOfOpen < 1) {
                openRoadIndex = i;
                numOfOpen++;
                continue;
            }
            roads.get(i).setOpen(false);
        }
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<Road> getRoads() {
        return roads;
    }
}

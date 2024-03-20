package traffic.program;


import java.util.List;

public class SystemState extends Thread {
    private static int COUNTER = 0;
    private boolean isSystemMode = false;
    private boolean isRunning;
    private final ProgramManagement program;
    private final RoadState roadState;

    public SystemState(ProgramManagement program) {
        this.program = program;
        isRunning = true;
        roadState = new RoadState();
    }

    @Override
    public void run() {
        int remainingTime = program.getIntervals();
        roadState.setInterval(remainingTime);

        while(isRunning) {
            roadState.setRoads(program.getRoadsList());
            roadState.manageRoadActivity();

            if (isSystemMode) {
                String roads = printRoads(roadState.getRoads());
                String result = "";
                result = "! " + COUNTER + "s. have passed since system startup !\n";
                result += "! Number of roads: " + program.getRoads() + " !\n";
                result += "! Interval: " + program.getIntervals() + " !\n";
                result += roads;
                result += "! Press \"Enter\" to open menu !";
                printSystemState(result);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            COUNTER++;
        }
    }

    private synchronized void printSystemState(String result) {
        System.out.println(result);
    }

    private String printRoads(List<Road> roads) {
        String result = "";
        for (Road road : roads) {
            String status = road.isOpen() ? "open" : "closed";
            String color = road.isOpen() ? "\u001B[32m" : "\u001B[31m";
            result += color + "Road \"" + road.getName()
                    + "\" will be " + status
                    + " for " + road.getRemainingTime() + "s."
                    + "\u001B[0m" + "\n";
        }
        return result;
    }

    public void setSystemMode(boolean systemMode) {
        isSystemMode = systemMode;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}

package traffic.program;

public class Road {
    private final String name;
    private int remainingTime;
    private boolean isOpen;

    public Road(String name) {
        this.name = name;
        isOpen = true;
        remainingTime = -1;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}

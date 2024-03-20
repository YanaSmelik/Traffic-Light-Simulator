package traffic.program;

import java.io.IOException;
import java.util.Scanner;

public class ProgramMenu {
    private int selectedOption;
    private final Scanner scanner = new Scanner(System.in);
    public ProgramMenu() {
        selectedOption = -1;
    }

    public void setSelectedOption(int selectedOption) {
        if (selectedOption > 3 || selectedOption < 0) {
            System.out.println("Incorrect option");
            scanner.nextLine();
        } else {
            this.selectedOption = selectedOption;
        }
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void printInformation(int selectedOption, SystemState queueThread, ProgramManagement program)
            throws IOException {
        switch (selectedOption) {
            case 1:
                program.addRoad();
                clearConsole(scanner);
                break;
            case 2:
                program.deleteRoad();
                clearConsole(scanner);
                break;
            case 3:
                queueThread.setSystemMode(true);
                System.in.read();
                queueThread.setSystemMode(false);
                break;
            case 0 :
                System.out.println("Bye!");
                queueThread.setRunning(false);
        }
    }

    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. System");
        System.out.println("0. Quit");
    }

    public void clearConsole(Scanner scanner) {
        scanner.nextLine();
    }
}

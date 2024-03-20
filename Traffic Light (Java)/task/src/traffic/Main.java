package traffic;

import traffic.program.ProgramManagement;
import traffic.program.ProgramMenu;
import traffic.program.SystemState;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the traffic management system!");
    ProgramManagement program = new ProgramManagement();
    program.getInputValues();

    ProgramMenu menu = new ProgramMenu();

    SystemState queueThread = new SystemState(program);
    queueThread.setName("QueueThread");
    queueThread.start();

    do {
     menu.printMenu();
     try {
       menu.setSelectedOption(scanner.nextInt());
     } catch (Exception e) {
         System.out.println("Incorrect option");
         menu.clearConsole(scanner);
     } finally {
         menu.clearConsole(scanner);
     }
     menu.printInformation(menu.getSelectedOption(), queueThread, program);
    } while (menu.getSelectedOption() != 0);
  }
}

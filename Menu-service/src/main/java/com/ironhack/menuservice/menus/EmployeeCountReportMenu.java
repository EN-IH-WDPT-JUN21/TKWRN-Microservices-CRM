package com.ironhack.menuservice.menus;

import com.ironhack.menuservice.dao.Variables;
import com.ironhack.menuservice.enums.ReportCommands;
import com.ironhack.menuservice.exceptions.NoSuchValueException;
import com.ironhack.menuservice.proxy.AccountReportServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Scanner;

@Component
public class EmployeeCountReportMenu implements Variables {

    @Autowired
    AccountReportServiceProxy accountReportServiceProxy;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;

    public void employeeCountReportMenu() throws NoSuchValueException, AWTException {

        System.out.println("\n" + colorHeadline + colorLogo
       + "                                                                                                \n" +
       "                                         *#### #####        ###################*   *####*         \n" +
       "                         #################### #####        ######################  #####          \n" +
       "                    ,######              ### #####        #####            ###### #####           \n" +
       "                  ####                  ### #####        #####    ############## #####            \n" +
       "                ####                   ### #####        #####      ###########  #####             \n" +
       "              ########################### #####        #####            ###### #####              \n" +
       "             ####################.###### ############ ###################### ############         \n" +
       "             ################ ####### # ############ #####################  ############          \n" + reset +
       colorMain + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
       + "║                                                 " + colorTable + "EMPLOYEE COUNT REPORTING MENU" + colorMain + "                                    ║\n"
       + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
       + "║ 1. Display MEAN EmployeeCount for Accounts" + colorHeadline + "- type: 'mean employeecount'" + colorMain + "                                           ║\n"
       + "║ 2. Display MEDIAN EmployeeCount for Accounts" + colorHeadline + "- type: 'median employeecount'" + colorMain + "                                       ║\n"
       + "║ 3. Display MAXIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'max employeecount'" + colorMain + "                                         ║\n"
       + "║ 4. Display MINIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'min employeecount'" + colorMain + "                                         ║\n"
       + "║ 5. To return to the Report menu " + colorHeadline + "- type: 'back'" + colorMain + "                                                                   ║\n"
       + "║ 6. To return to the Main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                                                ║\n"
       + "║ 7. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                                        ║\n"
       + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        Scanner scanner = new Scanner(System.in);
        try {

            // Creates String from scanner input
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 1) {
                throw new IllegalArgumentException();
            } else {
                switch (ReportCommands.getCommandType(input)){
                    case MEAN_EMPCOUNT:
                        System.out.println(colorMain + "\n╔════════════════════════════════════════════════════╗");
                        System.out.println("║" + colorTable + "  Average number of employees is: " + accountReportServiceProxy.findMeanEmployeeCount().get() + colorMain + "             ║");
                        System.out.println("╚════════════════════════════════════════════════════╝" + reset);
                        break;
                    case MEDIAN_EMPCOUNT:
                        System.out.println("Median number of employees is: " + getMedian(accountReportServiceProxy.findMedianEmployeeCountStep1()));
                        break;
                    case MAX_EMPCOUNT:
                        System.out.println("Maximum number of employees is: " + accountReportServiceProxy.findMaxEmployeeCount().get());
                        break;
                    case MIN_EMPCOUNT:
                        System.out.println("Minimum number of employees is: " + accountReportServiceProxy.findMinEmployeeCount().get());
                        break;
                    case BACK: reportMainMenu.reportMainMenu();
                    case MAIN_MENU: mainMenu.OS();
                        break;
                    case QUIT:
                        System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                        System.out.println(colorError + "Exiting the program" + reset);
                        System.exit(0);
                        break;
                    default: throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException  e) {
            System.out.println(colorError + "\nInvalid input" + reset);
        }

        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
//        employeeCountReportMenu();
    }

    public int getMedian(int[] intArray){
        try {
            int sizeOfArray = intArray.length;
            if (sizeOfArray % 2 == 1) {
                return intArray[(sizeOfArray + 1) / 2 - 1];
            } else {
                return (intArray[sizeOfArray / 2 - 1] + intArray[sizeOfArray / 2]) / 2;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
}

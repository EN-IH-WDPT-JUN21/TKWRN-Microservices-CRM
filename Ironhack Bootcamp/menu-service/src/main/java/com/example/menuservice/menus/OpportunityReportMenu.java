package com.example.menuservice.menus;



import com.example.menuservice.dao.Variables;
import com.example.menuservice.enums.ReportCommands;
import com.example.menuservice.exceptions.NoSuchValueException;
import com.example.menuservice.proxy.OppReportServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Scanner;

@Component
public class OpportunityReportMenu implements Variables {

    private OppReportServiceProxy oppReportServiceProxy;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;

    public void opportunityReportMenu() throws NoSuchValueException, AWTException {

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
                                   + "║                                                 " + colorTable + "OPPORTUNITY REPORTING MENU" + colorMain + "                                       ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1. Display MEAN number of Opportunities per Account" + colorHeadline + "- type: 'mean opps per account'" + colorMain + "                               ║\n"
                                   + "║ 2. Display MEDIAN number of Opportunities per Account" + colorHeadline + "- type: 'median opps per account'" + colorMain + "                           ║\n"
                                   + "║ 3. Display MAXIMUM number of Opportunities per Account" + colorHeadline + "- type: 'max opps per account'" + colorMain + "                             ║\n"
                                   + "║ 4. Display MINIMUM number of Opportunities per Account" + colorHeadline + "- type: 'min opps per account'" + colorMain + "                             ║\n"
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
                    case MEAN_OPPS_PERR_ACC:
                        //System.out.println("Average number of opportunities per account is: " + oppReportServiceProxy.findMeanOpportunitiesPerAccount().get());
                        break;
                    case MED_OPPS_PERR_ACC:
                        //System.out.println("Median number of opportunities per account is: " + getMedian(oppReportServiceProxy.findMedianOppsPerAccountStep1()));
                        break;
                    case MAX_OPPS_PERR_ACC:
                        //System.out.println("Maximum number of opportunities per account is: " + oppReportServiceProxy.findMaxOpportunitiesPerAccount().get());
                        break;
                    case MIN_OPPS_PERR_ACC:
                        //System.out.println("Minimum number of opportunities per account is: " + oppReportServiceProxy.findMinOpportunitiesPerAccount().get());
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
//        opportunityReportMenu();
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

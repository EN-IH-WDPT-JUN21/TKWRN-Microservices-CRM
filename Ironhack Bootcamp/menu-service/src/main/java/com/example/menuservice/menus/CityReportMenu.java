package com.example.menuservice.menus;


import com.example.menuservice.dao.Variables;
import com.example.menuservice.enums.ReportCommands;
import com.example.menuservice.enums.Status;
import com.example.menuservice.exceptions.NoSuchValueException;
import com.example.menuservice.proxy.OppReportServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Scanner;

@Component
public class CityReportMenu implements Variables {

    private OppReportServiceProxy oppReportServiceProxy;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;



    public void cityReportMenu() throws NoSuchValueException, AWTException {

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
                                   + "║                                                 " + colorTable + "CITY REPORTING MENU" + colorMain + "                                              ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1. Display count of all Opportunities by City " + colorHeadline + "- type: - 'report opportunity by city'" + colorMain + "                             ║\n"
                                   + "║ 2. Display count of CLOSED-WON Opportunities by City " + colorHeadline + "- type: 'report closed-won by city'" + colorMain + "                         ║\n"
                                   + "║ 3. Display count of CLOSED-LOST Opportunities by City" + colorHeadline + "- type: 'report closed-lost by city'" + colorMain + "                        ║\n"
                                   + "║ 4. Display count of OPEN Opportunities by City" + colorHeadline + "- type: 'report open by city'" + colorMain + "                                      ║\n"
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
                    case REPORT_OPP_BY_CITY:
                        var oppCountCity = oppReportServiceProxy.findCountOppForCity();
                        if(oppCountCity.isEmpty()){
                            System.out.println(colorTable + "\nThere are no entries matching reporting criteria" + reset);
                        } else {
                            System.out.println(printCountReport("Opportunity"));
                            for (int i = 0; i < oppCountCity.size(); i++) {
                                printTableRow(oppCountCity, i);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_CITY:
                        var oppCityW = oppReportServiceProxy.findCountOpportunityByCityForStatus(Status.CLOSED_WON.toString());
                        if(oppCityW.isEmpty()){
                            System.out.println(colorTable + "\nThere are no entries matching reporting criteria" + reset);
                        } else {
                            System.out.println(printCountReport("Opportunity"));
                            for (int i = 0; i < oppCityW.size(); i++) {
                                printTableRow(oppCityW, i);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_CITY:
                        var oppCityL = oppReportServiceProxy.findCountOpportunityByCityForStatus(Status.CLOSED_LOST.toString());
                        if(oppCityL.isEmpty()){
                            System.out.println(colorTable + "\nThere are no entries matching reporting criteria" + reset);
                        } else {
                            System.out.println(printCountReport("Opportunity"));
                            for (int i = 0; i < oppCityL.size(); i++) {
                                printTableRow(oppCityL, i);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_CITY:
                        var oppCityOp = oppReportServiceProxy.findCountOpportunityByCityForStatus(Status.OPEN.toString());
                        if(oppCityOp.isEmpty()){
                            System.out.println(colorTable + "\nThere are no entries matching reporting criteria" + reset);
                        } else {
                            System.out.println(printCountReport("Opportunity"));
                            for (int i = 0; i < oppCityOp.size(); i++) {
                                printTableRow(oppCityOp, i);
                            }
                        }
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
//        cityReportMenu();
    }
}

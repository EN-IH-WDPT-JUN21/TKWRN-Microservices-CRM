package com.ironhack.menuservice.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportCommandsTest {


    @Test
    void getCommandType_validOption() {
      assertEquals(ReportCommands.MEAN_OPPS_PERR_ACC, ReportCommands.getCommandType("MEAN OPPS PER ACCOUNT"));
    }

    @Test
    void getCommandType_invalidOption() {
        assertEquals(ReportCommands.NONE, ReportCommands.getCommandType("RANDOM STRING"));
    }


}
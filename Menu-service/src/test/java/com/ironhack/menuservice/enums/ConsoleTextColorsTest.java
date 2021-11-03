package com.ironhack.menuservice.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTextColorsTest {

    @Test
    void getColor_shouldWork() {
        assertEquals("\033[0;33m", ConsoleTextColors.ANSI_YELLOW.getColor() );
    }
}
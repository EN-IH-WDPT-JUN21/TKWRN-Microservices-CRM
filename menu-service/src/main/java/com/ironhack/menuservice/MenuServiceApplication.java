package com.ironhack.menuservice;

import com.ironhack.menuservice.exceptions.*;
import com.ironhack.menuservice.menus.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.awt.*;

import static com.ironhack.menuservice.dao.Login.getIsLoggedIn;

@SpringBootApplication
public class MenuServiceApplication implements CommandLineRunner{

	@Autowired
    MainMenu menu;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(MenuServiceApplication.class, args);
	}

	@Override
	public void run (String...args) throws NoSuchValueException, AWTException, NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
		if (getIsLoggedIn() == 1) {
			menu.OS();
		} else if (getIsLoggedIn() == 2) {
			menu.OSGuest();
		} else {
			throw new Error("Please run the program again to login! " +
					"Be sure to run it using 'Main' class from 'dao'!");
		}
	}
}


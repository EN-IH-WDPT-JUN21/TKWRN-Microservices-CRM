package com.ironhack.menuservice.dao;

import org.springframework.cloud.openfeign.EnableFeignClients;

public class Main {

    public static void main(String[] args){
        com.ironhack.menuservice.dao.Login.login();
    }
}

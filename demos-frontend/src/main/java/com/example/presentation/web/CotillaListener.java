package com.example.presentation.web;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class CotillaListener implements ServletContextListener {
    static final Logger log = Logger.getLogger("CotillaListener");
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.severe("Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.severe("Context destroyed");
    }
}

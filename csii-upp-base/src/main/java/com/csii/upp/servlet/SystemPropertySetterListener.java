package com.csii.upp.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.csii.upp.util.EnvironmentPropertyLoader;

public class SystemPropertySetterListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        new EnvironmentPropertyLoader().loadAndSetEnvironmentProperty();
    }
}

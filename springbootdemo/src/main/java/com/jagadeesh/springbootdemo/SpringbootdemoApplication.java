package com.jagadeesh.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    ThymeleafAutoConfiguration.class
})
@ComponentScan(basePackages = {
    "com.jagadeesh.springbootdemo",
    "com.payroll.controller",
    "com.payroll.service",
    "com.payroll.security"
})
@EntityScan(basePackages = {"com.payroll.model"})
@EnableJpaRepositories(basePackages = {"com.payroll.repository"})
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        killPort(9090);
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    private static void killPort(int port) {
        try {
            System.out.println("Checking port " + port + "...");
            ProcessBuilder finder = new ProcessBuilder(
                "cmd", "/c",
                "netstat -ano | findstr :" + port + " | findstr LISTENING"
            );
            finder.redirectErrorStream(true);
            Process findProcess = finder.start();
            String output = new String(findProcess.getInputStream().readAllBytes());
            findProcess.waitFor();

            if (output != null && !output.trim().isEmpty()) {
                String[] lines = output.trim().split("\n");
                for (String line : lines) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 5) {
                        String pid = parts[4].trim();
                        if (!pid.isEmpty() && pid.matches("\\d+")) {
                            ProcessBuilder killer = new ProcessBuilder(
                                "cmd", "/c", "taskkill /PID " + pid + " /F"
                            );
                            killer.redirectErrorStream(true);
                            Process killProcess = killer.start();
                            killProcess.waitFor();
                            System.out.println("Killed process PID " + pid + " on port " + port);
                        }
                    }
                }
                Thread.sleep(1000);
            } else {
                System.out.println("Port " + port + " is free — starting now!");
            }
        } catch (Exception e) {
            System.out.println("Port check skipped: " + e.getMessage());
        }
    }
}

@Component
class BrowserLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            String url = "http://localhost:9090/index.html";
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI(url));
                    System.out.println("Browser opened: " + url);
                }
            } else {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
            }
        } catch (Exception e) {
            System.out.println("Open manually: http://localhost:9090/index.html");
        }
    }
}
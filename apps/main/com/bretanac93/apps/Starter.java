package com.bretanac93.apps;

import com.bretanac93.apps.backoffice.BackofficeApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;

public class Starter {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("There are not enough arguments");
        }

        String appName = args[0];
        String commandName = args[1];

        boolean isServerCommand = commandName.equals("server");

        assertAppExists(appName);
        assertCommandExists(appName, commandName);

        Class<?> appClass = applications().get(appName);

        SpringApplication app = new SpringApplication(appClass);

        if (!isServerCommand) {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        ConfigurableApplicationContext context = app.run(args);
    }

    private static void assertAppExists(String appName) {
        if (!applications().containsKey(appName)) {
            throw new RuntimeException(String.format(
                "The application <%s> doesn't exists. Valid:\n- %s",
                appName,
                String.join("\n-", applications().keySet())
            ));
        }
    }

    private static void assertCommandExists(String appName, String commandName) {
        if (!"server".equals(commandName) && !existsCommand(appName, commandName)) {
            throw new RuntimeException(String.format(
                "The command <%s> for application <%s> doesn't exist. Valid (application.command):\n- api\n- %s",
                commandName,
                appName,
                String.join("\n- ", commands().get(appName).keySet())
            ));
        }
    }

    private static HashMap<String, Class<?>> applications() {
        HashMap<String, Class<?>> applications = new HashMap<>();

        // register applications here
        applications.put("backoffice", BackofficeApplication.class);

        return applications;
    }

    private static HashMap<String, HashMap<String, Class<?>>> commands() {
        HashMap<String, HashMap<String, Class<?>>> commands = new HashMap<>();

        // register commands here
        commands.put("backoffice", BackofficeApplication.commands());
        return commands;
    }

    private static Boolean existsCommand(String appName, String commandName) {
        HashMap<String, HashMap<String, Class<?>>> commands = commands();

        return commands.containsKey(appName) && commands.get(appName).containsKey(commandName);
    }
}

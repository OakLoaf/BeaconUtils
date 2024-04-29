package org.beaconstudios.beaconutils.function;

import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Function {
    private final String id;
    private final List<String> commands;
    private final String commandSelector;

    public Function(String id, ConfigurationSection configurationSection) {
        this.id = id;
        this.commands = configurationSection.getStringList("commands");
        this.commandSelector = configurationSection.getString("command-selection", "all");
    }

    public void run() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();

        if (commandSelector.equalsIgnoreCase("random")) {
            Bukkit.dispatchCommand(console, commands.get(BeaconUtils.RANDOM.nextInt(commands.size())));
        } else {
            commands.forEach(command -> Bukkit.dispatchCommand(console, command));
        }
    }

    public String getId() {
        return id;
    }
}

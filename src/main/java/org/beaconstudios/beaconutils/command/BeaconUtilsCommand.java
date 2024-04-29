package org.beaconstudios.beaconutils.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.lushlib.command.Command;

public class BeaconUtilsCommand extends Command {

    public BeaconUtilsCommand() {
        super("beaconutils");
        addSubCommand(new FunctionCommand());
        addSubCommand(new ReloadCommand());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}

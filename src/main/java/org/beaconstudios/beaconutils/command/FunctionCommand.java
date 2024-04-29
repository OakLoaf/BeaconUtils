package org.beaconstudios.beaconutils.command;

import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.lushlib.command.SubCommand;

import java.util.List;

public class FunctionCommand extends SubCommand {

    protected FunctionCommand() {
        super("function");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Incorrect usage");
            return true;
        }

        BeaconUtils.getInstance().getFunctionManager().runFunction(args[0]);
        return true;
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return BeaconUtils.getInstance().getFunctionManager().getFunctionNames().stream().toList();
    }
}

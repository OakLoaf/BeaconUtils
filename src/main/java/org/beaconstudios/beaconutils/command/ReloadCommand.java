package org.beaconstudios.beaconutils.command;

import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.lushlib.command.SubCommand;

import java.util.List;

public class ReloadCommand extends SubCommand {

    public ReloadCommand() {
        super("reload");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            String category = args[0];
            if (category.equalsIgnoreCase("functions")) {
                BeaconUtils.getInstance().getFunctionManager().reloadFunctions();
            }

            sender.sendMessage("Successfully reloaded '" + category + "'");
            return true;
        }

        return true;
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return args.length == 1 ? List.of("functions") : null;
    }
}

package org.beaconstudios.beaconutils.command;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.dave.chatcolorhandler.ChatColorHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.lushlib.command.Command;
import org.lushplugins.lushlib.command.SubCommand;

import java.net.MalformedURLException;
import java.net.URL;

public class SkinCommand extends Command {

    public SkinCommand() {
        super("skin");
        addSubCommand(new SetSkinSubCommand());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    public static class SetSkinSubCommand extends SubCommand {

        public SetSkinSubCommand() {
            super("set");
        }

        @Override
        public boolean execute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (!(sender instanceof Player player)) {
                ChatColorHandler.sendMessage(sender, "This command must be ran by a player");
                return true;
            }

            if (args.length == 1) {
                try {
                    PlayerProfile profile = player.getPlayerProfile();
                    profile.getTextures().setSkin(new URL(args[0]));
                    player.setPlayerProfile(profile);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    ChatColorHandler.sendMessage(sender, "Failed to update skin");
                    return true;
                }

                ChatColorHandler.sendMessage(sender, "Your skin has been updated");
            } else {
                ChatColorHandler.sendMessage(sender, "Invalid args");
            }

            return true;
        }
    }
}

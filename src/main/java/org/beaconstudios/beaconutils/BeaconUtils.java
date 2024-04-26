package org.beaconstudios.beaconutils;

import org.beaconstudios.beaconutils.hook.HookId;
import org.beaconstudios.beaconutils.hook.skillslibrary.SkillsLibraryHook;
import org.lushplugins.lushlib.LushLib;
import org.lushplugins.lushlib.hook.Hook;
import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.beaconstudios.beaconutils.command.EntityFillCommand;

import java.util.Random;

public final class BeaconUtils extends SpigotPlugin {
    public static final Random RANDOM = new Random();

    @Override
    public void onLoad() {
        LushLib.getInstance().enable(this);
    }

    @Override
    public void onEnable() {
        addHook(HookId.SKILLS_LIBRARY, () -> registerHook(new SkillsLibraryHook()));

        registerCommand(new EntityFillCommand());

        getHooks().forEach(Hook::enable);
    }

    @Override
    public void onDisable() {
        unregisterAllHooks();
    }
}

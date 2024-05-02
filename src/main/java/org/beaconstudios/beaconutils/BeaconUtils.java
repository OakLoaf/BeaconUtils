package org.beaconstudios.beaconutils;

import org.beaconstudios.beaconutils.command.BeaconUtilsCommand;
import org.beaconstudios.beaconutils.function.FunctionManager;
import org.beaconstudios.beaconutils.hook.HookId;
import org.beaconstudios.beaconutils.hook.enchantedbosses.EnchantedBossesHook;
import org.beaconstudios.beaconutils.hook.skillslibrary.SkillsLibraryHook;
import org.lushplugins.lushlib.LushLib;
import org.lushplugins.lushlib.hook.Hook;
import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.beaconstudios.beaconutils.command.EntityFillCommand;

import java.util.Random;

public final class BeaconUtils extends SpigotPlugin {
    public static final Random RANDOM = new Random();
    private static BeaconUtils plugin;

    private FunctionManager functionManager;

    @Override
    public void onLoad() {
        plugin = this;
        LushLib.getInstance().enable(this);
    }

    @Override
    public void onEnable() {
        this.functionManager = new FunctionManager();
        functionManager.reloadFunctions();

        addHook(HookId.ENCHANTED_BOSSES, () -> registerHook(new EnchantedBossesHook()));
        addHook(HookId.SKILLS_LIBRARY, () -> registerHook(new SkillsLibraryHook()));

        registerCommand(new BeaconUtilsCommand());
        registerCommand(new EntityFillCommand());

        getHooks().forEach(Hook::enable);
    }

    @Override
    public void onDisable() {
        unregisterAllHooks();
    }

    public FunctionManager getFunctionManager() {
        return functionManager;
    }

    public static BeaconUtils getInstance() {
        return plugin;
    }
}

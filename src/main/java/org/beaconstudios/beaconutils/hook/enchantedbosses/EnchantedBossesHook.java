package org.beaconstudios.beaconutils.hook.enchantedbosses;

import me.xemor.enchantedbosses.damagemodifiers.DamageModifiers;
import org.beaconstudios.beaconutils.hook.HookId;
import org.beaconstudios.beaconutils.hook.enchantedbosses.damagemodifier.SharedDamageModifier;
import org.lushplugins.lushlib.hook.Hook;

public class EnchantedBossesHook extends Hook {

    public EnchantedBossesHook() {
        super(HookId.ENCHANTED_BOSSES);
    }

    @Override
    protected void onEnable() {
        DamageModifiers.registerDamageModifier("SHARED", SharedDamageModifier.class);
    }
}
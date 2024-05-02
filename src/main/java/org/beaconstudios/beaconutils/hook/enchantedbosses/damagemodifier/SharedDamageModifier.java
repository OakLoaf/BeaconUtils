package org.beaconstudios.beaconutils.hook.enchantedbosses.damagemodifier;

import me.xemor.enchantedbosses.damagemodifiers.EasedDamageModifier;
import org.bukkit.configuration.ConfigurationSection;

public class SharedDamageModifier extends EasedDamageModifier {
    private final double playerScalingModifier;

    public SharedDamageModifier(ConfigurationSection section) {
        super(section);
        this.playerScalingModifier = section.getDouble("playerScalingModifier", 0.5);
    }

    public double modify(double x, long numberOfPlayers) {
        return x > this.expectedMaximumDamage ? this.damageCap : this.damageCap / Math.pow((double) numberOfPlayers, this.playerScalingModifier) * (1.0 - (1.0 - 10 / this.expectedMaximumDamage) * (1.0 - 10 / this.expectedMaximumDamage));
    }
}

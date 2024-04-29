package org.beaconstudios.beaconutils.hook.skillslibrary.effect;

import me.xemor.skillslibrary2.effects.Effect;
import me.xemor.skillslibrary2.effects.EntityEffect;
import me.xemor.skillslibrary2.effects.LocationEffect;
import me.xemor.skillslibrary2.effects.TargetEffect;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;

public class PhantomNoFireEffect extends Effect implements EntityEffect, LocationEffect, TargetEffect {
    private final boolean burnInDay;

    public PhantomNoFireEffect(int effect, ConfigurationSection configurationSection) {
        super(effect, configurationSection);
        burnInDay = configurationSection.getBoolean("burnInDay");
    }

    @Override
    public boolean useEffect(Entity entity) {
        return setAnchorLocation(entity);
    }

    @Override
    public boolean useEffect(Entity entity, Location location) {
        return setAnchorLocation(entity);
    }

    @Override
    public boolean useEffect(Entity entity, Entity entity1) {
        return setAnchorLocation(entity);
    }

    public boolean setAnchorLocation(Entity entity) {
        if (entity instanceof Phantom phantom) {
            phantom.setShouldBurnInDay(burnInDay);
            return true;
        }

        return false;
    }
}

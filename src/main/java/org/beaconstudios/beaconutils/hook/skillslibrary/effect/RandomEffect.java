package org.beaconstudios.beaconutils.hook.skillslibrary.effect;

import me.xemor.skillslibrary2.effects.*;
import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class RandomEffect extends WrapperEffect implements EntityEffect, LocationEffect, TargetEffect {

    public RandomEffect(int effect, ConfigurationSection configurationSection) {
        super(effect, configurationSection);
    }

    @Override
    public boolean useEffect(Entity entity) {
        return handleRandomEffect(entity);
    }

    @Override
    public boolean useEffect(Entity entity, Location location) {
        return handleRandomEffect(entity, location);
    }

    @Override
    public boolean useEffect(Entity entity, Entity entity1) {
        return handleRandomEffect(entity, entity1);
    }

    private boolean handleRandomEffect(Entity entity, Object... objects) {
        Effect effect = getRandomEffect();
        if (effect instanceof EntityEffect entityEffect) {
            return entityEffect.useEffect(entity);
        } else if (effect instanceof LocationEffect locationEffect) {
            return locationEffect.useEffect(entity, (Location) objects[0]);
        } else if (effect instanceof TargetEffect targetEffect) {
            return targetEffect.useEffect(entity, (Entity) objects[0]);
        } else {
            return false;
        }
    }

    private Effect getRandomEffect() {
        EffectList effectList = this.getEffects();
        List<Effect> effects = new ArrayList<>();
        effectList.forEach(effects::add);

        return effects.get(BeaconUtils.RANDOM.nextInt(effects.size()));
    }
}

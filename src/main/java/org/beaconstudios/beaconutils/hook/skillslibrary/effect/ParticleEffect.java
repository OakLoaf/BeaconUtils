package org.beaconstudios.beaconutils.hook.skillslibrary.effect;

import me.xemor.skillslibrary2.effects.Effect;
import me.xemor.skillslibrary2.effects.EntityEffect;
import me.xemor.skillslibrary2.effects.LocationEffect;
import me.xemor.skillslibrary2.effects.TargetEffect;
import org.beaconstudios.beaconutils.particle.ParticleSettings;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ParticleEffect extends Effect implements EntityEffect, LocationEffect, TargetEffect {

    private final ParticleSettings particleSettings;
    private final ParticleShape shape;

    public ParticleEffect(int effect, ConfigurationSection configurationSection) {
        super(effect, configurationSection);

        particleSettings = new ParticleSettings(
            Particle.valueOf(configurationSection.getString("particle", "END_ROD").toUpperCase()),
            configurationSection.getInt("count", 1),
            configurationSection.getDouble("offsetX", 0.0),
            configurationSection.getDouble("offsetY", 0.0),
            configurationSection.getDouble("offsetZ", 0.0),
            configurationSection.getDouble("extra", 0.0),
            configurationSection.getBoolean("force")
        );
        shape = ParticleShape.valueOf(configurationSection.getString("shape", "SINGLE").toUpperCase());
    }

    @Override
    public boolean useEffect(Entity entity) {
        spawnParticle(entity.getWorld(), entity.getLocation());
        return true;
    }

    @Override
    public boolean useEffect(Entity entity, Location location) {
        spawnParticle(entity.getWorld(), location, entity.getLocation());
        return false;
    }

    @Override
    public boolean useEffect(Entity entity, Entity target) {
        spawnParticle(entity.getWorld(), target.getLocation(), entity.getLocation());
        return false;
    }

    public void spawnParticle(@NotNull World world, @NotNull Location location, Location... locations) {
        shape.generate(particleSettings, world, location, locations);
    }

    @SuppressWarnings("unused")
    public enum ParticleShape {
        SINGLE((particleSettings, world, location, locations) -> particleSettings.spawnParticle(world, location)),
        LINE((particleSettings, world, location, locations) -> {
            Location fromLocation = location.clone();
            Location toLocation = locations.length > 0 ? locations[locations.length - 1] : location;
            Vector vector = toLocation.toVector().subtract(location.toVector());

            for (double i = 1; i <= fromLocation.distance(toLocation); i += 0.5) {
                vector.multiply(i);
                fromLocation.add(vector);
                particleSettings.spawnParticle(world, fromLocation);
                fromLocation.subtract(vector);
                vector.normalize();
            }
        }),
        BLOCK_FACE((particleSettings, world, location, locations) -> {
            double minX = location.getBlockX();
            double minZ = location.getBlockZ();
            double maxX = location.getBlockX() + 1;
            double maxZ = location.getBlockZ() + 1;

            for (double x = minX; x <= maxX; x += 0.05) {
                for (double z = minZ; z <= maxZ; z += 0.05) {
                    if (x == minX || x >= maxX - 0.05 || z == minZ || z >= maxZ - 0.05) {
                        particleSettings.spawnParticle(world, new Location(world, x, location.getBlockY(), z));
                    }
                }
            }
        }),
        BLOCK((particleSettings, world, location, locations) -> {
            double minX = location.getBlockX();
            double minY = location.getBlockY();
            double minZ = location.getBlockZ();
            double maxX = location.getBlockX() + 1;
            double maxY = location.getBlockY() + 1;
            double maxZ = location.getBlockZ() + 1;

            for (double x = minX; x <= maxX; x += 0.05) {
                for (double y = minY; y <= maxY; y += 0.05) {
                    for (double z = minZ; z <= maxZ; z += 0.05) {
                        int successes = 0;
                        if (x == minX || x >= maxX - 0.05) successes++;
                        if (y == minY || y >= maxY - 0.05) successes++;
                        if (z == minZ || z >= maxZ - 0.05) successes++;

                        if (successes >= 2) {
                            particleSettings.spawnParticle(world, new Location(world, x, y, z));
                        }
                    }
                }
            }
        });

        private final ParticleGenerator generator;

        ParticleShape(ParticleGenerator generator) {
            this.generator = generator;
        }

        public void generate(ParticleSettings particleSettings, @NotNull World world, @NotNull Location location, Location... locations) {
            generator.generate(particleSettings, world, location, locations);
        }

        @FunctionalInterface
        private interface ParticleGenerator {
            void generate(ParticleSettings particleSettings, @NotNull World world, @NotNull Location location, Location... locations);
        }
    }
}

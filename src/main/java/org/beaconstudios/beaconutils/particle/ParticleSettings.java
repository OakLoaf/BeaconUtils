package org.beaconstudios.beaconutils.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public record ParticleSettings(Particle particle, int count, double offsetX, double offsetY, double offsetZ, double extra, boolean force) {

    public void spawnParticle(@NotNull World world, @NotNull Location location) {
        world.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra, null, force);
    }
}

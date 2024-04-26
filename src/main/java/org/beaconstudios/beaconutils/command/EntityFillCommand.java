package org.beaconstudios.beaconutils.command;

import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.lushlib.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityFillCommand extends Command {

    public EntityFillCommand() {
        super("entityfill");
        addRequiredPermission("beaconutils.entityfill");
        addRequiredArgs(0, () -> List.of("x1"));
        addRequiredArgs(1, () -> List.of("y1"));
        addRequiredArgs(2, () -> List.of("z1"));
        addRequiredArgs(3, () -> List.of("x2"));
        addRequiredArgs(4, () -> List.of("y2"));
        addRequiredArgs(5, () -> List.of("z2"));
        addRequiredArgs(6, () -> List.of("world"));
        addRequiredArgs(7, () -> Arrays.stream(EntityType.values()).map(Enum::toString).toList());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        double x1 = Double.parseDouble(args[0]);
        double y1 = Double.parseDouble(args[1]);
        double z1 = Double.parseDouble(args[2]);
        double x2 = Double.parseDouble(args[3]);
        double y2 = Double.parseDouble(args[4]);
        double z2 = Double.parseDouble(args[5]);
        World world = Bukkit.getWorld(args[6]);
        EntityType entityType = EntityType.valueOf(args[7].toUpperCase());
        int count = args.length >= 9 ? Integer.parseInt(args[8]) : 20;

        getRandomLocations(new Location(world, x1, y1, z1), new Location(world, x2, y2, z2), count).forEach(location -> world.spawnEntity(location, entityType));
        return true;
    }

    private List<Location> getRandomLocations(Location loc1, Location loc2, int count) {
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());
        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double x = minX != maxX ? BeaconUtils.RANDOM.nextDouble(minX, maxX) : minX;
            double y = minY != maxY ? BeaconUtils.RANDOM.nextDouble(minY, maxY) : minY;
            double z = minZ != maxZ ? BeaconUtils.RANDOM.nextDouble(minZ, maxZ) : minZ;

            locations.add(new Location(loc1.getWorld(), x, y, z));
        }

        return locations;
    }
}

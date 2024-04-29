package org.beaconstudios.beaconutils.function;

import org.beaconstudios.beaconutils.BeaconUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class FunctionManager {
    private static final File FUNCTIONS_FOLDER = new File(BeaconUtils.getInstance().getDataFolder(), "functions");

    private final HashMap<String, Function> functions = new HashMap<>();

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public void runFunction(String name) {
        if (functions.containsKey(name)) {
            functions.get(name).run();
        }
    }

    public Set<String> getFunctionNames() {
        return functions.keySet();
    }

    public void reloadFunctions() {
        unregisterAll();

        try {
            Files.newDirectoryStream(FUNCTIONS_FOLDER.toPath(), "*.yml").forEach(entry -> {
                File functionFile = entry.toFile();
                YamlConfiguration functionConfig = YamlConfiguration.loadConfiguration(functionFile);

                String id = functionFile.getName();
                Function function = new Function(id, functionConfig);
                BeaconUtils.getInstance().getFunctionManager().registerFunction(id, function);
            });
        } catch (IOException e) {
            BeaconUtils.getInstance().log(Level.SEVERE, "Something went wrong whilst reading modules files");
            e.printStackTrace();
        }
    }

    public void registerFunction(String name, Function function) {
        functions.put(name, function);
    }

    public void unregisterFunction(Function function) {
        unregisterFunction(function.getId());
    }

    public void unregisterFunction(String name) {
        functions.remove(name);
    }

    public void unregisterAll() {
        functions.clear();
    }
}

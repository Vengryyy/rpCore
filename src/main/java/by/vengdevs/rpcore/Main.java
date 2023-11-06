package by.vengdevs.rpcore;

import by.vengdevs.rpcore.commands.ConfigCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        Objects.requireNonNull(getCommand("config")).setExecutor(new ConfigCommand(this));
        Objects.requireNonNull(getCommand("config")).setTabCompleter(new ConfigCommand(this));

        getServer().getConsoleSender().sendMessage("rpCore enabled!");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("rpCore disabled!");
    }
}

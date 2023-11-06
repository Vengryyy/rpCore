package by.vengdevs.rpcore.commands;

import by.vengdevs.rpcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ConfigCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public ConfigCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender, @Nullable Command command, @Nullable String s, @Nullable String[] strings) {
        String whoCanUse = this.plugin.getConfig().getString("commands.config.executor");
        String permissions = this.plugin.getConfig().getString("commands.config.permissions");
        boolean senderIsPlayer = (commandSender instanceof Player);

        if (whoCanUse == null || permissions == null) {
            return true;
        }

        assert commandSender != null;
        if (!commandSender.hasPermission(permissions)) {
            String notEnoughPermissions = "You don't have permissions!";
            commandSender.sendMessage(notEnoughPermissions);
            return true;
        }

        String cannotUse = "You cannot use this command";

        if (whoCanUse.equals("player"))
        {
            if (!senderIsPlayer) {
                commandSender.sendMessage(cannotUse);
                return true;
            }
        }
        else if (whoCanUse.equals("console"))
        {
            if (senderIsPlayer) {
                commandSender.sendMessage(cannotUse);
                return true;
            }
        }

        String usage = "/config reload";

        if (strings == null || strings.length < 1) {
            commandSender.sendMessage(usage);
            return true;
        }

        if (strings[0].equalsIgnoreCase("reload")) {
            String successfullyReloaded = "Config successfully reloaded!";
            this.plugin.reloadConfig();
            commandSender.sendMessage(successfullyReloaded);
            return true;
        }

        commandSender.sendMessage(usage);

        return true;
    }

    @Override
    public List<String> onTabComplete(@Nullable CommandSender commandSender, @Nullable Command command, @Nullable String s, @Nullable String[] strings) {
        assert strings != null;
        if (strings.length == 1) {
            return Collections.singletonList("reload");
        }
        return Collections.singletonList("");
    }
}

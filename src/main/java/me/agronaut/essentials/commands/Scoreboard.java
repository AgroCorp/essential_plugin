package me.agronaut.essentials.commands;

import me.agronaut.essentials.Classes.baseScoreBoard;
import me.agronaut.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Scoreboard implements CommandExecutor {
    Essentials plugin;

    public Scoreboard(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if(player.hasPermission("essentials.scoreboard")) {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                if (manager != null) {
                    baseScoreBoard baseScoreBoard = new baseScoreBoard(plugin);
                    if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
                        plugin.getLogger().info("Scoreboard nincs tehat show van meghivva");
                        baseScoreBoard.showScoreboard(player, true);
                    } else {
                        plugin.getLogger().info("Scoreboard megnyitva tehat toroljuk azt");
                        baseScoreBoard.removeScoreboard(player);
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Hiba a scoreboard parancs futasa kozben");
                }
                return true;
            } else {player.sendMessage(Essentials.permissionMsg);}
        }
        else {sender.sendMessage("This command use only player"); }
        return false;
    }
}

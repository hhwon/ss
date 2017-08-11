package com.imyvm.ss;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import cat.nyaa.nyaacore.Message;
import org.bukkit.inventory.ItemStack;

import static cat.nyaa.nyaacore.CommandReceiver.asPlayer;
import static cat.nyaa.nyaacore.CommandReceiver.getItemInHand;

/**
 * Created by huang on 2017/8/11.
 */
public class Main extends JavaPlugin{


    FileConfiguration config = getConfig();

    @Override
    //方法 - 插件启动时.
    public void onEnable() {
        config.addDefault("message", "{player} 正在展示 &b[&r {itemName:0} &e*&r {amount} &b]&r");
        config.addDefault("noiteminhandmessage", "{player} 正在展示 &eTA的难看的手");
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    //方法 - 插件关闭时.
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ss")) {
            Player player = asPlayer(sender);
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item != null && !item.getType().equals(Material.AIR)) {
                String Message = config.getString("message");
                Message = ChatColor.translateAlternateColorCodes('&', Message);
                Message = Message.replace("{player}", sender.getName());
                new Message("")
                        .append(Message, item)
                        .broadcast();
            }else {
                String Message2 = config.getString("noiteminhandmessage");
                Message2 = ChatColor.translateAlternateColorCodes('&', Message2);
                Message2 = Message2.replace("{player}", sender.getName());
                new Message("")
                        .append(Message2)
                        .broadcast();
            }
            return true;
        }

        //默认返回false
        return false;
    }
}
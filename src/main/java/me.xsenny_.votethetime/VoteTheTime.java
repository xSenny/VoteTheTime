package me.xsenny.votethetime;

import jdk.tools.jmod.Main;
import me.xsenny.votethetime.Commands.Toggle;
import me.xsenny.votethetime.Commands.VoteTimeAdmin;
import me.xsenny.votethetime.Events.PlayerJoin;
import me.xsenny.votethetime.MenuListener.MenuListener;
import me.xsenny.votethetime.Runs.TreceTimpul;
import me.xsenny.votethetime.Runs.TreceTimpulx2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public final class VoteTheTime extends JavaPlugin {

    public File messages = new File(getDataFolder(), "messages.yml");
    public YamlConfiguration messagesC = new YamlConfiguration();
    public File players = new File(getDataFolder(), "PlayerDates.yml");
    public YamlConfiguration playersC = new YamlConfiguration();
    public Integer votes_day = 0;
    public Integer votes_night = 0;
    private static VoteTheTime plugin;
    public static Main instance;
    public Integer timp_ramas = 0;
    public Integer timp_ramas2;

    @Override
    public void onEnable() {
        // Plugin startup logic
        createMessages();
        loadmessages();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        createPlayers();
        loadPlayers();
        getCommand("votetoggle").setExecutor(new Toggle(this));
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getCommand("timevoteadmin").setExecutor(new VoteTimeAdmin(this));
        plugin = this;
        BukkitTask trece2 = new TreceTimpulx2(this).runTaskTimer(this, 0L, 20L);
        BukkitTask trece = new TreceTimpul(this).runTaskTimer(this, 0L, getConfig().getInt("TIME_INTERVAL")*20L);
        votes_night =0;
        votes_day = 0;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void createMessages() {
        if (!messages.exists()) {
            saveResource("messages.yml", false);
        }
    }

    public void loadmessages() {
        try {
            messagesC.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to load messages.yml");
            e.printStackTrace();
        }
    }

    public YamlConfiguration getMessages() {
        return messagesC;
    }

    public void saveMessages() {
        try {
            messagesC.save(messages);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Couldn't save messages.yml");
        }
    }

    public void reloadMessages() {
        messagesC = YamlConfiguration.loadConfiguration(messages);
    }

    public void createPlayers() {
        if (!players.exists()) {
            saveResource("PlayerDates.yml", false);
        }
    }

    public void loadPlayers() {
        try {
            playersC.load(players);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to load PlayerDates.yml");
            e.printStackTrace();
        }
    }

    public YamlConfiguration getPlayers() {
        return playersC;
    }

    public void savePlayers() {
        try {
            playersC.save(players);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Couldn't save PlayerDates.yml");
        }
    }

    public void reloadPlayers() {
        playersC = YamlConfiguration.loadConfiguration(players);
    }

    public void Vote(Player p) {
        Inventory vote = Bukkit.createInventory(p, 9, ChatColor.translateAlternateColorCodes('&', getMessages().getString("Gui.name")));
        ItemStack day = new ItemStack(Material.GREEN_WOOL);
        ItemMeta day_meta = day.getItemMeta();
        day_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getMessages().getString("Gui.items.day.name")));
        ArrayList<String> day_lore = new ArrayList<>();
        for (int i = 0; i < getMessages().getList("Gui.items.day.lore").size(); i++) {
            day_lore.add(ChatColor.translateAlternateColorCodes('&', (String) getMessages().getList("Gui.items.day.lore").get(i)));
        }
        day_meta.setLore(day_lore);
        day.setItemMeta(day_meta);
        vote.setItem(3, day);


        ItemStack night = new ItemStack(Material.RED_WOOL);
        ItemMeta night_meta = night.getItemMeta();
        night_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getMessages().getString("Gui.items.night.name")));
        ArrayList<String> night_lore = new ArrayList<>();
        for (int i = 0; i < getMessages().getList("Gui.items.night.lore").size(); i++) {
            night_lore.add(ChatColor.translateAlternateColorCodes('&', (String) getMessages().getList("Gui.items.night.lore").get(i)));
        }
        night_meta.setLore(night_lore);
        night.setItemMeta(night_meta);
        vote.setItem(5, night);


        ItemStack cancel = new ItemStack(Material.BARRIER);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getMessages().getString("Gui.items.cancel.name")));
        ArrayList<String> cancel_lore = new ArrayList<>();
        for (int i = 0; i < getMessages().getList("Gui.items.cancel.lore").size(); i++) {
            cancel_lore.add(ChatColor.translateAlternateColorCodes('&', (String) getMessages().getList("Gui.items.cancel.lore").get(i)));
        }
        cancel_meta.setLore(cancel_lore);
        cancel.setItemMeta(cancel_meta);
        vote.setItem(8, cancel);

        p.openInventory(vote);
    }

    public void Vote21(Player p) throws InterruptedException {
    }
}

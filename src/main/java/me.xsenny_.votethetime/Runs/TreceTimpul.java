package me.xsenny.votethetime.Runs;

import me.xsenny.votethetime.Commands.VoteTimeAdmin;
import me.xsenny.votethetime.VoteTheTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TreceTimpul extends BukkitRunnable {

    VoteTheTime plugin;

    public TreceTimpul(VoteTheTime plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (VoteTimeAdmin.vote_forced == true){
            plugin.timp_ramas2 = 60+plugin.getConfig().getInt("TIME_ALLOWED");
            TreceTimpulx2.ma_depaseste = false;
        }else{
            VoteTimeAdmin.vote_forced = false;
        }
    }
}

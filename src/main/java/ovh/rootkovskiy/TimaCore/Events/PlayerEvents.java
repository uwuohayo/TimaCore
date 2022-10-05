package ovh.rootkovskiy.TimaCore.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import ovh.rootkovskiy.TimaCore.Main;

public class PlayerEvents implements Listener {
    Main main = Main.getInstance();

    @EventHandler
    public void join(final PlayerJoinEvent event) {
        if (main.getConfig().getBoolean("joinquitdeathmanager")) {
            event.setJoinMessage("");
        }
    }

    @EventHandler
    public void leave(final PlayerQuitEvent event) {
        if (main.getConfig().getBoolean("joinquitdeathmanager")) {
            event.setQuitMessage("");
        }
    }

    @EventHandler
    public void death(final PlayerDeathEvent event) {
        if (main.getConfig().getBoolean("joinquitdeathmanager")) {
            event.setDeathMessage("");
        }
    }

    @EventHandler
    public void respawn(final PlayerRespawnEvent event) {

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                String worldname = main.cfgspawn.getConfig().getString("spawn.world");

                if (worldname == null) {
                    return;
                }

                World w = Bukkit.getWorld(main.cfgspawn.getConfig().getString("spawn.world"));
                int x = main.cfgspawn.getConfig().getInt("spawn.x");
                int y = main.cfgspawn.getConfig().getInt("spawn.y");
                int z = main.cfgspawn.getConfig().getInt("spawn.z");
                float yaw = main.cfgspawn.getConfig().getInt("spawn.yaw");
                float pitch = main.cfgspawn.getConfig().getInt("spawn.pitch");

                Location spawn = new Location(w, x, y, z, yaw, pitch);

                event.getPlayer().teleport(spawn);

            }
        }, 1L);

    }
}
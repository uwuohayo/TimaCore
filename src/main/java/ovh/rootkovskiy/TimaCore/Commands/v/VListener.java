package ovh.rootkovskiy.TimaCore.Commands.v;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import ovh.rootkovskiy.TimaCore.Main;

public class VListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        for (Player p : Main.vanished)
            e.getPlayer().hidePlayer(p);
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {
        Main.vanished.remove(e.getPlayer());
        e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
    }

}

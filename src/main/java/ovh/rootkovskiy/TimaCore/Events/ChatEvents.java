package ovh.rootkovskiy.TimaCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ovh.rootkovskiy.TimaCore.Main;

import java.util.HashMap;
import java.util.UUID;

public class ChatEvents implements Listener {

    private final HashMap<UUID, Long> coolDown = new HashMap<>();
    Main main = Main.getInstance();

    @EventHandler
    public void chat(final AsyncPlayerChatEvent event) {

        boolean visualChatManager = main.getConfig().getBoolean("visual_chat_manager");
        if (visualChatManager) {
            final Player player = event.getPlayer();
            String finalMessage;
            finalMessage = "&9(&b" + player.getName() + "&9) &d>> &f" + event.getMessage();
            event.setFormat((finalMessage).replace("&", "§"));
        }
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {

        boolean сhatcooldownmanager = main.getConfig().getBoolean("сhatcooldownmanager");
        if (сhatcooldownmanager) {
            final UUID uuid = e.getPlayer().getUniqueId();
            final Player p = e.getPlayer();
            if (!p.hasPermission("timacore.chatbypass")) {
                if (this.coolDown.containsKey(uuid)) {
                    final int time = (int) ((System.currentTimeMillis() - this.coolDown.get(uuid)) / 1000L);
                    if (time < main.getConfig().getInt("chatcooldown")) {
                        final String noSpam = "&9╔ &bСистема&r\n&9╚ &cПодождите! Вы отправляете сообщения слишком быстро&r".replace("&", "§");
                        e.setCancelled(true);
                        p.sendMessage(noSpam);
                    } else {
                        this.coolDown.put(uuid, System.currentTimeMillis());
                    }
                } else {
                    this.coolDown.put(uuid, System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    public void onLeave(final PlayerQuitEvent e) {

        boolean сhatcooldownmanager = main.getConfig().getBoolean("сhatcooldownmanager");
        if (сhatcooldownmanager) {
            this.coolDown.remove(e.getPlayer().getUniqueId());
        }
    }

}

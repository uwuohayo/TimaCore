package ovh.rootkovskiy.TimaCore;

import org.bukkit.Bukkit;
import ovh.rootkovskiy.TimaCore.Commands.v.VListener;
import ovh.rootkovskiy.TimaCore.Events.ChatEvents;
import ovh.rootkovskiy.TimaCore.Events.PlayerEvents;

public class EventsRegister {

    public static void loadEvents() {
        Bukkit.getPluginManager().registerEvents(new ChatEvents(), Main.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), Main.getInstance());
        Bukkit.getPluginManager().registerEvents(new VListener(), Main.getInstance());
    }
}

package ovh.rootkovskiy.TimaCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.rootkovskiy.TimaCore.v.VListener;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    /*
     * версия alpha_1
     * Надо сделать
     *   /near +- (сделать его нормально)
     * Надо доделать
     *   /invsee (два режима, с возможностью отнимания предметов и без)
     *   /call
     *   /delspawn
     *   /warps /delwarp
     *   /sethome /home /delhome /homes
     *
     * надо пофиксить
     */

    public static long srvstart;
    public static ArrayList<Player> vanished = new ArrayList<>();
    public static ArrayList<Player> socialspy = new ArrayList<>();
    private static Main instance;
    public CfgMaster cfgspawn;
    public CfgMaster cfgtranslation;
    public CfgMaster cfghomes;
    public CfgMaster cfgwarps;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        getLogger().info("Plugin enabled");
        instance = this;

        saveDefaultConfig();
        this.cfgspawn = new CfgMaster("spawn.yml");
        this.cfgtranslation = new CfgMaster("translation.yml");
        this.cfghomes = new CfgMaster("homes.yml");
        this.cfgwarps = new CfgMaster("warps.yml");

        CommandsRegister.commands();

        Bukkit.getPluginManager().registerEvents(new ChatEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new VListener(), this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new LagUtils(), 100L, 1L);

        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Hello :D" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "TimaCore " + getDescription().getVersion() + " Loaded and Enabled!" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "MC Core Version: " + getServer().getBukkitVersion() + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Author: Timur Rootkovskiy (Adminov)" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Site: www.rootkovskiy.ovh" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "VK: @timurroot" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
        srvstart = System.currentTimeMillis();
    }

    public void onDisable() {
        getLogger().info("Plugin disabled");
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "TimaCore " + getDescription().getVersion() + " Disabled!" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "MC Core Version: " + getServer().getBukkitVersion() + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Author: Timur Rootkovskiy (Adminov)" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Site: www.rootkovskiy.ovh" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "VK: @timurroot" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Goodbye ;p" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
    }
}
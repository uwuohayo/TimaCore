package ovh.rootkovskiy.TimaCore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CfgMaster {

    private final File file;

    private final FileConfiguration config;

    public CfgMaster(final String name) {
        file = new File(Main.getInstance().getDataFolder(), name);
        try {
            if (!file.exists() && !file.createNewFile())
                throw new IOException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

}

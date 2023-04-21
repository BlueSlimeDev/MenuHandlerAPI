package me.blueslime.menuhandlerapi;

import me.blueslime.menuhandlerapi.inventory.MenuInventory;
import me.blueslime.menuhandlerapi.listener.InventoryClickListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public final class MenuHandlerAPI {
    private final ConcurrentHashMap<String, MenuInventory> menuMap = new ConcurrentHashMap<>();

    private static String CUSTOM_IDENTIFIER_PREFIX = "mha-";

    private static String CUSTOM_ITEM_PREFIX = "mhi-";

    private static String CUSTOM_MENU_PREFIX = "mhm-";

    private static final MenuHandlerAPI MENU_HANDLER_API = new MenuHandlerAPI();

    public static void register(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(
                new InventoryClickListener(),
                plugin
        );
    }

    public static ConcurrentHashMap<String, MenuInventory> getMenus() {
        return MENU_HANDLER_API.menuMap;
    }

    public static String getCustomIdentifierPrefix() {
        return CUSTOM_IDENTIFIER_PREFIX;
    }

    public static void setCustomIdentifierPrefix(String customIdentifierPrefix) {
        CUSTOM_IDENTIFIER_PREFIX = customIdentifierPrefix;
    }

    public static void setCustomItemPrefix(String newPrefix) {
        CUSTOM_ITEM_PREFIX = newPrefix;
    }

    public static String getCustomItemPrefix() {
        return CUSTOM_ITEM_PREFIX;
    }

    public static void setCustomMenuPrefix(String newPrefix) {
        CUSTOM_MENU_PREFIX = newPrefix;
    }

    public static String getCustomMenuPrefix() {
        return CUSTOM_MENU_PREFIX;
    }
}

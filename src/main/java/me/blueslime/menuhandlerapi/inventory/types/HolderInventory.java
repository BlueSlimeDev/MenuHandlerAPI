package me.blueslime.menuhandlerapi.inventory.types;

import me.blueslime.menuhandlerapi.inventory.MenuInventory;
import me.blueslime.menuhandlerapi.item.MenuItem;
import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import me.blueslime.menuhandlerapi.item.list.WrapperMenuItem;
import me.blueslime.menuhandlerapi.utils.SlotHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static org.bukkit.Bukkit.createInventory;

public class HolderInventory extends MenuInventory implements InventoryHolder {
    private final Map<Integer, Consumer<InventoryClickEvent>> itemPredicateMap = new ConcurrentHashMap<>();
    private final Map<Integer, Boolean> statusMap = new HashMap<>();

    private final Inventory inventory;

    private final String title;
    private final int rows;

    public HolderInventory(String identifier, String title, int size) {
        super(identifier);
        this.title = title;
        this.rows = SlotHandler.fromSize(size);

        this.inventory = createInventory(
            null,
            SlotHandler.fromSize(size),
            title
        );
    }

    private Inventory load(Player player) {
        for (MenuItem item : getItemStorage().getValues()) {
            MenuItem menuItem;

            if (item instanceof WrapperMenuItem) {
                menuItem = getItemBuilder().processItem(
                    player,
                    item
                );
            } else {
                menuItem = getItemBuilder().processItem(
                    player,
                    item.copy()
                );
            }

            ItemStack itemStack = menuItem.getItemStack(player);

            MenuItemAction action = menuItem.getAction();

            if (action != null) {
                itemPredicateMap.put(
                    menuItem.getSlot(), action.getClickEvent()
                );
            }

            statusMap.put(menuItem.getSlot(), menuItem.isBlocked());

            inventory.setItem(
                menuItem.getSlot(),
                itemStack
            );
        }
        return inventory;
    }

    public void clear() {
        inventory.clear();
    }

    @Override
    public void fillBorders() {

    }

    @Override
    public void fillRows() {

    }

    @Override
    public Collection<MenuItem> getItemList() {
        return getItemStorage().getValues();
    }

    @Override
    public void openInventory(Player player) {
        player.openInventory(
            load(player)
        );
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public boolean canIntroduceItems() {
        return false;
    }

    public Map<Integer, Boolean> getStatusMap() {
        return statusMap;
    }

    public Map<Integer, Consumer<InventoryClickEvent>> getItemPredicateMap() {
        return itemPredicateMap;
    }

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

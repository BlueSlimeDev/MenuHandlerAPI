package me.blueslime.menuhandlerapi.listener;

import me.blueslime.menuhandlerapi.inventory.types.HolderInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class MenuClickListener implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof HolderInventory) {
            HolderInventory inventory = (HolderInventory) event.getView().getTopInventory().getHolder();

            boolean wasCancelled = inventory.getStatusMap().getOrDefault(event.getRawSlot(), true);

            event.setCancelled(wasCancelled);

            Consumer<InventoryClickEvent> consumer = inventory.getItemPredicateMap().get(event.getRawSlot());

            if (consumer != null) {
                consumer.accept(event);
            }
        }
    }
}

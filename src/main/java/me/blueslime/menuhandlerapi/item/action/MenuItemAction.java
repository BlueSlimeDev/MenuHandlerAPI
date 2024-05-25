package me.blueslime.menuhandlerapi.item.action;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class MenuItemAction {

    private Consumer<InventoryClickEvent> clickEvent;

    public MenuItemAction(Consumer<InventoryClickEvent> clickEvent) {
        this.clickEvent = clickEvent;
    }

    public void setClickEvent(Consumer<InventoryClickEvent> clickEvent) {
        this.clickEvent = clickEvent;
    }

    public Consumer<InventoryClickEvent> getClickEvent() {
        return clickEvent;
    }
}

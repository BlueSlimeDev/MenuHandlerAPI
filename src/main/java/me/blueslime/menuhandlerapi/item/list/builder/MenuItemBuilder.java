package me.blueslime.menuhandlerapi.item.list.builder;

import me.blueslime.menuhandlerapi.item.list.DefaultMenuItem;
import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class MenuItemBuilder {
    private MenuItemAction action = null;

    private ItemStack itemStack = null;

    private boolean blocked = false;

    private final String identifier;

    private int slot;

    public MenuItemBuilder(String identifier, int slot) {
        this.identifier = identifier;
        this.slot = slot;
    }

    public MenuItemBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }

    /**
     * InventoryClickEvent represents the current event
     * The boolean represents the returned value.
     * If return value is false it will not cancel the click event
     * If the return value is true it will cancel the click event
     * But the result value will be ignored if the cancelClick method has been used
     * @param action of the Item
     * @return MenuItemBuilder
     */
    public MenuItemBuilder action(MenuItemAction action) {
        this.action = action;
        return this;
    }

    /**
     * InventoryClickEvent represents the current event
     * The boolean represents the returned value.
     * If return value is false it will not cancel the click event
     * If the return value is true it will cancel the click event
     * But the result value will be ignored if the cancelClick method has been used
     * @param function of the action
     * @return MenuItemBuilder
     */
    public MenuItemBuilder action(Function<InventoryClickEvent, Boolean> function) {
        this.action = new MenuItemAction(
                function::apply
        );
        return this;
    }

    public MenuItemBuilder item(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public MenuItemBuilder cancelClick(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public DefaultMenuItem build() {
        return DefaultMenuItem.fromItem(
                identifier,
                slot,
                itemStack,
                blocked,
                action
        );
    }
}

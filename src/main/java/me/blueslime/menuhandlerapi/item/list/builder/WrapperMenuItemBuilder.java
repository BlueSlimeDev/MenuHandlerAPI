package me.blueslime.menuhandlerapi.item.list.builder;

import me.blueslime.menuhandlerapi.item.MenuItem;
import me.blueslime.menuhandlerapi.item.list.WrapperMenuItem;
import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import me.blueslime.utilitiesapi.item.ItemWrapper;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class WrapperMenuItemBuilder {
    private MenuItemAction action = null;

    private ItemWrapper wrapper = null;

    private boolean blocked = false;

    private final String identifier;

    private int slot;

    public WrapperMenuItemBuilder(String identifier, int slot) {
        this.identifier = identifier;
        this.slot = slot;
    }

    public WrapperMenuItemBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }

    /**
     * InventoryClickEvent represents the current event
     * The boolean represents the returned value.
     * If return value is false it will not cancel the click event
     * If the return value is true it will cancel the click event
     * @param action for the item
     * @return WrapperMenuItemBuilder
     */
    public WrapperMenuItemBuilder action(MenuItemAction action) {
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
     * @return WrapperMenuItemBuilder
     */
    public WrapperMenuItemBuilder action(Function<InventoryClickEvent, Boolean> function) {
        this.action = new MenuItemAction(
                function::apply
        );
        return this;
    }

    public WrapperMenuItemBuilder item(ItemStack itemStack) {
        this.wrapper = ItemWrapper.fromItem(itemStack);
        return this;
    }

    public WrapperMenuItemBuilder item(ItemWrapper wrapper) {
        this.wrapper = wrapper;
        return this;
    }

    public WrapperMenuItemBuilder cancelClick(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public MenuItem build() {
        return WrapperMenuItem.fromItem(
                identifier,
                slot,
                wrapper,
                blocked,
                action
        );
    }
}

package me.blueslime.menuhandlerapi.item.list;

import me.blueslime.menuhandlerapi.item.MenuItem;
import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import me.blueslime.menuhandlerapi.item.list.builder.WrapperMenuItemBuilder;
import me.blueslime.utilitiesapi.item.ItemWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WrapperMenuItem implements MenuItem {
    private final MenuItemAction action;
    private final ItemWrapper wrapper;
    private final String identifier;
    private final boolean blocked;
    private final int slot;

    private WrapperMenuItem(String identifier, int slot, ItemWrapper wrapper, boolean blocked, MenuItemAction action) {
        this.identifier = identifier;
        this.wrapper = wrapper;
        this.blocked = blocked;
        this.action = action;
        this.slot = slot;
    }

    public static MenuItem fromItem(String identifier, int slot, ItemWrapper wrapper, boolean blockedItem, MenuItemAction action) {
        return new WrapperMenuItem(
                identifier,
                slot,
                wrapper,
                blockedItem,
                action
        );
    }

    public static MenuItem fromItem(String identifier, int slot, ItemWrapper wrapper, boolean blockedItem) {
        return fromItem(identifier, slot, wrapper, blockedItem, null);
    }

    public static MenuItem fromItem(String identifier, int slot, ItemWrapper wrapper) {
        return fromItem(identifier, slot, wrapper, false, null);
    }

    public static MenuItem fromItem(String identifier, ItemWrapper wrapper, boolean blockedItem) {
        return fromItem(identifier, -1, wrapper, blockedItem, null);
    }

    public static MenuItem fromItem(String identifier, ItemWrapper wrapper) {
        return fromItem(identifier, -1, wrapper, false, null);
    }

    public static WrapperMenuItemBuilder builder(String identifier, int slot) {
        return new WrapperMenuItemBuilder(identifier, slot);
    }

    public static WrapperMenuItemBuilder builder(String identifier) {
        return new WrapperMenuItemBuilder(identifier, -1);
    }

    public WrapperMenuItemBuilder asBuilder() {
        return new WrapperMenuItemBuilder(
            this.identifier,
            this.slot
        ).action(
            this.action
        ).cancelClick(
            this.blocked
        ).item(
            this.wrapper
        );
    }

    @Override
    public MenuItemAction getAction() {
        return action;
    }

    @Override
    public ItemStack getItemStack() {
        return wrapper.getItem();
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return wrapper.getDynamicItem(player).getItem();
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public MenuItem copy() {
        return new WrapperMenuItem(
            this.identifier,
            this.slot,
            this.wrapper.clone(),
            this.blocked,
            this.action
        );
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "identifier=" + identifier +
                ", slot=" + slot +
                ", wrapper=" + wrapper +
                ", blocked=" + blocked +
                '}';
    }
}

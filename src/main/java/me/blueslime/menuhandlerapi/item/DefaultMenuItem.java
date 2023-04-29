package me.blueslime.menuhandlerapi.item;

import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultMenuItem implements MenuItem {
    private final MenuItemAction action;

    private final ItemStack itemStack;

    private final String identifier;

    private final boolean blocked;

    private final int slot;

    private DefaultMenuItem(String identifier, int slot, ItemStack itemStack, boolean blocked, MenuItemAction action) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.blocked = blocked;
        this.action = action;
        this.slot = slot;

    }

    public static DefaultMenuItem fromItem(String identifier, int slot, ItemStack itemStack, boolean blockedItem, MenuItemAction action) {
        return new DefaultMenuItem(
                identifier,
                slot,
                itemStack,
                blockedItem,
                action
        );
    }

    public static DefaultMenuItem fromItem(String identifier, int slot, ItemStack itemStack, boolean blockedItem) {
        return fromItem(identifier, slot, itemStack, blockedItem, null);
    }

    public static DefaultMenuItem fromItem(String identifier, int slot, ItemStack itemStack) {
        return fromItem(identifier, slot, itemStack, false, null);
    }

    public static DefaultMenuItem fromItem(String identifier, ItemStack itemStack, boolean blockedItem) {
        return fromItem(identifier, -1, itemStack, blockedItem, null);
    }

    public static DefaultMenuItem fromItem(String identifier, ItemStack itemStack) {
        return fromItem(identifier, -1, itemStack, false, null);
    }

    public static MenuItemBuilder builder(String identifier, int slot) {
        return new MenuItemBuilder(identifier, slot);
    }

    public static MenuItemBuilder builder(String identifier) {
        return new MenuItemBuilder(identifier, -1);
    }

    public MenuItemBuilder asBuilder() {
        return new MenuItemBuilder(
                this.identifier,
                this.slot
        ).action(
                this.action
        ).cancelClick(
                this.blocked
        ).item(
                this.itemStack
        );
    }

    public MenuItemAction getAction() {
        return action;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemStack getItemStack(Player player) {
        return itemStack;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public MenuItem copy() {
        return new DefaultMenuItem(
                this.identifier,
                this.slot,
                itemStack.clone(),
                this.blocked,
                this.action
        );
    }

    public int getSlot() {
        return slot;
    }

    public MenuItem clone(String identifier, int slot) {
        return new DefaultMenuItem(identifier, slot, this.itemStack, this.blocked, this.action);
    }

    public MenuItem clone(String identifier, MenuItemAction action) {
        return new DefaultMenuItem(identifier, this.slot, this.itemStack, this.blocked, action);
    }

    public MenuItem clone(String identifier, ItemStack itemStack) {
        return new DefaultMenuItem(identifier, this.slot, itemStack, this.blocked, this.action);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "identifier=" + identifier +
                ", slot=" + slot +
                ", itemStack=" + itemStack +
                ", blocked=" + blocked +
                '}';
    }
}


package me.blueslime.menuhandlerapi.item.list.builder;

import me.blueslime.menuhandlerapi.item.MenuItem;
import me.blueslime.menuhandlerapi.item.list.WrapperMenuItem;
import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import me.blueslime.utilitiesapi.item.ItemWrapper;

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

    public WrapperMenuItemBuilder action(MenuItemAction action) {
        this.action = action;
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

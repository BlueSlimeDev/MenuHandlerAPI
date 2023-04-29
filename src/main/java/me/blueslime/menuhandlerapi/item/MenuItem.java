package me.blueslime.menuhandlerapi.item;

import me.blueslime.menuhandlerapi.item.action.MenuItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {
    /**
     * Action of the item when you clicked it
     * @return {@link me.blueslime.menuhandlerapi.item.action.MenuItemAction}
     */
    MenuItemAction getAction();

    /**
     * This method is used in the StaticMenu
     * Gets the ItemStack of a player
     * @return the itemStack
     */
    ItemStack getItemStack();

    /**
     * This method is used in the DynamicMenu
     * Gets the ItemStack of a player
     * @param player to obtain the item stack
     * @return the itemStack
     */
    ItemStack getItemStack(Player player);

    /**
     * Checks if the item is blocked in the menu
     * @return true if the item is blocked
     */
    boolean isBlocked();

    /**
     * Identifier of the item in the menu
     * @return item identifier
     */
    String getIdentifier();

    /**
     * Create a copy of this MenuItem
     * @return a new instance of the MenuItem
     */
    MenuItem copy();

    /**
     * Slot of the item in the menu
     * @return {@link java.lang.Integer}
     */
    int getSlot();
}

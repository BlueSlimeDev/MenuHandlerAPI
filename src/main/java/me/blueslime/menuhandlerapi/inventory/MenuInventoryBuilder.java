package me.blueslime.menuhandlerapi.inventory;

import me.blueslime.menuhandlerapi.MenuHandlerAPI;
import me.blueslime.menuhandlerapi.inventory.types.DynamicInventory;
import me.blueslime.menuhandlerapi.inventory.types.HolderInventory;
import me.blueslime.menuhandlerapi.inventory.types.StaticInventory;

public class MenuInventoryBuilder {
    private boolean canIntroduce;
    private String identifier;
    private String title;
    private int menuType;
    private int slots;

    private MenuInventoryBuilder(String identifier, int slots, String title, int menuType, boolean canIntroduce) {
        this.canIntroduce = canIntroduce;
        this.identifier   = identifier;
        this.menuType     = menuType;
        this.title        = title;
        this.slots        = slots;
    }

    /**
     * Toggle if players can add items to the inventory
     * or disable it using false
     * @param canIntroduce {@link org.bukkit.inventory.ItemStack}
     */
    public MenuInventoryBuilder setCanIntroduce(boolean canIntroduce) {
        this.canIntroduce = canIntroduce;
        return this;
    }

    @Deprecated
    public MenuInventoryBuilder perPlayer(boolean perPlayer) {
        return type(
            perPlayer ?
                MenuHandlerAPI.REGISTERED_LEGACY ?
                    MenuType.LEGACY_DYNAMIC_INVENTORY :
                    MenuType.LEGACY_STATIC_INVENTORY :
                MenuType.UPDATED_STATIC_INVENTORY
        );
    }

    /**
     * Customize if the plugin creates a new inventory
     * per player, toggle this option if you want to use
     * variables per-item with plugins like PlaceholdersAPI
     * or with your own plugin
     * @param menuType {@link org.bukkit.inventory.Inventory}
     */
    public MenuInventoryBuilder type(int menuType) {
        this.menuType = menuType;
        return this;
    }

    public MenuInventoryBuilder title(String title) {
        this.title = title;
        return this;
    }

    public MenuInventoryBuilder slots(int slots) {
        this.slots = slots;
        return this;
    }

    public MenuInventoryBuilder identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public boolean canIntroduceItems() {
        return canIntroduce;
    }

    public int getMenuType() {
        return menuType;
    }

    public String getTitle() {
        return title;
    }

    public int getSlots() {
        return slots;
    }

    public static MenuInventoryBuilder builder(String identifier, String title, int slots) {
        return new MenuInventoryBuilder(
                identifier, slots, title, MenuType.UPDATED_STATIC_INVENTORY, false
        );
    }

    public static MenuInventoryBuilder builder(String identifier, String title) {
        return builder(identifier, title, 3);
    }

    public static MenuInventoryBuilder builder(String identifier, int slots) {
        return builder(
                identifier, "Inventory", slots
        );
    }

    public static MenuInventoryBuilder builder(String identifier) {
        return builder(
                identifier, "Inventory", 3
        );
    }

    public MenuInventory build() {
        if (menuType >= MenuType.UPDATED_STATIC_INVENTORY) {
            return new HolderInventory(
                identifier,
                title,
                slots
            );
        }

        if (menuType == MenuType.LEGACY_DYNAMIC_INVENTORY) {
            return new DynamicInventory(
                identifier,
                title,
                slots,
                canIntroduce
            );
        }
        return new StaticInventory(
            identifier,
            title,
            slots,
            canIntroduce
        );
    }

}

package net.tinzin.forge.nurma.items.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.tinzin.forge.nurma.Nurma;

public class ItemArmor extends net.minecraft.item.ItemArmor {
    private String name;

    public ItemArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, 0, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
    }

    public void registerItemModel(Item item) {
        Nurma.proxy.registerItemRenderer(this, 0, name);
    }

}
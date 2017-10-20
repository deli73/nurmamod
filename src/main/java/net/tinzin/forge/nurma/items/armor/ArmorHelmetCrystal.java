package net.tinzin.forge.nurma.items.armor;

import com.elytradev.mirage.lighting.IColoredLight;
import com.elytradev.mirage.lighting.Light;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.tinzin.forge.nurma.Nurma;

public class ArmorHelmetCrystal extends ItemArmor implements IColoredLight{
    public static final ItemArmor.ArmorMaterial crystalMaterial = EnumHelper.addArmorMaterial("crystal","crystal",
            25, new int[]{1, 3, 5, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    private static float r = 0.717647059f;
    private static float g = 0.843137255f;
    private static float b = 0.803921569f;
    private static float radius = 30;
    private static float luminescence = 1;
    private static float angle = 0.98f;

    private Vec3d vector = new Vec3d(1D,10D,8D);
    private boolean dispLight = false;

    protected static String name = "helm_light";

    public ArmorHelmetCrystal(){
        super(ArmorMaterial.GOLD, EntityEquipmentSlot.HEAD,name);
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
        vector = player.getLookVec();
        dispLight = true;
    }

    public Light getColoredLight(){
        if(dispLight) {
            Light light = new Light.Builder().color(r, g, b).radius(radius).pos(0, 0, 0).intensity(luminescence).cone(vector, angle).build();
            return light;
        }
        else{
            return null;
        }
    }

    public void registerItemModel() {
        Nurma.proxy.registerItemRenderer(this, 0, name);
    }

}

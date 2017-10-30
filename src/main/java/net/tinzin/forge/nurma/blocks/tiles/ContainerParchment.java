package net.tinzin.forge.nurma.blocks.tiles;

import com.elytradev.concrete.inventory.gui.ConcreteContainer;
import com.elytradev.concrete.inventory.gui.widget.WGridPanel;
import com.elytradev.concrete.inventory.gui.widget.WImage;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ContainerParchment extends ConcreteContainer {
    public ContainerParchment(IInventory player, IInventory block){
        super(player, block);
        WGridPanel panel = new WGridPanel();
        setRootPanel(panel);
        WImage bg = new WImage(new ResourceLocation("minecraft","textures/map/map_background.png"));
        panel.add(bg, -1,-1,11,11);
    }
}

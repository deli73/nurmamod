package net.tinzin.forge.nurma.sound;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoundRegisterListener {
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(SoundRegistrator.GRIND);
    }
}

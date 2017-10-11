package net.tinzin.forge.Nurma.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.tinzin.forge.Nurma.Nurma;

public class SoundRegistrator {
    public static final SoundEvent GRIND = addSoundsToRegistry("grind");

    private static SoundEvent addSoundsToRegistry(String soundId) {
        ResourceLocation shotSoundLocation = new ResourceLocation(Nurma.modId, soundId);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        return soundEvent;
    }


}

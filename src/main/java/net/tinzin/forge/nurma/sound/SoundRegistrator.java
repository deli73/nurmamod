package net.tinzin.forge.nurma.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.tinzin.forge.nurma.Nurma;

public class SoundRegistrator {
    public static final SoundEvent GRIND = addSoundsToRegistry("grind");
    public static final SoundEvent INK = addSoundsToRegistry("ink_scrape");

    private static SoundEvent addSoundsToRegistry(String soundId) {
        ResourceLocation shotSoundLocation = new ResourceLocation(Nurma.modId, soundId);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        return soundEvent;
    }


}

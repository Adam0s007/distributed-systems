package server.device.television;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.*;

public class HomeCinemaTV extends Television implements IHomeCinemaTV {
    private SurroundEffect currentEffect;
    private List<SurroundEffect> surroundEffects;
    public HomeCinemaTV(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.surroundEffects = Arrays.asList(
                new SurroundEffect("forest", "Standard"),
                new SurroundEffect("lake", "Standard"),
                new SurroundEffect("ocean", "Movie"),
                new SurroundEffect("forest", "Movie"),
                new SurroundEffect("moon", "Game"),
                new SurroundEffect("sunset", "Game"),
                new SurroundEffect("jungle", "Dynamic"),
                new SurroundEffect("mountain", "Dynamic"),
                new SurroundEffect("sunset", "Custom"),
                new SurroundEffect("sunset", "Custom")
        );
        this.currentEffect = new SurroundEffect("Off", "");
    }

    @Override
    public List<SurroundEffect> getSurroundEffects(Current current) {
        System.out.println("Method HomeCinemaTV.getSurroundEffects with no args called by: " + current.id.name + ", category: " + current.id.category);
        return this.surroundEffects;
    }

    @Override
    public SurroundEffect getCurrentSurroundEffect(Current current) throws SurroundEffectException {
        System.out.println("Method HomeCinemaTV.getCurrentSurroundEffect with no args called by: " + current.id.name + ", category: " + current.id.category);
        if (this.currentEffect.surroundSound.equals("Off")) {
            throw new SurroundEffectException("Surround sound is currently disabled.", "getCurrentSurroundEffect");
        }
        return currentEffect;
    }


    @Override
    public boolean setSurroundEffect(SurroundEffect surroundEffect,Current current) throws SurroundEffectException {
        System.out.println("Method HomeCinemaTV.setSurroundEffect with args " + surroundEffect.surroundSound + ", " + surroundEffect.pictureMode + " called by: " + current.id.name + ", category: " + current.id.category);
        String surroundSound = surroundEffect.surroundSound;
        String pictureMode = surroundEffect.pictureMode;
        Optional<SurroundEffect> effect = surroundEffects.stream()
                .filter(e -> e.surroundSound.equals(surroundSound) && e.pictureMode.equals(pictureMode))
                .findFirst();
        if (effect.isPresent()) {
            this.currentEffect = surroundEffect;
        } else {
            throw new SurroundEffectException("Invalid surround effect", "setSurroundEffect");
        }
        return true;
    }
    @Override
    public void disableSurroundSound(Current current) {
        System.out.println("Method HomeCinemaTV.disableSurroundSound with no args called by: " + current.id.name + ", category: " + current.id.category);
        currentEffect = new SurroundEffect("Off", "Standard");
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + " current surround effect: \n" +
                "Surround sound: " + currentEffect.surroundSound + ", Picture mode: " + currentEffect.pictureMode + "\n";
    }
}

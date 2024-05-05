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
    public List<SurroundEffect> getEffects(Current current) throws NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method HomeCinemaTV.getEffects with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        return this.surroundEffects;
    }

    @Override
    public SurroundEffect getCurrentEffect(Current current) throws SurroundEffectException,NotEnabledException{
        this.isTurnedOn(current);
        System.out.println("Method HomeCinemaTV.getCurrentEffect with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        if (this.currentEffect.surroundSound.equals("Off")) {
            throw new SurroundEffectException("Surround sound is currently disabled.", "getCurrentEffect");
        }
        return currentEffect;
    }


    @Override
    public boolean setEffect(SurroundEffect surroundEffect,Current current) throws SurroundEffectException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method HomeCinemaTV.setEffect with args " + surroundEffect.surroundSound + ", " + surroundEffect.pictureMode + ", current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
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
    public void disableSound(Current current) throws NotEnabledException{
        this.isTurnedOn(current);
        System.out.println("Method HomeCinemaTV.disableSound with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        currentEffect = new SurroundEffect("Off", "Standard");
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException{
        return super.getDetails(current) + " current surround effect: \n" +
                "Surround sound: " + currentEffect.surroundSound + ", Picture mode: " + currentEffect.pictureMode + "\n";
    }

    @Override
    public void isTurnedOn(Current current) throws NotEnabledException {
        super.isTurnedOn(current);
    }
}

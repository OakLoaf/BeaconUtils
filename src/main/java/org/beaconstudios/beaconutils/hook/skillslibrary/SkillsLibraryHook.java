package org.beaconstudios.beaconutils.hook.skillslibrary;

import me.xemor.skillslibrary2.effects.Effects;
import org.beaconstudios.beaconutils.hook.HookId;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.ParticleEffect;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.PhantomAnchorEffect;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.PhantomNoFireEffect;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.RandomEffect;
import org.lushplugins.lushlib.hook.Hook;

public class SkillsLibraryHook extends Hook {

    public SkillsLibraryHook() {
        super(HookId.SKILLS_LIBRARY);
    }

    @Override
    protected void onEnable() {
        Effects.registerEffect("PARTICLE", ParticleEffect.class);
        Effects.registerEffect("PHANTOMANCHOR", PhantomAnchorEffect.class);
        Effects.registerEffect("PHANTOMNOFIRE", PhantomNoFireEffect.class);
        Effects.registerEffect("RANDOM", RandomEffect.class);
    }
}

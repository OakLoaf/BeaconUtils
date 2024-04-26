package org.beaconstudios.beaconutils.hook.skillslibrary;

import me.xemor.skillslibrary2.effects.Effects;
import org.beaconstudios.beaconutils.hook.HookId;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.PhantomAnchorEffect;
import org.beaconstudios.beaconutils.hook.skillslibrary.effect.RandomEffect;
import org.lushplugins.lushlib.hook.Hook;

public class SkillsLibraryHook extends Hook {

    public SkillsLibraryHook() {
        super(HookId.SKILLS_LIBRARY);
    }

    @Override
    protected void onEnable() {
        Effects.registerEffect("PHANTOMANCHOR", PhantomAnchorEffect.class);
        Effects.registerEffect("RANDOM", RandomEffect.class);
    }
}

package com.kaltinril.vampiric.client.renderer;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.client.models.VampireBatModel;
import com.kaltinril.vampiric.entity.VampireBat;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VampireBatRenderer extends MobRenderer<VampireBat, VampireBatModel>
{

    public VampireBatRenderer(EntityRendererManager manager)
    {
        super(manager, new VampireBatModel(), 0.25f);
    }

    @Override
    public ResourceLocation getEntityTexture(VampireBat entity) {
        return VampiricMod.location("textures/entity/vampire_bat.png");
    }
}

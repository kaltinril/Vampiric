package com.kaltinril.vampiric.client.renderer;

import com.kaltinril.vampiric.entity.VampireBat;
import com.kaltinril.vampiric.lists.EntityList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class RenderRegistry {

    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityList.vampire_bat, VampireBatRenderer::new);
    }
}

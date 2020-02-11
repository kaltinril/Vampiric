package com.kaltinril.vampiric.client.renderer;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.client.models.VampireBatModel;
import com.kaltinril.vampiric.entity.VampireBat;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VampireBatLayer {//} extends LayerRenderer<VampireBat, VampireBatModel> {
    /*private static final ResourceLocation TEXTURE = VampiricMod.location("textures/entity/vampire/vampire_bat.png");
    private final VampireBatModel<VampireBat> vampireModel = new VampireBatModel();

    public VampireBatLayer(IEntityRenderer<VampireBat, VampireBatModel> entityRendererIn)
    {
        super(entityRendererIn);
    }

    @Override
    public void func_225628_a_(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, VampireBat vampireBat, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!vampireBat.isInvisible())
            {
            this.getEntityModel().setModelAttributes(this.vampireModel);
            func_229140_a_(this.getEntityModel(), this.vampireModel, TEXTURE, matrixStack, iRenderTypeBuffer, i, v, v1, v2, v3, v4, v5, 0, 1, 1, 1);
        }
    }*/
}

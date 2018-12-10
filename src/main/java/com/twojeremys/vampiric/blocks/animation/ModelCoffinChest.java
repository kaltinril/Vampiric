package com.twojeremys.vampiric.blocks.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelChest - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelCoffinChest extends ModelBase {
    public ModelRenderer handle1;
    public ModelRenderer handle2;
    public ModelRenderer lid;
    public ModelRenderer storage;

    public ModelCoffinChest() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.handle1 = new ModelRenderer(this, 0, 0);
        this.handle1.setRotationPoint(22.0F, 9.0F, 15.0F);
        this.handle1.addBox(-1.0F, -2.0F, -15.0F, 4, 2, 1, 0.0F);
        this.handle2 = new ModelRenderer(this, 0, 0);
        this.handle2.setRotationPoint(8.0F, 9.0F, 15.0F);
        this.handle2.addBox(-1.0F, -2.0F, -15.0F, 4, 2, 1, 0.0F);
        this.storage = new ModelRenderer(this, 0, 19);
        this.storage.setRotationPoint(1.0F, 4.0F, 1.0F);
        this.storage.addBox(0.0F, 0.0F, 0.0F, 30, 12, 14, 0.0F);
        this.lid = new ModelRenderer(this, -7, 0);
        this.lid.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.lid.addBox(0.0F, -5.0F, -14.0F, 30, 5, 14, 0.0F);
    }

    public void renderAll(){
        this.lid.render(0.0625f);
        this.handle1.render(0.0625f);
        this.handle2.render(0.0625f);
        this.storage.render(0.0625f);

    }
}

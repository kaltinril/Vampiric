package com.twojeremys.vampiric.blocks.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ModelCoffinChest extends ModelBase {
    public ModelRenderer handle1;
    public ModelRenderer handle2;
    public ModelRenderer handle3;
    public ModelRenderer handle4;
    public ModelRenderer lid;
    public ModelRenderer storage;

    public ModelCoffinChest() {
        this.textureWidth = 128;
        this.textureHeight = 128;

        // Handles
        this.handle1 = new ModelRenderer(this, 0, 0);
        this.handle1.setRotationPoint(22.0F, 9.0F, 15.0F);
        this.handle1.addBox(-1.0F, 0.0F, -15.0F, 4, 2, 1, 0.0F);

        this.handle2 = new ModelRenderer(this, 0, 0);
        this.handle2.setRotationPoint(8.0F, 9.0F, 15.0F);
        this.handle2.addBox(-1.0F, 0.0F, -15.0F, 4, 2, 1, 0.0F);

        this.handle3 = new ModelRenderer(this, 0, 0);
        this.handle3.setRotationPoint(22.0F, 9.0F, 15.0F);
        this.handle3.addBox(-1.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);

        this.handle4 = new ModelRenderer(this, 0, 0);
        this.handle4.setRotationPoint(8.0F, 9.0F, 15.0F);
        this.handle4.addBox(-1.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);

        // Storage (bottom)
        this.storage = new ModelRenderer(this, 0, 17);
        this.storage.setRotationPoint(1.0F, 4.0F, 1.0F);
        this.storage.addBox(0.0F, 0.0F, 0.0F, 30, 12, 14, 0.0F);

        // Lid
        this.lid = new ModelRenderer(this, 0, 0);
        this.lid.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.lid.addBox(0.0F, -5.0F, -14.0F, 30, 3, 14, 0.0F);
    }

    public void renderAll(){
        this.lid.render(0.0625f);
        this.handle1.render(0.0625f);
        this.handle2.render(0.0625f);
        this.handle3.render(0.0625f);
        this.handle4.render(0.0625f);
        this.storage.render(0.0625f);
    }
}

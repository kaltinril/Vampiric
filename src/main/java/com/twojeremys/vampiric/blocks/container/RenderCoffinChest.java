package com.twojeremys.vampiric.blocks.container;

import com.twojeremys.vampiric.blocks.animation.ModelCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCoffinChest extends TileEntitySpecialRenderer<TileEntityCoffinChest> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/coffin_chest.png");
    private static final ModelCoffinChest MODEL = new ModelCoffinChest();

    @Override
    public void render(TileEntityCoffinChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        // We only want to render 1 chest, so just render the first one
        if (te.isFirst) {
            GlStateManager.enableDepth();
            GlStateManager.depthFunc(515);
            GlStateManager.depthMask(true);

            ModelCoffinChest model = MODEL;

            if (destroyStage >= 0) {
                this.bindTexture(DESTROY_STAGES[destroyStage]);
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scale(4.0F, 4.0F, 1.0F);
                GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            } else this.bindTexture(TEXTURE);

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);

            // Decide if we need to rotate the block based on it's FACING direction
            Block block = te.getBlockType();
            int i = te.getBlockMetadata();

            int j = 0; // Set J to 0 for default NORTH
            if (i == 2) j = 180; // SOUTH
            if (i == 4) j = 90;// EAST
            if (i == 5) j = -90; // WEST

            // Move the render of the second block so it lines up exactly ontop of the other one
            /*
            if (i == 2 && !te.isFirst) // South
                GlStateManager.translate(1.0F, 0.0F, 0.0F);

            if (i == 5 && !te.isFirst) // West
                GlStateManager.translate(0.0F, 0.0F, -1.0F);

            if (i == 3 && !te.isFirst) // North
                GlStateManager.translate(-1.0F, 0.0F, 0.0F);

            if (i == 4 && !te.isFirst) // East
                GlStateManager.translate(0.0F, 0.0F, 1.0F);

            */

            GlStateManager.rotate((float) j, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);

            float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
            f = 1.0F - f;
            f = 1.0F - f * f * f;
            model.lid.rotateAngleX = -(f * ((float) Math.PI / 2F));
            model.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (destroyStage >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }
}

package com.twojeremys.vampiric.blocks.gui;

import com.twojeremys.vampiric.blocks.container.ContainerCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoffinChest extends GuiContainer {
    private static final ResourceLocation GUI_CHEST = new ResourceLocation(Reference.MOD_ID + ":textures/gui/coffin_chest.png");

    private final InventoryPlayer playerInventory;
    private final TileEntityCoffinChest te;

    public GuiCoffinChest(InventoryPlayer playerInventory, TileEntityCoffinChest chestInventory, EntityPlayer player){
        super(new ContainerCoffinChest(playerInventory, chestInventory, player));
        this.playerInventory = playerInventory;
        this.te = chestInventory;

        this.xSize = 179;
        this.ySize = 184;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 16, 1654681);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 1654681);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(GUI_CHEST);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}

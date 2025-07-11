package com.imoonday.modulararmor.client.screen;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.client.screen.container.VestCraftTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class VestCraftTableScreen extends AbstractContainerScreen<VestCraftTableMenu> {

    public static final ResourceLocation TEXTURE = ModularArmorSystem.id("textures/gui/vest_crafttable.png");
    public static final ResourceLocation INACTIVE_SLOT_TEXTURE = ModularArmorSystem.id("textures/gui/inactive_slot.png");

    public VestCraftTableScreen(VestCraftTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        for (int k = 1; k < 10; k++) {
            Slot slot = this.menu.getSlot(k);
            if (!slot.isActive()) {
                pGuiGraphics.blit(INACTIVE_SLOT_TEXTURE, i + slot.x - 1, j + slot.y - 1, 0, 0, 18, 18, 18, 18);
            }
        }
    }
}

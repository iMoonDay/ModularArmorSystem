package com.imoonday.modulararmor.client.screen;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.imoonday.modulararmor.client.screen.container.VestStorageMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class VestStorageScreen extends AbstractContainerScreen<VestStorageMenu> {

    public static final ResourceLocation TEXTURE_0 = ModularArmorSystem.id("textures/gui/vest_storage_0.png");
    public static final ResourceLocation TEXTURE_3 = ModularArmorSystem.id("textures/gui/vest_storage_3.png");
    public static final ResourceLocation TEXTURE_6 = ModularArmorSystem.id("textures/gui/vest_storage_6.png");
    public static final ResourceLocation TEXTURE_9 = ModularArmorSystem.id("textures/gui/vest_storage_9.png");
    public static final ResourceLocation TEXTURE_12 = ModularArmorSystem.id("textures/gui/vest_storage_12.png");
    public static final ResourceLocation TEXTURE_15 = ModularArmorSystem.id("textures/gui/vest_storage_15.png");

    private final int storageSlots;

    public VestStorageScreen(VestStorageMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.storageSlots = this.menu.getStorageSlots();

        this.imageHeight = this.storageSlots > 9 ? 151 : 133;
        this.inventoryLabelY = this.imageHeight - 94;
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
        pGuiGraphics.blit(getBackgroundTexture(), i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    public ResourceLocation getBackgroundTexture() {
        if (storageSlots > 12) {
            return TEXTURE_15;
        } else if (storageSlots > 9) {
            return TEXTURE_12;
        } else if (storageSlots > 6) {
            return TEXTURE_9;
        } else if (storageSlots > 3) {
            return TEXTURE_6;
        } else if (storageSlots > 0) {
            return TEXTURE_3;
        } else {
            return TEXTURE_0;
        }
    }
}

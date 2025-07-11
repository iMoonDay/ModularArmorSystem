package com.imoonday.modulararmor.client.render;

import com.imoonday.modulararmor.client.RenderArmorItemExtension;
import com.imoonday.modulararmor.item.BagItemBase;
import com.imoonday.modulararmor.item.Modular;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BagRenderLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public BagRenderLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.isEmpty() || !(stack.getItem() instanceof Modular modular)) {
            return;
        }

        for (ItemStack part : modular.getInstalledParts(stack)) {
            tryRenderPart(poseStack, buffer, packedLight, livingEntity, part);
        }
    }

    public void tryRenderPart(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, ItemStack part) {
        if (part.isEmpty() || !(part.getItem() instanceof BagItemBase bagItem)) {
            return;
        }

        if (!(bagItem.getRenderPropertiesInternal() instanceof RenderArmorItemExtension<?> renderArmorItemExtension)) {
            return;
        }

        Model model = renderArmorItemExtension.getModel();
        M parentModel = this.getParentModel();
        if (parentModel instanceof HumanoidModel<?> humanoidModel) {
            model = renderArmorItemExtension.getGenericArmorModel(livingEntity, part, EquipmentSlot.CHEST, humanoidModel);
        }
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(renderArmorItemExtension.getTextureLocation()));

        float r = 1.0F, g = 1.0F, b = 1.0F;
        if (bagItem.hasCustomColor(part)) {
            int color = bagItem.getColor(part);
            r = (float) (color >> 16 & 255) / 255.0F;
            g = (float) (color >> 8 & 255) / 255.0F;
            b = (float) (color & 255) / 255.0F;
        }
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }
}

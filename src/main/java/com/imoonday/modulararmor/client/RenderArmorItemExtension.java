package com.imoonday.modulararmor.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static net.minecraft.world.item.ItemDisplayContext.GUI;

public class RenderArmorItemExtension<T extends EntityModel<? extends Entity>> implements IClientItemExtensions {

    private final ModelLayerLocation layerLocation;
    private final ResourceLocation textureLocation;
    private final Function<ModelPart, T> model;
    @Nullable
    private Function<T, ModelPart> head;
    @Nullable
    private Function<T, ModelPart> hat;
    @Nullable
    private Function<T, ModelPart> body;
    @Nullable
    private Function<T, ModelPart> rightArm;
    @Nullable
    private Function<T, ModelPart> leftArm;
    @Nullable
    private Function<T, ModelPart> rightLeg;
    @Nullable
    private Function<T, ModelPart> leftLeg;

    private double guiOffsetY = 0.85;
    private double normalOffsetY = 1;

    public RenderArmorItemExtension(ModelLayerLocation layerLocation, Function<ModelPart, T> model) {
        this.layerLocation = layerLocation;
        this.textureLocation = new ResourceLocation("textures/models/armor/" + layerLocation.getModel().getPath() + "_layer_1.png");
        this.model = model;
    }

    public T getModel() {
        return model.apply(getRoot());
    }

    public ModelPart getRoot() {
        return Minecraft.getInstance().getEntityModels().bakeLayer(layerLocation);
    }

    public RenderArmorItemExtension<T> setHead(Function<T, ModelPart> head) {
        this.head = head;
        return this;
    }

    public RenderArmorItemExtension<T> setHat(Function<T, ModelPart> hat) {
        this.hat = hat;
        return this;
    }

    public RenderArmorItemExtension<T> setBody(Function<T, ModelPart> body) {
        this.body = body;
        return this;
    }

    public RenderArmorItemExtension<T> setRightArm(Function<T, ModelPart> rightArm) {
        this.rightArm = rightArm;
        return this;
    }

    public RenderArmorItemExtension<T> setLeftArm(Function<T, ModelPart> leftArm) {
        this.leftArm = leftArm;
        return this;
    }

    public RenderArmorItemExtension<T> setRightLeg(Function<T, ModelPart> rightLeg) {
        this.rightLeg = rightLeg;
        return this;
    }

    public RenderArmorItemExtension<T> setLeftLeg(Function<T, ModelPart> leftLeg) {
        this.leftLeg = leftLeg;
        return this;
    }

    public double getGuiOffsetY() {
        return guiOffsetY;
    }

    public RenderArmorItemExtension<T> setGuiOffsetY(double guiOffsetY) {
        this.guiOffsetY = guiOffsetY;
        return this;
    }

    public double getNormalOffsetY() {
        return normalOffsetY;
    }

    public RenderArmorItemExtension<T> setNormalOffsetY(double normalOffsetY) {
        this.normalOffsetY = normalOffsetY;
        return this;
    }

    public RenderArmorItemExtension<T> setOffsetY(double guiOffsetY, double normalOffsetY) {
        this.setGuiOffsetY(guiOffsetY);
        this.setNormalOffsetY(normalOffsetY);
        return this;
    }

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        T model = getModel();
        HumanoidModel<?> armorModel = new HumanoidModel<>(new ModelPart(List.of(), Map.of("head", getOrEmpty(model, head),
                                                                                          "hat", getOrEmpty(model, hat),
                                                                                          "body", getOrEmpty(model, body),
                                                                                          "right_arm", getOrEmpty(model, rightArm),
                                                                                          "left_arm", getOrEmpty(model, leftArm),
                                                                                          "right_leg", getOrEmpty(model, rightLeg),
                                                                                          "left_leg", getOrEmpty(model, leftLeg))));
        armorModel.crouching = livingEntity.isShiftKeyDown();
        armorModel.riding = original.riding;
        armorModel.young = livingEntity.isBaby();
        return armorModel;
    }

    public static <T extends EntityModel<? extends Entity>> ModelPart getOrEmpty(T model, Function<T, ModelPart> modelPartGetter) {
        if (modelPartGetter != null) {
            ModelPart modelPart = modelPartGetter.apply(model);
            if (modelPart != null) {
                return modelPart;
            }
        }
        return createEmtpy();
    }

    public static @NotNull ModelPart createEmtpy() {
        ModelPart part = new ModelPart(List.of(), Map.of());
        part.visible = false;
        return part;
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        Minecraft mc = Minecraft.getInstance();
        EntityModel<? extends Entity> model = getModel();
        return new BlockEntityWithoutLevelRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels()) {
            @Override
            public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
                float r = 1.0F;
                float g = 1.0F;
                float b = 1.0F;
                if (stack.getItem() instanceof DyeableLeatherItem dyeable && dyeable.hasCustomColor(stack)) {
                    int color = dyeable.getColor(stack);
                    r = (float) (color >> 16 & 255) / 255.0F;
                    g = (float) (color >> 8 & 255) / 255.0F;
                    b = (float) (color & 255) / 255.0F;
                }

                poseStack.pushPose();
                if (displayContext == GUI) {
                    poseStack.translate(0.5, guiOffsetY, 0.5);
                    poseStack.mulPose(Axis.ZN.rotationDegrees(180));
                    model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityTranslucent(textureLocation)), packedLight, packedOverlay, r, g, b, 1.0F);
                } else {
                    poseStack.translate(0.5, normalOffsetY, 0.5);
                    poseStack.scale(-1, -1, 1);
                    RenderType renderType = RenderType.entityCutout(textureLocation);
                    model.renderToBuffer(poseStack, buffer.getBuffer(renderType), packedLight, packedOverlay, r, g, b, 1.0F);
                }
                poseStack.popPose();
            }
        };
    }
}

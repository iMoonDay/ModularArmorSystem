package com.imoonday.modulararmor.client.model;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class KevlarChestplateModel<T extends LivingEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("kevlar_chestplate"), "main");
    public final ModelPart armor;

    public KevlarChestplateModel(ModelPart root) {
        this.armor = root.getChild("armor");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition armor = partdefinition.addOrReplaceChild("armor", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.0F, 7.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(0, 12).addBox(-3.5F, 0.0F, 2.0F, 7.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(16, 0).addBox(-5.2F, 4.0F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(26, 10).addBox(-5.2F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(16, 11).addBox(4.2F, 4.0F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(26, 15).addBox(4.2F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(28, 20).addBox(-2.0F, 10.075F, 1.975F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(26, 0).addBox(-4.2F, 0.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                        .texOffs(26, 5).addBox(2.2F, 0.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = armor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 23).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6038F, 11.134F, 2.475F, 0.0F, 0.0F, -0.5367F));

        PartDefinition cube_r2 = armor.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 25).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6038F, 11.134F, 2.475F, 0.0F, 0.0F, 0.5367F));

        PartDefinition cube_r3 = armor.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -5.5F, -0.5F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.549F, 5.5F, -1.817F, 0.0F, -0.5236F, 0.0F));

        PartDefinition cube_r4 = armor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 22).addBox(-1.5F, -5.5F, -0.5F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.549F, 5.5F, 1.817F, 0.0F, 0.5236F, 0.0F));

        PartDefinition cube_r5 = armor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(6, 24).addBox(-1.5F, -5.5F, -0.5F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.683F, 5.5F, 2.317F, 0.0F, -0.5236F, 0.0F));

        PartDefinition cube_r6 = armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(16, 22).addBox(-1.5F, -5.5F, -0.5F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.683F, 5.5F, -2.317F, 0.0F, 0.5236F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        armor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
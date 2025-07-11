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

public class KevlarHelmetModel<T extends LivingEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("kevlar_helmet"), "main");
    public final ModelPart tk1;

    public KevlarHelmetModel(ModelPart root) {
        this.tk1 = root.getChild("tk1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tk1 = partdefinition.addOrReplaceChild("tk1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.7F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.1F))
                                                                                    .texOffs(0, 31).addBox(-4.5142F, -9.2F, 5.4142F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(20, 31).addBox(-4.5142F, -9.2F, -6.4142F, 9.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 12).addBox(4.9F, -9.2F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(22, 12).addBox(-5.9284F, -9.2F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = tk1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 25).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.243F, 6.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r2 = tk1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 28).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.866F, -8.968F, 0.0F, 0.0F, 1.5708F, -0.5236F));

        PartDefinition cube_r3 = tk1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -2.0F, -1.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.034F, -8.468F, -1.0F, 0.0F, 1.5708F, 0.5236F));

        PartDefinition cube_r4 = tk1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, -1.0F, -0.5F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.6554F, -4.9742F, -0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tk1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 34).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1929F, -8.7F, 5.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r6 = tk1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 35).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5142F, -8.7F, -5.7071F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r7 = tk1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(26, 34).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2213F, -8.7F, 5.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r8 = tk1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 34).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4858F, -8.7F, -5.7071F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        tk1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
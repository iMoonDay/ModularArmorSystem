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

public class TacVestModel<T extends LivingEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("tac_vest"), "main");
    public final ModelPart kj3;

    public TacVestModel(ModelPart root) {
        this.kj3 = root.getChild("kj3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition kj3 = partdefinition.addOrReplaceChild("kj3", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, 1.0F, -3.25F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 0).addBox(-4.0F, 0.0F, 2.25F, 8.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(18, 0).addBox(-4.9397F, 5.5F, -3.092F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(18, 26).addBox(-4.0F, -0.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(-0.1F))
                                                                                    .texOffs(0, 27).addBox(2.0F, -0.5F, -2.975F, 2.0F, 1.0F, 6.0F, new CubeDeformation(-0.1F))
                                                                                    .texOffs(32, 24).addBox(-4.0F, 0.2F, -3.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(20, 33).addBox(2.0F, 0.2F, -3.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = kj3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 33).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.384F, 1.133F, -2.75F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r2 = kj3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 33).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.384F, 1.133F, -2.75F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r3 = kj3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 16).addBox(2.106F, -3.5F, -0.0315F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3693F, 9.0F, 0.0386F, 0.0F, -1.3963F, 0.0F));

        PartDefinition cube_r4 = kj3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 13).addBox(-0.5704F, -3.5F, -3.0534F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3693F, 9.0F, 0.0386F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r5 = kj3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 33).addBox(0.4718F, -3.5F, 1.964F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3693F, 9.0F, 0.0386F, -3.1416F, 0.3491F, 3.1416F));

        PartDefinition cube_r6 = kj3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 8).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3605F, 9.0F, -2.6864F, 0.0F, 1.7453F, 0.0F));

        PartDefinition cube_r7 = kj3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2988F, 9.0F, 2.6091F, 0.0F, -0.3491F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        kj3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
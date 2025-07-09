package com.imoonday.modulararmor.client.model;

import com.imoonday.modulararmor.ModularArmorSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TacVestModel<T extends Entity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("tac_vest"), "main");
    public final ModelPart kj2;

    public TacVestModel(ModelPart root) {
        this.kj2 = root.getChild("kj2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition kj2 = partdefinition.addOrReplaceChild("kj2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 11).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(18, 6).addBox(-4.0F, -0.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(-0.1F))
                                                                                    .texOffs(18, 13).addBox(2.0F, -0.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(-0.1F))
                                                                                    .texOffs(18, 0).addBox(-4.0F, 4.0F, -3.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.3F))
                                                                                    .texOffs(10, 24).addBox(-5.4F, 6.0F, -2.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(20, 24).addBox(-5.4F, 8.0F, -2.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 24).addBox(-5.4F, 4.0F, -2.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = kj2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 34).addBox(-0.5F, 1.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(0, 34).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(30, 31).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.284F, 6.5F, -2.267F, 0.0F, -1.0472F, 0.0F));

        PartDefinition cube_r2 = kj2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 28).addBox(-0.5F, 1.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(30, 25).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(30, 22).addBox(-0.5F, -2.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.284F, 6.5F, 1.867F, 0.0F, 1.0472F, 0.0F));

        PartDefinition cube_r3 = kj2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 22).addBox(1.192F, -2.5F, 1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.158F, 10.5F, 4.2F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r4 = kj2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 22).addBox(1.192F, -2.5F, 1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.158F, 8.5F, 4.2F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r5 = kj2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(18, 20).addBox(1.192F, -2.5F, 1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.158F, 6.5F, 4.2F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r6 = kj2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 29).addBox(-0.808F, -2.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(10, 29).addBox(-0.808F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(20, 29).addBox(-0.808F, 1.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.558F, 6.5F, -0.2F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition cube_r7 = kj2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(34, 6).addBox(-2.1361F, -2.5F, 0.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(34, 9).addBox(-2.1361F, -0.5F, 0.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(12, 34).addBox(-2.1361F, 1.5F, 0.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.558F, 6.5F, -0.2F, -3.1416F, -1.0472F, 3.1416F));

        PartDefinition cube_r8 = kj2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 12).addBox(-2.1361F, -2.5F, -2.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(34, 15).addBox(-2.1361F, -0.5F, -2.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                                                                                 .texOffs(18, 34).addBox(-2.1361F, 1.5F, -2.3002F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.558F, 6.5F, -0.2F, -3.1416F, 1.0472F, 3.1416F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        kj2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
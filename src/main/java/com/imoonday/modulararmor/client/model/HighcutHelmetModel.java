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

public class HighcutHelmetModel<T extends LivingEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("highcut_helmet"), "main");
    public final ModelPart tk3;

    public HighcutHelmetModel(ModelPart root) {
        this.tk3 = root.getChild("tk3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tk3 = partdefinition.addOrReplaceChild("tk3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tk = tk3.addOrReplaceChild("tk", CubeListBuilder.create().texOffs(34, 0).addBox(-5.9F, -20.0F, 4.7321F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(0, 0).addBox(-5.9F, -21.0F, -4.5F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(0, 28).addBox(-6.9F, -20.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(38, 15).addBox(-6.9F, -18.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(36, 33).addBox(-5.9F, -20.0F, -5.7321F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(18, 30).addBox(2.1F, -20.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(38, 19).addBox(2.1F, -18.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 11.2F, 0.0F));

        PartDefinition cube_r1 = tk.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.734F, -18.0F, 4.366F, 0.0F, -0.5236F, 0.0F));

        PartDefinition cube_r2 = tk.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 10).addBox(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.734F, -18.0F, -4.366F, 0.0F, 0.5236F, 0.0F));

        PartDefinition cube_r3 = tk.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.234F, -20.5F, 0.0F, 0.0F, 0.0F, -2.618F));

        PartDefinition cube_r4 = tk.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.534F, -19.634F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r5 = tk.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(36, 30).addBox(-4.0F, -0.5F, -1.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, -20.067F, 4.516F, 2.618F, 0.0F, 0.0F));

        PartDefinition cube_r6 = tk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(34, 6).addBox(-2.0F, -1.0F, -1.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9F, -19.6183F, -4.2977F, 0.5236F, 0.0F, 0.0F));

        PartDefinition cube_r7 = tk.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 37).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4F, -18.0F, 4.866F, 0.0F, 0.5236F, 0.0F));

        PartDefinition cube_r8 = tk.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(10, 38).addBox(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.4F, -18.0F, -4.866F, 0.0F, -0.5236F, 0.0F));

        PartDefinition jd = tk3.addOrReplaceChild("jd", CubeListBuilder.create().texOffs(6, 38).addBox(-5.899F, -17.9F, -5.132F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(8, 38).addBox(2.1F, -17.9F, -5.132F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(34, 9).addBox(-5.9F, -10.9F, -5.132F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(38, 23).addBox(2.2F, -15.5F, 3.7929F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(16, 38).addBox(-5.999F, -15.5F, 3.7929F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 11.2F, 0.0F));

        PartDefinition cube_r9 = jd.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(20, 10).addBox(-1.0F, 3.0F, -7.0F, 0.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                                                                                .texOffs(20, 20).addBox(7.199F, 3.0F, -7.0F, 0.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.999F, -15.7718F, 1.3926F, 0.2967F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        tk3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
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

public class TacHelmetModel<T extends Entity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("tac_helmet"), "main");
    public final ModelPart tk2;

    public TacHelmetModel(ModelPart root) {
        this.tk2 = root.getChild("tk2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tk2 = partdefinition.addOrReplaceChild("tk2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tk = tk2.addOrReplaceChild("tk", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -33.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(0, 31).addBox(-4.0F, -32.5F, 4.4F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(36, 28).addBox(4.4F, -32.5F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(16, 9).addBox(4.4F, -32.5F, -3.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(0, 9).addBox(-5.4F, -32.5F, -3.0F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(36, 32).addBox(-5.4F, -32.5F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(18, 31).addBox(-4.0108F, -32.5F, -5.4142F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = tk.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 3).addBox(0.0F, -2.0F, -1.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -32.0787F, 5.3929F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r2 = tk.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 21).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.325F, -32.425F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r3 = tk.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.325F, -32.425F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r4 = tk.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, -2.0F, -1.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -31.3787F, -4.7071F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tk.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 14).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6929F, -30.5F, 4.7071F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r6 = tk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(18, 35).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9858F, -30.5F, -4.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r7 = tk.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 7).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9858F, -30.5F, 4.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r8 = tk.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 35).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6929F, -30.5F, -4.7071F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r9 = tk.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 37).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
                                                                                .texOffs(36, 36).addBox(9.3F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-4.9F, -29.311F, -3.3186F, 0.5149F, 0.0F, 0.0F));

        PartDefinition jd = tk2.addOrReplaceChild("jd", CubeListBuilder.create().texOffs(32, 6).addBox(-4.4108F, -24.5F, -4.8142F, 9.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(34, 35).addBox(4.5892F, -29.5F, -4.8142F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(4, 37).addBox(4.6F, -27.5F, 3.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(6, 37).addBox(-4.399F, -27.5F, 3.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                                                                       .texOffs(36, 21).addBox(-4.4108F, -29.5F, -4.8142F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, 24.0F, 0.0F));

        PartDefinition cube_r10 = jd.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 35).addBox(0.0F, -3.0F, -0.5F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.399F, -25.3801F, 0.8755F, -1.3003F, 0.0F, 0.0F));

        PartDefinition cube_r11 = jd.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 35).addBox(0.0F, -3.0F, -0.5F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5892F, -25.318F, 0.8703F, -1.3003F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        tk2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
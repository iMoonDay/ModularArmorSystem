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

public class SmallBagModel<T extends LivingEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ModularArmorSystem.id("small_bag"), "main");
    public final ModelPart bag;

    public SmallBagModel(ModelPart root) {
        this.bag = root.getChild("bag");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bag = partdefinition.addOrReplaceChild("bag", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 7.0F, -4.1F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 0).addBox(-1.0F, 7.0F, -4.1F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                                                                                    .texOffs(0, 0).addBox(1.5F, 7.0F, -4.1F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bag.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
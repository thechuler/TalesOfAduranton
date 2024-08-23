package net.rbk.talesofaduranton.Entidades.Modelos;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbk.talesofaduranton.Entidades.Animaciones.MosKabumAnimaciones;
import net.rbk.talesofaduranton.Entidades.entity.NitroMoscaEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;


public class NitroMoscaModel<T extends NitroMoscaEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "moskabummodel"), "main");
	private final ModelPart Root;


	public NitroMoscaModel(ModelPart root) {
		this.Root = root.getChild("Root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition Cuerpo = Root.addOrReplaceChild("Cuerpo", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Alas = Cuerpo.addOrReplaceChild("Alas", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Ala1 = Alas.addOrReplaceChild("Ala1", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -10.0F, 2.0F));

		PartDefinition Ala2 = Alas.addOrReplaceChild("Ala2", CubeListBuilder.create().texOffs(12, 15).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -10.0F, 2.0F));

		PartDefinition Torso = Cuerpo.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition Torso1 = Torso.addOrReplaceChild("Torso1", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition Torso2 = Torso.addOrReplaceChild("Torso2", CubeListBuilder.create(), PartPose.offset(0.0F, -0.1F, 0.0F));

		PartDefinition cube_r1 = Torso2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Patas2 = Cuerpo.addOrReplaceChild("Patas2", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 2.0F));

		PartDefinition Patas1 = Cuerpo.addOrReplaceChild("Patas1", CubeListBuilder.create().texOffs(0, 23).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition Cabeza = Cuerpo.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Art1 = Cabeza.addOrReplaceChild("Art1", CubeListBuilder.create().texOffs(21, 24).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition Art2 = Cabeza.addOrReplaceChild("Art2", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition cube_r2 = Art2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 23).addBox(0.0F, -5.0F, -4.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.8F, -0.2F, 0.8727F, 0.0F, 0.0F));

		PartDefinition Art3 = Cabeza.addOrReplaceChild("Art3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}




	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
		super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
	}


	@Override
	public ModelPart root() {
		return this.Root;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((NitroMoscaEntity) t).idleAnimationState, MosKabumAnimaciones.idle, v2, 1f);
	}
}
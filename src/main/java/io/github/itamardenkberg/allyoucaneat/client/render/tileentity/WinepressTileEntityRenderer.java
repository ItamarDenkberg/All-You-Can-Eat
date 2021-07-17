package io.github.itamardenkberg.allyoucaneat.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.itamardenkberg.allyoucaneat.common.tileentities.WinepressTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class WinepressTileEntityRenderer extends TileEntityRenderer<WinepressTileEntity> {
	private Minecraft mc = Minecraft.getInstance();

	public WinepressTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(WinepressTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer,
			int combinedLightIn, int combinedOverlayIn) {
		if (tile.getItem().equals(ItemStack.EMPTY) || tile.getItem().equals(Items.AIR))
			return;

		int lightLevel = getLightLevel(tile.getWorld(), tile.getPos().up());

		renderItem(tile.getItem(), new double[] { 0.5d, 0.4d, 0.5d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.55d, 0.42d, 0.53d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.53d, 0.44d, 0.56d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.5d, 0.46d, 0.53d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.52d, 0.48d, 0.49d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.55d, 0.5d, 0.53d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
		renderItem(tile.getItem(), new double[] { 0.52d, 0.52d, 0.55d }, Vector3f.XP.rotationDegrees(90f), matrixStack,
				buffer, partialTicks, combinedOverlayIn, lightLevel, 0.8f);
	}

	private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack,
			IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int lightLevel, float scale) {
		matrixStack.push();
		matrixStack.translate(translation[0], translation[1], translation[2]);
		matrixStack.rotate(rotation);
		matrixStack.scale(scale, scale, scale);

		IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);
		mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer,
				lightLevel, combinedOverlay, model);
		matrixStack.pop();
	}

	private int getLightLevel(World world, BlockPos pos) {
		int bLight = world.getLightFor(LightType.BLOCK, pos);
		int sLight = world.getLightFor(LightType.SKY, pos);
		return LightTexture.packLight(bLight, sLight);

	}
}

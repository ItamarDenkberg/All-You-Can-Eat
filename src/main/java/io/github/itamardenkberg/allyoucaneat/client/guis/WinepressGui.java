package io.github.itamardenkberg.allyoucaneat.client.guis;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.containers.WinepressContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WinepressGui extends ContainerScreen<WinepressContainer> {

	private static final ResourceLocation WINEPRESS_GUI = new ResourceLocation(AllYouCanEat.MOD_ID,
			"textures/guis/container/winepress.png");

	public WinepressGui(WinepressContainer container, PlayerInventory inventory, ITextComponent component) {
		super(container, inventory, component);

		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 175;
		this.ySize = 201;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(stack, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack stack, int x, int y) {
		this.font.drawText(stack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX,
				(float) this.playerInventoryTitleY, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bindTexture(WINEPRESS_GUI);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.blit(stack, x, y, 0, 0, this.xSize, this.ySize);
	}
}

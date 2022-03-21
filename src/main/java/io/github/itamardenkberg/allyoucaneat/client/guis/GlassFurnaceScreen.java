package io.github.itamardenkberg.allyoucaneat.client.guis;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.items.crafting.GlassFurnaceRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class GlassFurnaceScreen extends AbstractContainerScreen<GlassFurnaceMenu> {
	private static final int RECIPES_X = 30;
	private static final int RECIPES_Y = 16;
	protected int imageHeight = 169;
	private float scrollOffs;
	private int startIndex;
	private boolean displayRecipes;
	private boolean scrolling;

	private static final ResourceLocation TEXTURE = new ResourceLocation(AllYouCanEat.MOD_ID,
			"textures/guis/container/glass_furnace_gui.png");

	public GlassFurnaceScreen(GlassFurnaceMenu furnaceMenu, Inventory inventory, Component title) {
		super(furnaceMenu, inventory, title);
		furnaceMenu.registerUpdateListener(this::containerChanged);
		this.inventoryLabelY = this.imageHeight - 98;
		this.inventoryLabelX = this.inventoryLabelX - 2;
		this.titleLabelY = this.titleLabelY - 3;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, delta);
		renderTooltip(stack, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack stack, float pPartialTicks, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);

		if (menu.isCrafting()) {
			blit(stack, x + 112, y + 36, 16, 183, menu.getScaledProgress(), 36);
		}

		if (menu.hasFuel()) {
			blit(stack, x + 9, y + 36 + 14 - menu.getScaledFuelProgress(), 16, 183 - menu.getScaledFuelProgress(), 14,
					menu.getScaledFuelProgress());
		}

		int i = this.leftPos;
		int j = this.topPos;
		int k = (int) (41.0F * this.scrollOffs);
		this.blit(stack, i + 97, j + 16 + k, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
		int l = this.leftPos + RECIPES_X;
		int i1 = this.topPos + RECIPES_Y;
		int j1 = this.startIndex + 12;
		this.renderButtons(stack, x, y, l, i1, j1);
		this.renderRecipes(l, i1, j1);
	}

	@Override
	protected void renderTooltip(PoseStack stack, int x, int y) {
		super.renderTooltip(stack, x, y);
		if (this.displayRecipes) {
			int i = this.leftPos + RECIPES_X;
			int j = this.topPos + RECIPES_Y;
			int k = this.startIndex + 12;
			List<GlassFurnaceRecipe> list = this.menu.getRecipes();

			for (int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
				int i1 = l - this.startIndex;
				int j1 = i + i1 % 4 * 16;
				int k1 = j + i1 / 4 * 18 + 2;
				if (x >= j1 && x < j1 + 16 && y >= k1 && y < k1 + 18) {
					this.renderTooltip(stack, list.get(l).getResultItem(), x, y);
				}
			}
		}

	}

	private void renderButtons(PoseStack stack, int x, int y, int p_99345_, int p_99346_, int p_99347_) {
		for (int i = this.startIndex; i < p_99347_ && i < this.menu.getNumRecipes(); ++i) {
			int j = i - this.startIndex;
			int k = p_99345_ + j % 4 * 16;
			int l = j / 4;
			int i1 = p_99346_ + l * 18 + 2;
			int j1 = this.imageHeight;
			if (i == this.menu.getSelectedRecipeIndex()) {
				j1 += 18;
			} else if (x >= k && y >= i1 && x < k + 16 && y < i1 + 18) {
				j1 += 36;
			}

			this.blit(stack, k, i1 - 1, 0, j1, 16, 18);
		}

	}

	private void renderRecipes(int left, int top, int recipeIndexOffsetMax) {
		final List<GlassFurnaceRecipe> recipes = this.menu.getRecipes();

		for (int i = this.startIndex; i < recipeIndexOffsetMax && i < this.menu.getNumRecipes(); ++i) {
			int j = i - this.startIndex;
			int k = left + j % 4 * 16;
			int l = j / 4;
			int i1 = top + l * 18 + 2;
			this.minecraft.getItemRenderer().renderAndDecorateItem(recipes.get(i).getResultItem(), k, i1);
		}

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		this.scrolling = false;
		if (this.displayRecipes) {
			int i = this.leftPos + RECIPES_X;
			int j = this.topPos + RECIPES_Y;
			int k = this.startIndex + 12;

			for (int l = this.startIndex; l < k; ++l) {
				int i1 = l - this.startIndex;
				double d0 = mouseX - (double) (i + i1 % 4 * 16);
				double d1 = mouseY - (double) (j + i1 / 4 * 18);
				if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D
						&& this.menu.clickMenuButton(this.minecraft.player, l)) {
					Minecraft.getInstance().getSoundManager()
							.play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
					this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
					return true;
				}
			}

			i = this.leftPos + 119;
			j = this.topPos + 9;
			if (mouseX >= (double) i && mouseX < (double) (i + 12) && mouseY >= (double) j
					&& mouseY < (double) (j + 54)) {
				this.scrolling = true;
			}
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (this.scrolling && this.isScrollBarActive()) {
			int i = this.topPos + RECIPES_Y;
			int j = i + 54;
			this.scrollOffs = ((float) mouseY - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5D) * 4;
			return true;
		} else {
			return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
		if (this.isScrollBarActive()) {
			int i = this.getOffscreenRows();
			this.scrollOffs = (float) ((double) this.scrollOffs - delta / (double) i);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) i) + 0.5D) * 4;
		}

		return true;
	}

	private boolean isScrollBarActive() {
		return this.displayRecipes && this.menu.getNumRecipes() > 12;
	}

	protected int getOffscreenRows() {
		return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
	}

	private void containerChanged() {
		this.displayRecipes = this.menu.hasInputItem();
		if (!this.displayRecipes) {
			this.scrollOffs = 0.0F;
			this.startIndex = 0;
		}

	}
}
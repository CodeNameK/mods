package redstone.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import redstone.models.ModelCannon;

public class RenderCannon implements IItemRenderer {

	private ModelCannon gun;

	private static final ResourceLocation LOC = new ResourceLocation("subaraki:rhg/aran.png");

	public RenderCannon() {
		gun = new ModelCannon();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case  EQUIPPED: return true;
		case  EQUIPPED_FIRST_PERSON: return true;
		case ENTITY: return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case  EQUIPPED:
			caseEq(type, item, data);
			break;
		case  EQUIPPED_FIRST_PERSON:
			caseEq(type, item, data);
			break;
		case ENTITY:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
			GL11.glScalef(2f,2f,2f);
			GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-180, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0f,-0.2f,0F);
			gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
		default:
			break;
		}		

	}

	public void caseEq(ItemRenderType type, ItemStack item, Object... data){
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(LOC);
		GL11.glRotatef(15, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(12, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(195, 1.0f, 0.0f, 0.0f);

		if(data[1] != null && data[1] instanceof EntityPlayer)
		{
			if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
			{
				float f = 1.65f;
				GL11.glScalef(f,f,f);
				GL11.glTranslatef(0.13F, -0.1F, -0F);
			}
			else
			{
				GL11.glScalef(3f, 3f, 3f);
				GL11.glRotatef(80F, 1.0f, 0.0f, 0.0f);
				GL11.glRotatef(0F, 1.0f, 0.0f, 1.0f);
				GL11.glRotatef(-100F, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(95F, 0.0f, 0.0f, 1.0f);
				GL11.glTranslatef(-0.5f ,-0.1f,-0.8F);
			}
		}
		else
		{
			GL11.glScalef(2.2f, 2.2f, 2.2f);
			GL11.glTranslatef(0F, 0.0F, 0F);
		}
		gun.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}

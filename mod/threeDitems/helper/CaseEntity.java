package threeDitems.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import threeDitems.config.Config3D;
import threeDitems.models.bottle;
import threeDitems.models.bow;
import threeDitems.models.enderball;
import threeDitems.models.head;

public class CaseEntity
{
	private static final ResourceLocation bottleLoc = new ResourceLocation("subaraki:3d/bottle.png");
	private static final ResourceLocation spotsLoc = new ResourceLocation("subaraki:3d/eggSpawnSpots.png");

	private static final ResourceLocation skullSkelly = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation skullWither = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	private static final ResourceLocation skullZombie = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation skullCreeper = new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation skullSteve = new ResourceLocation("textures/entity/steve.png");



	public void potionContent(Entity p, ItemStack item, ModelBase theItem)
	{
		Minecraft mc = Minecraft.getMinecraft();
		Minecraft.getMinecraft().renderEngine.bindTexture(bottleLoc);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(item.getItem() != null)
			if(item.getItem().equals(Item.potion) || item.getItem().equals(Item.glassBottle))
			{
				int color = ((ItemPotion)item.getItem()).getColorFromDamage(item.getItemDamage());
				float red = ((color >> 16) & 255) / 255.0F;
				float green = ((color >> 8) & 255) / 255.0F;
				float blue = (color & 255) / 255.0F;
				GL11.glColor4f(red, green,blue, 1.0F);
				((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
			else if(item.getItem().equals(Item.expBottle))
			{
				GL11.glColor4f(0.7f, 1.0f, 0.0f, 1.0F);
				((bottle)theItem).renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
	}

	public void render(ItemRenderType type, ItemStack item, float x, float y, float z, float rotZ, float rotY, float rotX,
			float X, float Y, float Z, float fpsX, float fpsY, float fpsZ, float scale,
			String name, RenderBlocks render, FrameHelper frame, ModelBase theItem, MinecartHelper helper,
			Block blockToRender, Object[] data)
	{
		GL11.glPushMatrix();
		if(item.getItem() instanceof ItemSkull)
			switch(item.getItemDamage()){
			case 0:
				Minecraft.getMinecraft().renderEngine.bindTexture(skullSkelly);
				break;
			case 1:
				Minecraft.getMinecraft().renderEngine.bindTexture(skullWither);
				break;
			case 2:
				Minecraft.getMinecraft().renderEngine.bindTexture(skullZombie);
				break;
			case 3:
				if(item.getTagCompound() != null)
					if (item.getTagCompound().hasKey("SkullOwner")){
						ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;

						if ((item.getTagCompound().getString("SkullOwner") != null) && (item.getTagCompound().getString("SkullOwner").length() > 0))
						{
							resourcelocation = AbstractClientPlayer.getLocationSkin(item.getTagCompound().getString("SkullOwner"));
							AbstractClientPlayer.getDownloadImageSkin(resourcelocation, item.getTagCompound().getString("SkullOwner"));
						}

						Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
					}
				break;
			case 4:
				Minecraft.getMinecraft().renderEngine.bindTexture(skullCreeper);
				break;
			default:
				Minecraft.getMinecraft().renderEngine.bindTexture(skullSteve);
				break;
			}

		GL11.glScalef(3f, 3f,3f);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-180, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(0f,0f,0F);

		GL11.glScalef(scale,scale, scale);

		if(item.getItem().equals(Item.itemFrame)){
			GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(360-90, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef(0f,0f,-0.5F);
			frame.renderFrameItemAsBlock(render, item.getItem());
		}

		if(item.getItem() instanceof ItemArmor){
			ArmorHelper ah= new ArmorHelper();
			int c =((ItemArmor)item.getItem()).armorType;
			ah.setArmorModel((ModelBiped)theItem, item, c, RenderBiped.bipedArmorFilenamePrefix[((ItemArmor)item.getItem()).renderIndex]);
			if(c == 0)
				GL11.glTranslatef(0f, -0.5f ,0F);if(c == 1){
					GL11.glTranslatef(0f, 0f ,-0.8F);
					GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
				}if(c == 2){
					GL11.glTranslatef(0f, 0f,-1.2F);
					GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
				}if(c == 3){
					GL11.glTranslatef(0f, 0f ,-1.4F);
					GL11.glRotatef(90f, 1.0f,0.0f,0.0f);
				}
		}
		if(item.getItem() instanceof ItemMinecart){
			GL11.glTranslatef(0f,-0.2f,0F);
			helper.cartzz(item, theItem, render);
		}
		if(blockToRender != null){
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			GL11.glRotatef(180f, 0.0f,0.0f,1.0f);
			GL11.glTranslatef(0f, 0.4f ,0F);
			render.renderBlockAsItem(blockToRender, 0, 2f);
		}

		if((item.getItem() != null) && item.getItem().equals(Item.bow))
			if((Entity)data[1] instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)data[1];
				int count =player.getItemInUseCount();
				int passed = 72000 - count;
				((bow)theItem).renderBase();
				((bow)theItem).restBow(false);
				((bow)theItem).pullSlow(false);
				((bow)theItem).pullHard(false);
				if(passed == 72000)
					((bow)theItem).restBow(true);
				if((passed >=1) && (passed <=10)){
					((bow)theItem).pullSlow(true);
					if(!(((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity) &&
							(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))){

					}else{
						GL11.glTranslatef(-0.1f,0+0.3f,0);
						GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
						GL11.glRotatef(-20, 0.0f, 1.0f, 0.0f);
						GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);
					}
				}
				if((passed >10) && (passed <72000)){
					((bow)theItem).pullHard(true);
					if(!(((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity) &&
							(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))){

					}else{
						GL11.glTranslatef(-0.1f, 0.3f,0);
						GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
						GL11.glRotatef(-20, 0.0f, 1.0f, 0.0f);
						GL11.glRotatef(-10, 1.0f, 0.0f, 0.0f);
					}
				}
			}else{
				((bow)theItem).renderBase();
				((bow)theItem).restBow(true);
				((bow)theItem).pullSlow(false);
				((bow)theItem).pullHard(false);
				GL11.glScalef(0.8f, 0.8f, 0.8f);
				GL11.glTranslatef(-0.1f, 0f,0);
				GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
				GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
				GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
			}

		if(item.getItem() instanceof ItemSkull)
			switch(item.getItemDamage()){
			case 0:
				((head)theItem).renderHead(0.0625f);
				break;
			case 1:
				((head)theItem).renderHead(0.0625f);
				break;
			case 2:
				((head)theItem).renderZombie(0.0625f);
				break;
			case 3:
				((head)theItem).renderHead(0.0625f);
				break;
			case 4:
				((head)theItem).renderHead(0.0625f);
				break;
			}

		if(item.getItem() instanceof ItemMonsterPlacer)
			if(item.getItem().equals(Item.monsterPlacer))
			{
				int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,1);
				float red = ((color >> 16) & 255) / 255.0F;
				float green = ((color >> 8) & 255) / 255.0F;
				float blue = (color & 255) / 255.0F;
				GL11.glColor4f(red, green,blue, 1.0F);
			}
		if((item.getItem() instanceof ItemSword) && !Config3D.instance.SwordModel)
			GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
		if(item.getItem() instanceof ItemPickaxe)
			GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
		theItem.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		if(item.getItem() instanceof ItemMonsterPlacer)
			renderDots((Entity)data[1], item, theItem, 0);
		if(item.getItem().equals(Item.blazeRod)){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0f,0.5f,0f, 0.5f);
			GL11.glScalef(1.2f,1.2f,1.2f);
			GL11.glTranslatef(-0.005f, 0.005f, 0.01f);
			theItem.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if(item.getItem() instanceof ItemEnderEye ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			((enderball)theItem).renderEye((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0f, 0.5f, 0f, 0.2f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if((item.getItem() instanceof ItemPotion)|| item.getItem().equals(Item.expBottle))
			this.potionContent((Entity)data[1], item, theItem);

		if((item.getItem() instanceof ItemPotion) ||
				item.getItem().equals(Item.glassBottle)||
				item.getItem().equals(Item.expBottle)){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.2f);
			((bottle)theItem).renderBottle((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		if(item.getItem() instanceof ItemEnderPearl ){
			GL11.glScalef(0.5f,0.5f,0.5f);
			GL11.glColor4f(0f, 0f, 0f, 1.0f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		if((item.getItem() instanceof ItemEnderPearl)|| (item.getItem() instanceof ItemEnderEye) ){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0.3f, 0.3f, 0.3f, 0.3f);
			GL11.glTranslatef(-0.02f, 0.05f, -0.001f);
			GL11.glScalef(1.3f,1.3f,1.3f);
			((enderball)theItem).renderBall((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glPopMatrix();
	}

	public void renderDots(Entity p, ItemStack item, ModelBase theItem, int colorParser)
	{
		Minecraft mc = Minecraft.getMinecraft();
		Minecraft.getMinecraft().renderEngine.bindTexture(spotsLoc);
		if(item.getItem() != null)
			if(item.getItem().equals(Item.monsterPlacer))
			{
				int color = ((ItemMonsterPlacer)item.getItem()).getColorFromItemStack(item,colorParser);
				float red = ((color >> 16) & 255) / 255.0F;
				float green = ((color >> 8) & 255) / 255.0F;
				float blue = (color & 255) / 255.0F;
				GL11.glColor4f(red, green,blue, 0.3F);
				theItem.render(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}
	}
}

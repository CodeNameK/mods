package threeDitems.render.item.tools;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderPickaxe extends Render3DInterface {

	public RenderPickaxe(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(50,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);
		
		GL11.glTranslatef(0f, -1.3f, 0.5f);
	}

	@Override
	public void renderEntity() {
		float f = 1.5f;
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(-40,0,1,0);
		GL11.glRotatef(250,0,0,1);
		GL11.glRotatef(0,1,0,0);
		GL11.glTranslatef(-1f, -0.5f, 0f);
	}

	@Override
	public void renderScale() {
		float f = 1.5f;
		GL11.glScalef(f, f, f);
	}

}
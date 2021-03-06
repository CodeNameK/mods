package threeDitems.render.item;

import net.minecraft.client.model.ModelBase;

import org.lwjgl.opengl.GL11;

import threeDitems.render.Render3DInterface;

public class RenderBook extends Render3DInterface {

	public RenderBook(ModelBase model, String texture) {
		super(model, texture);
	}

	@Override
	public void renderEntity() {
		GL11.glRotatef(0,0,1,0);
		GL11.glRotatef(180,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0f, 0f, 0f);
	}

	@Override
	public void renderEquipped() {
		GL11.glRotatef(10,0,1,0);
		GL11.glRotatef(95,0,0,1);
		GL11.glRotatef(0,1,0,0);

		GL11.glTranslatef(0.2f, -0.5f, 0.3f);

		float f = 0.7f;
		GL11.glScalef(f, f, f);
	}

	@Override
	public void renderEquippedFP() {
		GL11.glRotatef(45,0,1,0);
		GL11.glRotatef(90,0,0,1);
		GL11.glRotatef(90,1,0,0);

		GL11.glTranslatef(1f, 0.5f, -0f);
	}

	@Override
	public void renderScale() {
		float f = 3f;
		GL11.glScalef(f, f, f);
	}

}
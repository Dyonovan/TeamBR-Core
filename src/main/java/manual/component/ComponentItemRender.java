package manual.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ComponentItemRender extends ComponentBase {
    protected static ItemRenderer itemRender = new ItemRenderer(Minecraft.getMinecraft());
    protected ItemStack stack;

    public ComponentItemRender(ItemStack itemStack) {
        stack = itemStack;
    }

    @Override
    public void drawComponent(int x, int y, int mouseX, int mouseY) {
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslated(x + xPos - (width / 2), y + yPos + (width / 2), 0);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glRotated(150, 1.0, 0.0, 0.0);
        GL11.glRotated(-135, 0.0, 1.0, 0.0);
        GL11.glScaled(width, width, width);

        itemRender.renderItem(Minecraft.getMinecraft().thePlayer, stack, stack.getItemDamage(), IItemRenderer.ItemRenderType.INVENTORY);

        GL11.glPopMatrix();
        super.drawComponent(x, y, mouseX, mouseY);
    }
}

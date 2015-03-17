package crazypants.enderio.machine;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.GroupObject;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import crazypants.render.TechneUtil;
import crazypants.render.VertexTransform;

public class TechneModelRenderer implements ISimpleBlockRenderingHandler {

  private List<GroupObject> model;
  private int renderId;

  protected VertexTransform vt;

  public TechneModelRenderer(String modelPath, int renderId) {
    this(modelPath, renderId, null);
  }

  public TechneModelRenderer(String modelPath, int renderId, VertexTransform vt) {
    model = TechneUtil.getModel(modelPath);
    this.renderId = renderId;
    this.vt = vt;
  }

  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    TechneUtil.vt = this.vt;
    TechneUtil.renderInventoryBlock(model, block, metadata);
  }

  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    IIcon override = renderer.overrideBlockTexture;

    TechneUtil.vt = this.vt;

    if(override != null) {
      return TechneUtil.renderWorldBlock(model, override, world, x, y, z, block);
    }
    return TechneUtil.renderWorldBlock(model, world, x, y, z, block);
  }

  @Override
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }

  @Override
  public int getRenderId() {
    return renderId;
  }
}
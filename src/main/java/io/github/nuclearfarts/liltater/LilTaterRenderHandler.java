package io.github.nuclearfarts.liltater;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LilTaterRenderHandler implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int meta, int j, RenderBlocks renderBlocks) {
		//System.out.println("render?");
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		renderTopAndBottom(tess, meta, block, 0, 0, 0);
		renderSides(tess, meta, block, 0, 0, 0);
		tess.draw();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iBlockAccess, int i, int j, int k, Block block, int l, RenderBlocks renderBlocks) {
		Tessellator tess = Tessellator.instance;

		tess.setBrightness(block.getMixedBrightnessForBlock(renderBlocks.blockAccess, i, j, k));
		float var7 = 1.0F;
		tess.setColorOpaque_F(var7, var7, var7);
		int meta = renderBlocks.blockAccess.getBlockMetadata(i, j, k);
		renderTopAndBottom(tess, meta, block, i, j, k);
		renderSides(tess, meta, block, i, j, k);
		return true;
	}
	
	private void renderTopAndBottom(Tessellator tess, int meta, Block block, int x, int y, int z) {
		for(int i = 0; i <= 1; i++) {
			int tex = block.getBlockTextureFromSideAndMetadata(i, meta);
			int someUValue = (tex & 15) << 4;
			int someVValue = tex & 240;
			double minU = (double) ((float) someUValue / 256.0F);
			double maxU = (double) (((float) someUValue + 8.0F) / 256.0F);
			double minV = (double) ((float) someVValue / 256.0F);
			double maxV = (double) (((float) someVValue + 8.0F) / 256.0F);
			
			if(i == 0) {
				tess.addVertexWithUV(x + 10/16d, y, z + 6/16d,  minU, minV);
				tess.addVertexWithUV(x + 10/16d, y, z + 10/16d, minU, maxV);
				tess.addVertexWithUV(x + 6/16d,  y, z + 10/16d, maxU, maxV);
				tess.addVertexWithUV(x + 6/16d,  y, z + 6/16d,  maxU, minV);
			} else {
				tess.addVertexWithUV(x + 6/16d,  y + 7/16d, z + 6/16d,  maxU, minV);
				tess.addVertexWithUV(x + 6/16d,  y + 7/16d, z + 10/16d, maxU, maxV);
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 10/16d, minU, maxV);
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 6/16d,  minU, minV);
			}
		}
	}
	
	private void renderSides(Tessellator tess, int meta, Block block, int x, int y, int z) {
		for(int i = 2; i <= 5; i++) {
			int tex = block.getBlockTextureFromSideAndMetadata(i, meta);
			int someUValue = (tex & 15) << 4;
			int someVValue = tex & 240;
			double minU = (double) ((float) someUValue / 256.0F);
			double maxU = (double) (((float) someUValue + 8.0F) / 256.0F);
			double minV = (double) ((float) someVValue / 256.0F);
			double maxV = (double) (((float) someVValue + 14.0F) / 256.0F);
			switch(i) {
			case 4:
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 6/16d, minU, minV);
				tess.addVertexWithUV(x + 10/16d, y,         z + 6/16d, minU, maxV);
				tess.addVertexWithUV(x + 6/16d,  y,         z + 6/16d, maxU, maxV);
				tess.addVertexWithUV(x + 6/16d,  y + 7/16d, z + 6/16d, maxU, minV);
				break;
			case 2:
				tess.addVertexWithUV(x + 10/16d, y        , z + 10/16d, maxU, maxV);
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 10/16d, maxU, minV);
				tess.addVertexWithUV(x + 6/16d,  y + 7/16d, z + 10/16d, minU, minV);
				tess.addVertexWithUV(x + 6/16d,  y        , z + 10/16d, minU, maxV);
				break;
			case 5:
				tess.addVertexWithUV(x + 6/16d, y,         z + 10/16d, maxU, maxV);
				tess.addVertexWithUV(x + 6/16d, y + 7/16d, z + 10/16d, maxU, minV);
				tess.addVertexWithUV(x + 6/16d, y + 7/16d, z + 6/16d , minU, minV);
				tess.addVertexWithUV(x + 6/16d, y,         z + 6/16d , minU, maxV);
				break;
			case 3:
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 10/16d, minU, minV);
				tess.addVertexWithUV(x + 10/16d, y,         z + 10/16d, minU, maxV);
				tess.addVertexWithUV(x + 10/16d, y,         z + 6/16d , maxU, maxV);
				tess.addVertexWithUV(x + 10/16d, y + 7/16d, z + 6/16d , maxU, minV);
				break;
			}
		}
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return 1337;
	}

}

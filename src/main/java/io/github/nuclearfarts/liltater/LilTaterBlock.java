package io.github.nuclearfarts.liltater;

import net.minecraft.src.BlockDirectional;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class LilTaterBlock extends BlockDirectional {

	private final int texOffset;

	public LilTaterBlock(int par1, int texOffset, Material par2Material) {
		super(par1, par2Material);
		this.texOffset = texOffset;
		setBlockBounds(6 / 16f, 0, 6 / 16f, 10 / 16f, 7 / 16f, 10 / 16f);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if (side == 0 || side == 1)
			return side + texOffset;
		return (side - 2 + meta) % 4 + 2 + texOffset;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1337;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityLiving) {
		int var6 = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, var6);
	}
}

package net.minecraft.server;

public class BlockCobbleWall extends Block {

    public static final BlockStateBoolean UP = BlockStateBoolean.of("up");
    public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
    public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
    public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
    public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");
    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumCobbleVariant.class);

    public BlockCobbleWall(Block block) {
        super(block.material);
        this.j(this.blockStateList.getBlockData().set(BlockCobbleWall.UP, Boolean.valueOf(false)).set(BlockCobbleWall.NORTH, Boolean.valueOf(false)).set(BlockCobbleWall.EAST, Boolean.valueOf(false)).set(BlockCobbleWall.SOUTH, Boolean.valueOf(false)).set(BlockCobbleWall.WEST, Boolean.valueOf(false)).set(BlockCobbleWall.VARIANT, EnumCobbleVariant.NORMAL));
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
        this.a(CreativeModeTab.b);
    }

    public boolean d() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        boolean flag = this.e(iblockaccess, blockposition.north());
        boolean flag1 = this.e(iblockaccess, blockposition.south());
        boolean flag2 = this.e(iblockaccess, blockposition.west());
        boolean flag3 = this.e(iblockaccess, blockposition.east());
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag) {
            f2 = 0.0F;
        }

        if (flag1) {
            f3 = 1.0F;
        }

        if (flag2) {
            f = 0.0F;
        }

        if (flag3) {
            f1 = 1.0F;
        }

        if (flag && flag1 && !flag2 && !flag3) {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        } else if (!flag && !flag1 && flag2 && flag3) {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }

        this.a(f, 0.0F, f2, f1, f4, f3);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.updateShape(world, blockposition);
        this.maxY = 1.5D;
        return super.a(world, blockposition, iblockdata);
    }

    public boolean e(IBlockAccess iblockaccess, BlockPosition blockposition) {
        Block block = iblockaccess.getType(blockposition).getBlock();

        return block == Blocks.BARRIER ? false : (block != this && !(block instanceof BlockFenceGate) ? (block.material.k() && block.d() ? block.material != Material.PUMPKIN : false) : true);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumCobbleVariant) iblockdata.get(BlockCobbleWall.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockCobbleWall.VARIANT, EnumCobbleVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumCobbleVariant) iblockdata.get(BlockCobbleWall.VARIANT)).a();
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return iblockdata.set(BlockCobbleWall.UP, Boolean.valueOf(!iblockaccess.isEmpty(blockposition.up()))).set(BlockCobbleWall.NORTH, Boolean.valueOf(this.e(iblockaccess, blockposition.north()))).set(BlockCobbleWall.EAST, Boolean.valueOf(this.e(iblockaccess, blockposition.east()))).set(BlockCobbleWall.SOUTH, Boolean.valueOf(this.e(iblockaccess, blockposition.south()))).set(BlockCobbleWall.WEST, Boolean.valueOf(this.e(iblockaccess, blockposition.west())));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockCobbleWall.UP, BlockCobbleWall.NORTH, BlockCobbleWall.EAST, BlockCobbleWall.WEST, BlockCobbleWall.SOUTH, BlockCobbleWall.VARIANT});
    }
}

package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenPyramidPiece extends WorldGenScatteredPiece {

    private boolean[] e = new boolean[4];
    private static final List f = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 3), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 2, 7, 15), new StructurePieceTreasure(Items.EMERALD, 0, 1, 3, 2), new StructurePieceTreasure(Items.BONE, 0, 4, 6, 20), new StructurePieceTreasure(Items.ROTTEN_FLESH, 0, 3, 7, 16), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.IRON_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 1)});

    public WorldGenPyramidPiece() {}

    public WorldGenPyramidPiece(Random random, int i, int j) {
        super(random, i, 64, j, 21, 15, 21);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("hasPlacedChest0", this.e[0]);
        nbttagcompound.setBoolean("hasPlacedChest1", this.e[1]);
        nbttagcompound.setBoolean("hasPlacedChest2", this.e[2]);
        nbttagcompound.setBoolean("hasPlacedChest3", this.e[3]);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.e[0] = nbttagcompound.getBoolean("hasPlacedChest0");
        this.e[1] = nbttagcompound.getBoolean("hasPlacedChest1");
        this.e[2] = nbttagcompound.getBoolean("hasPlacedChest2");
        this.e[3] = nbttagcompound.getBoolean("hasPlacedChest3");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);

        int i;

        for (i = 1; i <= 9; ++i) {
            this.a(world, structureboundingbox, i, i, i, this.a - 1 - i, i, this.c - 1 - i, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
            this.a(world, structureboundingbox, i + 1, i, i + 1, this.a - 2 - i, i, this.c - 2 - i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        }

        int j;

        for (i = 0; i < this.a; ++i) {
            for (j = 0; j < this.c; ++j) {
                byte b0 = -5;

                this.b(world, Blocks.SANDSTONE.getBlockData(), i, b0, j, structureboundingbox);
            }
        }

        i = this.a(Blocks.SANDSTONE_STAIRS, 3);
        j = this.a(Blocks.SANDSTONE_STAIRS, 2);
        int k = this.a(Blocks.SANDSTONE_STAIRS, 0);
        int l = this.a(Blocks.SANDSTONE_STAIRS, 1);
        int i1 = ~EnumColor.ORANGE.getInvColorIndex() & 15;
        int j1 = ~EnumColor.BLUE.getInvColorIndex() & 15;

        this.a(world, structureboundingbox, 0, 0, 0, 4, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 10, 1, 3, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), 2, 10, 0, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(j), 2, 10, 4, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(k), 0, 10, 2, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(l), 4, 10, 2, structureboundingbox);
        this.a(world, structureboundingbox, this.a - 5, 0, 0, this.a - 1, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 4, 10, 1, this.a - 2, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), this.a - 3, 10, 0, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(j), this.a - 3, 10, 4, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(k), this.a - 5, 10, 2, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(l), this.a - 1, 10, 2, structureboundingbox);
        this.a(world, structureboundingbox, 8, 0, 0, 12, 4, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 9, 1, 0, 11, 3, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 9, 1, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 9, 2, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 9, 3, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 10, 3, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 11, 3, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 11, 2, 1, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 11, 1, 1, structureboundingbox);
        this.a(world, structureboundingbox, 4, 1, 1, 8, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 1, 2, 8, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 12, 1, 1, 16, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 12, 1, 2, 16, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 4, 5, this.a - 6, 4, this.c - 6, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 9, 4, 9, 11, 4, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 1, 8, 8, 3, 8, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 12, 1, 8, 12, 3, 8, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 8, 1, 12, 8, 3, 12, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 12, 1, 12, 12, 3, 12, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 1, 1, 5, 4, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 5, 1, 5, this.a - 2, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 6, 7, 9, 6, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 7, 7, 9, this.a - 7, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 5, 9, 5, 7, 11, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, this.a - 6, 5, 9, this.a - 6, 7, 11, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, Blocks.AIR.getBlockData(), 5, 5, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 5, 6, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 6, 6, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), this.a - 6, 5, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), this.a - 6, 6, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), this.a - 7, 6, 10, structureboundingbox);
        this.a(world, structureboundingbox, 2, 4, 4, 2, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 3, 4, 4, this.a - 3, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), 2, 4, 5, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), 2, 3, 4, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), this.a - 3, 4, 5, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(i), this.a - 3, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, 1, 1, 3, 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 3, 1, 3, this.a - 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, Blocks.SANDSTONE_STAIRS.getBlockData(), 1, 1, 2, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.getBlockData(), this.a - 2, 1, 2, structureboundingbox);
        this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.SAND.a()), 1, 2, 2, structureboundingbox);
        this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.SAND.a()), this.a - 2, 2, 2, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(l), 2, 1, 2, structureboundingbox);
        this.a(world, Blocks.SANDSTONE_STAIRS.fromLegacyData(k), this.a - 3, 1, 2, structureboundingbox);
        this.a(world, structureboundingbox, 4, 3, 5, 4, 3, 18, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 5, 3, 5, this.a - 5, 3, 17, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 5, 4, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, this.a - 6, 1, 5, this.a - 5, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);

        int k1;

        for (k1 = 5; k1 <= 17; k1 += 2) {
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 4, 1, k1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 4, 2, k1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), this.a - 5, 1, k1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), this.a - 5, 2, k1, structureboundingbox);
        }

        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 10, 0, 7, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 10, 0, 8, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 9, 0, 9, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 11, 0, 9, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 8, 0, 10, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 12, 0, 10, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 7, 0, 10, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 13, 0, 10, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 9, 0, 11, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 11, 0, 11, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 10, 0, 12, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 10, 0, 13, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(j1), 10, 0, 10, structureboundingbox);

        for (k1 = 0; k1 <= this.a - 1; k1 += this.a - 1) {
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 2, 1, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 2, 2, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 2, 3, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 3, 1, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 3, 2, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 3, 3, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 4, 1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), k1, 4, 2, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 4, 3, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 5, 1, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 5, 2, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 5, 3, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 6, 1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), k1, 6, 2, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 6, 3, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 7, 1, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 7, 2, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 7, 3, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 8, 1, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 8, 2, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 8, 3, structureboundingbox);
        }

        for (k1 = 2; k1 <= this.a - 3; k1 += this.a - 3 - 2) {
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 - 1, 2, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 2, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 + 1, 2, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 - 1, 3, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 3, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 + 1, 3, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 - 1, 4, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), k1, 4, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 + 1, 4, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 - 1, 5, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 5, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 + 1, 5, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 - 1, 6, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), k1, 6, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 + 1, 6, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 - 1, 7, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1, 7, 0, structureboundingbox);
            this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), k1 + 1, 7, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 - 1, 8, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1, 8, 0, structureboundingbox);
            this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), k1 + 1, 8, 0, structureboundingbox);
        }

        this.a(world, structureboundingbox, 8, 4, 0, 12, 6, 0, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, Blocks.AIR.getBlockData(), 8, 6, 0, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 12, 6, 0, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 9, 5, 0, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 10, 5, 0, structureboundingbox);
        this.a(world, Blocks.STAINED_HARDENED_CLAY.fromLegacyData(i1), 11, 5, 0, structureboundingbox);
        this.a(world, structureboundingbox, 8, -14, 8, 12, -11, 12, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 8, -10, 8, 12, -10, 12, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), false);
        this.a(world, structureboundingbox, 8, -9, 8, 12, -9, 12, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), false);
        this.a(world, structureboundingbox, 8, -8, 8, 12, -1, 12, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 9, -11, 9, 11, -1, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.STONE_PRESSURE_PLATE.getBlockData(), 10, -11, 10, structureboundingbox);
        this.a(world, structureboundingbox, 9, -13, 9, 11, -13, 11, Blocks.TNT.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.AIR.getBlockData(), 8, -11, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 8, -10, 10, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 7, -10, 10, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 7, -11, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 12, -11, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 12, -10, 10, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 13, -10, 10, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 13, -11, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 10, -11, 8, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 10, -10, 8, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 10, -10, 7, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 10, -11, 7, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 10, -11, 12, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 10, -10, 12, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.CHISELED.a()), 10, -10, 13, structureboundingbox);
        this.a(world, Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a()), 10, -11, 13, structureboundingbox);
        Iterator iterator = EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();

            if (!this.e[enumdirection.b()]) {
                int l1 = enumdirection.getAdjacentX() * 2;
                int i2 = enumdirection.getAdjacentZ() * 2;

                this.e[enumdirection.b()] = this.a(world, structureboundingbox, random, 10 + l1, -11, 10 + i2, StructurePieceTreasure.a(WorldGenPyramidPiece.f, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }
        }

        return true;
    }
}

package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.io.File;
import java.util.function.BiFunction;
import javax.annotation.Nullable;

public class DimensionManager implements MinecraftSerializable {

    // CraftBukkit start
    public static final DimensionManager OVERWORLD = a("overworld", new DimensionManager(1, "", "", WorldProviderNormal::new, true, null));
    public static final DimensionManager NETHER = a("the_nether", new DimensionManager(0, "_nether", "DIM-1", WorldProviderHell::new, false, null));
    public static final DimensionManager THE_END = a("the_end", new DimensionManager(2, "_end", "DIM1", WorldProviderTheEnd::new, false, null));
    // CraftBukkit end
    private final int d;
    private final String e;
    public final String f;
    public final BiFunction<World, DimensionManager, ? extends WorldProvider> g;
    private final boolean h;

    public static DimensionManager a(String s, DimensionManager dimensionmanager) {
        return (DimensionManager) IRegistry.a(IRegistry.DIMENSION_TYPE, dimensionmanager.d, s, dimensionmanager);
    }

    // CraftBukkit - add type
    public DimensionManager(int i, String s, String s1, BiFunction<World, DimensionManager, ? extends WorldProvider> bifunction, boolean flag, DimensionManager type) {
        this.d = i;
        this.e = s;
        this.f = s1;
        this.g = bifunction;
        this.h = flag;
        this.type = type; // CraftBukkit
    }

    public static DimensionManager a(Dynamic<?> dynamic) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.get(new MinecraftKey(dynamic.asString("")));
    }

    public static Iterable<DimensionManager> a() {
        return IRegistry.DIMENSION_TYPE;
    }

    public int getDimensionID() {
        return this.d + -1;
    }

    public String c() {
        return this.e;
    }

    public File a(File file) {
        return this.f.isEmpty() ? file : new File(file, this.f);
    }

    public WorldProvider getWorldProvider(World world) {
        return (WorldProvider) this.g.apply(world, this);
    }

    public String toString() {
        return a(this).toString();
    }

    @Nullable
    public static DimensionManager a(int i) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.fromId(i - -1);
    }

    @Nullable
    public static DimensionManager a(MinecraftKey minecraftkey) {
        return (DimensionManager) IRegistry.DIMENSION_TYPE.get(minecraftkey);
    }

    @Nullable
    public static MinecraftKey a(DimensionManager dimensionmanager) {
        return IRegistry.DIMENSION_TYPE.getKey(dimensionmanager);
    }

    public boolean hasSkyLight() {
        return this.h;
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createString(IRegistry.DIMENSION_TYPE.getKey(this).toString());
    }

    // CraftBukkit start
    private final DimensionManager type;

    public DimensionManager getType() {
        return (type == null) ? this : type;
    }
    // CraftBukkit end
}

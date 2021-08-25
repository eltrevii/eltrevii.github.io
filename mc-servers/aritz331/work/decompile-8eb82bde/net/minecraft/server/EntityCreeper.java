package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    private int b;
    private int fuseTicks;
    private int maxFuseTicks = 30;
    private int explosionRadius = 3;
    private int bm = 0;

    public EntityCreeper(World world) {
        super(world);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalSwell(this));
        this.goalSelector.a(2, this.a);
        this.goalSelector.a(3, new PathfinderGoalAvoidTarget(this, new EntitySelectorCreeperOcelot(this), 6.0F, 1.0D, 1.2D));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.d).setValue(0.25D);
    }

    public int aF() {
        return this.getGoalTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
    }

    public void e(float f, float f1) {
        super.e(f, f1);
        this.fuseTicks = (int) ((float) this.fuseTicks + f * 1.5F);
        if (this.fuseTicks > this.maxFuseTicks - 5) {
            this.fuseTicks = this.maxFuseTicks - 5;
        }

    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
        this.datawatcher.a(17, Byte.valueOf((byte) 0));
        this.datawatcher.a(18, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.datawatcher.getByte(17) == 1) {
            nbttagcompound.setBoolean("powered", true);
        }

        nbttagcompound.setShort("Fuse", (short) this.maxFuseTicks);
        nbttagcompound.setByte("ExplosionRadius", (byte) this.explosionRadius);
        nbttagcompound.setBoolean("ignited", this.cl());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.datawatcher.watch(17, Byte.valueOf((byte) (nbttagcompound.getBoolean("powered") ? 1 : 0)));
        if (nbttagcompound.hasKeyOfType("Fuse", 99)) {
            this.maxFuseTicks = nbttagcompound.getShort("Fuse");
        }

        if (nbttagcompound.hasKeyOfType("ExplosionRadius", 99)) {
            this.explosionRadius = nbttagcompound.getByte("ExplosionRadius");
        }

        if (nbttagcompound.getBoolean("ignited")) {
            this.cm();
        }

    }

    public void s_() {
        if (this.isAlive()) {
            this.b = this.fuseTicks;
            if (this.cl()) {
                this.a(1);
            }

            int i = this.ck();

            if (i > 0 && this.fuseTicks == 0) {
                this.makeSound("creeper.primed", 1.0F, 0.5F);
            }

            this.fuseTicks += i;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }

            if (this.fuseTicks >= this.maxFuseTicks) {
                this.fuseTicks = this.maxFuseTicks;
                this.cp();
            }
        }

        super.s_();
    }

    protected String bn() {
        return "mob.creeper.say";
    }

    protected String bo() {
        return "mob.creeper.death";
    }

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            int i = Item.getId(Items.RECORD_13);
            int j = Item.getId(Items.RECORD_WAIT);
            int k = i + this.random.nextInt(j - i + 1);

            this.a(Item.getById(k), 1);
        } else if (damagesource.getEntity() instanceof EntityCreeper && damagesource.getEntity() != this && ((EntityCreeper) damagesource.getEntity()).isPowered() && ((EntityCreeper) damagesource.getEntity()).cn()) {
            ((EntityCreeper) damagesource.getEntity()).co();
            this.a(new ItemStack(Items.SKULL, 1, 4), 0.0F);
        }

    }

    public boolean r(Entity entity) {
        return true;
    }

    public boolean isPowered() {
        return this.datawatcher.getByte(17) == 1;
    }

    protected Item getLoot() {
        return Items.GUNPOWDER;
    }

    public int ck() {
        return this.datawatcher.getByte(16);
    }

    public void a(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
    }

    public void onLightningStrike(EntityLightning entitylightning) {
        super.onLightningStrike(entitylightning);
        this.datawatcher.watch(17, Byte.valueOf((byte) 1));
    }

    protected boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.makeSound(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.ignite", 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            entityhuman.bv();
            if (!this.world.isStatic) {
                this.cm();
                itemstack.damage(1, entityhuman);
                return true;
            }
        }

        return super.a(entityhuman);
    }

    private void cp() {
        if (!this.world.isStatic) {
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            float f = this.isPowered() ? 2.0F : 1.0F;

            this.world.explode(this, this.locX, this.locY, this.locZ, (float) this.explosionRadius * f, flag);
            this.die();
        }

    }

    public boolean cl() {
        return this.datawatcher.getByte(18) != 0;
    }

    public void cm() {
        this.datawatcher.watch(18, Byte.valueOf((byte) 1));
    }

    public boolean cn() {
        return this.bm < 1 && this.world.getGameRules().getBoolean("doMobLoot");
    }

    public void co() {
        ++this.bm;
    }
}

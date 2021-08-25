package net.minecraft.server;

public interface IMerchant {

    void a_(EntityHuman entityhuman);

    EntityHuman u_();

    MerchantRecipeList getOffers(EntityHuman entityhuman);

    void a(MerchantRecipe merchantrecipe);

    void a_(ItemStack itemstack);

    IChatBaseComponent getScoreboardDisplayName();
}

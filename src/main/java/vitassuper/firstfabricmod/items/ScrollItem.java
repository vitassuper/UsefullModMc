package vitassuper.firstfabricmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vitassuper.firstfabricmod.FirstFabricMod;

public class ScrollItem extends Item {
    boolean finishSmoke = false;

    public ScrollItem(Settings settings) {
        super(settings);

        settings.maxCount(3);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);

        if(!this.finishSmoke){
            playerEntity.playSound(FirstFabricMod.SMOKE_SOUND_EVENT, 1.0F, 1.0F);
            playerEntity.setCurrentHand(hand);

            return TypedActionResult.consume(itemStack);
        }

        return TypedActionResult.pass(playerEntity.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof PlayerEntity){
            FirstFabricMod.LOGGER.info("FINISH");
            stack.decrement(1);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 120, 0));

            this.finishSmoke = true;
        }

        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 20 * 1;
    }

}

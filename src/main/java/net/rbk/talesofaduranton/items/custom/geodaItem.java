package net.rbk.talesofaduranton.items.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class geodaItem extends Item {
    public geodaItem(Properties pProperties) {
        super(pProperties);
    }





    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!pLevel.isClientSide()){
         ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
         itemStack.setCount(itemStack.getCount()-1);
         ItemStack dropeo = generarLoot();
         pPlayer.addItem(dropeo);

            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),SoundEvents.DEEPSLATE_BREAK, SoundSource.NEUTRAL, 4.5F, 5F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }



    private ItemStack generarLoot(){
        List<Item> DropsTierBajo = Arrays.asList(Items.RAW_COPPER,Items.COAL,Items.STONE);

        List<Item> DropsTiermedio = Arrays.asList(Items.RAW_IRON,Items.RAW_GOLD,Items.AMETHYST_SHARD);
        List<Item> DropsTierAlto = Arrays.asList(Items.EMERALD,Items.DIAMOND);
        List<Item> DropsTierMaximo = Arrays.asList(Items.NETHERITE_SCRAP,Items.TOTEM_OF_UNDYING);



        int probabilidad = new Random().nextInt(100);

        ItemStack Drop = null;

        if(probabilidad >=0  && probabilidad <= 50){
                Drop = new ItemStack(DropsTierBajo.get(new Random().nextInt(DropsTierBajo.size())));
        } else if (probabilidad > 50 && probabilidad <= 90) {
            Drop = new ItemStack(DropsTiermedio.get(new Random().nextInt(DropsTiermedio.size())));
        } else if (probabilidad > 90 &&  probabilidad<= 98) {
            Drop = new ItemStack(DropsTierAlto.get(new Random().nextInt(DropsTierAlto.size())));
        } else if (probabilidad >= 99) {
            Drop = new ItemStack(DropsTierMaximo.get(new Random().nextInt(DropsTierMaximo.size())));
        }


        return Drop;
    }

}

package com.kaltinril.vampiric.core.item;

import com.kaltinril.vampiric.client.util.helpers.KeyboardHelper;
import com.kaltinril.vampiric.core.util.helpers.Constants.Color;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

// Example "advanced" item.  Only did this so that I can get tooltip in.  May need "right-click" action on it later?

public class WoodenHolyCross extends Item {
    public WoodenHolyCross(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (KeyboardHelper.isHoldingShift()){
            tooltip.add(new StringTextComponent("Put in shield slot to repel vampires!  They must be in front of you, they can still attack from behind!"));
        }else {
            tooltip.add(new StringTextComponent("Hold" + Color.YELLOW.getStringValue() + " [SHIFT] " + Color.GRAY.getStringValue()  + "for more information."));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 200;
    }
}

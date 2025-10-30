package org.mechaRp.mecharp.util.tooltip;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import java.util.List;

public interface TooltipItem {
    void appendClientTooltip(ItemStack stack, TooltipAccept tooltips);

    record TooltipAccept(
            List<Text> tooltips
    ) {
        public void accept(Text text) {
            tooltips.add(text);
        }
    }
}

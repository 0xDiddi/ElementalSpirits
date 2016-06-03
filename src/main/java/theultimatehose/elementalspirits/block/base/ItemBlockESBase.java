package theultimatehose.elementalspirits.block.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockESBase extends ItemBlock {

    public ItemBlockESBase(Block block) {
        super(block);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}

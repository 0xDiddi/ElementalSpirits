package theultimatehose.elementalspirits.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockESBase extends Block {

    private final String name;

    public BlockESBase(Material blockMaterialIn, MapColor blockMapColorIn, String name) {
        super(blockMaterialIn, blockMapColorIn);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ItemBlockESBase getItemBlock() {
        return new ItemBlockESBase(this);
    }

}

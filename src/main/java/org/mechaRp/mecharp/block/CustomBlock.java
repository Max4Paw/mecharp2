package org.mechaRp.mecharp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class CustomBlock extends Block {
    private final String topTexture;
    private final String sideTexture;
    private final String bottomTexture;
    private final boolean hasSeparateTextures;

    // Конструктор для блоков с одинаковыми текстурами
    public CustomBlock(AbstractBlock.Settings settings, String textureName) {
        super(settings);
        this.hasSeparateTextures = false;
        this.topTexture = textureName;
        this.sideTexture = textureName;
        this.bottomTexture = textureName;
    }

    // Конструктор для блоков с разными текстурами
    public CustomBlock(AbstractBlock.Settings settings, String topTexture, String sideTexture, String bottomTexture) {
        super(settings);
        this.hasSeparateTextures = true;
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
    }

    // Упрощенный конструктор (верх/низ одинаковые, боковые - другие)
    public CustomBlock(AbstractBlock.Settings settings, String topBottomTexture, String sideTexture) {
        this(settings, topBottomTexture, sideTexture, topBottomTexture);
    }

    public String getTopTexture() {
        return topTexture;
    }

    public String getSideTexture() {
        return sideTexture;
    }

    public String getBottomTexture() {
        return bottomTexture;
    }

    public boolean hasSeparateTextures() {
        return hasSeparateTextures;
    }
}
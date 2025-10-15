package org.mechaRp.mecharp.config;

import org.mechaRp.mecharp.config.provider.ModConfigProvider;
import org.mechaRp.mecharp.config.provider.SimpleConfig;
import com.mojang.datafixers.util.Pair;

public class PalladiumConfig {

    public static SimpleConfig PALLADIUM_CONFIG;
    private static ModConfigProvider configs;

    public static int toughnessValuePalladiumArmor;
    public static int enchantmentValuePalladiumArmor;
    public static int knockbackResistanceValuePalladiumArmor;
    public static int protectionValuePalladiumBoots;
    public static int protectionValuePalladiumLeggings;
    public static int protectionValuePalladiumChestplate;
    public static int protectionValuePalladiumHelmet;

    public static boolean speedIPalladiumArmor;
    public static boolean jumpIPalladiumArmor;
    public static boolean nightVisionPalladiumArmor;
    public static boolean canWalkOnPowderedSnowPalladium;

    public static int speedPalladiumTier;
    public static int enchantmentValuePalladiumBow;
    public static int damagePalladiumBow;
    public static int attackDamageBonusPalladiumTier;
    public static int enchantmentValuePalladiumTier;
    public static int attackDamagePalladiumPickaxe;
    public static double attackSpeedPalladiumPickaxe;
    public static int attackDamagePalladiumAxe;
    public static double attackSpeedPalladiumAxe;
    public static int attackDamagePalladiumShovel;
    public static double attackSpeedPalladiumShovel;
    public static int attackDamagePalladiumSword;
    public static double attackSpeedPalladiumSword;
    public static int attackDamagePalladiumHoe;
    public static double attackSpeedPalladiumHoe;
    public static int attackDamagePalladiumPaxel;
    public static double attackSpeedPalladiumPaxel;
    public static int attackDamagePalladiumHammer;
    public static double attackSpeedPalladiumHammer;
    public static int attackDamagePalladiumExcavator;
    public static double attackSpeedPalladiumExcavator;
    public static int radiusPalladiumHammer;
    public static int radiusPalladiumExcavator;
    public static int arrowCountPalladiumBow;
    public static int durabilityPalladium;
    public static boolean unbreakablePalladium;


    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        PALLADIUM_CONFIG = SimpleConfig.of("palladium_common.toml")
                .provider(configs)
                .request();

        assignConfigs();

    }

    private static void createConfigs() {
        configs.addComment("--- Palladium Pickaxe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumPickaxe", 7), "Attack damage of Palladium Pickaxe");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumPickaxe", 0.5), "Attack speed of Palladium Pickaxe");

        configs.addComment("--- Palladium Tool Tier Base Properties ---");
        configs.addKeyValuePair(new Pair<>("speedPalladiumTier", 30), "Speed value of Palladium tier");
        configs.addKeyValuePair(new Pair<>("enchantmentValuePalladiumTier", 55), "Enchantment value of Palladium tier");
        configs.addKeyValuePair(new Pair<>("attackDamageBonusPalladiumTier", 1), "Attack damage bonus of Palladium tier");
        configs.addKeyValuePair(new Pair<>("durabilityPalladium", 5162), "Durability of Palladium tools");
        configs.addKeyValuePair(new Pair<>("unbreakablePalladium", false), "Whether Palladium tools are unbreakable");




    }
    private static void assignConfigs(){
        protectionValuePalladiumBoots = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumBoots", 6);
        protectionValuePalladiumLeggings = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumLeggings", 9);
        protectionValuePalladiumChestplate = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumChestplate", 12);
        protectionValuePalladiumHelmet = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumHelmet", 6);

        toughnessValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("toughnessValuePalladiumArmor", 3);
        knockbackResistanceValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("knockbackResistanceValuePalladiumArmor", 1);
        enchantmentValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("enchantmentValuePalladiumArmor", 55);
        durabilityPalladium = PALLADIUM_CONFIG.getOrDefault("durabilityPalladium", 2562);
        unbreakablePalladium = PALLADIUM_CONFIG.getOrDefault("unbreakablePalladium", false);

        speedIPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("speedIPalladiumArmor", true);
        jumpIPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("jumpIPalladiumArmor", true);
        nightVisionPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("nightVisionPalladiumArmor", true);
        canWalkOnPowderedSnowPalladium = PALLADIUM_CONFIG.getOrDefault("canWalkOnPowderedSnowPalladium", true);

        speedPalladiumTier = PALLADIUM_CONFIG.getOrDefault("speedPalladiumTier", 30);
        enchantmentValuePalladiumBow = PALLADIUM_CONFIG.getOrDefault("enchantmentValuePalladiumBow", 5);
        damagePalladiumBow = PALLADIUM_CONFIG.getOrDefault("damagePalladiumBow", 5);
        attackDamageBonusPalladiumTier = PALLADIUM_CONFIG.getOrDefault("attackDamageBonusPalladiumTier", 1);
        enchantmentValuePalladiumTier = PALLADIUM_CONFIG.getOrDefault("enchantmentValuePalladiumTier", 55);

        attackDamagePalladiumPickaxe = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumPickaxe", 7);
        attackSpeedPalladiumPickaxe = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumPickaxe", 0.5);

        attackDamagePalladiumAxe = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumAxe", 12);
        attackSpeedPalladiumAxe = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumAxe", 0.5);

        attackDamagePalladiumShovel = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumShovel", 7);
        attackSpeedPalladiumShovel = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumShovel", 0.5);

        attackDamagePalladiumSword = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumSword", 10);
        attackSpeedPalladiumSword = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumSword", 0.5);

        attackDamagePalladiumHoe = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumHoe", 7);
        attackSpeedPalladiumHoe = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumHoe", 0.5);

        attackDamagePalladiumPaxel = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumPaxel", 11);
        attackSpeedPalladiumPaxel = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumPaxel", 0.5);

        attackDamagePalladiumHammer = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumHammer", 7);
        attackSpeedPalladiumHammer = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumHammer", 0.5);
        radiusPalladiumHammer = PALLADIUM_CONFIG.getOrDefault("radiusPalladiumHammer", 1);

        attackDamagePalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumExcavator", 7);
        attackSpeedPalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumExcavator", 0.5);
        radiusPalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("radiusPalladiumExcavator", 1);

        arrowCountPalladiumBow = PALLADIUM_CONFIG.getOrDefault("arrowCountPalladiumBow", 2);

    }
}

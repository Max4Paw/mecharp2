package org.mechaRp.mecharp.config;

import org.mechaRp.mecharp.config.provider.ModConfigProvider;
import org.mechaRp.mecharp.config.provider.SimpleConfig;
import com.mojang.datafixers.util.Pair;


public class MagmariumConfig {

    public static int toughnessValueMagmariumArmor;
    public static int enchantmentValueMagmariumArmor;
    public static int knockbackResistanceValueMagmariumArmor;
    public static int protectionValueMagmariumBoots;
    public static int protectionValueMagmariumLeggings;
    public static int protectionValueMagmariumChestplate;
    public static int protectionValueMagmariumHelmet;

    public static boolean speedIIIMagmariumArmor;
    public static boolean jumpIIIMagmariumArmor;
    public static boolean nightVisionMagmariumArmor;
    public static boolean canWalkOnPowderedSnowMagmarium;

    public static int speedMagmariumTier;
    public static int enchantmentValueMagmariumBow;
    public static int damageMagmariumBow;
    public static int attackDamageBonusMagmariumTier;
    public static int enchantmentValueMagmariumTier;
    public static int attackDamageMagmariumPickaxe;
    public static double attackSpeedMagmariumPickaxe;
    public static int attackDamageMagmariumAxe;
    public static double attackSpeedMagmariumAxe;
    public static int attackDamageMagmariumShovel;
    public static double attackSpeedMagmariumShovel;
    public static int attackDamageMagmariumSword;
    public static double attackSpeedMagmariumSword;
    public static int attackDamageMagmariumHoe;
    public static double attackSpeedMagmariumHoe;
    public static int attackDamageMagmariumPaxel;
    public static double attackSpeedMagmariumPaxel;
    public static int attackDamageMagmariumHammer;
    public static double attackSpeedMagmariumHammer;
    public static int attackDamageMagmariumExcavator;
    public static double attackSpeedMagmariumExcavator;
    public static int radiusMagmariumHammer;
    public static int radiusMagmariumExcavator;
    public static int arrowCountMagmariumBow;
    public static int durabilityMagmarium;
    public static boolean unbreakableMagmarium;
    public static boolean fireResistanceMagmariumArmor;
    public static boolean neverLoseHungerMagmariumArmor;
    public static boolean canFlyMagmariumArmor;
    public static boolean makesPiglinsNeutralMagmarium;
    public static boolean endermanWillNotBeAngryWithYouMagmarium;
    public static boolean immuneToFallDamageMagmariumArmor;

    public static SimpleConfig MAGMARIUM_CONFIG;
    private static ModConfigProvider configs;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        MAGMARIUM_CONFIG = SimpleConfig.of("Magmarium_common.toml")
                .provider(configs)
                .request();

        assignConfigs();

    }
    private static void createConfigs() {
        configs.addComment("--- Magmarium Armor Protection and Resistance ---");
        configs.addKeyValuePair(new Pair<>("protectionValueMagmariumBoots", 6), "Protection value of Magmarium Boots");
        configs.addKeyValuePair(new Pair<>("protectionValueMagmariumLeggings", 9), "Protection value of Magmarium Leggings");
        configs.addKeyValuePair(new Pair<>("protectionValueMagmariumChestplate", 12), "Protection value of Magmarium Chestplate");
        configs.addKeyValuePair(new Pair<>("protectionValueMagmariumHelmet", 6), "Protection value of Magmarium Helmet");

        configs.addKeyValuePair(new Pair<>("toughnessValueMagmariumArmor", 3), "Toughness value of Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("knockbackResistanceValueMagmariumArmor", 1), "Knockback resistance of Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("enchantmentValueMagmariumArmor", 55), "Enchantment value of Magmarium Armor");

        configs.addComment("--- Magmarium Armor Special Effects and Abilities ---");
        configs.addKeyValuePair(new Pair<>("speedIMagmariumArmor", true), "Speed I effect for Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("jumpIMagmariumArmor", true), "Jump I effect for Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("immuneToFallDamageMagmariumArmor", true), "Immunity to fall damage for Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("fireResistanceMagmariumArmor", true), "Fire resistance for Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("neverLoseHungerMagmariumArmor", true), "Never lose hunger with Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("nightVisionMagmariumArmor", true), "Night Vision effect for Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("canFlyMagmariumArmor", true), "Ability to fly with Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("endermanWillNotBeAngryWithYouMagmarium", true), "Endermen won't be angry with you with Magmarium Armor");
        configs.addKeyValuePair(new Pair<>("makesPiglinsNeutralMagmarium", true), "Makes Piglins neutral with Magmarium Armor");

        configs.addKeyValuePair(new Pair<>("canWalkOnPowderedSnowMagmarium", true), "Can walk on powdered snow with Magmarium Armor");

        configs.addComment("--- Vibranium Bow Settings ---");
        configs.addKeyValuePair(new Pair<>("enchantmentValueVibraniumBow", 5), "Enchantment value of Vibranium Bow");
        configs.addKeyValuePair(new Pair<>("damageVibraniumBow", 5), "Damage value of Vibranium Bow");
        configs.addKeyValuePair(new Pair<>("arrowCountVibraniumBow", 2), "Arrow count for Vibranium Bow");

        configs.addComment("--- Vibranium Tool Tier Base Properties ---");
        configs.addKeyValuePair(new Pair<>("speedVibraniumTier", 30), "Speed value of Vibranium tier");
        configs.addKeyValuePair(new Pair<>("enchantmentValueVibraniumTier", 55), "Enchantment value of Vibranium tier");
        configs.addKeyValuePair(new Pair<>("attackDamageBonusVibraniumTier", 1), "Attack damage bonus of Vibranium tier");
        configs.addKeyValuePair(new Pair<>("durabilityVibranium", 2562), "Durability of Vibranium tools");
        configs.addKeyValuePair(new Pair<>("unbreakableVibranium", false), "Whether Vibranium tools are unbreakable");

        configs.addComment("--- Vibranium Pickaxe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumPickaxe", 7), "Attack damage of Vibranium Pickaxe");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumPickaxe", 0.5), "Attack speed of Vibranium Pickaxe");

        configs.addComment("--- Vibranium Axe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumAxe", 12), "Attack damage of Vibranium Axe");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumAxe", 0.5), "Attack speed of Vibranium Axe");

        configs.addComment("--- Vibranium Shovel Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumShovel", 7), "Attack damage of Vibranium Shovel");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumShovel", 0.5), "Attack speed of Vibranium Shovel");

        configs.addComment("--- Vibranium Sword Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumSword", 10), "Attack damage of Vibranium Sword");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumSword", 0.5), "Attack speed of Vibranium Sword");

        configs.addComment("--- Vibranium Hoe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumHoe", 7), "Attack damage of Vibranium Hoe");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumHoe", 0.5), "Attack speed of Vibranium Hoe");

        configs.addComment("--- Vibranium Paxel Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumPaxel", 11), "Attack damage of Vibranium Paxel");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumPaxel", 0.5), "Attack speed of Vibranium Paxel");

        configs.addComment("--- Vibranium Hammer Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumHammer", 7), "Attack damage of Vibranium Hammer");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumHammer", 0.5), "Attack speed of Vibranium Hammer");
        configs.addKeyValuePair(new Pair<>("radiusVibraniumHammer", 1), "Hammering radius for Vibranium Hammer");

        configs.addComment("--- Vibranium Excavator Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageVibraniumExcavator", 7), "Attack damage of Vibranium Excavator");
        configs.addKeyValuePair(new Pair<>("attackSpeedVibraniumExcavator", 0.5), "Attack speed of Vibranium Excavator");
        configs.addKeyValuePair(new Pair<>("radiusVibraniumExcavator", 1), "Excavation radius for Vibranium Excavator");


    }
    private static void assignConfigs(){
        protectionValueMagmariumBoots = MAGMARIUM_CONFIG.getOrDefault("protectionValueMagmariumBoots", 6);
        protectionValueMagmariumLeggings = MAGMARIUM_CONFIG.getOrDefault("protectionValueMagmariumLeggings", 9);
        protectionValueMagmariumChestplate = MAGMARIUM_CONFIG.getOrDefault("protectionValueMagmariumChestplate", 12);
        protectionValueMagmariumHelmet = MAGMARIUM_CONFIG.getOrDefault("protectionValueMagmariumHelmet", 6);

        toughnessValueMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("toughnessValuePalladiumArmor", 3);
        knockbackResistanceValueMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("knockbackResistanceValuePalladiumArmor", 1);
        enchantmentValueMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("enchantmentValuePalladiumArmor", 55);
        durabilityMagmarium = MAGMARIUM_CONFIG.getOrDefault("durabilityPalladium", 2562);
        unbreakableMagmarium = MAGMARIUM_CONFIG.getOrDefault("unbreakablePalladium", false);

        speedIIIMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("speedIIIMagmariumArmor", true);
        jumpIIIMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("jumpIIIMagmariumArmor", true);
        canFlyMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("canFlyMagmariumArmor", true);
        makesPiglinsNeutralMagmarium = MAGMARIUM_CONFIG.getOrDefault("makesPiglinsNeutralMagmarium", true);
        fireResistanceMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("fireResistanceMagmariumArmor", true);
        immuneToFallDamageMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("immuneToFallDamageMagmariumArmor", true);
        nightVisionMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("nightVisionMagmariumArmor", true);
        canWalkOnPowderedSnowMagmarium = MAGMARIUM_CONFIG.getOrDefault("canWalkOnPowderedSnowMagmarium", true);
        neverLoseHungerMagmariumArmor = MAGMARIUM_CONFIG.getOrDefault("neverLoseHungerMagmariumArmor", true);
        endermanWillNotBeAngryWithYouMagmarium = MAGMARIUM_CONFIG.getOrDefault("endermanWillNotBeAngryWithYouMagmarium", true);

        speedMagmariumTier = MAGMARIUM_CONFIG.getOrDefault("speedPalladiumTier", 30);
        enchantmentValueMagmariumBow = MAGMARIUM_CONFIG.getOrDefault("enchantmentValuePalladiumBow", 5);
        damageMagmariumBow = MAGMARIUM_CONFIG.getOrDefault("damagePalladiumBow", 5);
        attackDamageBonusMagmariumTier = MAGMARIUM_CONFIG.getOrDefault("attackDamageBonusPalladiumTier", 1);
        enchantmentValueMagmariumTier = MAGMARIUM_CONFIG.getOrDefault("enchantmentValuePalladiumTier", 55);

        attackDamageMagmariumPickaxe = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumPickaxe", 7);
        attackSpeedMagmariumPickaxe = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumPickaxe", 0.5);

        attackDamageMagmariumAxe = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumAxe", 12);
        attackSpeedMagmariumAxe = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumAxe", 0.5);

        attackDamageMagmariumShovel = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumShovel", 7);
        attackSpeedMagmariumShovel = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumShovel", 0.5);

        attackDamageMagmariumSword = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumSword", 10);
        attackSpeedMagmariumSword = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumSword", 0.5);

        attackDamageMagmariumHoe = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumHoe", 7);
        attackSpeedMagmariumHoe = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumHoe", 0.5);

        attackDamageMagmariumPaxel = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumPaxel", 11);
        attackSpeedMagmariumPaxel = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumPaxel", 0.5);

        attackDamageMagmariumHammer = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumHammer", 7);
        attackSpeedMagmariumHammer = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumHammer", 0.5);
        radiusMagmariumHammer = MAGMARIUM_CONFIG.getOrDefault("radiusPalladiumHammer", 1);

        attackDamageMagmariumExcavator = MAGMARIUM_CONFIG.getOrDefault("attackDamagePalladiumExcavator", 7);
        attackSpeedMagmariumExcavator = MAGMARIUM_CONFIG.getOrDefault("attackSpeedPalladiumExcavator", 0.5);
        radiusMagmariumExcavator = MAGMARIUM_CONFIG.getOrDefault("radiusPalladiumExcavator", 1);

        arrowCountMagmariumBow = MAGMARIUM_CONFIG.getOrDefault("arrowCountPalladiumBow", 2);

    }


}

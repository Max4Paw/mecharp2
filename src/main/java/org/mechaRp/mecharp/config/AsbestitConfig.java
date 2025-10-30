package org.mechaRp.mecharp.config;

import com.mojang.datafixers.util.Pair;
import org.mechaRp.mecharp.config.provider.ModConfigProvider;
import org.mechaRp.mecharp.config.provider.SimpleConfig;

public class AsbestitConfig {
    public static int toughnessValueAsbesitArmor;
    public static int enchantmentValueAsbesitArmor;
    public static int knockbackResistanceValueAsbesitArmor;
    public static int protectionValueAsbesitBoots;
    public static int protectionValueAsbesitLeggings;
    public static int protectionValueAsbesitChestplate;
    public static int protectionValueAsbesitHelmet;

    public static boolean speedIIIAsbesitArmor;
    public static boolean jumpIIIAsbesitArmor;
    public static boolean nightVisionAsbesitArmor;
    public static boolean canWalkOnPowderedSnowAsbesit;

    public static int speedAsbesitTier;
    public static int enchantmentValueAsbesitBow;
    public static int damageAsbesitBow;
    public static int attackDamageBonusAsbesitTier;
    public static int enchantmentValueAsbesitTier;
    public static int attackDamageAsbesitPickaxe;
    public static double attackSpeedAsbesitPickaxe;
    public static int attackDamageAsbesitAxe;
    public static double attackSpeedAsbesitAxe;
    public static int attackDamageAsbesitShovel;
    public static double attackSpeedAsbesitShovel;
    public static int attackDamageAsbesitSword;
    public static double attackSpeedAsbesitSword;
    public static int attackDamageAsbesitHoe;
    public static double attackSpeedAsbesitHoe;
    public static int attackDamageAsbesitPaxel;
    public static double attackSpeedAsbesitPaxel;
    public static int attackDamageAsbesitHammer;
    public static double attackSpeedAsbesitHammer;
    public static int attackDamageAsbesitExcavator;
    public static double attackSpeedAsbesitExcavator;
    public static int radiusAsbesitHammer;
    public static int radiusAsbesitExcavator;
    public static int arrowCountAsbesitBow;
    public static int durabilityAsbesit;
    public static boolean unbreakableAsbesit;
    public static boolean fireResistanceAsbesitArmor;
    public static boolean neverLoseHungerAsbesitArmor;
    public static boolean canFlyAsbesitArmor;
    public static boolean makesPiglinsNeutralAsbesit;
    public static boolean endermanWillNotBeAngryWithYouAsbesit;
    public static boolean immuneToFallDamageAsbesitArmor;

    public static SimpleConfig ASBESTIT_CONFIG;
    private static ModConfigProvider configs;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        ASBESTIT_CONFIG = SimpleConfig.of("asbestit_common.toml")
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
        configs.addKeyValuePair(new Pair<>("durabilityAsbesit", 4562), "Durability of Asbesit tools");
        configs.addKeyValuePair(new Pair<>("unbreakableVibranium", false), "Whether Vibranium tools are unbreakable");

        configs.addComment("--- Vibranium Pickaxe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamageAsbesitPickaxe", 9), "Attack damage of Asbesit Pickaxe");
        configs.addKeyValuePair(new Pair<>("attackSpeedAsbesitPickaxe", 0.7), "Attack speed of Asbesit Pickaxe");

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
        protectionValueAsbesitBoots = ASBESTIT_CONFIG.getOrDefault("protectionValueAsbesitBoots", 6);
        protectionValueAsbesitLeggings = ASBESTIT_CONFIG.getOrDefault("protectionValueAsbesitLeggings", 9);
        protectionValueAsbesitChestplate = ASBESTIT_CONFIG.getOrDefault("protectionValueAsbesitChestplate", 12);
        protectionValueAsbesitHelmet = ASBESTIT_CONFIG.getOrDefault("protectionValueAsbesitHelmet", 6);

        toughnessValueAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("toughnessValueAsbestitArmor", 3);
        knockbackResistanceValueAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("knockbackResistanceValueAsbesitArmor", 1);
        enchantmentValueAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("enchantmentValueAsbesitArmor", 55);
        durabilityAsbesit = ASBESTIT_CONFIG.getOrDefault("durabilityAsbesit", 4562);
        unbreakableAsbesit = ASBESTIT_CONFIG.getOrDefault("unbreakableAsbesit", false);

        speedIIIAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("speedIIIAsbesitArmor", true);
        jumpIIIAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("jumpIIIAsbesitArmor", true);
        canFlyAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("canFlyAsbesitArmor", true);
        makesPiglinsNeutralAsbesit = ASBESTIT_CONFIG.getOrDefault("makesPiglinsNeutralAsbesit", true);
        fireResistanceAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("fireResistanceAsbesitArmor", true);
        immuneToFallDamageAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("immuneToFallDamageAsbesitArmor", true);
        nightVisionAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("nightVisionAsbesitArmor", true);
        canWalkOnPowderedSnowAsbesit = ASBESTIT_CONFIG.getOrDefault("canWalkOnPowderedSnowAsbesit", true);
        neverLoseHungerAsbesitArmor = ASBESTIT_CONFIG.getOrDefault("neverLoseHungerAsbesitArmor", true);
        endermanWillNotBeAngryWithYouAsbesit = ASBESTIT_CONFIG.getOrDefault("endermanWillNotBeAngryWithYouAsbesit", true);

        speedAsbesitTier = ASBESTIT_CONFIG.getOrDefault("speedAsbesitTier", 30);
        enchantmentValueAsbesitBow = ASBESTIT_CONFIG.getOrDefault("enchantmentValueAsbesitBow", 5);
        damageAsbesitBow = ASBESTIT_CONFIG.getOrDefault("damageAsbesitBow", 5);
        attackDamageBonusAsbesitTier = ASBESTIT_CONFIG.getOrDefault("attackDamageBonusAsbesitTier", 1);
        enchantmentValueAsbesitTier = ASBESTIT_CONFIG.getOrDefault("enchantmentValueAsbesitTier", 55);

        attackDamageAsbesitPickaxe = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitPickaxe", 9);
        attackSpeedAsbesitPickaxe = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitPickaxe", 0.7);

        attackDamageAsbesitAxe = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitAxe", 12);
        attackSpeedAsbesitAxe = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitAxe", 0.5);

        attackDamageAsbesitShovel = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitShovel", 7);
        attackSpeedAsbesitShovel = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitShovel", 0.5);

        attackDamageAsbesitSword = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitSword", 10);
        attackSpeedAsbesitSword = ASBESTIT_CONFIG.getOrDefault("attackSpeedPAsbesitSword", 0.5);

        attackDamageAsbesitHoe = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitHoe", 7);
        attackSpeedAsbesitHoe = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitHoe", 0.5);

        attackDamageAsbesitPaxel = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitPaxel", 11);
        attackSpeedAsbesitPaxel = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitPaxel", 0.5);

        attackDamageAsbesitHammer = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitHammer", 7);
        attackSpeedAsbesitHammer = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitHammer", 0.5);
        radiusAsbesitHammer = ASBESTIT_CONFIG.getOrDefault("radiusAsbesitHammer", 1);

        attackDamageAsbesitExcavator = ASBESTIT_CONFIG.getOrDefault("attackDamageAsbesitExcavator", 7);
        attackSpeedAsbesitExcavator = ASBESTIT_CONFIG.getOrDefault("attackSpeedAsbesitExcavator", 0.5);
        radiusAsbesitExcavator = ASBESTIT_CONFIG.getOrDefault("radiusAsbesitExcavator", 1);

        arrowCountAsbesitBow = ASBESTIT_CONFIG.getOrDefault("arrowCountAsbesitBow", 2);

    }
}

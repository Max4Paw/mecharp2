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
    public static boolean endermanWillNotBeAngryWithYouPalladium;



    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        PALLADIUM_CONFIG = SimpleConfig.of("palladium_common.toml")
                .provider(configs)
                .request();

        assignConfigs();

    }

    private static void createConfigs() {
        configs.addComment("--- Palladium Armor Protection and Resistance ---");
        configs.addKeyValuePair(new Pair<>("protectionValuePalladiumBoots", 5), "Protection value of Palladium Boots");
        configs.addKeyValuePair(new Pair<>("protectionValuePalladiumLeggings", 7), "Protection value of Palladium Leggings");
        configs.addKeyValuePair(new Pair<>("protectionValuePalladiumChestplate", 9), "Protection value of Palladium Chestplate");
        configs.addKeyValuePair(new Pair<>("protectionValuePalladiumHelmet", 5), "Protection value of Palladium Helmet");

        // --- Броня: Дополнительные свойства ---
        configs.addComment("--- Palladium Armor Additional Properties ---");
        configs.addKeyValuePair(new Pair<>("toughnessValuePalladiumArmor", 3), "Toughness value of Palladium Armor");
        configs.addKeyValuePair(new Pair<>("knockbackResistanceValuePalladiumArmor", 1), "Knockback resistance value of Palladium Armor");
        configs.addKeyValuePair(new Pair<>("enchantmentValuePalladiumArmor", 55), "Enchantment value of Palladium Armor");

        // --- Броня: Эффекты ---
        configs.addComment("--- Palladium Armor Effects ---");
        configs.addKeyValuePair(new Pair<>("speedIPalladiumArmor", true), "Whether Palladium Armor gives Speed effect");
        configs.addKeyValuePair(new Pair<>("jumpIPalladiumArmor", true), "Whether Palladium Armor gives Jump Boost effect");
        configs.addKeyValuePair(new Pair<>("nightVisionPalladiumArmor", true), "Whether Palladium Armor gives Night Vision effect");
        configs.addKeyValuePair(new Pair<>("canWalkOnPowderedSnowPalladium", true), "Whether Palladium Armor allows walking on powdered snow");
        configs.addKeyValuePair(new Pair<>("endermanWillNotBeAngryWithYouPalladium", true), "Endermen won't be angry with you with Palladium Armor");

        // --- Уровень инструментов (Tier) ---
        configs.addComment("--- Palladium Tool Tier Base Properties ---");
        configs.addKeyValuePair(new Pair<>("speedPalladiumTier", 30), "Speed value of Palladium tier");
        configs.addKeyValuePair(new Pair<>("enchantmentValuePalladiumTier", 55), "Enchantment value of Palladium tier");
        configs.addKeyValuePair(new Pair<>("attackDamageBonusPalladiumTier", 1), "Attack damage bonus of Palladium tier");
        configs.addKeyValuePair(new Pair<>("durabilityPalladium", 3062), "Durability of Palladium tools");
        configs.addKeyValuePair(new Pair<>("unbreakablePalladium", false), "Whether Palladium tools are unbreakable");

        // --- Лук ---
        configs.addComment("--- Palladium Bow Stats ---");
        configs.addKeyValuePair(new Pair<>("enchantmentValuePalladiumBow", 5), "Enchantment value of Palladium Bow");
        configs.addKeyValuePair(new Pair<>("damagePalladiumBow", 5), "Damage value of Palladium Bow");
        configs.addKeyValuePair(new Pair<>("arrowCountPalladiumBow", 2), "Arrow count of Palladium Bow");

        // --- Кирка ---
        configs.addComment("--- Palladium Pickaxe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumPickaxe", 7), "Attack damage of Palladium Pickaxe");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumPickaxe", 0.5), "Attack speed of Palladium Pickaxe");

        // --- Топор ---
        configs.addComment("--- Palladium Axe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumAxe", 12), "Attack damage of Palladium Axe");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumAxe", 0.5), "Attack speed of Palladium Axe");

        // --- Лопата ---
        configs.addComment("--- Palladium Shovel Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumShovel", 7), "Attack damage of Palladium Shovel");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumShovel", 0.5), "Attack speed of Palladium Shovel");

        // --- Меч ---
        configs.addComment("--- Palladium Sword Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumSword", 10), "Attack damage of Palladium Sword");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumSword", 0.5), "Attack speed of Palladium Sword");

        // --- Мотыга ---
        configs.addComment("--- Palladium Hoe Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumHoe", 7), "Attack damage of Palladium Hoe");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumHoe", 0.5), "Attack speed of Palladium Hoe");

        // --- Мультиинструменты ---
        configs.addComment("--- Palladium Multi-Tools Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumPaxel", 11), "Attack damage of Palladium Paxel");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumPaxel", 0.5), "Attack speed of Palladium Paxel");

        // --- Молот ---
        configs.addComment("--- Palladium Hammer Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumHammer", 7), "Attack damage of Palladium Hammer");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumHammer", 0.5), "Attack speed of Palladium Hammer");
        configs.addKeyValuePair(new Pair<>("radiusPalladiumHammer", 1), "Radius of Palladium Hammer");

        // --- Экскаватор ---
        configs.addComment("--- Palladium Excavator Stats ---");
        configs.addKeyValuePair(new Pair<>("attackDamagePalladiumExcavator", 7), "Attack damage of Palladium Excavator");
        configs.addKeyValuePair(new Pair<>("attackSpeedPalladiumExcavator", 0.5), "Attack speed of Palladium Excavator");
        configs.addKeyValuePair(new Pair<>("radiusPalladiumExcavator", 3), "Radius of Palladium Excavator");



    }
    private static void assignConfigs(){
        protectionValuePalladiumBoots = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumBoots", 5);
        protectionValuePalladiumLeggings = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumLeggings", 7);
        protectionValuePalladiumChestplate = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumChestplate", 9);
        protectionValuePalladiumHelmet = PALLADIUM_CONFIG.getOrDefault("protectionValuePalladiumHelmet", 5);

        toughnessValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("toughnessValuePalladiumArmor", 3);
        knockbackResistanceValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("knockbackResistanceValuePalladiumArmor", 1);
        enchantmentValuePalladiumArmor = PALLADIUM_CONFIG.getOrDefault("enchantmentValuePalladiumArmor", 55);
        durabilityPalladium = PALLADIUM_CONFIG.getOrDefault("durabilityPalladium", 3062);
        unbreakablePalladium = PALLADIUM_CONFIG.getOrDefault("unbreakablePalladium", false);

        speedIPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("speedIPalladiumArmor", true);
        jumpIPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("jumpIPalladiumArmor", true);
        nightVisionPalladiumArmor = PALLADIUM_CONFIG.getOrDefault("nightVisionPalladiumArmor", true);
        canWalkOnPowderedSnowPalladium = PALLADIUM_CONFIG.getOrDefault("canWalkOnPowderedSnowPalladium", true);
        endermanWillNotBeAngryWithYouPalladium = PALLADIUM_CONFIG.getOrDefault("endermanWillNotBeAngryWithYouPalladium", true);

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
        radiusPalladiumHammer = PALLADIUM_CONFIG.getOrDefault("radiusPalladiumHammer", 3);

        attackDamagePalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("attackDamagePalladiumExcavator", 7);
        attackSpeedPalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("attackSpeedPalladiumExcavator", 0.5);
        radiusPalladiumExcavator = PALLADIUM_CONFIG.getOrDefault("radiusPalladiumExcavator", 1);

        arrowCountPalladiumBow = PALLADIUM_CONFIG.getOrDefault("arrowCountPalladiumBow", 2);

    }
}

//package org.mechaRp.mecharp.item;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.component.ComponentType;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//import org.mechaRp.mecharp.Mecharp;
//
//public class MyDataComponents {
//    public static final ComponentType<String> PIN_CODE = ComponentType.<String>builder()
//            .codec(Codec.STRING)
//            .build();
//
//    public static final ComponentType<String> CARD_NUMBER = ComponentType.<String>builder()
//            .codec(Codec.STRING)
//            .build();
//
//    public static final ComponentType<Long> BALANCE = ComponentType.<Long>builder()
//            .codec(Codec.LONG)
//            .build();
//
//    public static void register() {
//       // Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Mecharp.MOD_ID, "pin_code"), PIN_CODE);
//       // Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Mecharp.MOD_ID, "card_number"), CARD_NUMBER);
//       // Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Mecharp.MOD_ID, "balance"), BALANCE);
//    }
//}
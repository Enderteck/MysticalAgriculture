package com.blakebr0.mysticalagriculture.config;

import java.io.File;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EssenceConfig {
	
	public static Configuration config;
	
	public static int cobblestone;
	public static int stone;
	public static int andesite;
	public static int diorite;
	public static int granite;
	public static int crackedStonebrick;
	public static int chiseledStonebrick;
	public static int flint;
	public static int dirt;
	public static int grass;
	public static int coarseDirt;
	public static int podzol;
	public static int gravel;
	public static int mycelium;
	public static int vines;
	public static int cactus;
	public static int sugarcane;
	public static int pumpkin;
	public static int melon;
	public static int wheat;
	public static int potato;
	public static int poisonousPotato;
	public static int carrot;
	public static int beetroot;
	public static int lilypad;
	public static int mushroom;
	public static int mossyCobblestone;
	public static int mossyStonebrick;
	public static int wood;
	public static int sapling;
	public static int snow;
	public static int ice;
	public static int packedIce;
	public static int clay;
	public static int sand;
	public static int dye;
	public static int netherrack;
	public static int soulSand;
	public static int netherBrick;
	public static int endStone;
	
	public static int coal;
	public static int iron;
	public static int quartz;
	public static int glowstone;
	public static int redstone;
	public static int obsidian;
	public static int gold;
	public static int lapis;
	public static int experienceBottle;
	public static int diamond;
	public static int emerald;
	
	public static int rottenFlesh;
	public static int pork;
	public static int chicken;
	public static int feather;
	public static int egg;
	public static int beef;
	public static int leather;
	public static int mutton;
	public static int wool;
	public static int slimeBall;
	public static int arrow;
	public static int bone;
	public static int gunpowder;
	public static int string;
	public static int spiderEye;
	public static int rabbit;
	public static int rabbitFoot;
	public static int rabbitHide;
	public static int fish;
	public static int prismarineShard;
	public static int prismarineCrystal;
	public static int blazeRod;
	public static int ghastTear;
	public static int enderPearl;
	public static int witherSkeletonSkull;
	
	public static int rubber;
	public static int aluminum;
	public static int copper;
	public static int tin;
	public static int bronze;
	public static int silver;
	public static int lead;
	public static int steel;
	public static int nickel;
	public static int electrum;
	public static int invar;
	public static int platinum;
	
	public static int ruby;
	public static int sapphire;
	public static int peridot;
	
	public static int blizz;
	public static int blitz;
	public static int basalz;
	public static int signalum;
	public static int lumium;
	public static int enderium;
	
	public static int aluminumBrass;
	public static int knightslime;
	public static int ardite;
	public static int cobalt;
	public static int manyullyn;
	
	public static int electricalSteel;
	public static int redstoneAlloy;
	public static int conductiveIron;
	public static int soularium;
	public static int dark_steel;
	public static int pulsatingIron;
	public static int energeticAlloy;
	public static int vibrantAlloy;
	
	public static int mysticalFlower;
	public static int manasteel;
	public static int terrasteel;
	
	public static int osmium;
	public static int refinedObsidian;
	
	public static int marble;
	public static int limestone;
	public static int basalt;
	
	public static int draconium;
	
	public static int yellorium;
	
	public static int certusQuartz;
	public static int fluix;
	public static int press;
	
	public static int quartzEnrichedIron;
	
	public static int constantan;
	
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs){
        if(eventArgs.getModID().equals(MysticalAgriculture.MOD_ID)){
            EssenceConfig.syncConfig();
        }
    }
	
	public static void init(File file){
        config = new Configuration(file);
        syncConfig();
	}
	
	public static void syncConfig(){
		
		String category;
		
		category = "Common Resources";
		cobblestone = config.get(category, "cobblestone", 32).getInt();
		stone = config.get(category, "stone", 32).getInt();
		andesite = config.get(category, "andesite", 16).getInt();
		diorite = config.get(category, "diorite", 16).getInt();
		granite = config.get(category, "granite", 16).getInt();
		crackedStonebrick = config.get(category, "cracked_stonebrick", 12).getInt();
		chiseledStonebrick = config.get(category, "chiseled_stonebrick", 12).getInt();
		flint = config.get(category, "flint", 8).getInt();
		dirt = config.get(category, "dirt", 24).getInt();
		grass = config.get(category, "grass", 12).getInt();
		coarseDirt = config.get(category, "coarse_dirt", 16).getInt();
		podzol = config.get(category, "podzol", 8).getInt();
		gravel = config.get(category, "gravel", 16).getInt();
		mycelium = config.get(category, "mycelium", 16).getInt();
		vines = config.get(category, "vines", 16).getInt();
		cactus = config.get(category, "cactus", 16).getInt();
		sugarcane = config.get(category, "sugarcane", 16).getInt();
		pumpkin = config.get(category, "pumpkin", 16).getInt();
		melon = config.get(category, "melon", 8).getInt();
		wheat = config.get(category, "wheat", 12).getInt();
		potato = config.get(category, "potato", 12).getInt();
		poisonousPotato = config.get(category, "poisonous_potato", 1).getInt();
		carrot = config.get(category, "carrot", 12).getInt();
		beetroot = config.get(category, "beetroot", 12).getInt();
		lilypad = config.get(category, "lilypad", 8).getInt();
		mushroom = config.get(category, "mushroom", 8).getInt();
		mossyCobblestone = config.get(category, "mossy_cobblestone", 16).getInt();
		mossyStonebrick = config.get(category, "mossy_stonebrick", 16).getInt();
		wood = config.get(category, "wood", 16).getInt();
		sapling = config.get(category, "sapling", 4).getInt();
		snow = config.get(category, "snow", 12).getInt();
		ice = config.get(category, "ice", 8).getInt();
		packedIce = config.get(category, "packed_ice", 12).getInt();
		clay = config.get(category, "clay", 24).getInt();
		sand = config.get(category, "sand", 16).getInt();
		dye = config.get(category, "dye", 6).getInt();
		netherrack = config.get(category, "netherrack", 32).getInt();
		soulSand = config.get(category, "soul_sand", 20).getInt();
		netherBrick = config.get(category, "nether_brick", 24).getInt();
		endStone = config.get(category, "end_stone", 16).getInt();
		
		category = "Rare Resources";
		coal = config.get(category, "coal", 12).getInt();
		iron = config.get(category, "iron", 6).getInt();
		quartz = config.get(category, "quartz", 12).getInt();
		glowstone = config.get(category, "glowstone", 16).getInt();
		redstone = config.get(category, "redstone", 16).getInt();
		obsidian = config.get(category, "obsidian", 12).getInt();
		gold = config.get(category, "gold", 4).getInt();
		lapis = config.get(category, "lapis", 12).getInt();
		experienceBottle = config.get(category, "experience_bottle", 12).getInt();
		diamond = config.get(category, "diamond", 1).getInt();
		emerald = config.get(category, "emerald", 1).getInt();
	
		category = "Mob Drops";
		rottenFlesh = config.get(category, "rotten_flesh", 12).getInt();
		pork = config.get(category, "pork", 4).getInt();
		chicken = config.get(category, "chicken", 4).getInt();
		feather = config.get(category, "feather", 8).getInt();
		egg = config.get(category, "egg", 8).getInt();
		beef = config.get(category, "beef", 4).getInt();
		leather = config.get(category, "leather", 8).getInt();
		mutton = config.get(category, "mutton", 4).getInt();
		wool = config.get(category, "wool", 6).getInt();
		slimeBall = config.get(category, "slime_ball", 8).getInt();
		arrow = config.get(category, "arrow", 8).getInt();
		bone = config.get(category, "bone", 8).getInt();
		gunpowder = config.get(category, "gunpowder", 6).getInt();
		string = config.get(category, "string", 8).getInt();
		spiderEye = config.get(category, "spider_eye", 3).getInt();
		rabbit = config.get(category, "rabbit", 4).getInt();
		rabbitFoot = config.get(category, "rabbit_foot", 3).getInt();
		rabbitHide = config.get(category, "rabbit_hide", 8).getInt();
		fish = config.get(category, "fish", 4).getInt(fish);
		prismarineShard = config.get(category, "prismarine_shard", 12).getInt();
		prismarineCrystal = config.get(category, "prismarine_crystal", 16).getInt();
		blazeRod = config.get(category, "blaze_rod", 3).getInt();
		ghastTear = config.get(category, "ghast_tear", 2).getInt();
		enderPearl = config.get(category, "ender_pearl", 4).getInt();
		witherSkeletonSkull = config.get(category, "wither_skeleton_skull", 1).getInt();
		
		category = "OreDict Resources";
		rubber = config.get(category, "rubber", 8).getInt();
		aluminum = config.get(category, "aluminum", 8).getInt();
		copper = config.get(category, "copper", 6).getInt();
		tin = config.get(category, "tin", 4).getInt();
		bronze = config.get(category, "bronze", 4).getInt();
		silver = config.get(category, "silver", 4).getInt();
		lead = config.get(category, "lead", 4).getInt();
		steel = config.get(category, "steel", 3).getInt();
		nickel = config.get(category, "nickel", 4).getInt();
		constantan = config.get(category, "constantan", 4).getInt();
		electrum = config.get(category, "electrum", 4).getInt();
		invar = config.get(category, "invar", 4).getInt();
		platinum = config.get(category, "platinum", 2).getInt();
		
		category = "Gems";
		ruby = config.get(category, "ruby", 6).getInt();
		sapphire = config.get(category, "sapphire", 6).getInt();
		peridot = config.get(category, "peridot", 6).getInt();
		
		category = "Thermal Foundation";
		blizz = config.get(category, "blizz", 3).getInt();
		blitz = config.get(category, "blitz", 3).getInt();
		basalz = config.get(category, "basalz", 3).getInt();
		signalum = config.get(category, "signalum", 4).getInt();
		lumium = config.get(category, "lumium", 4).getInt();
		enderium = config.get(category, "enderium", 2).getInt();
		
		category = "Tinkers Construct";
		aluminumBrass = config.get(category, "aluminum_brass", 6).getInt();
		knightslime = config.get(category, "knightslime", 4).getInt();
		ardite = config.get(category, "ardite", 3).getInt();
		cobalt = config.get(category, "cobalt", 3).getInt();
		manyullyn = config.get(category, "manyullyn", 2).getInt();
		
		category = "Ender IO";
		electricalSteel = config.get(category, "electrical_steel", 5).getInt();
		redstoneAlloy = config.get(category, "redstone_alloy", 5).getInt();
		conductiveIron = config.get(category, "conductive_iron", 5).getInt();
		soularium = config.get(category, "soularium", 4).getInt();
		dark_steel = config.get(category, "dark_steel", 4).getInt();
		pulsatingIron = config.get(category, "pulsating_iron", 3).getInt();
		energeticAlloy = config.get(category, "energetic_alloy", 3).getInt();
		vibrantAlloy = config.get(category, "vibrant_alloy", 3).getInt();
		
		category = "Botania";
		mysticalFlower = config.get(category, "mystical_flower", 8).getInt();
		manasteel = config.get(category, "manasteel", 5).getInt();
		terrasteel = config.get(category, "terrasteel", 2).getInt();
		
		category = "Mekanism";
		osmium = config.get(category, "osmium", 4).getInt();
		refinedObsidian = config.get(category, "refined_obsidian", 2).getInt();
		
		category = "Chisel";
		marble = config.get(category, "marble", 24).getInt();
		limestone = config.get(category, "limestone", 24).getInt();
		basalt = config.get(category, "basalt", 16).getInt();
	
		category = "Draconic Evolution";
		draconium = config.get(category, "draconium", 1).getInt();
	
		category = "Extreme Reactors";
		yellorium = config.get(category, "yellorium", 4).getInt();
		
		category = "Applied Energistics 2";
		certusQuartz = config.get(category, "certus_quartz", 6).getInt();
		fluix = config.get(category, "fluix", 6).getInt();
		press = config.get(category, "press", 1).getInt();
		
		category = "Refined Storage";
		quartzEnrichedIron = config.get(category, "quartz_enriched_iron", 8).getInt();
		
		if(config.hasChanged()){
			config.save();	
		}
	}
}
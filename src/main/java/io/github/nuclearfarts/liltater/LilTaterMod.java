package io.github.nuclearfarts.liltater;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;

@Mod(modid = "liltater", name="Lil Tater", version="1.0")
public class LilTaterMod {
	
	private static int taterId = -1;
	private static int irritaterId = -1;
	
	public static final LilTaterRenderHandler TATER_RENDERER = new LilTaterRenderHandler();
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration conf = new Configuration(event.getSuggestedConfigurationFile());
		taterId = conf.getBlock("liltater", 1337).getInt();
		irritaterId = conf.getBlock("irritater", 1338).getInt();
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
		Blocks.init();
		GameRegistry.registerBlock(Blocks.LIL_TATER, "liltater");
		GameRegistry.registerBlock(Blocks.IRRITATER, "irritater");
		MinecraftForgeClient.preloadTexture("/assets/liltater/liltater.png");
		RenderingRegistry.registerBlockHandler(1337, TATER_RENDERER);
		LanguageRegistry.addName(Blocks.LIL_TATER, "Lil' Tater");
		LanguageRegistry.addName(Blocks.IRRITATER, "Irritated Tater");
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.LIL_TATER), Item.potato, Item.potato);
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.IRRITATER), Item.poisonousPotato, Item.poisonousPotato);
	}
	
	public static class Blocks {
		private static void init() {}
		
		public static final LilTaterBlock LIL_TATER = (LilTaterBlock) new LilTaterBlock(taterId, 0, Material.ground).setStepSound(Block.soundGrassFootstep).setBlockName("liltater").setCreativeTab(CreativeTabs.tabBlock).setTextureFile("/assets/liltater/liltater.png");
		public static final LilTaterBlock IRRITATER = (LilTaterBlock) new LilTaterBlock(irritaterId, 6, Material.ground).setStepSound(Block.soundGrassFootstep).setBlockName("irritater").setCreativeTab(CreativeTabs.tabBlock).setTextureFile("/assets/liltater/liltater.png");
	}
}

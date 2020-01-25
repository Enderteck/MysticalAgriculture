package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemModelGenerator;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.function.Function;

public class ModelHandler {
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("minecraft", "textures/atlas/blocks.png");

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        for (int i = 0; i < 8; i++) {
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
    }

    @SubscribeEvent // TODO: IMPLEMENT
    public void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel[] resourceCropModels = new IBakedModel[8];
        IBakedModel[] mobCropModels = new IBakedModel[8];
        for (int i = 0; i < 7; i++) {
            resourceCropModels[i] = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            mobCropModels[i] = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelBakery bakery = event.getModelLoader();
        Function<Material, TextureAtlasSprite> getSprite = bakery.getSpriteMap()::getSprite;
        ItemModelGenerator generator = new ItemModelGenerator();

        IUnbakedModel resourceCropModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_7"));
        RetextureableBlockModelWrapper resourceCropModelWrapper = new RetextureableBlockModelWrapper((BlockModel) resourceCropModel);
        IUnbakedModel mobCropModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_7"));
        RetextureableBlockModelWrapper mobCropModelWrapper = new RetextureableBlockModelWrapper((BlockModel) mobCropModel);
        IUnbakedModel essenceModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        RetextureableItemModelWrapper essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        IUnbakedModel seedsModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        RetextureableItemModelWrapper seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        registry.forEach((location, model) -> {
            if (location.getNamespace().equals(MysticalAgriculture.MOD_ID)) {
                if (location.getPath().endsWith("_crop")) {
                    try {
                        int i = Integer.parseInt(((ModelResourceLocation) location).getVariant().substring(4));
                        String name = location.getPath().replace("_crop", "");
                        ICrop crop = CropRegistry.getInstance().getCropByName(name);
                        if (crop != null) {
                            if (i == 7) {
                                ResourceLocation texture = crop.getTextures().getFlowerTexture();
                                IUnbakedModel cropRetexturedModel;
                                if (crop.getType() == CropType.MOB) {
                                    cropRetexturedModel = mobCropModelWrapper.retexture(ImmutableMap.of("flower", texture.toString()));
                                } else {
                                    cropRetexturedModel = resourceCropModelWrapper.retexture(ImmutableMap.of("flower", texture.toString()));
                                }
                                IBakedModel cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, location);

                                registry.replace(location, cropBakedModel);
                            } else {
                                if (crop.getType() == CropType.MOB) {
                                    registry.replace(location, mobCropModels[i]);
                                } else {
                                    registry.replace(location, resourceCropModels[i]);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        //
                    }
                }

                // TODO: Improve check for main essences
                if (location.getPath().endsWith("_essence") && !location.getPath().contains("ium")) {
                    String name = location.getPath().replace("_essence", "");
                    ICrop crop = CropRegistry.getInstance().getCropByName(name);
                    if (crop != null) {
                        ResourceLocation texture = crop.getTextures().getEssenceTexture();
                        IUnbakedModel essenceRetexturedModel = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                        IUnbakedModel essenceUnbakedModel = generator.makeItemModel(getSprite, (BlockModel) essenceRetexturedModel);
                        IBakedModel essenceBakedModel = essenceUnbakedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, location);

                        registry.replace(location, essenceBakedModel);
                    }
                }

                if (location.getPath().endsWith("_seeds")) {
                    String name = location.getPath().replace("_seeds", "");
                    ICrop crop = CropRegistry.getInstance().getCropByName(name);
                    if (crop != null) {
                        ResourceLocation texture = crop.getTextures().getSeedTexture();
                        IUnbakedModel seedsRetexturedModel = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                        IUnbakedModel seedsUnbakedModel = generator.makeItemModel(getSprite, (BlockModel) seedsRetexturedModel);
                        IBakedModel seedsBakedModel = seedsUnbakedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, location);

                        registry.replace(location, seedsBakedModel);
                    }
                }
            }
        });
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().getId().equals(BLOCK_ATLAS)) {
            CropRegistry.getInstance().getCrops().forEach(crop -> {
                CropTextures textures = crop.getTextures();

                event.addSprite(textures.getFlowerTexture());
                event.addSprite(textures.getEssenceTexture());
                event.addSprite(textures.getSeedTexture());
            });
        }
    }

    public static void onClientSetup() {
        RenderTypeLookup.setRenderLayer(ModBlocks.PROSPERITY_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFERIUM_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOULIUM_ORE.get(), RenderType.getCutoutMipped());

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            RenderTypeLookup.setRenderLayer(crop.getCrop(), RenderType.getCutoutMipped());
        });
    }
}

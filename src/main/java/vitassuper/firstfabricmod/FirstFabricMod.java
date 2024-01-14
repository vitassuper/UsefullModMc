package vitassuper.firstfabricmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vitassuper.firstfabricmod.items.ScrollItem;

public class FirstFabricMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("firstfabricmod");

	public static final ScrollItem SCROLL_ITEM = new ScrollItem(new FabricItemSettings());

	public static final Identifier SMOKE_SOUND_ID = new Identifier("useful:smoke_sound");
	public static SoundEvent SMOKE_SOUND_EVENT = SoundEvent.of(SMOKE_SOUND_ID);

	private static final ItemGroup SMOKEABLE_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(SCROLL_ITEM))
			.displayName(Text.translatable("Stuff for smoke"))
			.entries((context, entries) -> {
				entries.add(SCROLL_ITEM);
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.ITEM, new Identifier("useful", "scroll"), SCROLL_ITEM);
		Registry.register(Registries.ITEM_GROUP, new Identifier("useful", "smokeable_group"), SMOKEABLE_GROUP);
		Registry.register(Registries.SOUND_EVENT, SMOKE_SOUND_ID, SMOKE_SOUND_EVENT);

		LOGGER.info("Hello Fabric world!");
	}
}
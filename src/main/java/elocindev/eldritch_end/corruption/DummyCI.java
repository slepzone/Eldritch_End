package elocindev.eldritch_end.corruption;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elocindev.eldritch_end.api.CorruptionAPI;
import elocindev.eldritch_end.registry.AttributeRegistry;
import elocindev.eldritch_end.worldgen.util.TextUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DummyCI extends Item {
    public DummyCI(Settings settings) {
        super(settings);
    }
    
    @Override
    public Text getName() {
        return CorruptionAPI.getCMenuTitle();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        
        int resistance = (int)CorruptionAPI.getCorruptionResistanceLevel(client.player);
        int corruption = (int)CorruptionAPI.getTotalCorruptionLevel(client.player);

        Style descriptionStyle = Style.EMPTY.withColor(0xc95f1c);

        Text corruptionText = Text.literal("\uA999 ").append(Text.literal(corruption+" Total Corruption").setStyle(TextUtils.Styles.DAMAGE_CORRUPTION));
        Text resistText = Text.literal("\uAB01 ").append(Text.literal(resistance+"% Etyr").setStyle(TextUtils.Styles.CORRUPTION_RESISTANCE));

        tooltip.add(Text.translatable("eldritch_end.corruption.gui.desc.1").setStyle(descriptionStyle));
        tooltip.add(Text.translatable("eldritch_end.corruption.gui.desc.2").setStyle(descriptionStyle));
        tooltip.add(Text.empty());
        tooltip.add(resistText);
        tooltip.add(corruptionText);
   }
}

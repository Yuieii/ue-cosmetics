package me.yuieii.cosmetics.mixin;

import me.yuieii.cosmetics.platform.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ModMixinPlugin implements IMixinConfigPlugin {
    private static final Logger LOGGER = LogManager.getLogger("ue-cosmetics/Mixin");

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean isSkinTextureDownloaderMixin = mixinClassName.startsWith("me.yuieii.cosmetics.mixin.client.SkinTextureDownloader") && mixinClassName.endsWith("Mixin");
        if (isSkinTextureDownloaderMixin) {
            boolean isIntermediate = Services.PLATFORM.isUsingIntermediateMapping();
            boolean isIntermediateMixin = mixinClassName.contains("Intermediate");
            boolean shouldApply = isIntermediate == isIntermediateMixin;

            if (shouldApply) {
                String type = isIntermediate ? "intermediate" : "regular";
                LOGGER.info("Applying {} mapping for SkinTextureDownloader", type);
            }

            return shouldApply;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}

package settingdust.heraclesforblabber.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.blabber.impl.common.PlayerDialogueTracker;
import org.ladysnake.blabber.impl.common.machine.DialogueStateMachine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.heraclesforblabber.EntrypointKt;

@Mixin(PlayerDialogueTracker.class)
public class PlayerDialogueTrackerMixin {
    @Shadow
    @Final
    private PlayerEntity player;

    @Shadow
    private @Nullable DialogueStateMachine currentDialogue;

    @Inject(method = "endDialogue", at = @At("HEAD"))
    private void dialogueStarted(final CallbackInfo ci) {
        EntrypointKt.dialogueStarted(currentDialogue.getId(), (ServerPlayerEntity) player);
    }
}

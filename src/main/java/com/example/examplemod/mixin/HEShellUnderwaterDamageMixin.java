package com.example.examplemod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import rbasamoyai.createbigcannons.munitions.big_cannon.he_shell.HEShellProjectile;

@Mixin(targets = "rbasamoyai.createbigcannons.remix.CustomExplosion$CustomDamageCalculator")
public class HEShellUnderwaterDamageMixin {

    @ModifyExpressionValue(
        method = "getEntityDamageAmount(Lnet/minecraft/world/level/Explosion;Lnet/minecraft/world/entity/Entity;)F",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Explosion;getSeenPercent(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/Entity;)F"
        )
    )
    private float hefix$forceFullSeenPercentForHEShells(float originalSeenPercent, Explosion explosion, Entity entity) {
        Entity source = explosion.getDirectSourceEntity();
        if (source instanceof HEShellProjectile) {
            return 1.0F;
        }
        return originalSeenPercent;
    }
}

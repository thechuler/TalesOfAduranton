package net.rbk.talesofaduranton.Entidades.ia;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.rbk.talesofaduranton.Bloques.InicializarBloques;
import net.rbk.talesofaduranton.Entidades.entity.NitroMoscaEntity;
import net.rbk.talesofaduranton.Particulas.InicializarParticulas;
import net.rbk.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbk.talesofaduranton.Utilidades;

import java.util.EnumSet;

public class GenerarNitroFluidoGoal extends Goal {

    private final NitroMoscaEntity entidad;

    private final int interval;
    private int timer;

    public GenerarNitroFluidoGoal(NitroMoscaEntity entidad, int interval) {
        this.entidad = entidad;
        this.interval = interval;
        this.timer = interval;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void tick() {

        if (--timer <= 0) {

            BlockPos blockPos = entidad.blockPosition().below();
            if (entidad.level().isEmptyBlock(blockPos)) {
                entidad.playSound(InicializarSonidos.NITRO_FLUID_SPLASH.get());
                Utilidades.spawnearParticulas(this.entidad,10, InicializarParticulas.NITRO_FLUID_PARTICLES.get());
                entidad.level().setBlockAndUpdate(blockPos, InicializarBloques.NITRO_FLUID_BLOCK.get().defaultBlockState());
            }
            timer = interval; // Reinicia el temporizador
        }
    }


    @Override
    public boolean canContinueToUse() {
        return true; // Siempre puede continuar usando este objetivo
    }

}

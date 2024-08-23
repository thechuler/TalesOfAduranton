package net.rbk.talesofaduranton;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;

public class Utilidades {


    public static List<LivingEntity> ObtenerEntidadesEnArea(Level level, BlockPos centro, int radio) {
        // Calcular los límites del AABB en función del punto central y el radio
        double minX = centro.getX() - radio;
        double minY = centro.getY() - radio;
        double minZ = centro.getZ() - radio;
        double maxX = centro.getX() + radio;
        double maxY = centro.getY() + radio;
        double maxZ = centro.getZ() + radio;

        // Crear el AABB
        AABB area = new AABB(minX, minY, minZ, maxX, maxY, maxZ);

        // Obtener y devolver la lista de entidades vivientes dentro del AABB
        return level.getEntitiesOfClass(LivingEntity.class, area);
    }







    public static void spawnParticlesEnArea(Level level, ParticleOptions particleType, BlockPos center, double radius, int particleCount) {
        if (level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < particleCount; i++) {
                // Generar un ángulo aleatorio para las coordenadas esféricas
                double theta = Math.random() * Math.PI; // Ángulo polar
                double phi = Math.random() * 2 * Math.PI; // Ángulo azimutal

                // Calcular las coordenadas en el espacio esférico
                double x = center.getX() + radius * Math.sin(theta) * Math.cos(phi);
                double y = center.getY() + radius * Math.cos(theta);
                double z = center.getZ() + radius * Math.sin(theta) * Math.sin(phi);

                // Enviar la partícula a la posición calculada
                serverLevel.sendParticles(particleType, x, y, z, 1, 0, 0, 0, 0);
            }
        }
    }



    public static ItemStack EncontrarItemEnJugador(Player player, Item item){
        for(ItemStack itemStack : player.getInventory().items){
            if(!itemStack.isEmpty() && itemStack.getItem() == item){
                return itemStack;
            }
        }
        return  ItemStack.EMPTY;
    }



    public static void spawnearParticulas(LivingEntity entity, int particleCount , ParticleOptions particleType) {
        if (!entity.level().isClientSide()) {
            Random random = new Random();
            ServerLevel serverLevel = (ServerLevel) entity.level();


            for (int i = 0; i < particleCount; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 2.0;
                double offsetY = random.nextDouble() * 2.0;
                double offsetZ = (random.nextDouble() - 0.5) * 2.0;

                double posX = entity.getX() + offsetX;
                double posY = entity.getY() + offsetY;
                double posZ = entity.getZ() + offsetZ;


                serverLevel.sendParticles(particleType, posX, posY, posZ, 1, 0, 0, 0, 0);

            }

        }
    }








}

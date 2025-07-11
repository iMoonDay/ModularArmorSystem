package com.imoonday.modulararmor.network;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;

public interface NetworkPacket {

    void encode(FriendlyByteBuf buf);

    default void handle(NetworkEvent.Context context) {
        if (context.getDirection().getOriginationSide().isServer()) {
            handleClient();
        } else {
            handleServer(context.getSender());
        }
    }

    @OnlyIn(Dist.CLIENT)
    default void handleClient() {

    }

    default void handleServer(ServerPlayer player) {

    }

    default void sendTo(@Nullable ServerPlayer player) {
        if (player != null) {
            sendToClient(player);
        } else {
            sendToServer();
        }
    }

    default void sendToClient(ServerPlayer player) {
        Network.sendToClient(player, this);
    }

    default void sendToServer() {
        Network.sendToServer(this);
    }
}

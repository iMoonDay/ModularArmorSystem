package com.imoonday.modulararmor.network;

import com.imoonday.modulararmor.ModularArmorSystem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Network {

    private static final String PROTOCOL = "1";
    private static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            ModularArmorSystem.id("network"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );
    private static int index = 0;

    public static void init() {
        registerMessage(OpenVestStorageC2SRequest.class, OpenVestStorageC2SRequest::encode, OpenVestStorageC2SRequest::new);
    }

    public static <MSG extends NetworkPacket> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder) {
        NETWORK.registerMessage(index++, messageType, encoder, decoder, Network::handleMessage);
    }

    public static <MSG extends NetworkPacket> void handleMessage(MSG message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> message.handle(context));
        context.setPacketHandled(true);
    }

    public static <MSG> void sendToServer(MSG message) {
        NETWORK.sendToServer(message);
    }

    public static <MSG> void sendToClient(ServerPlayer player, MSG message) {
        NETWORK.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}

package net.minecraft.network.play.server;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

import java.io.IOException;

public class S35PacketUpdateTileEntity implements Packet<INetHandlerPlayClient> {
    private BlockPos blockPos;

    /**
     * Used only for vanilla tile entities
     */
    private int metadata;
    private NBTTagCompound nbt;

    public S35PacketUpdateTileEntity() {
    }

    public S35PacketUpdateTileEntity(final BlockPos blockPosIn, final int metadataIn, final NBTTagCompound nbtIn) {
        this.blockPos = blockPosIn;
        this.metadata = metadataIn;
        this.nbt = nbtIn;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(final PacketBuffer buf) throws IOException {
        this.blockPos = buf.readBlockPos();
        this.metadata = buf.readUnsignedByte();
        this.nbt = buf.readNBTTagCompoundFromBuffer();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(final PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.blockPos);
        buf.writeByte((byte) this.metadata);
        buf.writeNBTTagCompoundToBuffer(this.nbt);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(final INetHandlerPlayClient handler) {
        handler.handleUpdateTileEntity(this);
    }

    public BlockPos getPos() {
        return this.blockPos;
    }

    public int getTileEntityType() {
        return this.metadata;
    }

    public NBTTagCompound getNbtCompound() {
        return this.nbt;
    }
}
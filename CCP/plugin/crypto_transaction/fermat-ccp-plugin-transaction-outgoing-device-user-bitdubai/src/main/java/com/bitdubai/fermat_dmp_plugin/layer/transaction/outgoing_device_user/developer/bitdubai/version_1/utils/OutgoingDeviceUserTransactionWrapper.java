package com.bitdubai.fermat_dmp_plugin.layer.transaction.outgoing_device_user.developer.bitdubai.version_1.utils;

import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.ReferenceWallet;
import com.bitdubai.fermat_dmp_plugin.layer.transaction.outgoing_device_user.developer.bitdubai.version_1.enums.TransactionState;
import com.bitdubai.fermat_dmp_plugin.layer.transaction.outgoing_device_user.developer.bitdubai.version_1.interfaces.OutgoingDeviceUserTransactionRecord;

import java.util.UUID;

/**
 * Created by Joaquin Carrasquero on 18/03/16.
 */
public class OutgoingDeviceUserTransactionWrapper implements OutgoingDeviceUserTransactionRecord {



    UUID TransactionId;

    String TransactionHash;

    long cryptoAmount;

    TransactionState TransactionState;

    String Memo;

    long Timestamp;

    Actors ActoType;

    ReferenceWallet ReferenceWalletSending;

    ReferenceWallet ReferenceWalletReceiving;

    String WalletSendingPlublicKey;

    String WalletReceivingPublicKey;

    BlockchainNetworkType BlockchainNetworkType;


    public OutgoingDeviceUserTransactionWrapper(UUID transactionId, String transactionHash, long cryptoAmount, com.bitdubai.fermat_dmp_plugin.layer.transaction.outgoing_device_user.developer.bitdubai.version_1.enums.TransactionState transactionState, String memo, long timestamp, Actors actoType, ReferenceWallet referenceWalletSending, ReferenceWallet referenceWalletReceiving, String walletSendingPlublicKey, String walletReceivingPublicKey, com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType blockchainNetworkType) {
        TransactionId = transactionId;
        TransactionHash = transactionHash;
        this.cryptoAmount = cryptoAmount;
        TransactionState = transactionState;
        Memo = memo;
        Timestamp = timestamp;
        ActoType = actoType;
        ReferenceWalletSending = referenceWalletSending;
        ReferenceWalletReceiving = referenceWalletReceiving;
        WalletSendingPlublicKey = walletSendingPlublicKey;
        WalletReceivingPublicKey = walletReceivingPublicKey;
        BlockchainNetworkType = blockchainNetworkType;
    }

    @Override
    public UUID getTransactionId() {
        return TransactionId;
    }

    @Override
    public String getTransactionHash() {
        return TransactionHash;
    }

    @Override
    public long cryptoAmount() {
        return cryptoAmount;
    }

    @Override
    public TransactionState getTransactionState() {
        return TransactionState;
    }

    @Override
    public String getMemo() {
        return Memo;
    }

    @Override
    public long getTimestamp() {
        return Timestamp;
    }

    @Override
    public Actors getActoType() {
        return ActoType;
    }

    @Override
    public ReferenceWallet getReferenceWalletSending() {
        return ReferenceWalletSending;
    }

    @Override
    public ReferenceWallet getReferenceWalletReceiving() {
        return ReferenceWalletReceiving;
    }

    @Override
    public String getWalletSendingPlublicKey() {
        return WalletSendingPlublicKey;
    }

    @Override
    public String getWalletReceivingPublicKey() {
        return WalletReceivingPublicKey;
    }

    @Override
    public BlockchainNetworkType getBlockchainNetworkType() {
        return BlockchainNetworkType;
    }
}

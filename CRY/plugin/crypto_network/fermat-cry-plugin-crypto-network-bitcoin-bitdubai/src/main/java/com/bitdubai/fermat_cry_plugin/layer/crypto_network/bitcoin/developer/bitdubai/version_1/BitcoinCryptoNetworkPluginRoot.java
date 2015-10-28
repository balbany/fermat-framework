package com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1;

import com.bitdubai.fermat_api.CantStartPluginException;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.Service;
import com.bitdubai.fermat_api.layer.all_definition.developer.LogManagerForDevelopers;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.dmp_world.wallet.exceptions.CantStartAgentException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.DealsWithPluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.DealsWithLogger;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogLevel;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogManager;
import com.bitdubai.fermat_cry_api.layer.crypto_network.bitcoin.BitcoinCryptoNetworkManager;
import com.bitdubai.fermat_cry_api.layer.crypto_network.bitcoin.exceptions.CantConnectToBitcoinNetwork;
import com.bitdubai.fermat_cry_api.layer.crypto_vault.CryptoVault;
import com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.exceptions.CantCreateBlockStoreFileException;
import com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.structure.BitcoinCryptoNetworkMonitoringAgent;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.DealsWithErrors;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.UnexpectedPluginExceptionSeverity;

import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by ciencias on 20.01.15.
 */

/**
 * This plugin interfaces the bitcoin network. It primary mission is to hold the bitcoins for each user on this device.
 * <p/>
 * It handles a bitcoin wallet for each user process transactions upon request from other plugins.
 * <p/>
 * It also monitors the bitcoin network for incoming transactions for any of the device's users.
 * <p/>
 * <p/>
 * * * * * * * *
 */

public class BitcoinCryptoNetworkPluginRoot implements BitcoinCryptoNetworkManager, DealsWithErrors, DealsWithPluginFileSystem, DealsWithLogger, LogManagerForDevelopers, Service, Plugin {

    /**
     * BitcoinCryptoNetworkManager interface member variables
     */
    CryptoVault cryptoVault;
    BitcoinCryptoNetworkMonitoringAgent bitcoinCryptoNetworkMonitoringAgent;

    /**
     * DealsWithErrors interface member variables
     */
    ErrorManager errorManager;

    /**
     * DealsWithPluginIdentity interface member variable
     */
    UUID pluginId;

    /**
     * DealsWithLogManager interface member variable
     */
    LogManager logManager;
    static Map<String, LogLevel> newLoggingLevel = new HashMap<String, LogLevel>();


    @Override
    public List<String> getClassesFullPath() {
        List<String> returnedClasses = new ArrayList<String>();
        returnedClasses.add("com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.BitcoinCryptoNetworkPluginRoot");
        returnedClasses.add("com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.structure.BitcoinCryptoNetworkMonitoringAgent");
        returnedClasses.add("com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.structure.BitcoinEventListeners");
        returnedClasses.add("com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.structure.StoredBlockChain");
        returnedClasses.add("com.bitdubai.fermat_cry_plugin.layer.crypto_network.bitcoin.developer.bitdubai.version_1.structure.BitcoinNetworkConfiguration");

        /**
         * I return the values.
         */
        return returnedClasses;
    }

    @Override
    public void setLoggingLevelPerClass(Map<String, LogLevel> newLoggingLevel) {
        /**
         * I will check the current values and update the LogLevel in those which is different
         */
        try {
            for (Map.Entry<String, LogLevel> pluginPair : newLoggingLevel.entrySet()) {
                /**
                 * if this path already exists in the Root.bewLoggingLevel I'll update the value, else, I will put as new
                 */
                if (BitcoinCryptoNetworkPluginRoot.newLoggingLevel.containsKey(pluginPair.getKey())) {
                    BitcoinCryptoNetworkPluginRoot.newLoggingLevel.remove(pluginPair.getKey());
                    BitcoinCryptoNetworkPluginRoot.newLoggingLevel.put(pluginPair.getKey(), pluginPair.getValue());
                } else {
                    BitcoinCryptoNetworkPluginRoot.newLoggingLevel.put(pluginPair.getKey(), pluginPair.getValue());
                }
            }
        } catch (Exception exception) {
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_BITCOIN_CRYPTO_NETWORK, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, exception);
        }
    }

    /**
     * DealswithPluginFileSystem interface member variable
     */
    PluginFileSystem pluginFileSystem;

    /**
     * Service Interface member variables.
     */
    ServiceStatus serviceStatus = ServiceStatus.CREATED;


    /**
     * DealsWithError interface implementation
     *
     * @param errorManager
     */
    @Override
    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    @Override
    public void setLogManager(LogManager logManager) {
        this.logManager = logManager;
    }

    /**
     * DealsWithPluginFileSystem interface implementation
     *
     * @param pluginFileSystem
     */
    @Override
    public void setPluginFileSystem(PluginFileSystem pluginFileSystem) {
        this.pluginFileSystem = pluginFileSystem;
    }

    /**
     * PlatformService Interface implementation.
     */

    @Override
    public void start() throws CantStartPluginException {
        try {
            this.serviceStatus = ServiceStatus.STARTED;
        } catch (Exception exception) {
            throw new CantStartPluginException(CantStartPluginException.DEFAULT_MESSAGE, FermatException.wrapException(exception), null, null);
        }
    }

    @Override
    public void pause() {
        this.serviceStatus = ServiceStatus.PAUSED;
    }

    @Override
    public void resume() {
        this.serviceStatus = ServiceStatus.STARTED;
    }

    @Override
    public void stop() {
        this.serviceStatus = ServiceStatus.STOPPED;
    }

    @Override
    public ServiceStatus getStatus() {
        return serviceStatus;
    }

    /**
     * Plugin interface implementation
     *
     * @param uuid
     */
    @Override
    public void setId(UUID uuid) {
        this.pluginId = uuid;
    }

    @Override
    public void setVault(CryptoVault cryptoVault) {
        this.cryptoVault = cryptoVault;
    }

    @Override
    public void connectToBitcoinNetwork(CryptoVault cryptoVault) throws CantConnectToBitcoinNetwork {
        this.cryptoVault = cryptoVault;
        this.connectToBitcoinNetwork();

    }

    @Override
    public void connectToBitcoinNetwork() throws CantConnectToBitcoinNetwork {
        System.out.println("Bitcoin Crypto Network Connected.");
        bitcoinCryptoNetworkMonitoringAgent = new BitcoinCryptoNetworkMonitoringAgent((Wallet) cryptoVault.getWallet(), cryptoVault.getUserPublicKey());
        bitcoinCryptoNetworkMonitoringAgent.setPluginFileSystem(pluginFileSystem);
        bitcoinCryptoNetworkMonitoringAgent.setLogManager(this.logManager);
        bitcoinCryptoNetworkMonitoringAgent.setErrorManager(errorManager);
        bitcoinCryptoNetworkMonitoringAgent.setPluginId(pluginId);

        try {
            bitcoinCryptoNetworkMonitoringAgent.configureBlockChain();
            bitcoinCryptoNetworkMonitoringAgent.configurePeers();
            bitcoinCryptoNetworkMonitoringAgent.start();
        } catch (CantStartAgentException exception) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_BITCOIN_CRYPTO_NETWORK, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, exception);
            throw new CantConnectToBitcoinNetwork("Couldn't connect to Bitcoin Network.", exception, "UserId : " + cryptoVault.getUserPublicKey(), "Error starting Agent.");
        } catch (CantCreateBlockStoreFileException exception) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_BITCOIN_CRYPTO_NETWORK, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, exception);
            throw new CantConnectToBitcoinNetwork("Couldn't connect to Bitcoin Network.", exception, "UserId : " + cryptoVault.getUserPublicKey(), "Blockchain not saved " +
                    "on disk.");
        } catch(Exception exception){
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_BITCOIN_CRYPTO_NETWORK, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, exception);
            throw new CantConnectToBitcoinNetwork("Couldn't connect to Bitcoin Network.", FermatException.wrapException(exception), null, null);
        }
    }

    @Override
    public void disconnectFromBitcoinNetwork() {
        bitcoinCryptoNetworkMonitoringAgent.stop();
    }

    @Override
    public Object getBroadcasters() {
        return bitcoinCryptoNetworkMonitoringAgent.getPeers();
    }

    public static LogLevel getLogLevelByClass(String className) {
        try {
            /**
             * sometimes the classname may be passed dinamically with an $moretext
             * I need to ignore whats after this.
             */
            String[] correctedClass = className.split((Pattern.quote("$")));
            return BitcoinCryptoNetworkPluginRoot.newLoggingLevel.get(correctedClass[0]);
        } catch (Exception e) {
            System.err.println("CantGetLogLevelByClass: " + e.getMessage());
            /**
             * If I couldn't get the correct loggin level, then I will set it to minimal.
             */
            return DEFAULT_LOG_LEVEL;
        }
    }

    /**
     * gets the amount if peers connected.
     *
     * @return
     */
    public int getConnectedPeers() {
        return bitcoinCryptoNetworkMonitoringAgent.getConnectedPeers();
    }

    @Override
    public void broadcastTransaction(Transaction transaction) {
        bitcoinCryptoNetworkMonitoringAgent.broadcastTransaction(transaction);
    }
}
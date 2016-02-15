package com.bitdubai.fermat_dap_plugin.layer.sub_app_module.asset.user.developer.bitdubai.version_1;

import com.bitdubai.fermat_api.layer.all_definition.common.system.abstract_classes.AbstractPlugin;
import com.bitdubai.fermat_api.layer.all_definition.common.system.annotations.NeededAddonReference;
import com.bitdubai.fermat_api.layer.all_definition.common.system.annotations.NeededIndirectPluginReferences;
import com.bitdubai.fermat_api.layer.all_definition.common.system.annotations.NeededPluginReference;
import com.bitdubai.fermat_api.layer.all_definition.common.system.utils.PluginVersionReference;
import com.bitdubai.fermat_api.layer.all_definition.enums.Addons;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.Layers;
import com.bitdubai.fermat_api.layer.all_definition.enums.Platforms;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_api.layer.all_definition.settings.structure.SettingsManager;
import com.bitdubai.fermat_api.layer.all_definition.util.Version;
import com.bitdubai.fermat_api.layer.modules.common_classes.ActiveActorIdentityInformation;
import com.bitdubai.fermat_api.layer.modules.exceptions.ActorIdentityNotSelectedException;
import com.bitdubai.fermat_api.layer.modules.exceptions.CantGetSelectedActorIdentityException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_dap_api.layer.all_definition.exceptions.CantGetIdentityAssetUserException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.DAPActor;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.exceptions.CantGetAssetIssuerActorsException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.ActorAssetIssuer;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.ActorAssetIssuerManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.AssetUserActorRecord;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.ActorAssetUserGroupAlreadyExistException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantConnectToActorAssetUserException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantCreateAssetUserActorException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantCreateAssetUserGroupException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantDeleteAssetUserGroupException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantGetAssetUserActorsException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantGetAssetUserGroupException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantUpdateAssetUserGroupException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUser;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUserGroup;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUserGroupMember;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUserManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_user.exceptions.CantRequestListActorAssetUserRegisteredException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_user.interfaces.AssetUserActorNetworkServiceManager;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_user.exceptions.CantGetAssetUserIdentitiesException;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_user.interfaces.IdentityAssetUser;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_user.interfaces.IdentityAssetUserManager;
import com.bitdubai.fermat_dap_api.layer.dap_module.wallet_asset_issuer.interfaces.AssetIssuerWalletSupAppModuleManager;
import com.bitdubai.fermat_dap_api.layer.dap_module.wallet_asset_user.AssetUserSettings;
import com.bitdubai.fermat_dap_api.layer.dap_sub_app_module.asset_user_community.interfaces.AssetUserCommunitySubAppModuleManager;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.common.exceptions.RecordsNotFoundException;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.enums.UnexpectedPluginExceptionSeverity;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.interfaces.ErrorManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TODO explain here the main functionality of the plug-in.
 * <p/>
 * Created by Nerio on 13/10/15.
 * Modified by Luis Campo 27/11/2015
 */
@NeededIndirectPluginReferences(indirectReferences = {
        @NeededPluginReference(platform = Platforms.CRYPTO_CURRENCY_PLATFORM, layer = Layers.TRANSACTION, plugin = Plugins.INCOMING_EXTRA_USER),
        @NeededPluginReference(platform = Platforms.CRYPTO_CURRENCY_PLATFORM, layer = Layers.TRANSACTION, plugin = Plugins.INCOMING_INTRA_USER)
//        @NeededPluginReference(platform = Platforms.BLOCKCHAINS, layer = Layers.MIDDLEWARE, plugin = Plugins.CRYPTO_ADDRESSES),
})
public class AssetUserCommunitySubAppModulePluginRoot extends AbstractPlugin implements
        AssetUserCommunitySubAppModuleManager {

    @NeededPluginReference(platform = Platforms.DIGITAL_ASSET_PLATFORM, layer = Layers.ACTOR, plugin = Plugins.ASSET_USER)
    ActorAssetUserManager actorAssetUserManager;

    @NeededPluginReference(platform = Platforms.DIGITAL_ASSET_PLATFORM, layer = Layers.WALLET_MODULE, plugin = Plugins.ASSET_ISSUER)
    AssetIssuerWalletSupAppModuleManager assetIssuerWalletSupAppModuleManager;

    @NeededPluginReference(platform = Platforms.DIGITAL_ASSET_PLATFORM, layer = Layers.ACTOR, plugin = Plugins.ASSET_ISSUER)
    ActorAssetIssuerManager actorAssetIssuerManager;

    @NeededPluginReference(platform = Platforms.DIGITAL_ASSET_PLATFORM, layer = Layers.ACTOR_NETWORK_SERVICE, plugin = Plugins.ASSET_USER)
    AssetUserActorNetworkServiceManager assetUserActorNetworkServiceManager;

    @NeededAddonReference(platform = Platforms.OPERATIVE_SYSTEM_API, layer = Layers.SYSTEM, addon = Addons.PLUGIN_FILE_SYSTEM)
    private PluginFileSystem pluginFileSystem;

    @NeededAddonReference(platform = Platforms.PLUG_INS_PLATFORM, layer = Layers.PLATFORM_SERVICE, addon = Addons.ERROR_MANAGER)
    ErrorManager errorManager;

    @NeededPluginReference(platform = Platforms.DIGITAL_ASSET_PLATFORM, layer = Layers.IDENTITY, plugin = Plugins.ASSET_USER)
    IdentityAssetUserManager identityAssetUserManager;

    private SettingsManager<AssetUserSettings> settingsManager;

    BlockchainNetworkType blockchainNetworkType;

    public AssetUserCommunitySubAppModulePluginRoot() {
        super(new PluginVersionReference(new Version()));
    }

    @Override
    public List<AssetUserActorRecord> getAllActorAssetUserRegistered() throws CantGetAssetUserActorsException {
        List<ActorAssetUser> list = null;
        List<AssetUserActorRecord> assetUserActorRecords = null;

        try {
            list = assetUserActorNetworkServiceManager.getListActorAssetUserRegistered();
            actorAssetUserManager.createActorAssetUserRegisterInNetworkService(list);
        } catch (CantRequestListActorAssetUserRegisteredException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
        } catch (CantCreateAssetUserActorException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
        }

        if (list != null) {
            assetUserActorRecords = new ArrayList<>();

            try {
                BlockchainNetworkType blockchainNetworkType = assetIssuerWalletSupAppModuleManager.getSelectedNetwork();
                for (ActorAssetUser actorAssetUser : actorAssetUserManager.getAllAssetUserActorInTableRegistered(blockchainNetworkType)) {

                    AssetUserActorRecord assetUserActorRecord = (AssetUserActorRecord) actorAssetUser;
                    assetUserActorRecords.add(assetUserActorRecord);

                }

            } catch (CantGetAssetUserActorsException e) {
                errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
                e.printStackTrace();
            }
        }
        return assetUserActorRecords;
    }

    @Override
    public List<AssetUserActorRecord> getAllActorAssetUserRegisteredWithCryptoAddressNotIntheGroup(String groupId) throws CantGetAssetUserActorsException {
        List<AssetUserActorRecord> allUserRegistered = this.getAllActorAssetUserRegistered();
        List<ActorAssetUser> allUserRegisteredInGroup = this.getListActorAssetUserByGroups(groupId);
        List<AssetUserActorRecord> allUserRegisteredFiltered = new ArrayList<>();
        for (AssetUserActorRecord record : allUserRegistered)
        {
            if (record.getCryptoAddress() != null && (!userInGroup(record.getActorPublicKey(), allUserRegisteredInGroup)))
            {
                allUserRegisteredFiltered.add(record);
            }
        }

        return allUserRegisteredFiltered;
    }

    private boolean userInGroup(String actorPublicKey, List<ActorAssetUser> usersInGroup) {
        for (ActorAssetUser record : usersInGroup) {
            if (record.getActorPublicKey().equals(actorPublicKey))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void connectToActorAssetUser(DAPActor requester, List<ActorAssetUser> actorAssetUsers) throws CantConnectToActorAssetUserException {
        blockchainNetworkType = assetIssuerWalletSupAppModuleManager.getSelectedNetwork();
        try {
            ActorAssetIssuer actorAssetIssuer = actorAssetIssuerManager.getActorAssetIssuer();
            if (actorAssetIssuer != null) {
                actorAssetUserManager.connectToActorAssetUser(actorAssetIssuer, actorAssetUsers, blockchainNetworkType);
            } else {
                ActorAssetUser actorAssetUser = actorAssetUserManager.getActorAssetUser();
                if (actorAssetUser != null) {
                    actorAssetUserManager.connectToActorAssetUser(actorAssetUser, actorAssetUsers, blockchainNetworkType);
                } else {
                    throw new CantConnectToActorAssetUserException(CantConnectToActorAssetUserException.DEFAULT_MESSAGE, null, "There was an error connecting to users.", null);
                }
            }
        } catch (CantGetAssetIssuerActorsException | CantGetAssetUserActorsException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw new CantConnectToActorAssetUserException(CantConnectToActorAssetUserException.DEFAULT_MESSAGE, e, "There was an error connecting to users.", null);
        }
    }

    @Override
    public ActorAssetUserGroup createAssetUserGroup(String groupName) throws CantCreateAssetUserGroupException, ActorAssetUserGroupAlreadyExistException {
        try {
            return actorAssetUserManager.createAssetUserGroup(groupName);
        } catch (CantCreateAssetUserGroupException | ActorAssetUserGroupAlreadyExistException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public void renameGroup(ActorAssetUserGroup assetUserGroup) throws CantUpdateAssetUserGroupException, RecordsNotFoundException {
        try {
            actorAssetUserManager.updateAssetUserGroup(assetUserGroup);
        } catch (CantUpdateAssetUserGroupException | RecordsNotFoundException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public void deleteGroup(String assetUserGroupId) throws CantDeleteAssetUserGroupException, RecordsNotFoundException {
        try {
            actorAssetUserManager.deleteAssetUserGroup(assetUserGroupId);
        } catch (CantDeleteAssetUserGroupException | RecordsNotFoundException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public void addActorAssetUserToGroup(ActorAssetUserGroupMember actorAssetUserGroupMember) throws CantCreateAssetUserGroupException {
        try {
            actorAssetUserManager.addAssetUserToGroup(actorAssetUserGroupMember);
        } catch (CantCreateAssetUserGroupException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public void removeActorAssetUserFromGroup(ActorAssetUserGroupMember assetUserGroupMember) throws CantDeleteAssetUserGroupException, RecordsNotFoundException {
        try {
            actorAssetUserManager.removeAssetUserFromGroup(assetUserGroupMember);
        } catch (CantDeleteAssetUserGroupException | RecordsNotFoundException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public List<ActorAssetUserGroup> getGroups() throws CantGetAssetUserGroupException {
        try {
            return actorAssetUserManager.getAssetUserGroupsList();
        } catch (CantGetAssetUserGroupException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public List<ActorAssetUser> getListActorAssetUserByGroups(String groupId) throws CantGetAssetUserActorsException {
        try {
            return actorAssetUserManager.getListActorAssetUserByGroups(groupId);
        } catch (CantGetAssetUserActorsException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public List<ActorAssetUserGroup> getListGroupsByActorAssetUser(String actorAssetUserPublicKey) throws CantGetAssetUserGroupException {
        try {
            return actorAssetUserManager.getListAssetUserGroupsByActorAssetUser(actorAssetUserPublicKey);
        } catch (CantGetAssetUserGroupException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    @Override
    public ActorAssetUserGroup getGroup(String groupId) throws CantGetAssetUserGroupException {
        try {
            return actorAssetUserManager.getAssetUserGroup(groupId);
        } catch (CantGetAssetUserGroupException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw e;
        }
    }

    public IdentityAssetUser getActiveAssetUserIdentity() throws CantGetIdentityAssetUserException {
        try {
            return identityAssetUserManager.getIdentityAssetUser();
        } catch (CantGetAssetUserIdentitiesException e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
            throw new CantGetIdentityAssetUserException(e);
        }
    }

    @Override
    public SettingsManager getSettingsManager() {
        if (this.settingsManager != null)
            return this.settingsManager;

        this.settingsManager = new SettingsManager<>(
                pluginFileSystem,
                pluginId
        );

        return this.settingsManager;
    }

    @Override
    public ActiveActorIdentityInformation getSelectedActorIdentity() throws CantGetSelectedActorIdentityException, ActorIdentityNotSelectedException {
        return identityAssetUserManager.getSelectedActorIdentity();
    }

    @Override
    public void createIdentity(String name, String phrase, byte[] profile_img) throws Exception {
        identityAssetUserManager.createNewIdentityAssetUser(name, profile_img);
    }

    @Override
    public void setAppPublicKey(String publicKey) {

    }

    @Override
    public int[] getMenuNotifications() {
        return new int[0];
    }
}

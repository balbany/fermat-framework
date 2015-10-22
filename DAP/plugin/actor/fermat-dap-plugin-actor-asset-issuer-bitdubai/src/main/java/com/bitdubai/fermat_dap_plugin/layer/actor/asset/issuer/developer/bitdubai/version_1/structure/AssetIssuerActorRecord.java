package com.bitdubai.fermat_dap_plugin.layer.actor.asset.issuer.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.enums.ConnectionState;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.all_definition.util.validations.Validate;
import com.bitdubai.fermat_api.layer.osa_android.location_system.Location;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.ActorAssetIssuer;

/**
 * Created by Nerio on 22/09/15.
 */
public class AssetIssuerActorRecord implements ActorAssetIssuer {

    private String name;
    private String description;
    private String publicKey;
    private byte[] profileImage ;
    private long registrationDate;
    private ConnectionState connectionState;
    private Location location;
    private CryptoAddress cryptoAddress;

    /**
     * Constructor
     */

    public AssetIssuerActorRecord(String name, String publicKey) {
        this.name = name;
        this.publicKey = publicKey;
    }

    public AssetIssuerActorRecord(String name, String publicKey, byte[] profileImage, long registrationDate) {

        this.name = name;
        this.publicKey = publicKey;
        this.profileImage = profileImage.clone();
        this.registrationDate = registrationDate;
        this.connectionState = ConnectionState.CONNECTED;

    }

    /**
     * The metho <code>getPublicKey</code> gives us the public key of the represented Asset Issuer
     *
     * @return the public key
     */
    @Override
    public String getPublicKey() {
        return this.publicKey;
    }

    /**
     * The method <code>getName</code> gives us the name of the represented Asset Issuer
     *
     * @return the name of the intra user
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * The method <code>getContactRegistrationDate</code> gives us the date when both  Asset Issuers
     * exchanged their information and accepted each other as contacts.
     *
     * @return the date
     */
    @Override
    public long getContactRegistrationDate() {
        return this.registrationDate;
    }

    /**
     * The method <coda>getProfileImage</coda> gives us the profile image of the represented Asset Issuer
     *
     * @return the image
     */
    @Override
    public byte[] getProfileImage() {
        return this.profileImage.clone();
    }

    /**
     * The method <code>getContactState</code> gives us the contact state of the represented Asset Issuer
     *
     * @return the contact state
     */
    @Override
    public ConnectionState getContactState() {
        return this.connectionState;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getDescription() {
        return Validate.verifyString(description);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public CryptoAddress getCryptoAddress() {
        return cryptoAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public void setRegistrationDate(long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setContactState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCryptoAddress(CryptoAddress cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }
}

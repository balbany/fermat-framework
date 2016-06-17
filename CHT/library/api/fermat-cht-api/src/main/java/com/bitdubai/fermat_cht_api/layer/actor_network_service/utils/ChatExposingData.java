package com.bitdubai.fermat_cht_api.layer.actor_network_service.utils;

import com.bitdubai.fermat_api.layer.osa_android.location_system.Location;

import java.util.Arrays;

/**
 * Created by José D. Vilchez A. (josvilchezalmera@gmail.com) on 05/04/16.
 */
public class ChatExposingData {

    private final String publicKey;
    private final String alias    ;
    private final byte[] image    ;
    private final String country  ;
    private final String state    ;
    private final String city     ;
    private final String status;
    private final Location location;

    public ChatExposingData(final String publicKey,
                                    final String alias    ,
                                    final byte[] image,
                                    final String country,
                                    final String state,
                                    final String city,
                            final String status,
                            final Location location) {

        this.publicKey = publicKey;
        this.alias     = alias    ;
        this.image     = image    ;
        this.country   = country;
        this.state     = state;
        this.city      = city;
        this.status = status;
        this.location = location;
    }

    /**
     * @return a string representing the public key.
     */
    public final String getPublicKey() {
        return publicKey;
    }

    /**
     * @return a string representing the alias of the crypto broker.
     */
    public final String getAlias() {
        return alias;
    }

    /**
     * @return an array of bytes with the image exposed by the Crypto Broker.
     */
    public final byte[] getImage() {
        return image;
    }

    public String getCountry() {
        return this.country;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getStatus() {
        return status;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "ChatExposingData{" +
                "publicKey='" + publicKey + '\'' +
                ", alias='" + alias + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}

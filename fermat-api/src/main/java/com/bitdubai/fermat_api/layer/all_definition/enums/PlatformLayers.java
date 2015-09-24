/*
 * @#java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_api.layer.all_definition.enums;

import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;

/**
 * The Class <code>com.bitdubai.fermat_api.layer.all_definition.enums.PlatformLayers</code> define
 * the type of layers
 * <p/>
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 24/07/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public enum PlatformLayers {

    /**
     * Definitions types
     */
    BITDUBAI_COMMUNICATION_LAYER("BCOMML", Developers.BITDUBAI),
    BITDUBAI_DEFINITION_LAYER("BDEFL", Developers.BITDUBAI),
    BITDUBAI_PLATFORM_SERVICE_LAYER("BPSL", Developers.BITDUBAI),
    BITDUBAI_OS_LAYER("BOSL", Developers.BITDUBAI),
    BITDUBAI_HARDWARE_LAYER("BHARDWL", Developers.BITDUBAI),
    BITDUBAI_USER_LAYER("BUSERL", Developers.BITDUBAI),
    BITDUBAI_LICENSE_LAYER("BLICL", Developers.BITDUBAI),
    BITDUBAI_WORLD_LAYER("BWORLDL", Developers.BITDUBAI),
    BITDUBAI_CRYPTO_LAYER("BCRYPTL", Developers.BITDUBAI),
    BITDUBAI_CRYPTO_NETWORK_LAYER("BCRYPTNL", Developers.BITDUBAI),
    BITDUBAI_CRYPTO_VAULT_LAYER("BCRYPTVL", Developers.BITDUBAI),
    BITDUBAI_CRYPTO_ROUTER_LAYER("BCRYPTRL", Developers.BITDUBAI),
    BITDUBAI_NETWORK_SERVICE_LAYER("BNETSL", Developers.BITDUBAI),
    BITDUBAI_TRANSACTION_LAYER("BTRANSL", Developers.BITDUBAI),
    BITDUBAI_MIDDLEWARE_LAYER("BMIDDL", Developers.BITDUBAI),
    BITDUBAI_MODULE_LAYER("BMODL", Developers.BITDUBAI),
    BITDUBAI_AGENT_LAYER("BAGL", Developers.BITDUBAI),
    BITDUBAI_BASIC_WALLET_LAYER("BWALL", Developers.BITDUBAI),
    BITDUBAI_WALLET_MODULE_LAYER("BWALML", Developers.BITDUBAI),
    BITDUBAI_ACTOR_LAYER("BACTL", Developers.BITDUBAI),
    BITDUBAI_PIP_ACTOR_LAYER("BPACTL", Developers.BITDUBAI),
    BITDUBAI_IDENTITY_LAYER("BIDL", Developers.BITDUBAI),
    BITDUBAI_PIP_IDENTITY_LAYER("BPIDL", Developers.BITDUBAI),
    BITDUBAI_PIP_MODULE_LAYER("BPIPML", Developers.BITDUBAI),
    BITDUBAI_REQUEST_LAYER("BRL", Developers.BITDUBAI),
    BITDUBAI_PIP_NETWORK_SERVICE_LAYER("BPIPNSL", Developers.BITDUBAI),
    BITDUBAI_DIGITAL_ASSET_TRANSACTION("BDAT", Developers.BITDUBAI),
    BITDUBAI_DAP_ACTOR_LAYER("BDAPAL",Developers.BITDUBAI),
    BITDUBAI_DAP_IDENTITY_LAYER("BDAPIL", Developers.BITDUBAI),
    BITDUBAI_DAP_MODULE_LAYER("BDAPML", Developers.BITDUBAI),
    BITDUBAI_DIGITAL_ASSET_FACTORY("BDAF", Developers.BITDUBAI),
    BITDUBAI_ENGINE_LAYER("BEL",Developers.BITDUBAI),

    // CCP Layers
    BITDUBAI_CCP_IDENTITY_LAYER("BCCPIDL", Developers.BITDUBAI),
    BITDUBAI_CCP_MIDDLEWARE_LAYER("BCCPMIL", Developers.BITDUBAI),
    BITDUBAI_CCP_NETWORK_SERVICE_LAYER("BCCPNSL", Developers.BITDUBAI)

    ;

    /**
     * Represent the key
     */
    private String code;

    /**
     * Represent the developer
     */
    private final Developers developer;

    /**
     * Constructor
     *
     * @param code
     * @param developer
     */
    PlatformLayers(String code, Developers developer) {
        this.code = code;
        this.developer = developer;
    }

    /**
     * Get the key representation
     *
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Get the developer
     *
     * @return Developers
     */
    public Developers getDeveloper() {
        return this.developer;
    }

    /**
     * Get the platform layer representation from key
     *
     * @param code
     * @return PlatformLayers
     * @throws InvalidParameterException
     */
    public static PlatformLayers getByCode(String code) throws InvalidParameterException {
        switch (code) {

            case "BCOMML":
                return BITDUBAI_COMMUNICATION_LAYER;
            case "BDEFL":
                return BITDUBAI_DEFINITION_LAYER;
            case "BPSL":
                return BITDUBAI_PLATFORM_SERVICE_LAYER;
            case "BOSL":
                return BITDUBAI_OS_LAYER;
            case "BHARDWL":
                return BITDUBAI_HARDWARE_LAYER;
            case "BUSERL":
                return BITDUBAI_USER_LAYER;
            case "BLICL":
                return BITDUBAI_LICENSE_LAYER;
            case "BWORLDL":
                return BITDUBAI_WORLD_LAYER;
            case "BCRYPTL":
                return BITDUBAI_CRYPTO_LAYER;
            case "BCRYPTNL":
                return BITDUBAI_CRYPTO_NETWORK_LAYER;
            case "BCRYPTVL":
                return BITDUBAI_CRYPTO_VAULT_LAYER;
            case "BCRYPTRL":
                return BITDUBAI_CRYPTO_ROUTER_LAYER;
            case "BNETSL":
                return BITDUBAI_NETWORK_SERVICE_LAYER;
            case "BTRANSL":
                return BITDUBAI_TRANSACTION_LAYER;
            case "BMIDDL":
                return BITDUBAI_MIDDLEWARE_LAYER;
            case "BMODL":
                return BITDUBAI_MODULE_LAYER;
            case "BAGL":
                return BITDUBAI_AGENT_LAYER;
            case "BWALL":
                return BITDUBAI_BASIC_WALLET_LAYER;
            case "BWALML":
                return BITDUBAI_WALLET_MODULE_LAYER;
            case "BACTL":
                return BITDUBAI_ACTOR_LAYER;
            case "BPACTL":
                return BITDUBAI_PIP_ACTOR_LAYER;
            case "BPIDL":
                return BITDUBAI_PIP_IDENTITY_LAYER;
            case "BIDL":
                return BITDUBAI_IDENTITY_LAYER;
            case "BPIPML":
                return BITDUBAI_PIP_MODULE_LAYER;
            case "BRL":
                return BITDUBAI_REQUEST_LAYER;
            case "BPIPNSL":
                return BITDUBAI_PIP_NETWORK_SERVICE_LAYER;
            case "BDAT":
                return BITDUBAI_DIGITAL_ASSET_TRANSACTION;
            case "BDAPAL":
                return BITDUBAI_DAP_ACTOR_LAYER;
            case "BDAPIL":
                return BITDUBAI_DAP_IDENTITY_LAYER;
            case "BDAF":
                return BITDUBAI_DIGITAL_ASSET_FACTORY;
            case "BDAPML":
                return BITDUBAI_DAP_MODULE_LAYER;
            case "BEL":
                return BITDUBAI_ENGINE_LAYER;

            // CCP Layers
            case "BCCPIDL":
                return BITDUBAI_CCP_IDENTITY_LAYER;
            case "BCCPNSL":
                return BITDUBAI_CCP_NETWORK_SERVICE_LAYER;
            case "BCCPMIL":
                return BITDUBAI_CCP_MIDDLEWARE_LAYER;
            default:
                throw new InvalidParameterException(InvalidParameterException.DEFAULT_MESSAGE, null, "Code Received: " + code, "This Code Is Not Valid for the PlatformLayers enum");

        }
    }

    /**
     * (non-Javadoc)
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return getCode();
    }

}
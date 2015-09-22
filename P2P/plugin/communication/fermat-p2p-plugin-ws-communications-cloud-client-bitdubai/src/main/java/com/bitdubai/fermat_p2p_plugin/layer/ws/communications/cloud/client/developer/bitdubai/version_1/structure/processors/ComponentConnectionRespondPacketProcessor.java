/*
 * @#ComponentConnectionRespondPacketProcessor.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.client.developer.bitdubai.version_1.structure.processors;

import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.AsymmectricCryptography;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.contents.FermatPacket;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.AttNamesConstants;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.FermatPacketType;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.NetworkServiceType;
import com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.client.developer.bitdubai.version_1.structure.vpn.WsCommunicationVPNClientManagerAgent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The Class <code>com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.client.developer.bitdubai.version_1.structure.processors.ComponentConnectionRespondPacketProcessor</code> implement
 * the logic to process the packet when a packet type <code>com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.FermatPacketType.COMPONENT_CONNECTION_RESPOND</code> is receive by the server.
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 13/09/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ComponentConnectionRespondPacketProcessor extends FermatPacketProcessor {

    /**
     * (no-javadoc)
     * @see FermatPacketProcessor#processingPackage(FermatPacket)
     */
    @Override
    public void processingPackage(FermatPacket receiveFermatPacket) {


        System.out.println(" --------------------------------------------------------------------- ");
        System.out.println("RequestListComponentRegisterPacketProcessor - Starting processingPackage");

        /*
         * Get the filters from the message content and decrypt
         */
        String messageContentJsonStringRepresentation = AsymmectricCryptography.decryptMessagePrivateKey(receiveFermatPacket.getMessageContent(), getWsCommunicationsCloudClientChannel().getServerIdentity());


        System.out.println("RequestListComponentRegisterPacketProcessor - messageContentJsonStringRepresentation = "+messageContentJsonStringRepresentation);

        /*
         * Construct the json object
         */
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject respond = parser.parse(messageContentJsonStringRepresentation).getAsJsonObject();

        try {

            //Get all values
            URI vpnServerUri = new URI(respond.get(AttNamesConstants.JSON_ATT_NAME_VPN_URI).getAsString());
            String vpnServerIdentity = respond.get(AttNamesConstants.JSON_ATT_NAME_VPN_SERVER_IDENTITY).getAsString();
            String remotePlatformComponentProfileIdentity = respond.get(AttNamesConstants.JSON_ATT_NAME_REMOTE_PARTICIPANT_IDENTITY_VPN).getAsString();
            NetworkServiceType networkServiceType = gson.fromJson(respond.get(AttNamesConstants.JSON_ATT_NAME_NETWORK_SERVICE_TYPE), NetworkServiceType.class) ;

            /*
             * Get the  wsCommunicationVPNClientManagerAgent
             */
            WsCommunicationVPNClientManagerAgent wsCommunicationVPNClientManagerAgent = getWsCommunicationsCloudClientChannel().getWsCommunicationsCloudClientConnection().getWsCommunicationVPNClientManagerAgent();

            /**
             * Create a new VPN client
             */
            wsCommunicationVPNClientManagerAgent.createNewWsCommunicationVPNServer(vpnServerUri, networkServiceType, vpnServerIdentity, remotePlatformComponentProfileIdentity);

            /*
             * Is not running
             */
            if (!wsCommunicationVPNClientManagerAgent.isRunning()){
                wsCommunicationVPNClientManagerAgent.start();
            }

        } catch (URISyntaxException e) {
           throw new RuntimeException(e);
        }

    }

    /**
     * (no-javadoc)
     * @see FermatPacketProcessor#getFermatPacketType()
     */
    @Override
    public FermatPacketType getFermatPacketType() {
        return FermatPacketType.COMPONENT_CONNECTION_RESPOND;
    }
}
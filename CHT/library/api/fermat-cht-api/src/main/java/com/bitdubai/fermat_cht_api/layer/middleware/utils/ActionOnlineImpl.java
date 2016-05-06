package com.bitdubai.fermat_cht_api.layer.middleware.utils;

import com.bitdubai.fermat_cht_api.layer.middleware.enums.ActionState;
import com.bitdubai.fermat_cht_api.layer.middleware.interfaces.ActionOnline;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by José D. Vilchez A. (josvilchezalmera@gmail.com) on 05/05/16.
 */
public class ActionOnlineImpl implements ActionOnline {
    private UUID actionId;
    private String publicKey;
    private ActionState actionState;
    private boolean value;
    private Timestamp lastConnection;

    @Override
    public UUID getId() {
        return actionId;
    }

    @Override
    public void setId(UUID actionId) {
        this.actionId=actionId;
    }

    @Override
    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public void setPublicKey(String publicKey) {
        this.publicKey=publicKey;
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }

    @Override
    public void setActionState(ActionState actionState) {
        this.actionState=actionState;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(boolean value) {
        this.value=value;
    }

    @Override
    public Timestamp getLastConnection() {
        return lastConnection;
    }

    @Override
    public void setLastConnection(Timestamp lastConnection) {
        this.lastConnection = lastConnection;
    }
}

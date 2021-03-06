package com.bitdubai.fermat_cbp_api.layer.desktop_module.sub_app_manager.interfaces;

import com.bitdubai.fermat_api.layer.all_definition.util.Version;

import java.util.UUID;

/**
 * Created by natalia on 16/09/15.
 */
public interface CryptoSubAppInstalledSkin {
    /**
     * This method gives us the name (alias) of an skin
     *
     * @return the skin name
     */
    String getAlias();

    /**
     * This method gives us the identifier of an skin
     *
     * @return the skin identifier
     */
    UUID getId();

    /**
     * This method gives us the name of the preview image of the skin
     *
     * @return the name of the image used to reach it in the wallet resources plug-in
     */
    String getPreview();

    /**
     * This method gives us the version of the skin
     *
     * @return the skin version
     */
    Version getVersion();
}


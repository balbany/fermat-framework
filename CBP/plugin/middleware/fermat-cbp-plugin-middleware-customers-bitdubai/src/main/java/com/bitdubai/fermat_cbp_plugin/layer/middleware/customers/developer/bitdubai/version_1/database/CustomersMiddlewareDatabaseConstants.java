package com.bitdubai.fermat_cbp_plugin.layer.middleware.customers.developer.bitdubai.version_1.database;

/**
 * The Class <code>com.bitdubai.fermat_cbp_plugin.layer.middleware.customers.developer.bitdubai.version_1.database.CustomersMiddlewareDatabaseConstants</code>
 * keeps constants the column names of the database.<p/>
 * <p/>
 *
 * Created by Angel Veloz - (vlzangel91@gmail.com) on 28/09/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class CustomersMiddlewareDatabaseConstants {

    /**
     * Wallet Identity database table definition.
     */
    static final String WALLET_IDENTITY_TABLE_NAME = "wallet_identity";

    static final String WALLET_IDENTITY_RELATIONSHIP_ID_COLUMN_NAME = "relationship_id";
    static final String WALLET_IDENTITY_CRYPTO_BROKER_PUBLIC_KEY_COLUMN_NAME = "crypto_broker_public_key";
    static final String WALLET_IDENTITY_CRYPTO_CUSTOMER_PUBLIC_KEY_COLUMN_NAME = "crypto_customer_public_key";
    static final String WALLET_IDENTITY_CUSTOMER_TYPE_COLUMN_NAME = "customer_type";

    static final String WALLET_IDENTITY_FIRST_KEY_COLUMN = "relationship_id";

}
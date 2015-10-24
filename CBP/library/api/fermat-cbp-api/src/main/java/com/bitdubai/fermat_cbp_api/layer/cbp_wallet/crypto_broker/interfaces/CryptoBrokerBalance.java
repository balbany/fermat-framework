package com.bitdubai.fermat_cbp_api.layer.cbp_wallet.crypto_broker.interfaces;

import com.bitdubai.fermat_cbp_api.all_definition.enums.BalanceType;
import com.bitdubai.fermat_cbp_api.layer.cbp_wallet.crypto_broker.exceptions.CantCalculateBalanceException;
import com.bitdubai.fermat_cbp_api.layer.cbp_wallet.crypto_broker.exceptions.CantRegisterCreditException;
import com.bitdubai.fermat_cbp_api.layer.cbp_wallet.crypto_broker.exceptions.CantRegisterDebitException;

/**
 * Created by Yordin Alayn on 30.09.15.
 */
public interface CryptoBrokerBalance {

    long getBalance()  throws CantCalculateBalanceException;

    void debit(CryptoBrokerTransactionRecord BankMoneyTransactionRecord, BalanceType balanceType) throws CantRegisterDebitException;

    void credit(CryptoBrokerTransactionRecord BankMoneyTransactionRecord, BalanceType balanceType)  throws CantRegisterCreditException;
}
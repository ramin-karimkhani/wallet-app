package com.wallet.walletapp.service.wallet;

import com.wallet.walletapp.model.dto.WalletDto;
import com.wallet.walletapp.model.dto.WalletRequestDto;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {

    List<String> getCurrencyList();

    List<String> getPSPList();

    WalletDto registerWallet(WalletRequestDto walletRequest);

    WalletDto findWalletByAddress(Long walletAddress);

    WalletDto deposit(Long walletAddress, BigDecimal amount, String pspCode);

    WalletDto withdraw(Long walletAddress, BigDecimal amount, String pspCode);

    String transfer(Long fromWalletAddress, Long toWalletAddress, BigDecimal amount);

}

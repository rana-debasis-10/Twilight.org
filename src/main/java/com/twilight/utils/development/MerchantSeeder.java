package com.twilight.utils.development;

import com.twilight.repositories.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MerchantSeeder {

    public final MerchantRepository merchantRepository;
    public void seed(){};

}

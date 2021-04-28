package com.transaction.core.match.core.services;

import com.transaction.core.match.entites.Entrust;
import com.transaction.core.match.mapper.EntrustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: TranscationService
 * Description:
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/26 15:12
 */
@Service
public class TranscationService {

    @Autowired
    private EntrustMapper entrustMapper;


    public void addEntrust(Entrust entrust) {
        this.entrustMapper.insertSelective(entrust);
    }

}

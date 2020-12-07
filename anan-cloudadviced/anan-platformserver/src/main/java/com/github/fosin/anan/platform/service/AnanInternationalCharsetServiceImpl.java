package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.entity.AnanInternationalCharsetEntity;
import com.github.fosin.anan.platform.repository.AnanInternationalCharsetRepository;
import com.github.fosin.anan.platform.service.inter.AnanInternationalCharsetService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 国际化明显(anan_international_charset)表服务实现类
 *
 * @author fosin
 * @date 2020-12-03 21:18:05
 */
@Service
@Lazy
public class AnanInternationalCharsetServiceImpl implements AnanInternationalCharsetService {

    private final AnanInternationalCharsetRepository ananInternationalCharsetRepository;

    public AnanInternationalCharsetServiceImpl(AnanInternationalCharsetRepository ananInternationalCharsetRepository) {
        this.ananInternationalCharsetRepository = ananInternationalCharsetRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanInternationalCharsetRepository getRepository() {
        return ananInternationalCharsetRepository;
    }

    @Override
    public List<AnanInternationalCharsetEntity> findAllByInternationalId(Integer internationalId) {
        return this.getRepository().findAllByInternationalId(internationalId);
    }

    @Override
    public Map<String, Object> findCharsetByInternationalId(Integer internationalId) {
        Map<String, Object> rc = new HashMap<>();
        Map<String, Object> prevMap = new HashMap<>();
        List<AnanInternationalCharsetEntity> charsetEntities = this.getRepository().findAllByInternationalId(internationalId);
        for (AnanInternationalCharsetEntity charsetEntity : charsetEntities) {
            String key = charsetEntity.getKey();
            String value = charsetEntity.getValue();
            String[] skeys = key.split("\\.");
            Map<String, Object> nextMap = null;
            Object nextObject = null;
            for (int i = 0; i < skeys.length; i++) {
                String skey = skeys[i];
                if (i == 0) {
                    if (prevMap.containsKey(skey)) {
                        nextObject = prevMap.get(skey);
//                        if (skeys.length == 1) {
//                            nextObject = prevMap.get(skey);
//                        } else {
//                            nextMap = prevMap.get(skey);
//                        }
                    } else {
                        nextMap = new HashMap<>();
                        prevMap.put(skey, nextMap);
                    }
                } else {
                    Map<String, Object> findMap = findIfExsits(prevMap);
                    if (findMap == null) {
                        findMap = new HashMap<>();
                        findMap.put(skey, new HashMap<>());
                    }
                    prevMap.put(skey, findMap);
                }
            }
            for (int i = skeys.length - 1; i >= 0; i--) {
                String skey = skeys[i];
                if (i == skeys.length - 1) {
                    nextMap = new HashMap<>();
                    nextMap.put(skey, value);
                } else {
                    Map<String, Object> findMap = findIfExsits(rc);
                    if (findMap == null) {
                        findMap = new HashMap<>();
                        findMap.put(skey, nextMap);
                        prevMap = findMap;
                    }
                    prevMap = findMap;
                }

            }
        }
        return rc;
    }

    private Map<String, Object> findIfExsits(Map<String, Object> prevMap) {
        return new HashMap<>();
    }
}

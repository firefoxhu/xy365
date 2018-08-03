package com.xy365.web.service.impl;
import com.google.common.collect.Maps;
import com.xy365.core.model.Mchnt;
import com.xy365.core.model.Shop;
import com.xy365.core.model.ShopInfo;
import com.xy365.core.model.User;
import com.xy365.core.properties.XyProperties;
import com.xy365.core.repository.MchntRepository;
import com.xy365.core.repository.ShopInfoRepository;
import com.xy365.core.repository.ShopRepository;
import com.xy365.core.util.FileUtil;
import com.xy365.web.domain.form.ShopInfoForm;
import com.xy365.web.domain.support.ResultMap;
import com.xy365.web.exception.AuthException;
import com.xy365.web.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @Autowired
    private MchntRepository mchntRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XyProperties xyProperties;


    @Transactional
    @Override
    public Map<String, Object> viewShop(long shoInfoId) {

        shopInfoRepository.updateViewsCount(shoInfoId);

        Map<String,Object> map = Maps.newHashMap();

        return map;
    }
    @Transactional
    @Override
    public Map<String, Object> updateShopInfo(HttpServletRequest request,ShopInfoForm shopInfoForm) {

        log.info("正在修改门店信息(哪个不为空修改哪里)：{}",shopInfoForm);

        User user = Optional.ofNullable(redisTemplate.opsForValue().get(request.getHeader("xy365_3rd_session"))).map(u->(User)u).orElseThrow(()->new AuthException("纳秒之间的用户登录过期！万年一见。"));

        Mchnt mchnt = mchntRepository.findMchntByUserId(user.getId());

        Shop shop = shopRepository.findShopByMchntId(mchnt.getId());

        ShopInfo shopInfo = shopInfoRepository.findShopInfoByShopId(shop.getId());

        List<String> deleteFileNames = null;

        List<String> saveFileNames = null;

        if(shopInfoForm.getShopPhone() != null){

            shopInfo.setPhone(shopInfoForm.getShopPhone());

        }else if(shopInfoForm.getSkillDescription() != null){

            shopInfo.setSkillDescription(shopInfoForm.getSkillDescription());

        }else if(shopInfoForm.getBannerPicture() != null){

            deleteFileNames = Arrays.asList(shopInfo.getBannerPicture().split(","));
            saveFileNames = Arrays.asList(shopInfoForm.getBannerPicture().split(","));
            shopInfo.setBannerPicture(shopInfoForm.getBannerPicture());

        }else if(shopInfoForm.getDescription() != null) {

            shopInfo.setDescription(shopInfoForm.getDescription());

        }else if(shopInfoForm.getGalleryPicture() != null){

            if(shopInfo.getGalleryPicture() == null) {
                deleteFileNames = Arrays.asList();
            }else {
                deleteFileNames = Arrays.asList(shopInfo.getGalleryPicture().split(","));
            }
            saveFileNames = Arrays.asList(shopInfoForm.getGalleryPicture().split(","));
            shopInfo.setGalleryPicture(shopInfoForm.getGalleryPicture());
        }


        shopInfoRepository.save(shopInfo);

        // 图片处理
        if(deleteFileNames != null){
            // 删除云端图片
            deleteFileNames.parallelStream().forEach(i->
                    FileUtil.delete(i)
            );
        }

        if(saveFileNames != null){
            // 生成本地 缩略图 和 压缩图
            saveFileNames.parallelStream().forEach(i->
                    FileUtil.localUpload(xyProperties.getFileConfig().getTempDir(),i)
            );

            // 将缩略图 和 压缩图上传七牛云
            saveFileNames.parallelStream().forEach(i->
                    FileUtil.qiNiuUpload(xyProperties.getFileConfig().getTempDir(),i)
            );
        }

        return ResultMap.getInstance().put("msg","【系统提示】修改成功！").toMap();
    }

}

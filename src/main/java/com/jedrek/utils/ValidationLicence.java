package com.jedrek.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangjie22438 on 2017/8/30.
 */
@Component
public class ValidationLicence {

    @Autowired
    private LicenceFile licenceFile;
    //固定值
    private static final String fixedWord = "HSXTLICENCE1234567890ABC";

    /**
     * 通过比较从VersionicenceInfo得到相关信息和授权文件里的信息
     * MD5通过DigestUtils类实现
     * @param moduleCode 模块编码
     * @param licenceCode  授权码
     * @return true表示比配成功，false表示匹配失败
     */
    public boolean checkRightByFunctionCode(String moduleCode, String licenceCode) {
        try {

            String password = licenceCode;
            String md5Password = DigestUtils.md5Hex(password);
           String licenceCodeFromXml = licenceFile.getLicenceCode(moduleCode);
            String md5LicenceCode = DigestUtils.md5Hex(licenceCodeFromXml);
            if(md5Password.equals(md5LicenceCode)) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }


}

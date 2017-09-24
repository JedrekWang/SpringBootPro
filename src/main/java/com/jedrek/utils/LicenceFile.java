package com.jedrek.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * 授权文件类
 * Created by wangjie22438 on 2017/8/31.
 */
@Component
public class LicenceFile {
    private File licence;
    private static final String licenceFilePath = "C:\\Users\\wangjie22438\\Desktop\\test.xml";
    private SAXReader saxReader;
    private Document document;

    public LicenceFile() {
        licence = new File(licenceFilePath);
    }
    public LicenceFile(String filePath) {
        licence = new File(filePath);
    }

    /**
     * 通过模块编码获取授权码
     * @param moduleCode
     * @return
     */
    public String getLicenceCode(String moduleCode) {
        try {
            saxReader = new SAXReader();
            document = saxReader.read(licence);
            Element root = document.getRootElement();
            final String licenceCode = LicenceFile.findLicenceCode(root, moduleCode);
            return licenceCode;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过递归xml节点找到对应value的值，即授权码的值
     * ex：输入module1，输出password1
     * @param node  开始递归的节点
     * @param moduleCode 模块编码
     * @return 授权码
     */
    public  static String findLicenceCode(Element node, String moduleCode) {
        if("code".equals(node.getName()) && node.getText().equals(moduleCode)) {
            Element parent = node.getParent();
            List<Element> elements = parent.elements();
            for(Element element : elements) {
                if(element.getName().equals("value")) {
                    String ans = element.getText();
                    return ans;
                }
            }
        }
        List<Element> elements = node.elements();
        for(Element element : elements) {
            String code = findLicenceCode(element, moduleCode);
            if(code != null && !code.equals("")) {
                return code;
            }
        }
        return "";
    }


}

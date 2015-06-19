package com.gooker.dfg.utils;

import com.gooker.dfg.parser.BaseParser;

import java.util.HashMap;

public class FileUploadVO extends RequestVO {
    public HashMap<String, String> fileFieldMap;

    public FileUploadVO() {
        super();
    }

    public FileUploadVO(String requestUrl, HashMap<String, String> requestDataMap, HashMap<String, String> fileFieldMap, BaseParser<?> jsonParser) {
        super();
        this.requestUrl = requestUrl;
        this.requestDataMap = requestDataMap;
        this.fileFieldMap = fileFieldMap;
        this.jsonParser = jsonParser;
    }

}

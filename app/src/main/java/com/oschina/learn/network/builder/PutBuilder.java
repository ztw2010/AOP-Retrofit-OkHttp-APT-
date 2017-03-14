package com.oschina.learn.network.builder;


import com.oschina.learn.network.request.PutStringRequest;
import com.oschina.learn.network.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

public class PutBuilder extends OkHttpRequestBuilder
{
    private String content;
    
    private MediaType mediaType;
    
    public PutBuilder content(String content)
    {
        this.content = content;
        return this;
    }
    
    public PutBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }
    
    @Override
    public PutBuilder url(String url)
    {
        this.url = url;
        return this;
    }
    
    @Override
    public PutBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }
    
    @Override
    public PutBuilder params(Map<String, Object> params)
    {
        this.params = params;
        return this;
    }
    
    @Override
    public PutBuilder addParams(String key, Object val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }
    
    @Override
    public PutBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }
    
    @Override
    public PutBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
    
    @Override
    public RequestCall build()
    {
        return new PutStringRequest(url, tag, params, headers, content, mediaType).build();
    }
    
}

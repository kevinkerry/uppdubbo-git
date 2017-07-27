package com.csii.upp.dubbo;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.csii.pe.dubbo.adapter.ServiceFactoryBean;
import com.csii.pe.dubbo.adapter.spring.DubboAdapterBeanDefinitionParser;
/**
 * 
 * @author xujin
 *
 */
public class DubboAdapterNamespaceHandlerSupport extends NamespaceHandlerSupport
{
  public void init()
  {
    registerBeanDefinitionParser("impl", new DubboAdapterBeanDefinitionParser(ServiceFactoryBean.class));
    registerBeanDefinitionParser("bridge", new DubboAdapterBeanDefinitionParser(SimpleServiceFactoryBean.class));
    registerBeanDefinitionParser("transport", new DubboAdapterBeanDefinitionParser(DubboTransport.class));
  }
}
/**
 * ======================
 *
 * @author : wentao.ma
 * @date : 2018/1/22
 * ======================
 * Description:
 * <p>
 * ======================
 * Major changes:
 */

package com.mantou.tinymvc.aop.annotation;

public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}

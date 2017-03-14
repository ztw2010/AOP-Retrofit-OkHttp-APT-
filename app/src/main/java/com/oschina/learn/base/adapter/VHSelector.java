package com.oschina.learn.base.adapter;

/**
 * Created by ztw on 16/12/30.
 */

public interface VHSelector<M> {
    Class getTypeClass(M m);
}

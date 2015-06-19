/**
 *
 */
package com.gooker.dfg.services;

/**
 * May 21, 2014 5:12:28 PM
 */
public abstract interface DataCallback<T> {
    public abstract void processData(T paramObject, boolean paramBoolean);
}
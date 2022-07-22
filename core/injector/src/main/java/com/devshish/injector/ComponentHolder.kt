package com.devshish.injector

/**
 * Generic Interface for Component Holder pattern
 */
interface ComponentHolder<API : BaseFeatureAPI, DEP : BaseFeatureDependencies> {

    /**
     * Return component API for feature
     */
    fun get(): API
}

package com.devshish.injector

/**
 * Marker interface for Feature API
 *
 * It is an interface that is provided by a module externally
 * and contains particular interfaces to be used by other modules.
 *
 * The [BaseFeatureAPI] implementations does not contain methods that do any work.
 *
 * It contains only getters (or “val” properties in terms of Kotlin) for other interfaces.
 */
interface BaseFeatureAPI

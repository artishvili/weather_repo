package com.devshish.injector

/**
 * Interface for Feature dependencies.
 *
 * Is provided to a module externally and contains particular interfaces to be used by this module.
 *
 * The [BaseFeatureDependencies] implementations does not contain methods that do any work.
 *
 * It contains only getters (or “val” properties in terms of Kotlin) for other interfaces.
 */
interface BaseFeatureDependencies
